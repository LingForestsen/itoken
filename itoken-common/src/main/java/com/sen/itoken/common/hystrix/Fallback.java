package com.sen.itoken.common.hystrix;

import com.google.common.collect.Lists;
import com.sen.itoken.common.dto.BaseResult;
import com.sen.itoken.common.constans.HttpStatusContants;
import com.sen.itoken.common.utils.MapperUtils;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 03:30
 * @Description:
 * 通用的熔断方法
 */
public class Fallback {

    /**
     * 502
     * @return
     */
    public static String BadGateway() {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error(String.valueOf(HttpStatusContants.BAD_GATEWAY.getStatus()),
                        HttpStatusContants.BAD_GATEWAY.getMessage())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
