package com.zust.writeme.config;

import com.zust.writeme.model.Article;
import com.zust.writeme.model.ArticleClick;
import com.zust.writeme.model.Collect;

import java.io.*;
import java.util.List;

/**
 * @auther 叶连松
 * @date 2018/9/14 13:12
 */
public class CsvUtils {
    /**
     * 导出
     *
     * @param file     csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<ArticleClick> dataList) {
        boolean isSucess = false;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (ArticleClick data : dataList) {
                    bw.append(data.getUserId().toString()).append(",");
                    bw.append(data.getArticleId().toString()).append(",");
                    bw.append(data.getVal()).append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

}
