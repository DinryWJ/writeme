package com.zust.writeme.dao;

import com.zust.writeme.config.MyMapper;
import com.zust.writeme.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
}