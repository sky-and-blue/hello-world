package com.itany.netClass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.exception.PointCountIsLessException;

import java.util.Map;

public interface GoldPointsService {
    /**
     *查询积分信息
     * @param userId
     * @return
     */
    Map<String,Integer> findByUserId(int userId);

    /**
     * 分页查询积分信息
     * @param page
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<GoldPoints> findgoldPoints(int page, int pageSize, int userId);

    /**
     * 积分兑换
     * @param changeNum
     * @param userId
     * @throws PointCountIsLessException 
     */
    void changeMoney(int changeNum, int userId) throws PointCountIsLessException;
}
