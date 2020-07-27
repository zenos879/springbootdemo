package com.example.demo.service;

import com.example.demo.dao.HiddenCategoryInfoDao;
import com.example.demo.dao.HiddenRequirementsDao;
import com.example.demo.pojo.CategoryInfo;
import com.example.demo.pojo.HiddenCategoryInfo;
import com.example.demo.pojo.HiddenRequirements;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequirementsService {


    @Autowired
    HiddenCategoryInfoDao cateDao;
    @Autowired
    HiddenRequirementsDao reqDao;

    //通过catid获得安全要求
    public String getRequirementsByCatId(String catId) {
        JSONArray arr = new JSONArray();
        if (catId != null && !catId.equals("")) {
            String[] catIdList = catId.split(",");
            List<HiddenRequirements> list = reqDao.getRequirementByCatid(catIdList);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    HiddenRequirements re = list.get(i);
                    JSONObject obj = JSONObject.fromObject(re);
                    obj.put("collect", 1111);
                    obj.put("agree", 1111);
                    arr.add(obj);
                }
            }
        }
        return arr.toString();
    }


    //通过keywords和catid得到安全要求
    public String getRequirementsByKeywords(String keywords, String catId, int start, int count) {
        JSONArray arr = new JSONArray();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("keywords", keywords);
        if (catId != null && !"".equals(catId)) {
            String[] catIdList = catId.split(",");
            System.out.println(catIdList);
            paraMap.put("catIds", catIdList);
        } else {
            paraMap.put("catIds", null);
        }
        paraMap.put("start", start);
        paraMap.put("count", count);
        List<HiddenRequirements> list = reqDao.getRequirementsByKeywords(paraMap);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HiddenRequirements re = list.get(i);
                JSONObject obj = JSONObject.fromObject(re);
                arr.add(obj);
            }
        }
        return arr.toString();
    }


    public int getRequirementsNoByKeywords(String keywords, String catId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (keywords != null && !"".equals(keywords)) {
            paraMap.put("keywords", keywords);
        } else {
            return 0;
        }
        if (catId != null && !"".equals(catId)) {
            String[] catIdList = catId.split(",");
            System.out.println(catIdList);
            paraMap.put("catIds", catIdList);
        } else {
            paraMap.put("catIds", null);
        }
        int totalCnt = reqDao.getRequirementsCountByKeywords(paraMap);
        return totalCnt;
    }

    //获得前两级的分类目录，level_no = 1,2
    public String getCategory() {
        List<HiddenCategoryInfo> topCatList = null;
        Map<Integer, List<HiddenCategoryInfo>> lowerCatMap = new HashMap<Integer, List<HiddenCategoryInfo>>();
        JSONArray allCatArr = new JSONArray();
        if (topCatList == null || topCatList.size() == 0) {
            topCatList = cateDao.selectTopCate();
        }
        if (topCatList != null && topCatList.size() > 0) {
            for (int i = 0; i < topCatList.size(); i++) {
                JSONObject upperCat = new JSONObject();
                HiddenCategoryInfo topCat = topCatList.get(i);
                String catName = topCat.getCatName();
                int catId = topCat.getCatId();
                if (lowerCatMap.size() == 0 || lowerCatMap.get(catId) == null) {
                    List<HiddenCategoryInfo> listTmp = cateDao.selectLowerCat(catId);
                    lowerCatMap.put(catId, listTmp);
                }
                JSONArray lowerCatArr = new JSONArray();
                List<HiddenCategoryInfo> list = lowerCatMap.get(catId);
                if (list != null && list.size() > 0) {
                    for (int j = 0; j < list.size(); j++) {
                        HiddenCategoryInfo lowerRet = list.get(j);
                        JSONObject lowerCat = new JSONObject();
                        lowerCat.put("child_id", lowerRet.getCatId());
                        lowerCat.put("name", lowerRet.getCatName());
                        lowerCat.put("subTitle", lowerRet.getCatName());
                        lowerCatArr.add(lowerCat);
                    }
                }
                upperCat.put("cate_id", catId);
                upperCat.put("cate_name", catName);
                if (lowerCatArr != null && lowerCatArr.size() > 0) {
                    upperCat.put("ishaveChild", true);
                } else {
                    upperCat.put("ishaveChild", false);
                }
                upperCat.put("children", lowerCatArr);
                allCatArr.add(upperCat);
            }
        }
        return allCatArr.toString();
    }

    //获得最后一级的分类 目录 ：level_no = 3
    public String getLastCategory(String upperCatId) {
        List<HiddenCategoryInfo> list = cateDao.getLastCatgory(Integer.parseInt(upperCatId));
        JSONArray arrRet = new JSONArray();
        JSONArray arr = new JSONArray();
        String allCatId = "";
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HiddenCategoryInfo cat = list.get(i);
                JSONObject obj = new JSONObject();
                obj.put("sub_cate_id", cat.getCatId());
                obj.put("sub_cate_name", cat.getCatName());
                arr.add(obj);
                allCatId += cat.getCatId() + ",";
            }

            if (allCatId != null && allCatId != "" && allCatId.endsWith(",")) {
                allCatId = allCatId.substring(0, allCatId.length() - 1);
            }
            JSONObject objAll = new JSONObject();
            objAll.put("sub_cate_id", allCatId);
            objAll.put("sub_cate_name", "全部");
            arrRet.add(objAll);
            arrRet.addAll(arr);
        }
        return arrRet.toString();
    }


    public String getRequirementsByIds(String reqid) {
        List<HiddenRequirements> list = reqDao.selectByPrimaryKey(reqid.split(","));
        JSONArray arr = new JSONArray();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                HiddenRequirements re = list.get(i);
                JSONObject obj = JSONObject.fromObject(re);
                arr.add(obj);
            }
        }
        return arr.toString();
    }
}
