package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.BugInfo;
import com.byzx.auth.bean.BugReplay;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.UserInfo;

public interface BugInfoDao {
	public List<ProjectInfo> findProjectManage(UserInfo user);

	public List<ProjectInfo> findProjectadmin(UserInfo user);

	public List<ProjectInfo> findProjectStaff(UserInfo user);

	/**
	 * Bug
	 */
	public List<BugInfo> findBugByCondition(HashMap map);

	/**
	 * Bug
	 */
	public int findBugNumByCondition(BugInfo buginfo);

	public int insertBug(BugInfo buginfo);

	/**
	 * 修改BUG
	 * 
	 * @return
	 */
	public int updateBug(BugInfo buginfo);

	/**
	 * 分配BUG
	 */
	public int updateAssignBug(BugInfo buginfo);

	/**
	 * 
	 */
	public int updateBugState(BugInfo buginfo);

	/**
	 * 
	 * @return
	 */
	public int deleteBug();

	/**
	 * 检查BUG编号是否存在
	 */
	public int findBugNumExist(BugInfo buginfo);

	/**
	 * 查询项目成员
	 */
	public List<UserInfo> finduserByProj(BugInfo buginfo);

	/**
	 * 查询BUG详情
	 * 
	 * @param buginfo
	 * @return
	 */
	public BugInfo findBugDetail(BugInfo buginfo);

	/**
	 * 添加BUg的评论
	 */
	public int insertBugRemake(BugReplay play);

	/**
	 * 根据BugID找BUG
	 */
	public List<BugReplay> findBugRemake(HashMap map);

	public int deleteReply(BugReplay play);

}
