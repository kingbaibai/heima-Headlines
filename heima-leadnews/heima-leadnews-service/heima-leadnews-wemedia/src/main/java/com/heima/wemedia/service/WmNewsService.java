package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.common.dtos.PageResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 16:58
 * @Description: com.heima.wemedia.service
 * @version: 1.0
 */
public interface WmNewsService extends IService<WmNews> {
    /**
     * 查询自媒体文章列表
     * @param dto
     * @return
     */
    PageResponseResult findAll(WmNewsPageReqDto dto);
}
