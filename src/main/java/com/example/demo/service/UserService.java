package com.example.demo.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	int default_vip_left_day = 10;

	public List<User> selectUserByUsername(String username) {
		return userDao.selectUserByUsername(username);
	}

	public List<UserInfo> selectNicknameByOpenid(String openId) {
		return userDao.selectNicknameByOpenid(openId);
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
		String now = getCurrentTime();
		int ifExist = userDao.selectIfExit(openId);
		if (ifExist > 0) {
			userDao.updateUserOperations(collections, now, openId);
		} else {
			userDao.insertUserOperations(collections, "[]", default_vip_left_day, now, now, openId);
		}
		return "";
	}

	public String updateUserViewHistory(String openId, String histories) {
		String now = getCurrentTime();
		// 更新user_operations表中的historyList字段
		int ifExist = userDao.selectIfExistUserOperations(openId);
		if (ifExist > 0) {
			userDao.updateUserHistories(histories, now, openId);
		} else {
			userDao.insertUserOperations("[]", histories, default_vip_left_day, now, now, openId);
		}
		return "";
	}

	public String updateArticleViewtimes(String artId) {
		String now = getCurrentTime();
		int ifExistInArtOper = userDao.selectIfExistArtOperations(artId);
		if (ifExistInArtOper > 0) {
			// 更新art_operations表 中的viewTimes
			userDao.updateArticleOperations(artId, now);
		} else {
			userDao.insertArticleOperations(artId, 0, 1, now, now);
		}
		return "";
	}

	public String register(UserInfo user) {
		// 先查userinfo表里是否有openId对应的记录，如果有，更新；如果没有，插入。且，没有后面的查询部分。
		JSONArray arr = new JSONArray();
		JSONObject json = new JSONObject();
		int ifExit = userDao.selectIfExit(user.getOpenId());
		if (ifExit > 0) {
			userDao.updateUser(user);
			// 查询user_operations表获得该用户的收藏、历史等情况
			HashMap<String, String> map = userDao.queryOperations(user);
			json.put("collections", map.get("collections"));
			json.put("histories", map.get("histories"));
		} else {// 插入一条用户数据
			user.setCreate_time(new Date());
			user.setUpdate_time(new Date());
			userDao.insertUserInfo(user);
			json.put("collections", "[]");
			json.put("histories", "[]");
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
		HashMap<String, Object> map = userDao.selectUserVipday(openId);
		if (map != null) {
			Date createTime = (Date) map.get("create_time");
			int vipDay = (int) map.get("vip_left_day");
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
			userDao.insertUserOperations("[]", "[]", default_vip_left_day, now, now, openId);
			return default_vip_left_day + "";
		}
	}

	public String updateVipperiod(String openId) {
		String leftDay = getVipperiod(openId);
		if (leftDay != null && "0".equals(leftDay)) {// 无VIP权限，更新createTime为当前时间，vip权限=10
			userDao.updateUserCreatetimeAndVip(default_vip_left_day, getCurrentTime(), openId);
		} else if (leftDay != null && !"0".equals(leftDay)) {// 有vip权限，累加10天
			userDao.updateUserVipday(openId);
		}
		int leftDayNow = Integer.parseInt(leftDay) + default_vip_left_day;
		System.out.println("更新一次vip 时限！" + leftDayNow);
		return leftDayNow + "";
	}
}