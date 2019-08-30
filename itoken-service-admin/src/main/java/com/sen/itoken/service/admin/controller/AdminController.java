package com.sen.itoken.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.dto.BaseResult;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.service.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Sen
 * @Date: 2019/8/22 23:01
 * @Description:
 */
@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value ="page/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @PathVariable(required = false) String tbSysUser) {
        TbSysUser obj = new TbSysUser();
        if (StringUtils.isNotBlank(tbSysUser)) {
            try {
                obj= MapperUtils.json2pojo(tbSysUser, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<TbSysUser> pageInfo = adminService.findByPage(pageNum, pageSize, obj);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        //设置Cursor
        if (pageInfo != null) {
            cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
            cursor.setOffset(pageInfo.getPageNum());
            cursor.setLimit(pageInfo.getPageSize());
        }
        return BaseResult.ok(pageInfo.getList(), cursor);
    }
}
