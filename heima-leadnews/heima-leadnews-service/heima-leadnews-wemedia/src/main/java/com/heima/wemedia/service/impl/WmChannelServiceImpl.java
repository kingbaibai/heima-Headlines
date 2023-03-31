package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.stereotype.Service;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 16:41
 * @Description: com.heima.wemedia.service.impl
 * @version: 1.0
 */
@Service
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService  {

    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }

}
