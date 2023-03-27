package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.pojos.ApArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 12:51
 * @Description: com.heima.article.service.impl
 * @version: 1.0
 */
@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    @Autowired
    private ApArticleMapper apArticleMapper;
    /**
     * 文章加载
     * @return
     */
    @Override
    public ResponseResult load(ArticleDto dto,int type) {

        if(dto.getMinBehotTime()==null) dto.setMinBehotTime(new Date());
        if(dto.getMaxBehotTime()==null) dto.setMaxBehotTime(new Date());
        if(dto.getSize()==null) dto.setSize(10);
        if(dto.getSize()==null) dto.setTag("_all_");

       List<ApArticle> apArticleList =  apArticleMapper.loadArticle(dto, type);

        return ResponseResult.okResult(apArticleList);
    }
}
