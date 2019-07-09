package com.itany.netClass.dao;

import com.itany.netClass.entity.UserResource;
import com.itany.netClass.entity.UserResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserResourceMapper {
    int countByExample(UserResourceExample example);

    int deleteByExample(UserResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserResource record);

    int insertSelective(UserResource record);

    List<UserResource> selectByExample(UserResourceExample example);

    UserResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserResource record, @Param("example") UserResourceExample example);

    int updateByExample(@Param("record") UserResource record, @Param("example") UserResourceExample example);

    int updateByPrimaryKeySelective(UserResource record);

    int updateByPrimaryKey(UserResource record);
}