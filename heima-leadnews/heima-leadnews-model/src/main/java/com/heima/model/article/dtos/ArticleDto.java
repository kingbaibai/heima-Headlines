package com.heima.model.article.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 13:05
 * @Description: com.heima.model.article.dtos
 * @version: 1.0
 */
@Data
@ApiModel(description = "文章dto")
public class ArticleDto {
    @ApiModelProperty("最大时间")
    private Date maxBehotTime;

    @ApiModelProperty("最小时间")
    private Date minBehotTime;

    @ApiModelProperty("每页记录数")
    private Integer size;

    @ApiModelProperty("频道ID _all_")
    private String tag;

}
