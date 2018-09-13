package com.zust.writeme.service.articleServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zust.writeme.common.util.Pagination;
import com.zust.writeme.dao.ArticleMapper;
import com.zust.writeme.model.Article;
import com.zust.writeme.service.articleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public int addArticle(String title, String content, String preview, String coverImg, int corpusId, int userId, int status) {
        Article article = new Article();
        article.setTitle(title);
        article.setArticleContent(content);
        article.setArticlePreview(preview);
        article.setCoverImg(coverImg);
        article.setCorpusId(corpusId);
        article.setUserId(userId);
        article.setStatus(status);
        article.setCreateTime(new Date());
        return articleMapper.insertSelective(article);
    }

    @Override
    public Article getArticleById(int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        return articleMapper.selectByPrimaryKey(article);
    }

    @Override
    public Pagination<Article> getArticleListByTitleName(String title, int status, int pageNum, int pageSize) {
        Pagination<Article> pagination = new Pagination<>();

        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title", "%" + title + "%");
        criteria.andEqualTo("status", status);
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.selectByExample(example);

        pagination.setList(articleList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) articleList).getTotal());
        return pagination;
    }

    @Override
    public int deleteArticleById(int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        article.setStatus(9);
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateByPrimaryKey(article);
    }

    @Override
    public int updateArticle(int articleId, String title, String content, int corpusId) {
        Article article = new Article();
        article.setArticleId(articleId);
        article.setTitle(title);
        article.setArticleContent(content);
        article.setCorpusId(corpusId);
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public Pagination<Article> getArticleListByUserId(int userId, int status, int pageNum, int pageSize) {
        Pagination<Article> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.getArticleListByUserId(userId, status);

        pagination.setList(articleList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) articleList).getTotal());
        return pagination;
    }

    @Override
    public int getUserArticleCount(int userId) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return articleMapper.selectCountByExample(example);
    }

    @Override
    public Pagination<Article> getArticleList(int pageNum, int pageSize) {
        Pagination<Article> pagination = new Pagination<>();

        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.selectAll();

        pagination.setList(articleList);
        pagination.setPageNum((long) pageNum);
        pagination.setPageSize((long) pageSize);
        pagination.setTotal(((Page) articleList).getTotal());
        return pagination;
    }

    @Override
    public int articleManage(int articleId, int status) {
        Article article = new Article();
        article.setArticleId(articleId);
        article.setStatus(status);
        return articleMapper.updateByPrimaryKeySelective(article);
    }
}
