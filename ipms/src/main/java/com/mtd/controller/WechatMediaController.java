package com.mtd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.mtd.entity.MediaJsonBody;
import com.mtd.entity.WechatNews;
import com.mtd.service.WeChatService;
import com.mtd.util.SessionAttrUtil;

/**
 * @author just_bamboo
 * @ClassName: WechatMediaController
 * @Description: 微信素材管理控制器
 * @date 2015年12月3日 下午3:31:40
 */
@Controller
@RequestMapping("/media")
@RequiresPermissions("sys:wechat")
public class WechatMediaController {
    @Resource
    private WeChatService wechatService;
    @Resource
    private SessionAttrUtil sessionUtil;

    /**
     * @param page 分页页码，每页20条
     * @param type 素材类型,除图文素材类型，仅支持图片(IMAGE),视频(VIDEO),音频(VOICE)
     * @return 以 素材url列表的json字符串返回 [ {"url":
     * "https://mmbiz.qlogo.cn/mmbiz/8iahmeGy1DpZw3rk6kwdRiavbmmzert4tjt3cvfExulB27iaFnZNOrbNkwsEH3HJMzrGcFeXT4Cnd1OXH5FQX3kDg/0?wx_fmt=jpeg"
     * }, {"url":
     * "https://mmbiz.qlogo.cn/mmbiz/8iahmeGy1DpZw3rk6kwdRiavbmmzert4tjW20QUnMlrWQVt80ncKsHldC9t9Z4aBz6p2tKFKQvEGC0VLKJqV9Wicg/0?wx_fmt=jpeg"
     * } ]
     * @Description 向微信服务器请求永久素材列表
     * /wechat/media/getMaterialList/{type}/{page}.do
     * @author justbamboo
     * @date 2015年12月3日 下午2:56:55
     */
    @RequestMapping(value = "/getMaterialList/{type}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String getMaterialUrlList(@PathVariable("page") int page, @PathVariable("type") String type) {
        // String s=wechatService.getMaterialList(MaterialType.valueOf(type),
        // page)
        // System.out.println(s);
        return wechatService.getMaterialUrlList(MaterialType.valueOf(type), page);
    }

    @RequestMapping(value = "/getMaterialImageList/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String getMaterialImageList(@PathVariable("page") int page) {
        return null;
    }

    @RequestMapping(value = "/addMaterialNews", method = RequestMethod.POST)
    @ResponseBody
    public String addMaterialNews(String param) {
        if (param != null && !param.equals("")) {
            boolean flag = wechatService.addMaterialNews(param);
            return flag ? "ok" : "fail";
        }
        return "error";
    }

    @RequestMapping(value = "/getMaterialList/detail/{type}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String getMaterialList(@PathVariable("page") int page, @PathVariable("type") String type) {
        return wechatService.getMaterialList(MaterialType.valueOf(type), page);
    }

    @RequestMapping(value = "/toNewsPage", method = RequestMethod.GET)
    public ModelAndView toMediaNewsPage(ModelAndView m) {
        String s = wechatService.getMaterialList(MaterialType.valueOf("IMAGE"), 1);
        List<MediaJsonBody> list = new ArrayList<MediaJsonBody>(JSONArray.parseArray(s, MediaJsonBody.class));
        m.addObject("imgList", list);
        m.setViewName("/cms/cms_message_add");
        return m;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
//		wechatService.getWechatNewsList(1);
//		9jr54sgd2wd4O3jAouL37jGwRnshBvjn5VYtZwEmxvY
        wechatService.getWechatNews("9jr54sgd2wd4O3jAouL37jGwRnY", 0);
        return "ok";
    }

    @RequestMapping(value = "/{mediaId}/{index}/{thumbMediaId}/editNews", method = RequestMethod.GET)
    public ModelAndView editWechatNews(@PathVariable("index") String index, @PathVariable("mediaId") String mediaID, @PathVariable("thumbMediaId") String thumbMediaId, ModelAndView m) {
        WechatNews news = wechatService.getWechatNews(mediaID, Integer.parseInt(index));
        m.addObject("news", news);
        m.addObject("mediaID", mediaID);
        m.addObject("index", index);
        m.addObject("thumbMediaId", thumbMediaId);
        m.setViewName("cms/cms_message_edit");
        return m;
    }

    /**
     * @param media
     * @param user
     * @return
     * @Description 预览图文
     * @author justbamboo
     * @date 2016年1月5日 下午7:41:07
     */
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    @ResponseBody
    public String previewWechatNews(String media, String user) {
        BaseResponse resp = wechatService.previewMessage(media, user);
        if ("0".equals(resp.getErrcode())) {
            System.out.println(JSON.toJSON(resp));
            return "ok";
        } else {
            System.out.println(JSON.toJSON(resp));
        }
        return "error";
    }

}
