package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.pojos.ApArticle;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 12:50
 * @Description: com.heima.article.service
 * @version: 1.0
 */
public interface ApArticleService extends IService<ApArticle> {
    /**
     * 文章加载
     * @return
     */
    ResponseResult load(ArticleDto dto, int type);
}
