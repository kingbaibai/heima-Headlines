package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.common.dtos.PageResponseResult;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {

    /**
     * 查询所有图片
     * @param dto
     * @return
     */
    PageResponseResult list(WmMaterialDto dto);

    /**
     * 上传素材
     * @param multipartFile
     * @return
     */
    ResponseResult upload_picture(MultipartFile multipartFile);
}