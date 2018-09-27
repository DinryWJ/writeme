package com.zust.writeme.service.carouselService;

import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.model.Carousel;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/27 11:05
 * @Description:
 */
public interface CarouselService {
    /**
     * 新增轮播图
     *
     * @param tag
     * @param address
     * @param pic
     * @return
     */
    int addCarousel(String tag, String address, String pic);

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    int delCarousel(int id);

    /**
     * 获取轮播图列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<Carousel> getCarouselList(int pageNum, int pageSize);

    /**
     * 通过id获取轮播图
     *
     * @param id
     * @return
     */
    Carousel getCarouselById(int id);
}
