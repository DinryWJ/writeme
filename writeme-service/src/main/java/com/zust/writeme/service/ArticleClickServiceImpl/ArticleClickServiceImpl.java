package com.zust.writeme.service.ArticleClickServiceImpl;

import com.zust.writeme.dao.ArticleClickMapper;
import com.zust.writeme.model.ArticleClick;
import com.zust.writeme.service.ArticleClickService.ArticleClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class ArticleClickServiceImpl implements ArticleClickService {
    @Autowired
    private ArticleClickMapper articleClickMapper;
    @Override
    public int like(int articleId, int userId) {

        ArticleClick articleClick = new ArticleClick();
        articleClick.setArticleId(articleId);
        articleClick.setUserId(userId);

        return articleClickMapper.insert(articleClick);
    }

    @Override
    public int nolike(int articleId, int userId) {

        Example example = new Example(ArticleClick.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId",articleId);
        criteria.andEqualTo("userId",userId);

        return articleClickMapper.deleteByExample(example);
    }

    @Override
    public boolean isExist(int articleId, int userId) {
        Example example = new Example(ArticleClick.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId",articleId);
        criteria.andEqualTo("userId",userId);

        List<ArticleClick> articleList = articleClickMapper.selectByExample(example);
        if (!articleList.isEmpty())
            return true;
        return false;
    }

}
