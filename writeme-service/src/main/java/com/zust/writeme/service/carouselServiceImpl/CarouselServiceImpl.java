package com.zust.writeme.service.carouselServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.CarouselMapper;
import com.zust.writeme.model.Carousel;
import com.zust.writeme.service.carouselService.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/27 11:06
 * @Description:
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public int addCarousel(String tag, String address, String pic) {
        Carousel carousel = new Carousel();
        carousel.setAddress(address);
        carousel.setPic(pic);
        carousel.setTag(tag);
        return carouselMapper.insertSelective(carousel);
    }

    @Override
    public int delCarousel(int id) {
        return carouselMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Pagination<Carousel> getCarouselList(int pageNum, int pageSize) {
        Pagination<Carousel> pagination = new Pagination<>();

        PageHelper.startPage(pageNum,pageSize);
        List<Carousel> list = carouselMapper.selectAll();

        pagination.setList(list);
        pagination.setTotal(((Page)list).getTotal());
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);

        return pagination;
    }

    @Override
    public Carousel getCarouselById(int id) {
        return carouselMapper.selectByPrimaryKey(id);
    }
}
