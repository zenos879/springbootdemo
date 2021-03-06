package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "userInfo")
    public List<UserInfo> selectNicknameByOpenid() {
        String openId = request.getParameter("openId");
        if (openId == null || "".equals(openId)) {
            return null;
        }
        List<UserInfo> users = userService.selectNicknameByOpenid(openId);
        return users;
    }

    @RequestMapping(value = "register")
    public String register() {
        String openId = request.getParameter("openId");
        String nickName = request.getParameter("nickName");
        String avatarUrl = request.getParameter("avatarUrl");
        if (openId == null || "".equals(openId)) {
            return "";
        }
        UserInfo user = new UserInfo();
        user.setAvatarUrl(avatarUrl);
        user.setOpenId(openId);
        user.setNickName(nickName);
        String ret = userService.register(user);
        return ret;
    }

    @RequestMapping(value = "getVipperiod")
    public String getVipperiod() {
        String openId = request.getParameter("openId");
        if (openId == null || "".equals(openId)) {
            return "";
        }
        String ret = userService.getVipperiod(openId);
        return ret;
    }

    @RequestMapping(value = "updateVipperiod")
    public String updateVipperiod() {
        String openId = request.getParameter("openId");
        if (openId == null || "".equals(openId)) {
            return "";
        }
        String ret = userService.updateVipperiod(openId);
        return ret;
    }

    //未测
    @RequestMapping(value = "getOpenId")
    public String getOpenId() {
        String code = request.getParameter("code");
        String ret = userService.getOpenId(code);
        return ret;
    }

    @RequestMapping(value = "saveCollections")
    public String saveCollections() {
        String openId = request.getParameter("openId");
        String collections = request.getParameter("collections");
        if (openId == null || "".equals(openId)) {
            return "";
        }
        collections = "[" + collections + "]";
        String ret = userService.saveCollections(openId, collections);
        return ret;
    }

    @RequestMapping(value = "saveHCollections")
    public String saveHCollections() {
        String openId = request.getParameter("openId");
        String collections = request.getParameter("HCollections");
        if (openId == null || "".equals(openId)) {
            return "";
        }
        collections = "[" + collections + "]";
        String ret = userService.saveHCollections(openId, collections);
        return ret;
    }

    @RequestMapping(value = "saveHAgree")
    public String saveHAgree() {
        String openId = request.getParameter("openId");
        String HAgree = request.getParameter("HAgree");
        if (openId == null || "".equals(openId)) {
            return "";
        }
        HAgree = "[" + HAgree + "]";
        String ret = userService.saveHAgree(openId, HAgree);
        return ret;
    }

    @RequestMapping(value = "updateUserViewHistory")
    public String updateUserViewHistory() {
        String openId = request.getParameter("openId");
        String historyList = request.getParameter("historyList");
        historyList = "[" + historyList + "]";
        String ret = userService.updateUserViewHistory(openId, historyList);
        return ret;
    }

    @RequestMapping(value = "updateArticleViewtimes")
    public String updateArticleViewtimes() {
        String artId = request.getParameter("artId");
        String ret = userService.updateArticleViewtimes(artId);
        return ret;
    }

}