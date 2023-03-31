package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.dtos.AppHttpCodeEnum;
import com.heima.common.dtos.ResponseResult;
import com.heima.common.exception.LeadnewsException;
import com.heima.model.wemedia.dtos.WmLoginDto;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.BCrypt;
import com.heima.utils.common.JwtUtils;
import com.heima.utils.common.RsaUtils;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;

/**
 * @author zhangqin
 * @Date: 2023/3/28 - 03 - 28 - 20:08
 * @Description: com.heima.wemedia.service.impl
 * @version: 1.0
 */
@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {

    @Value("${leadnews.jwt.privateKeyPath}")
    private String privateKeyPath;


    @Value("${leadnews.jwt.expire}")
    private Integer expire;

    @Override
    public ResponseResult login(WmLoginDto dto) {
        if (StringUtils.isNotEmpty(dto.getName()) && StringUtils.isNotEmpty(dto.getPassword())) {
            //判断当前用户是否存在
            QueryWrapper<WmUser> query = new QueryWrapper<>();
            query.eq("name", dto.getName());
            WmUser wmUser = getOne(query);
            //不存在
            if (wmUser == null) {
                throw new LeadnewsException(AppHttpCodeEnum.DATA_NOT_EXIST);
            }
            //存在 判断密码是否正确
            //不正确
            if (!BCrypt.checkpw(dto.getPassword(), wmUser.getPassword())) {
                throw new LeadnewsException(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            //正确 生成token
            try {
                wmUser.setPassword(null);
                PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
                String token = JwtUtils.generateTokenExpireInMinutes(wmUser, privateKey, expire);

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("token", token);
                hashMap.put("user", wmUser);
                return ResponseResult.okResult(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

}
