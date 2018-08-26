package com.zust.writeme.service.concernServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.ConcernMapper;
import com.zust.writeme.model.Concern;
import com.zust.writeme.service.concernService.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("concernService")
public class ConcernServiceImpl implements ConcernService {
    @Autowired
    private ConcernMapper concernMapper;


    @Override
    public int addConcern(int userId, int concernedUserId) {
        Concern concern = new Concern();
        concern.setUserId(userId);
        concern.setConcernedId(concernedUserId);
        concern.setCreateTime(new Date());
        return concernMapper.insertSelective(concern);
    }

    @Override
    public int deleteConcern(int userId, int concernedUserId) {
        Example example = new Example(Concern.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("concernedId", concernedUserId);
        return concernMapper.deleteByExample(example);
    }

    @Override
    public int getValidConcern(int userId, int concernedUserId) {
        Example example = new Example(Concern.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("concernedId", concernedUserId);
        return concernMapper.selectCountByExample(example);
    }

    @Override
    public Pagination<Concern> getUserFansList(int userId, int pageNum, int pageSize) {
        Pagination<Concern> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<Concern> concernList = concernMapper.getUserFansList(userId);

        pagination.setList(concernList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) concernList).getTotal());
        return pagination;
    }

    @Override
    public int getUserFansCount(int userId) {
        Example example = new Example(Concern.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("concernedId", userId);
        return concernMapper.selectCountByExample(example);
    }

    @Override
    public Pagination<Concern> getUserConcernList(int userId, int pageNum, int pageSize) {
        Pagination<Concern> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<Concern> concernList = concernMapper.getUserConcernList(userId);

        pagination.setList(concernList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) concernList).getTotal());
        return pagination;
    }

    @Override
    public int getUserConcernCount(int userId) {
        Example example = new Example(Concern.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return concernMapper.selectCountByExample(example);
    }
}
