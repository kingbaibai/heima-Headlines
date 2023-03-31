package com.heima.wemedia.controller.v1;

import com.heima.common.dtos.ResponseResult;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 16:42
 * @Description: com.heima.wemedia.controller.v1
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1/channel")
public class WmChannelController {

    @Autowired
    private WmChannelService wmChannelService;

    /**
     * 查询所有频道
     * @return
     */
    @GetMapping("/channels")
    public ResponseResult channels(){
        return wmChannelService.findAll();
    }
}
