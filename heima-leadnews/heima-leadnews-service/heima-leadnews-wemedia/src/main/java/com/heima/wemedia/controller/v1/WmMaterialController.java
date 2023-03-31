package com.heima.wemedia.controller.v1;

import com.heima.common.dtos.PageResponseResult;
import com.heima.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 14:02
 * @Description: com.heima.wemedia.controller.v1
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {
    @Autowired
    private WmMaterialService wmMaterialService;

    /**
     * 上传素材
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_picture")
    public ResponseResult upload_picture(MultipartFile multipartFile) {
        return wmMaterialService.upload_picture(multipartFile);
    }

    /**
     * 查询所有素材
     */

    @PostMapping("/list")
    public PageResponseResult list(@RequestBody WmMaterialDto dto) {
       return wmMaterialService.list(dto);
    }



}
