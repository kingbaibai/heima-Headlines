package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 12:46
 * @Description: com.heima.article.mapper
 * @version: 1.0
 */
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    List<ApArticle> loadArticle(@Param("dto") ArticleDto dto,@Param("type") int type);
}
