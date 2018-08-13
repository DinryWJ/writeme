package com.zust.writeme.service.CollectServiceImpl;

import com.zust.writeme.dao.CollectMapper;
import com.zust.writeme.model.ArticleClick;
import com.zust.writeme.model.Collect;
import com.zust.writeme.service.CollectService.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public int collect(int articleId, int userId) {

        Collect collect = new Collect();
        collect.setArticleId(articleId);
        collect.setUserId(userId);

        return collectMapper.insert(collect);
    }

    @Override
    public int nocollect(int articleId, int userId) {
        Example example = new Example(Collect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId",articleId);
        criteria.andEqualTo("userId",userId);

        return collectMapper.deleteByExample(example);
    }

    @Override
    public boolean isExist(int articleId, int userId) {
        Example example = new Example(Collect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId",articleId);
        criteria.andEqualTo("userId",userId);

        List<Collect> collectList = collectMapper.selectByExample(example);
        if (!collectList.isEmpty())
            return true;
        return false;
    }
}
