package com.heima.wemedia.controller.v1;

import com.heima.common.dtos.PageResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 16:59
 * @Description: com.heima.wemedia.controller.v1
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewsService wmNewsService;

    /**
     * 查询自媒体文章列表
     * @param dto
     * @return
     */
    @PostMapping("/list")
    public PageResponseResult list(@RequestBody WmNewsPageReqDto dto) {
        return wmNewsService.findAll(dto);
    }
}
