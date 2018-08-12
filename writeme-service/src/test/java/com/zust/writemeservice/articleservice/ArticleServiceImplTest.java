package com.zust.writemeservice.articleservice;

import com.zust.writeme.model.Article;
import com.zust.writeme.service.articleservice.ArticleService;
import com.zust.writemeservice.WritemeServiceApplicationTests;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleServiceImplTest extends WritemeServiceApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Test
    @Ignore
    public void addArticle(){
        int eff = articleService.addArticle("title","content",1,1);
        Assert.assertEquals(1,eff);
        eff = articleService.addArticle("title2","content2",1,1);
        Assert.assertEquals(1,eff);
        eff = articleService.addArticle("title3","content3",1,1);
        Assert.assertEquals(1,eff);
        eff = articleService.addArticle("t11le4","content4",1,1);
        Assert.assertEquals(1,eff);
    }

    @Test
    @Ignore
    public void getArticleById(){
        Article article = articleService.getArticleById(3);
        Assert.assertEquals("title",article.getTitle());
    }

    @Test
    @Ignore
    public void getArticleListByTitleName(){
       //List<Article> articleList = articleService.getArticleListByTitleName("title",1,2);
      // Assert.assertEquals(2,articleList.size());
    }

    @Test
    @Ignore
    public void updateArticle(){
        int eff = articleService.updateArticle(5,"midify","modify",1);
        Assert.assertEquals(1,eff);
    }

    @Test
    @Ignore
    public void deleteArticle(){
        int eff = articleService.deleteArticleById(2);
        Assert.assertEquals(1,eff);
    }
}
