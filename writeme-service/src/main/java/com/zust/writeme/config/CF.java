package com.zust.writeme.config;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 叶连松
 * @date 2018/9/15 18:43
 */
public class CF {
    //最大文章数
    final static int MAX_ITEM=10;
    //文章推荐
    public List<Integer> ItemCF(long uid) throws Exception{
        //数据存放地址
//        String file = "G:\\shiyan\\writeme\\writeme-service\\datafile\\collect.csv";
        //构造数据模型，File-based
        DataModel model = new FileDataModel(ResourceUtils.getFile("classpath:datafile/collect.csv"));
        //计算内容相似度
        ItemSimilarity item = new EuclideanDistanceSimilarity(model);
        //构造推荐引擎
        Recommender r = new GenericItemBasedRecommender(model, item);
        LongPrimitiveIterator iter = model.getUserIDs();
        List<Integer> l=new ArrayList<Integer>();
        List<RecommendedItem> list = r.recommend(uid, MAX_ITEM);
       // System.out.printf("uid:%s", uid);
        for (RecommendedItem ritem : list) {
            l.add((int)ritem.getItemID());
           // System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
            }
           // System.out.println();
        return l;
    }
}
