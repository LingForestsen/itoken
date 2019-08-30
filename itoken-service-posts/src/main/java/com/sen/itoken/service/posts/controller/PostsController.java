package com.sen.itoken.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.sen.itoken.common.domain.TbPostsPost;
import com.sen.itoken.common.dto.BaseResult;
import com.sen.itoken.common.utils.MapperUtils;
import com.sen.itoken.service.posts.service.ServicePosts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 16:20
 * @Description: 文章服务控制中心
 */
@RestController
@RequestMapping(value = "v1/posts")
public class PostsController {
    @Autowired
    private ServicePosts servicePosts;

    /**
     * 通过文章id查找文章
     * @return 文章json
     */
    @RequestMapping(value = "{postGuid}",method = RequestMethod.GET)
    public BaseResult getPostsById(@PathVariable(value = "postGuid") String postGuid) {
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);
        TbPostsPost targetPosts = servicePosts.selectOne(tbPostsPost);
        return BaseResult.ok(targetPosts);
    }

    /**
     * 保存文章
     * @param tbPostsJson 文章对象的json数据
     * @param optsBy 创建者
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(String tbPostsJson, String optsBy) {
        TbPostsPost tbPostsPost = null;
        int result = 0;
        if (StringUtils.isNotBlank(tbPostsJson)) {
            try {
                tbPostsPost = MapperUtils.json2pojo(tbPostsJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tbPostsPost != null) {
            //主键为空表示新增
            if (StringUtils.isBlank(tbPostsPost.getPostGuid())) {
                tbPostsPost.setPostGuid(UUID.randomUUID().toString());
                result = servicePosts.insert(tbPostsPost, optsBy);
            }
            //主键不为空表示修改
            else {
                result = servicePosts.update(tbPostsPost, optsBy);
            }
            if (result > 0) {
                return BaseResult.ok("操作文章内容成功");
            }
        }
        return BaseResult.ok("操作文章内容失败");
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param tbPostsJson
     * @return
     */
    @ApiOperation("分页查询api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tbPostsJson", value = "对象的json格式", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public BaseResult page(@PathVariable(value = "pageNum") int pageNum,
                           @PathVariable(value = "pageSize") int pageSize,
                           String tbPostsJson) {
        TbPostsPost postsPost = null;
        if (StringUtils.isNotBlank(tbPostsJson)) {
            try {
                postsPost = MapperUtils.json2pojo(tbPostsJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<TbPostsPost> page = servicePosts.findByPage(pageNum, pageSize, postsPost);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        //封装Curson
        cursor.setOffset(page.getPageNum());
        cursor.setTotal(new Long(page.getTotal()).intValue());
        cursor.setLimit(page.getPageSize());

        return BaseResult.ok(page.getList(), cursor);
    }
}
