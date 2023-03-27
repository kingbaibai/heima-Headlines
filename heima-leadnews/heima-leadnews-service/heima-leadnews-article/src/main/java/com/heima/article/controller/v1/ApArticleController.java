package com.heima.article.controller.v1;

import com.heima.article.service.ApArticleService;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.article.dtos.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 12:52
 * @Description: com.heima.article.controller.v1
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1/article")
@Api(tags = "文章管理")
public class ApArticleController {

    @Autowired
    private ApArticleService apArticleService;

    /**
     * 文章加载
     * @return
     */
    @PostMapping("/load")
    @ApiOperation("加载文章")
    public ResponseResult load(@RequestBody ArticleDto dto){
        return apArticleService.load(dto,1);
    }


    @PostMapping("/loadnew")
    @ApiOperation("刷新新文章")
    public ResponseResult loadnew(@RequestBody ArticleDto dto) {
        return apArticleService.load(dto,2);
    }

    @PostMapping("/loadmore")
    @ApiOperation("加载就文章")
    public ResponseResult loadmore(@RequestBody ArticleDto dto) {
        return apArticleService.load(dto, 1);
    }
}
