package com.example.demo.controller;

import com.example.demo.service.RequirementsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hiddendanager")
public class RequirementsController {

    @Autowired
    RequirementsService service;

    @GetMapping("getCategory")
    public String getCategory() {
        return service.getCategory();
    }

    @GetMapping("getRequirementsByKeywords")
    public String getRequirements(String keywords, String catIdList, int start, int count) {
        String result = service.getRequirementsByKeywords(keywords, catIdList, start, count);
        int totalNo = service.getRequirementsNoByKeywords(keywords, catIdList);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("searchCnt", totalNo);
        return obj.toString();
    }

    @GetMapping("getRequirementsByIds")
    public String getRequirementsByIds(String reqid) {
        String result = service.getRequirementsByIds(reqid);
        JSONObject obj = new JSONObject();
        obj.put("data", result);

        return obj.toString();
    }
    @GetMapping("getLastCategory")
    public String getLastCategory(String upperCatId) {
        return service.getLastCategory(upperCatId);
    }

}
