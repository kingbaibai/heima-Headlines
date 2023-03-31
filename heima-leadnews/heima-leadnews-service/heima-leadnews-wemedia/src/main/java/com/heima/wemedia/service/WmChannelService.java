package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 16:16
 * @Description: com.heima.wemedia.service
 * @version: 1.0
 */
public interface WmChannelService extends IService<WmChannel> {
    /**
     * 查询所有频道
     * @return
     */
    ResponseResult findAll();
}
