package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.dtos.AppHttpCodeEnum;
import com.heima.common.dtos.PageResponseResult;
import com.heima.common.dtos.ResponseResult;
import com.heima.common.exception.LeadnewsException;
import com.heima.common.minio.MinIOFileStorageService;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.ThreadLocalUtils;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    private MinIOFileStorageService minIOFileStorageService;

    @Override
    public PageResponseResult list(WmMaterialDto dto) {
        //参数处理
        dto.checkParam();

        //判断是否登录（获取登录用户信息）
        WmUser wmUser = (WmUser)ThreadLocalUtils.get();
        if(wmUser==null){
            throw new LeadnewsException(AppHttpCodeEnum.NEED_LOGIN);
        }

        //准备分页参数
        IPage<WmMaterial> iPage = new Page<>(dto.getPage(),dto.getSize());

        //准备条件参数
        QueryWrapper<WmMaterial> queryWrapper = new QueryWrapper<>();
        //判断当前登录用户
        queryWrapper.eq("user_id",wmUser.getId());

        //是否收藏
        if(dto.getIsCollection()!=null && dto.getIsCollection()==1){
            queryWrapper.eq("is_collection",dto.getIsCollection());
        }

        //排序
        queryWrapper.orderByDesc("created_time");

        //分页查询
        /**
         * iPage封装分页前后数据（page,size, 总页数，总记录数，List列表）
         */
        iPage = page(iPage,queryWrapper);

        //封装分页数据
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)iPage.getTotal());
        pageResponseResult.setData(iPage.getRecords());
        pageResponseResult.setCode(200);
        pageResponseResult.setErrorMessage("查询成功");
        return pageResponseResult;
    }

    /**
     * 上传素材
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult upload_picture(MultipartFile multipartFile) {
        //1.判断是否处于登入状态
        WmUser wmUser = (WmUser) ThreadLocalUtils.get();
        if (wmUser == null) {
            throw new LeadnewsException(AppHttpCodeEnum.NEED_LOGIN);
        }
        //2.上传图片
        /**
         * 2.1获取文件名
         * 2.2获取文件后缀名
         * 2.3生成新的文件名
         * 2.4上传到minio中
         *
         */
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = uuid + extName;

            String url = minIOFileStorageService.uploadImgFile("", fileName, multipartFile.getInputStream());

            //3.将素材链接保存到数据库
            WmMaterial wmMaterial = new WmMaterial();
            wmMaterial.setUserId(wmUser.getId());
            wmMaterial.setUrl(url);
            wmMaterial.setType((short)0);
            wmMaterial.setIsCollection((short) 0);
            wmMaterial.setCreatedTime(new Date());
            save(wmMaterial);

            return ResponseResult.okResult(wmMaterial);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}

