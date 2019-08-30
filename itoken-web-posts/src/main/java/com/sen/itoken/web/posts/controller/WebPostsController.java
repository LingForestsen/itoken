package com.sen.itoken.web.posts.controller;

import com.sen.itoken.common.domain.TbPostsPost;
import com.sen.itoken.common.domain.TbSysUser;
import com.sen.itoken.common.dto.BaseResult;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.common.web.controller.BaseController;
import com.sen.itoken.web.posts.service.WebPostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 22:35
 * @Description:
 */
@Controller
public class WebPostsController extends BaseController<TbPostsPost, WebPostsService> {
    @Autowired
    private WebPostsService webPostsService;

    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping(value = {"","main"},method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"index"},method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 跳转表单页
     * @return
     */
    @RequestMapping(value = {"form"},method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    /**
     * 提供数据给form表单的相关表单使用
     * @return
     */
    @ModelAttribute
    public TbPostsPost tbPostsPost(String postGuid) {
        TbPostsPost tbPostsPost = null;
        //如果不为空则是修或者新增失败
        if (StringUtils.isNotBlank(postGuid)) {
            String json = webPostsService.getPostsById(postGuid);
            try {
                BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);
                tbPostsPost = (TbPostsPost) baseResult.getData();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            //防止前端thymeleaf报错
            tbPostsPost = new TbPostsPost();
        }
        return tbPostsPost;
    }

    /**
     * 保存文章
     *
     * @param tbPostsPost
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        //设置初始化参数
        tbPostsPost.setStatus("0");
        tbPostsPost.setTimePublished(new Date());

        TbSysUser admin = (TbSysUser) request.getSession().getAttribute("admin");
        String userCode = admin.getUserCode();
        BaseResult baseResult = null;
        try {
            String tbPostsJson = MapperUtils.obj2json(tbPostsPost);
            String baseResultJson = webPostsService.save(tbPostsJson, userCode);
            baseResult = MapperUtils.json2pojo(baseResultJson, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //把数据放进重定向数据
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (StringUtils.isNotBlank(baseResult.getSuccess()) && baseResult.getSuccess().endsWith("成功")) {
            return "redirect:index";
        }
        //失败
        return "redirect:form";
    }
}
