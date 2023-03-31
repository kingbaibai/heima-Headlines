package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.dtos.AppHttpCodeEnum;
import com.heima.common.dtos.PageResponseResult;
import com.heima.common.exception.LeadnewsException;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.ThreadLocalUtils;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.service.WmNewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhangqin
 * @Date: 2023/3/31 - 03 - 31 - 16:58
 * @Description: com.heima.wemedia.service.impl
 * @version: 1.0
 */
@Service
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    /**
     * 查询自媒体文章列表
     * @param dto
     * @return
     */
    @Override
    public PageResponseResult findAll(WmNewsPageReqDto dto) {
        dto.checkParam();
        //1.判断当前用户是否处于登入状态
        WmUser wmUser = (WmUser) ThreadLocalUtils.get();
        if (wmUser == null) {
            throw new LeadnewsException(AppHttpCodeEnum.NEED_LOGIN);
        }
        //2.条件查询
        /**
         * 设置分页条件
         */
        IPage<WmNews> iPage = new Page<>(dto.getPage(), dto.getSize());

        QueryWrapper<WmNews> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id", wmUser.getId());

        if (dto.getStatus() != null) {
            queryWrapper.eq("status", dto.getStatus());
        }

        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
            queryWrapper.between("publish_time", dto.getBeginPubDate(), dto.getEndPubDate());
        }

        if (dto.getChannelId() != null) {
            queryWrapper.eq("channel_id", dto.getChannelId());
        }

        if (StringUtils.isNotEmpty(dto.getKeyword())) {
            queryWrapper.eq("content", dto.getKeyword());
        }
        //3.返回结果
        iPage = page(iPage, queryWrapper);

        PageResponseResult pageResponseResult = new PageResponseResult();
        pageResponseResult.setCurrentPage(dto.getPage());
        pageResponseResult.setSize(dto.getSize());
        pageResponseResult.setTotal((int)iPage.getTotal());
        pageResponseResult.setCode(200);
        pageResponseResult.setData(iPage.getRecords());
        pageResponseResult.setErrorMessage("查询成功");

        return pageResponseResult;
    }
}
