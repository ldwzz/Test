package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byzx.auth.bean.Recycle;
import com.byzx.auth.dao.RecycleDao;

@Service
public class RecycleServiceImpl implements RecycleService {

	@Autowired
	RecycleDao recycleDao;

	/**
	 * @comment 根据不同人登录进来查询回收站内对应的所有信息
	 * @param recycle
	 * @return List<Recycle>
	 * @version 1.0
	 */
	@Override
	public List<Recycle> findRecycle(HashMap map) {
		// TODO Auto-generated method stub
		return recycleDao.findRecycle(map);
	}

	/**
	 * @comment 根据不同的人登录进来查询自己对应的信息总条数
	 * @param recycle
	 * @return int
	 * @version 1.0
	 */
	@Override
	public int findRecycleSum(HashMap map) {
		// TODO Auto-generated method stub
		return recycleDao.findRecycleSum(map);
	}

	/**
	 * @comment 根据recyId删除对应的数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int delRecycle(Recycle recycle) {
		// TODO Auto-generated method stub
		return recycleDao.delRecycle(recycle);
	}

	/**
	 * @comment 根据参数值插入数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int insertRecycle(Recycle recycle) {
		// TODO Auto-generated method stub
		return recycleDao.insertRecycle(recycle);
	}

	/**
	 * @comment 删除的同时我还要让你给我把数据插入到回收站里面
	 * @return
	 * @version 1.0
	 */
	@Transactional
	@Override
	public int delData(Recycle recycle) {
		int re = 0;
		re = recycleDao.delData(recycle);
		if (re > 0) {
			re = recycleDao.insertRecycle(recycle);
		}

		return re;
	}

	/**
	 * @comment 恢复数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int recoverData(Recycle recycle) {
		// TODO Auto-generated method stub
		return recycleDao.recoverData(recycle);
	}

	/**
	 * @comment 根据recyId查询对应的信息做恢复使用
	 * @param recycle
	 * @return List<Recycle>
	 * @version 1.0
	 */
	@Override
	public Recycle findRecyByRecyId(Recycle recycle) {
		// TODO Auto-generated method stub
		return recycleDao.findRecyByRecyId(recycle);
	}

	/**
	 * @comment 根据参数信息最终真正删除数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	@Override
	public int delEndData(Recycle recycle) {
		// TODO Auto-generated method stub
		return recycleDao.delEndData(recycle);
	}

}

/**
 * @filename RecycleServiceImpl.java
 * @author Administrator
 * @date 2018年9月2日 上午11:28:46
 * @version 1.0 Copyright (C) 2018
 */
