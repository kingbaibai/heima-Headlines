package com.heima.article.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heima.article.ArticleApplication;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.common.minio.MinIOFileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.utils.common.JsonUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 19:21
 * @Description: com.heima.article.test
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticleApplication.class)
public class FreeMarkTest {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private Configuration configuration;

    @Autowired
    private MinIOFileStorageService minIOFileStorageService;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Test
    public void createFreeMark() throws Exception{
        //1.生成freemarker
        /**
         * 1.1获取数据
         * 1.2获取静态模板
         * 1.3创建缓存流
         * 1.4生成模板存入缓存
         *
         */
        Long id =1302862387124125698L;
        QueryWrapper<ApArticleContent> query = new QueryWrapper<>();
        query.eq("article_id", id);
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(query);

        Template template = configuration.getTemplate("article.ftl");

        //加载文章详情页模板
        //模板制作过程：  前端设计静态页面 -> 后端开发拿到静态页面在合适的位置加上Freemarker的标签 -> 得到模板
        HashMap<String, Object> data = new HashMap<>();
        List<Map> mapList = JsonUtils.toList(apArticleContent.getContent(), Map.class);
        data.put("content", mapList);

        //生成静态页面
        //临时存储缓存流
        StringWriter stringWriter = new StringWriter();//放在内存
        template.process(data,stringWriter);

        //2上传到minio中
        //把静态页面存储到MinIO，获取到url地址
        ByteArrayInputStream inputStream = new ByteArrayInputStream(stringWriter.toString().getBytes());

        String url = minIOFileStorageService.uploadHtmlFile("", id+".html", inputStream);

        System.out.println(url);
        //存入数据中
        ApArticle apArticle = new ApArticle();
        apArticle.setId(id);
        apArticle.setStaticUrl(url);
        apArticleMapper.updateById(apArticle);
    }
}
