package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.pojos.ApUser;

/**
 * @author zhangqin
 * @Date: 2023/3/25 - 03 - 25 - 21:09
 * @Description: com.heima.user.service
 * @version: 1.0
 */
public interface ApUserService extends IService<ApUser> {
    ResponseResult login(LoginDto dto);
}
