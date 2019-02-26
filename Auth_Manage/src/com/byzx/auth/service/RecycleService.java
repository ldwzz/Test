package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.Recycle;

public interface RecycleService {
	/**
	 * @comment 根据不同人登录进来查询回收站内对应的所有信息
	 * @param recycle
	 * @return List<Recycle>
	 * @version 1.0
	 */
	public List<Recycle> findRecycle(HashMap map);

	/**
	 * @comment 根据不同的人登录进来查询自己对应的信息总条数
	 * @param recycle
	 * @return int
	 * @version 1.0
	 */
	public int findRecycleSum(HashMap map);

	/**
	 * @comment 根据recyId删除对应的数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	public int delRecycle(Recycle recycle);

	/**
	 * @comment 根据参数值插入数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	public int insertRecycle(Recycle recycle);

	/**
	 * @comment 根据参数删除（修改）相应的表的数据 （也就是去修改对应的表的字段的状态）--对外接口
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	public int delData(Recycle recycle);

	/**
	 * @comment 恢复数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	public int recoverData(Recycle recycle);

	/**
	 * @comment 根据recyId查询对应的信息做恢复使用
	 * @param recycle
	 * @return List<Recycle>
	 * @version 1.0
	 */
	public Recycle findRecyByRecyId(Recycle recycle);

	/**
	 * @comment 根据参数信息最终真正删除数据
	 * @param recycle
	 * @return 状态
	 * @version 1.0
	 */
	public int delEndData(Recycle recycle);

}

/**
 * @filename RecycleService.java
 * @author Administrator
 * @date 2018年9月2日 上午11:28:16
 * @version 1.0 Copyright (C) 2018
 */
