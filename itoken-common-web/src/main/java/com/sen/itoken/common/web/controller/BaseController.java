package com.sen.itoken.common.web.controller;

import com.sen.itoken.common.domain.BaseDomain;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.common.web.dataTables.DataTablesResult;
import com.sen.itoken.common.web.service.BaseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Sen
 * @Date: 2019/8/27 19:01
 * @Description:
 */
public abstract class BaseController<T extends BaseDomain, S extends BaseClientService> {

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public DataTablesResult page(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        String json = service.page(start, length, null);
        DataTablesResult dataTablesResult = null;
        try {
            dataTablesResult = MapperUtils.json2pojo(json, DataTablesResult.class);
            dataTablesResult.setDraw(draw);
            dataTablesResult.setRecordsTotal(dataTablesResult.getCursor().getTotal());
            dataTablesResult.setRecordsFiltered(dataTablesResult.getCursor().getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTablesResult;
    }
}
