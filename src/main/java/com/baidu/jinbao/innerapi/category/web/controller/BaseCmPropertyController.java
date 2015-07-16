package com.baidu.jinbao.innerapi.category.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;
import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;
import com.baidu.jinbao.innerapi.category.service.BaseCmPropertyService;

@Controller
@RequestMapping(value = "/category")
public class BaseCmPropertyController extends JsonBaseController {
    @Autowired
    BaseCmPropertyService baseCmPropertyService;

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult test() {
        List<BaseCmProperty> data = baseCmPropertyService.getPageRecords();

        return this.markSuccessResult(data, "scucess");
    }
}
