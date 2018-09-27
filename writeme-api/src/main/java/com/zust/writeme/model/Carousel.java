package com.zust.writeme.model;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Author: 吴佳杰
 * @Date: 2018/9/27 11:01
 * @Description: 轮播图
 */
public class Carousel {
    @Id
    private Integer id;

    private String tag;

    private String address;

    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
