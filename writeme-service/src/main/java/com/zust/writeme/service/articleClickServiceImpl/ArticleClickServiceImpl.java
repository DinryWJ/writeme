package com.zust.writeme.service.articleClickServiceImpl;

import com.zust.writeme.dao.ArticleClickMapper;
import com.zust.writeme.model.ArticleClick;
import com.zust.writeme.model.Concern;
import com.zust.writeme.service.articleClickService.ArticleClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service("articleClickService")
public class ArticleClickServiceImpl implements ArticleClickService {
    @Autowired
    private ArticleClickMapper articleClickMapper;

    @Override
    public int like(int articleId, int userId) {
        ArticleClick articleClick = new ArticleClick();
        articleClick.setArticleId(articleId);
        articleClick.setUserId(userId);

        return articleClickMapper.insertSelective(articleClick);
    }

    @Override
    public int nolike(int articleId, int userId) {
        Example example = new Example(ArticleClick.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        criteria.andEqualTo("userId", userId);

        return articleClickMapper.deleteByExample(example);
    }

    @Override
    public boolean isExist(int articleId, int userId) {
        Example example = new Example(ArticleClick.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        criteria.andEqualTo("userId", userId);

        int count = articleClickMapper.selectCountByExample(example);
        return count > 0;
    }

    @Override
    public int getStarsCount(int articleId) {
        Example example = new Example(ArticleClick.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);

        int count = articleClickMapper.selectCountByExample(example);
        return count;
    }

    @Override
    public List getNoReadArticleClickList(int userId) {
        List<ArticleClick> list = articleClickMapper.getNoReadConcernLists(userId);
        return list;
    }
}
