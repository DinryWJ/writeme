package com.zust.writeme.service.concernServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.ConcernMapper;
import com.zust.writeme.dao.UserMapper;
import com.zust.writeme.model.Concern;
import com.zust.writeme.model.User;
import com.zust.writeme.service.concernService.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("concernService")
public class ConcernServiceImpl implements ConcernService {
    @Autowired
    private ConcernMapper concernMapper;
    @Autowired
    private UserMapper userMapper;


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

    @Override
    public Pagination getNoReadConcernList(int concernedId, int pageNum, int pageSize) {
        Pagination<Map<String,Object>> pagination = new Pagination<>();
        List<Concern> list=concernMapper.getNoReadConcernList(concernedId);

        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> eff = new ArrayList<>();

        List<Concern> concerns = concernMapper.getNoReadConcernList(concernedId);
        List ismutual = new ArrayList<>();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < concerns.size(); i++) {
            users.add(userMapper.selectByPrimaryKey(concerns.get(i).getUserId()));
            //concernedId 登录用户id
            //concerns.get(i).getUserId() 主动关注者id
            int isconcern=getValidConcern(concernedId,concerns.get(i).getUserId());
            ismutual.add(isconcern);
        }
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        for (int i = 0; i < concerns.size(); i++) {
            Map map = new HashMap(5);
            map.put("concernId",concerns.get(i).getConcernId());
            map.put("userId",users.get(i).getUserId());
            map.put("img", users.get(i).getUserImage());
            map.put("name", users.get(i).getUserName());
            map.put("time", format.format(concerns.get(i).getCreateTime()));

            if (ismutual.get(i).equals(1))
            {
                map.put("isConcern",1);
            }
            else{
                map.put("isConcern",0);
            }
            eff.add(map);
        }

        pagination.setList(eff);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);

        pagination.setTotal((long)list.size());
        return pagination;
    }
}
