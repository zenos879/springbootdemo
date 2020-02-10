package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SearchDao;
import com.example.demo.pojo.CategoryInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SearchService {
	private SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	SearchDao searchDao;

	public String getCategory() {
		JSONArray allCatArr = new JSONArray();
		List<CategoryInfo> topCatList = searchDao.selectTopCate();
		if (topCatList != null && topCatList.size() > 0) {
			for (int i = 0; i < topCatList.size(); i++) {
				JSONObject upperCat = new JSONObject();
				CategoryInfo topCat = topCatList.get(i);
				String catName = topCat.getCatName();
				int catId = topCat.getCatId();
				List<CategoryInfo> lowerCatList = searchDao.selectLowerCat(catId);
				JSONArray lowerCatArr = new JSONArray();
				if (lowerCatList != null && lowerCatList.size() > 0) {
					for (int j = 0; j < lowerCatList.size(); j++) {
						CategoryInfo lowerRet = lowerCatList.get(j);
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

	public String getArticleList(String catId, int start, int count) {
		if (count > 0) {
			List<HashMap<String, String>> list = searchDao.selectArticleList(catId, start, count);
			JSONArray arr = new JSONArray();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject json = new JSONObject();
					HashMap<String, String> map = list.get(i);
					if (map != null) {
						json.put("art_id", map.get("art_id"));
						json.put("art_title", map.get("title"));
						json.put("art_no", map.get("art_no"));
						String filePath = map.get("file_path") == null ? "" : map.get("file_path");
						json.put("art_path", filePath);
						arr.add(json);
					}
				}
			}
			appendArticleViewTimes(arr);
			return arr.toString();
		} else {
			return searchDao.selectArticleCnt(catId) + "";

		}
	}

	private void appendArticleViewTimes(JSONArray arr) {
		if (arr != null && arr.size() > 0) {
			List<String> idList = new ArrayList<String>();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				idList.add(json.get("art_id").toString());
			}
			List<HashMap<String, Object>> list = searchDao.findArticleViewTimes(idList);
			Map<String, String> map = new TreeMap<String, String>();
			if (list != null && list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					HashMap<String, Object> obj = list.get(j);
					if (obj != null) {
						map.put(obj.get("art_id").toString(), obj.get("view_times").toString());
					}
				}
			}
			for (int k = 0; k < arr.size(); k++) {
				JSONObject jsonR = arr.getJSONObject(k);
				String artId = jsonR.getString("art_id");
				if (map.containsKey(artId)) {
					jsonR.put("art_view_times", map.get(artId));
				} else {
					jsonR.put("art_view_times", "0");
				}
				arr.set(k, jsonR);
			}
		}
	}

	public String getAritlcleTitle(String artId) {
		List<String> artList = Arrays.asList(artId.split(","));
		List<HashMap<String, String>> list = searchDao.getArticleTitle(artList);
		JSONArray arr = new JSONArray();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				HashMap<String, String> map = list.get(i);
				if (map != null) {
					json.put("art_id", map.get("art_id"));
					json.put("art_title", map.get("title"));
					json.put("art_no", map.get("art_no"));
					json.put("art_path", map.get("file_path"));
					arr.add(json);
				}
			}
		}
		appendArticleViewTimes(arr);
		return arr.toString();
	}

	public String getAriticleInfo(String artId) {
		List<HashMap<String, Object>> list = searchDao.getArticleInfo(artId);
		JSONObject json = new JSONObject();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> obj = list.get(i);
				if (obj != null) {
					String title = obj.get("title") == null ? "" : obj.get("title").toString();
					String art_no = obj.get("art_no") == null ? "" : obj.get("art_no").toString();
					Date publish_date = (Date) obj.get("publish_date");
					Date impl_date = (Date) obj.get("impl_date");
					String pubDateStr = "", implDateStr = "";
					if (publish_date != null) {
						pubDateStr = dateSDF.format(publish_date);
					}
					if (impl_date != null) {
						implDateStr = dateSDF.format(impl_date);
					}
					String filePath = obj.get("file_path") == null ? "" : obj.get("file_path").toString();
					json.put("title", title);
					json.put("art_no", art_no);
					json.put("publish_date", pubDateStr);
					json.put("impl_date", implDateStr);
					json.put("file_path", filePath);
				}
			}
		}
		return json.toString();
	}

	public String getUpdateList(int start, int count) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, -6);
		String publishDate = dateSDF.format(cal.getTime());
		if (count > 0) {
			JSONArray arr = new JSONArray();
			List<HashMap<String, Object>> updateList = searchDao.findUpdateList(publishDate, start, count);
			if (updateList != null && updateList.size() > 0) {
				for (int i = 0; i < updateList.size(); i++) {
					JSONObject json = new JSONObject();
					HashMap<String, Object> obj = updateList.get(i);
					if (obj != null) {
						json.put("art_id", obj.get("art_id"));
						json.put("art_title", obj.get("title"));
						json.put("art_no", obj.get("art_no"));
						json.put("publish_date", obj.get("publish_date"));
						String filePath = obj.get("file_path") == null ? "" : obj.get("file_path").toString();
						json.put("art_path", filePath);
						arr.add(json);
					}
				}
			}
			appendArticleViewTimes(arr);
			return arr.toString();
		} else {
			int updateListNo = searchDao.findUpdateListCount(publishDate);
			return updateListNo + "";
		}
	}

	public String getTotalArticles() {
		int totalArticles = searchDao.getTotalAriticles();
		return totalArticles + "";
	}

	public String getHotTag() {
		JSONArray arr = new JSONArray();
		int catIdStr = 0;
		List<Object> catIdList = searchDao.findCatIdByCatName();
		if (catIdList != null && catIdList.size() > 0) {
			Object obj = catIdList.get(0);
			catIdStr = Integer.parseInt(obj.toString().toString());
			List<CategoryInfo> hotTagList = searchDao.selectLowerCat(catIdStr);
			if (hotTagList != null && hotTagList.size() > 0) {
				for (int i = 0; i < hotTagList.size(); i++) {
					JSONObject json = new JSONObject();
					json.put("tag_id", hotTagList.get(i).getCatId());
					json.put("tag_title", hotTagList.get(i).getCatName());
					arr.add(json);
				}
			}
		}
		return arr.toString();
	}

	public String searchTitle(String keywords, int start, int count) {
		int totalCnt = searchDao.findArticleTitleCount(keywords);
		if (totalCnt > 0) {
			List<HashMap<String, String>> list = searchDao.searchArticleTitle(keywords, start, count);
			JSONArray arr = new JSONArray();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject json = new JSONObject();
					HashMap<String, String> obj = list.get(i);
					if (obj != null) {
						json.put("art_id", obj.get("art_id"));
						json.put("art_title", obj.get("title"));
						json.put("art_no", obj.get("art_no"));
						String filePath = obj.get("file_path") == null ? "" : obj.get("file_path");
						json.put("art_path", filePath);
						arr.add(json);
					}
				}
			}
			appendArticleViewTimes(arr);
			JSONObject finalObj = new JSONObject();
			finalObj.put("searchCnt", totalCnt);
			finalObj.put("data", arr.toString());
			return finalObj.toString();
		} else {
			return "";
		}
	}
}