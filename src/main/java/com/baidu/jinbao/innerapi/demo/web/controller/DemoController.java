package com.baidu.jinbao.innerapi.demo.web.controller;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;
import com.baidu.dpop.frame.core.context.SpringContextUtil;
import com.baidu.jinbao.innerapi.demo.service.FixService;
import com.baidu.jinbao.innerapi.demo.service.SkuFixService;

@Controller
@RequestMapping(value = "/fix")
public class DemoController extends JsonBaseController {

    @RequestMapping(value = "/execute.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult fix(String dbInfo, String sql, String splitDbInfo) {
        // 输入参数有误
        if (StringUtils.isEmpty(dbInfo) || StringUtils.isEmpty(sql)) {
            return this.markErrorResult("wrong params");
        }

        // jinbao_common 库
        if (dbInfo.equals("jinbao_common")) {
            FixService service = SpringContextUtil.getBean("commonFixService");
            if (sql.indexOf("insert") >= 0 || sql.indexOf("INSERT") >= 0) {
                service.executeInsert(sql);
            } else if (sql.indexOf("update") >= 0 || sql.indexOf("UPDATE") >= 0) {
                service.executeUpdate(sql);
            } else if (sql.indexOf("delete") >= 0 || sql.indexOf("DELETE") >= 0) {
                service.executeDelete(sql);
            } else {
                return this.markErrorResult("sql is wrong");
            }

        }
        // jinbao_2c_category 库
        else if (dbInfo.equals("jinbao_2c_category")) {
            FixService service = SpringContextUtil.getBean("huiCategoryFixService");
            if (sql.indexOf("insert") >= 0 || sql.indexOf("INSERT") >= 0) {
                service.executeInsert(sql);
            } else if (sql.indexOf("update") >= 0 || sql.indexOf("UPDATE") >= 0) {
                service.executeUpdate(sql);
            } else if (sql.indexOf("delete") >= 0 || sql.indexOf("DELETE") >= 0) {
                service.executeDelete(sql);
            } else {
                return this.markErrorResult("sql is wrong");
            }

        }
        // jinbao_2c_sku 库
        else if (dbInfo.equals("jinbao_2c_sku")) {
            if (StringUtils.isEmpty(splitDbInfo)) {
                return this.markErrorResult("must input split db info");
            }

            SkuFixService service = SpringContextUtil.getBean("huiSkuFixService");
            if (sql.indexOf("insert") >= 0 || sql.indexOf("INSERT") >= 0) {
                service.executeInsert(splitDbInfo, sql);
            } else if (sql.indexOf("update") >= 0 || sql.indexOf("UPDATE") >= 0) {
                service.executeUpdate(splitDbInfo, sql);
            } else if (sql.indexOf("delete") >= 0 || sql.indexOf("DELETE") >= 0) {
                service.executeDelete(splitDbInfo, sql);
            } else {
                return this.markErrorResult("sql is wrong");
            }

        }
        // jinbao_2c_spu 库
        else if (dbInfo.equals("jinbao_2c_spu")) {
            FixService service = SpringContextUtil.getBean("huiSpuFixService");
            if (sql.indexOf("insert") >= 0 || sql.indexOf("INSERT") >= 0) {
                service.executeInsert(sql);
            } else if (sql.indexOf("update") >= 0 || sql.indexOf("UPDATE") >= 0) {
                service.executeUpdate(sql);
            } else if (sql.indexOf("delete") >= 0 || sql.indexOf("DELETE") >= 0) {
                service.executeDelete(sql);
            } else {
                return this.markErrorResult("sql is wrong");
            }
        } else if (dbInfo.equals("jinbao_mall_category")) {
            FixService service = SpringContextUtil.getBean("mallCategoryFixService");
            if (sql.indexOf("insert") >= 0) {
                service.executeInsert(sql);
            } else if (sql.indexOf("update") >= 0) {
                service.executeUpdate(sql);
            } else if (sql.indexOf("delete") >= 0) {
                service.executeDelete(sql);
            } else {
                return this.markErrorResult("sql is wrong");
            }
        } else if (dbInfo.equals("jinbao_mall_item")) {
            FixService service = SpringContextUtil.getBean("mallItemFixService");
            if (sql.indexOf("insert") >= 0 || sql.indexOf("INSERT") >= 0) {
                service.executeInsert(sql);
            } else if (sql.indexOf("update") >= 0 || sql.indexOf("UPDATE") >= 0) {
                service.executeUpdate(sql);
            } else if (sql.indexOf("delete") >= 0 || sql.indexOf("DELETE") >= 0) {
                service.executeDelete(sql);
            } else {
                return this.markErrorResult("sql is wrong");
            }
        } else {
            return this.markErrorResult(dbInfo + " is not found");
        }

        return this.markSuccessResult("", "查找成功");
    }

}
