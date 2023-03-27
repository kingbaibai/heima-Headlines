package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.dtos.AppHttpCodeEnum;
import com.heima.common.dtos.ResponseResult;
import com.heima.common.exception.LeadnewsException;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.BCrypt;
import com.heima.utils.common.JwtUtils;
import com.heima.utils.common.RsaUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqin
 * @Date: 2023/3/25 - 03 - 25 - 21:10
 * @Description: com.heima.user.service.impl
 * @version: 1.0
 */
@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Value("${leadnews.jwt.privateKeyPath}")
    private String privateKeyPath;

    @Value("${leadnews.jwt.expire}")
    private Integer expire;

    @Override
    public ResponseResult login(LoginDto dto) {

        //1.用户登入
        if (StringUtils.isNotEmpty(dto.getPassword()) && StringUtils.isNotEmpty(dto.getPassword())) {
            QueryWrapper<ApUser> query = new QueryWrapper<>();
            query.eq("phone", dto.getPhone());

            ApUser apUser = getOne(query);
            if (apUser == null) {
                throw new LeadnewsException(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
            }
            if (!BCrypt.checkpw(dto.getPassword(), apUser.getPassword())) {
                throw new LeadnewsException(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

//            登入成功生成token返回
            //隐藏私密信息
            apUser.setPassword(null);
            return getResponseResult(apUser);


        } else {
            //2.游客登入
            ApUser apUser = new ApUser();
            apUser.setId(0);
            return getResponseResult(apUser);

        }

    }

    /**
     * 生成token
     * @param apUser
     * @return
     */
    private ResponseResult getResponseResult(ApUser apUser) {
        try {
            PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
            String token = JwtUtils.generateTokenExpireInMinutes(apUser, privateKey, expire);

            Map<String, Object> map = new HashMap<>();
            map.put("user", apUser);
            map.put("token", token);

            return ResponseResult.okResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("加载私钥失败");
        }
    }
}
