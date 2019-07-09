package com.itany.netClass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.GoldPointsMapper;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.GoldPointsExample;
import com.itany.netClass.exception.PointCountIsLessException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.GoldPointsService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoldPointsServiceImpl implements GoldPointsService {

    private GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");

    @Override
    public Map<String, Integer> findByUserId(int userId) {
        GoldPointsExample goldPointsExample = new GoldPointsExample();
        goldPointsExample.or().andUserIdEqualTo(userId);
        List<GoldPoints> list = goldPointsMapper.selectByExample(goldPointsExample);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("pointCount",0);
        map.put("goldCount",0);
        for (GoldPoints goldPoints : list
        ) {
            map.put("pointCount",map.get("pointCount")+goldPoints.getPointCount());
            map.put("goldCount",map.get("goldCount")+goldPoints.getGoldCount());
        }

        return map;
    }

    @Override
    public PageInfo<GoldPoints> findgoldPoints(int page, int pageSize, int userId) {
        GoldPointsExample example = new GoldPointsExample();
        example.or().andUserIdEqualTo(userId);
        PageHelper.startPage(page, pageSize);
        List<GoldPoints> list = goldPointsMapper.selectByExample(example);
        PageInfo<GoldPoints> pageInfo = new PageInfo<GoldPoints>(list);
        return pageInfo;
    }

    @Override
    public void changeMoney(int changeNum, int userId) throws PointCountIsLessException {
    	Map<String, Integer> map = findByUserId(userId);
    	if(map.get("pointCount")<changeNum){
    		throw new PointCountIsLessException("您的积分不够");
    	}
        GoldPoints points = new GoldPoints();
        points.setUserId(userId);
        points.setInfo("兑换金币消耗"+changeNum/10*10+"积分");
        points.setPointCount(-changeNum/10*10);
        points.setGoldCount(0);
        points.setCreateDate(new Date());
        goldPointsMapper.insertSelective(points);
        GoldPoints gold = new GoldPoints();
        gold.setUserId(userId);
        gold.setInfo("积分兑换"+changeNum/10+"金币");
        gold.setPointCount(0);
        gold.setGoldCount(changeNum/10);
        gold.setCreateDate(new Date());
        goldPointsMapper.insertSelective(gold);
    }
}


