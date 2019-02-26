/**
 * @filename projectTeamDao.java
 * @author Administrator
 * @date 2018年9月7日 下午11:15:32
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.TeamMember;
import com.byzx.auth.bean.UserInfo;

public interface ProjectTeamDao {
	/**
	 * 
	 * @comment 查看所有项目组信息
	 * @param request
	 * @param projectInfo
	 * @return 所有项目集合
	 * @version 1.0
	 */
	public List<ProjectTeam> findAllProjectTeam(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment 查看项目组总数
	 * @param
	 * @return 项目总数
	 * @version 1.0
	 */
	public int findAllProjectTeamNum(ProjectTeam projectTeam);

	/**
	 * 
	 * @comment 项目经理查看所有项目组信息
	 * @param request
	 * @param projectInfo
	 * @return 所有项目集合
	 * @version 1.0
	 */
	public List<ProjectTeam> findSomeProjectTeam(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment 项目经理查看项目组总数
	 * @param
	 * @return 项目总数
	 * @version 1.0
	 */
	public int findSomeProjectTeamNum(HashMap<Object, Object> map);
	/**
	 * 
	 * @comment 项目组长查看所有项目组信息
	 * @param request
	 * @param 
	 * @return 所有项目集合
	 * @version 1.0
	 */
	public List<ProjectTeam> findOnlyProjectTeam(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment 项目组长查看项目组总数
	 * @param
	 * @return 项目总数
	 * @version 1.0
	 */
	public int findOnlyProjectTeamNum(ProjectTeam projectTeam);

	/**
	 * 
	 * @comment 查看所有项目人员
	 * @return
	 * @version 1.0
	 */
	public List<TeamMember> findAllTeamMember(HashMap<Object, Object> map);

	/**
	 * 
	 * @comment 查看项目组总数
	 * @return
	 * @version 1.0
	 */
	public int findAllTeamMemberNum(TeamMember teamMember);

	/**
	 * 
	 * @comment 项目组负责人自动补全
	 * @return
	 * @version 1.0
	 */
	public String findAutoCompleteProjTeamChief(String name);

	/**
	 * 
	 * @comment 新建项目组
	 * @return
	 * @version 1.0
	 */
	public int insertProjectTeam(ProjectTeam ProjectTeam);

	/**
	 * 
	 * @comment 项目组code唯一校验
	 * @return
	 * @version 1.0
	 */
	public List<ProjectTeam> findProjectTeamCode(ProjectTeam ProjectTeam);

	/**
	 * 
	 * @comment 查询所有的项目
	 * @return
	 * @version 1.0
	 */
	public List<ProjectInfo> findAllProjects();

	/**
	 * 
	 * @comment 项目组成员名称自动补全
	 * @return
	 * @version 1.0
	 */
	public String findAutoCompleteProjTeamUserName(TeamMember teamMember);

	/**
	 * 
	 * @comment 新建项目组成员
	 * @return
	 * @version 1.0
	 */
	public int insertProjectTeamMember(TeamMember teamMember);

	/**
	 * 
	 * @comment 查询除了自身所属项目组外其他项目组
	 * @return
	 * @version 1.0
	 */
	public List<ProjectTeam> findAllotPeople(TeamMember teamMember);

	/**
	 * 
	 * @comment 判断（任务进度是否>0,实际消耗时间>0）
	 * @return
	 * @version 1.0
	 */
	public int findJudgeTask(TeamMember teamMember);

	/**
	 * 
	 * @comment 执行人重置为空，进行中任务修改为已暂停
	 * @return
	 * @version 1.0
	 */
	public int updateProjTask(String userName);

	/**
	 * 
	 * @comment 分配用户组
	 * @return
	 * @version 1.0
	 */
	public int updateProjectTeam(TeamMember teamMember);
}
