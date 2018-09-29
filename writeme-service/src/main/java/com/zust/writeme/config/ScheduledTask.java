package com.zust.writeme.config;

import com.zust.writeme.dao.ArticleClickMapper;
import com.zust.writeme.dao.CollectMapper;
import com.zust.writeme.model.ArticleClick;
import com.zust.writeme.model.Collect;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @auther 叶连松
 * @date 2018/9/14 12:38
 */
@Component
public class ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private ArticleClickMapper articleClickMapper;

    /**
     * 每隔5秒执行, 单位：ms。
     */
 //   @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//
//        System.out.println("当前时间: " + dateFormat.format(new Date()));
//        logger.info("打印当前时间: {}.", dateFormat.format(new Date()));
//    }
    //每天凌晨1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void testMyBatis() {
        System.out.println("每天凌晨1点开始执行");

        try {
            List<ArticleClick> list = articleClickMapper.selectAll();

            //定时任务可以做耗时操作，包括做生成数据库报表、文件IO等等需要定时执行的逻辑
            if (list != null) {
                //导出csv文件
                File ofile = ResourceUtils.getFile("classpath:datafile/collect.csv");
                CsvUtils.exportCsv(ofile, list);

            } else {
                System.out.println("什么事都不用做，等待下次再说吧");
            }
        } catch (Exception ex) {

        }
    }

}
