package com.example.demo.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.demo.dao.ArticleOperationsDao;
import com.example.demo.dao.UserInfoDao;
import com.example.demo.dao.UserOperationsDao;
import com.example.demo.pojo.ArticleOperations;
import com.example.demo.pojo.UserInfo;
import com.example.demo.pojo.UserOperations;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UserService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    ArticleOperationsDao articleOperationsDao;

    @Autowired
    UserOperationsDao userOperationsDao;
    int default_vip_left_day = 10;


    public List<UserInfo> selectNicknameByOpenid(String openId) {
        return userInfoDao.selectByPrimaryKey(openId);
    }

    public String getOpenId(String code) {
        String appId = "wx9774987d567fd009";
        String secret = "5b51058483d44f7ea4050e2de7783154";
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret
                + "&js_code=" + code + "&grant_type=authorization_code";
        String ret = "";
        try {
            ret = getUrl(requestUrl);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public String getUrl(String requestUrl) throws ClientProtocolException, IOException {
        String ret = "";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(requestUrl);
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            ret = EntityUtils.toString(resEntity, "utf-8");
        } else {
            System.out.println("请求失败");
        }
        return ret;
    }

    public String saveCollections(String openId, String collections) {
        List<UserInfo> list = userInfoDao.selectByPrimaryKey(openId);
        UserOperations uo = new UserOperations(openId, new Date());
        uo.setCollections(collections);
        if (list != null && list.size() > 0) {
            userOperationsDao.updateByPrimaryKeySelective(uo);
        } else {
            uo.setCreateTime(new Date());
            uo.setVipLeftDay(default_vip_left_day);
            uo.setHistories("[]");
            uo.sethAgree("[]");
            uo.sethCollections("[]");
            userOperationsDao.insert(uo);
        }
        return "";
    }

    public String saveHCollections(String openId, String collections) {
        List<UserInfo> list = userInfoDao.selectByPrimaryKey(openId);
        UserOperations uo = new UserOperations(openId, new Date());
        uo.sethCollections(collections);
        if (list != null && list.size() > 0) {
            userOperationsDao.updateByPrimaryKeySelective(uo);
        } else {
            uo.setCreateTime(new Date());
            uo.setVipLeftDay(default_vip_left_day);
            uo.setHistories("[]");
            uo.sethAgree("[]");
            uo.setCollections("[]");
            userOperationsDao.insert(uo);
        }
        return "";
    }

    public String saveHAgree(String openId, String hagree) {
        List<UserInfo> list = userInfoDao.selectByPrimaryKey(openId);
        UserOperations uo = new UserOperations(openId, new Date());
        uo.sethAgree(hagree);
        if (list != null && list.size() > 0) {
            userOperationsDao.updateByPrimaryKeySelective(uo);
        } else {
            uo.setCreateTime(new Date());
            uo.setVipLeftDay(default_vip_left_day);
            uo.setHistories("[]");
            uo.setCollections("[]");
            uo.sethCollections("[]");
            userOperationsDao.insert(uo);
        }
        return "";
    }

    public String updateUserViewHistory(String openId, String histories) {
        String now = getCurrentTime();
        // 更新user_operations表中的historyList字段
        int ifExist = userOperationsDao.selectIfExistUserOperations(openId);
        UserOperations uo = new UserOperations(openId, new Date());
        uo.setHistories(histories);
        if (ifExist > 0) {
            userOperationsDao.updateByPrimaryKeySelective(uo);
        } else {
            uo.setCreateTime(new Date());
            uo.setVipLeftDay(default_vip_left_day);
            uo.setCollections("[]");
            uo.sethCollections("[]");
            uo.sethAgree("[]");
            userOperationsDao.insert(uo);
        }
        return "";
    }

    public String updateArticleViewtimes(String artId) {
        int ifExistInArtOper = articleOperationsDao.selectIfExistArtOperations(artId);
        ArticleOperations ao = new ArticleOperations();
        ao.setArtId(Integer.parseInt(artId));
        ao.setUpdateTime(new Date());
        if (ifExistInArtOper > 0) {
            // 更新art_operations表 中的viewTimes
            articleOperationsDao.updateByPrimaryKeySelective(ao);
        } else {
            ao.setCreateTime(new Date());
            ao.setCollectTimes(0);
            ao.setViewTimes(1);
            articleOperationsDao.insertSelective(ao);
        }
        return "";
    }

    public String register(UserInfo user) {
        // 先查userinfo表里是否有openId对应的记录，如果有，更新；如果没有，插入。且，没有后面的查询部分。
        JSONArray arr = new JSONArray();
        JSONObject json = new JSONObject();
        List<UserInfo> list = userInfoDao.selectByPrimaryKey(user.getOpenId());
        if (list != null && list.size() > 0) {
            userInfoDao.updateByPrimaryKeySelective(user);
            // 查询user_operations表获得该用户的收藏、历史等情况
            UserOperations uo = userOperationsDao.selectByPrimaryKey(user.getOpenId());
            json.put("collections", uo.getCollections());
            json.put("histories", uo.getHistories());
            json.put("hid_collections", uo.gethCollections());//增加隐患排查的收藏和点赞功能2020-7-20
            json.put("hid_agree", uo.gethAgree());
        } else {// 插入一条用户数据
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userInfoDao.insert(user);
            json.put("collections", "[]");
            json.put("histories", "[]");
            json.put("hid_collections", "[]");
            json.put("hid_agree", "[]");
        }
        arr.add(json);
        return arr.toString();
    }

    public String getCurrentTime() {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(nowDate);
        return now;
    }

    public String getVipperiod(String openId) {
        UserOperations uo = userOperationsDao.selectByPrimaryKey(openId);
        if (uo != null) {
            Date createTime = uo.getCreateTime();
            int vipDay = uo.getVipLeftDay();
            Date nowDate = new Date();
            double difTime = (nowDate.getTime() - createTime.getTime()) / (1000 * 3600 * 24);
            int difDay = new Double(difTime).intValue();
            int leftDay = vipDay - difDay;
            if (leftDay > 0) {
                return leftDay + "";
            } else {
                return "0";
            }
        } else {
            String now = getCurrentTime();
            uo = new UserOperations(openId, "[]", "[]", "[]", "[]", default_vip_left_day, new Date(), new Date());
            userOperationsDao.insert(uo);
            return default_vip_left_day + "";
        }
    }

    public String updateVipperiod(String openId) {
        String leftDay = getVipperiod(openId);
        UserOperations uo = new UserOperations(openId, default_vip_left_day, new Date());
        if (leftDay != null && "0".equals(leftDay)) {// 无VIP权限，更新createTime为当前时间，vip权限=10
            userOperationsDao.updateByPrimaryKeySelective(uo);
        } else if (leftDay != null && !"0".equals(leftDay)) {// 有vip权限，累加10天
            uo.setVipLeftDay(uo.getVipLeftDay() + 10);
            userOperationsDao.updateByPrimaryKeySelective(uo);
        }
        int leftDayNow = Integer.parseInt(leftDay) + default_vip_left_day;
        System.out.println("更新一次vip 时限！" + leftDayNow);
        return leftDayNow + "";
    }
}