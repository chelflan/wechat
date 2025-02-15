package com.tasty.cowechat.controller;

import com.tasty.common.result.ResultVO;
import com.tasty.common.util.Utils;
import com.tasty.cowechat.api.TokenService;
import com.tasty.cowechat.api.dto.GetDepartmentInfoDTO;
import com.tasty.cowechat.api.dto.GetUserInfoDTO;
import com.tasty.cowechat.api.service.IWeChatApiService;
import com.tasty.cowechat.api.service.impl.GetDepartmentInfoService;
import com.tasty.cowechat.api.service.impl.GetUserInfoService;
import com.tasty.mybatis.common.util.SeqUtil;
import com.tasty.mybatis.common.util.SpringUtil;
import com.tasty.mybatis.mapper.ITestPunish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhu.zexin
 * @Date: 2019/6/8
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ITestPunish testPunish;
    @RequestMapping("/hello")
    public ResultVO Hello(){
        //return ResultVO.success("Hello World!");
        System.out.println("token:" + TokenService.getToken());
        return  ResultVO.success(testPunish.qryPunishById("1"));
    }

    @RequestMapping("/getUserInfo")
    public ResultVO getUserInfo(String userId){
        IWeChatApiService service = SpringUtil.getBean(GetUserInfoService.class);
        GetUserInfoDTO params = new GetUserInfoDTO();
        params.setUserId(userId);
        return ResultVO.success(service.service(params));
    }

    @RequestMapping("/getDepartmentInfo")
    public ResultVO getDepartmentInfo(String departmentId){
        IWeChatApiService service = SpringUtil.getBean(GetDepartmentInfoService.class);
        GetDepartmentInfoDTO params = new GetDepartmentInfoDTO();
        if (!Utils.isEmpty(departmentId)){
            params.setDepartmentId(departmentId);
        }
        return ResultVO.success(service.service(params));
    }
}
