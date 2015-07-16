package com.baidu.jinbao.innerapi.category.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;
import com.baidu.jinbao.mall.item.service.MallSkuPpsService;

@Controller
@RequestMapping(value = "/category")
public class CategoryPropertyController extends JsonBaseController {

    @Autowired
    private MallSkuPpsService mallSkuPpsService;

    @RequestMapping(value = "/find.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult find(Long id) {

        return this.markSuccessResult("", "successful!");
    }
}
