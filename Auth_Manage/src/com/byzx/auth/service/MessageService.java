/**
 * @filename MessageService.java
 * @author ldwzz
 * @date 2018年9月4日 上午10:54:15
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.Message;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.bean.UserMessage;

/**
 *@comment 站内信业务层接口
 *@author ldwzz
 *@date 2018年9月4日 上午10:54:40
 *@modifyUser ldwzz
 *@modifyDate 2018年9月4日 上午10:54:40
 *@modifyDesc  TODO
 *@version TODO
 */
public interface MessageService {

	/**
	 * @comment 查询我的站内信
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public List<Message> findMyMessage(HashMap<Object, Object> map);

	/**
	 * @comment 查询我的站内信条数
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int getMyMessageNum(Message message);

	/**
	 * @comment 查询已发送站内信条数
	 * @param user
	 * @return
	 * @version 1.0
	 */
	public int getMessageNum(Message message);

	/**
	 * @comment 查询已发送站内信
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public List<Message> findMessage(HashMap<Object, Object> map);

	/**
	 * @comment 根据userIds查询用户名
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	public String findUserName(String userId);

	/**
	 * @comment 查询所有用户
	 * @return
	 * @version 1.0
	 */
	public List<UserInfo> findUserInfo(UserInfo userInfo);

	/**
	 * @comment 插入站内信内容
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public int insertMessage(Message message);

	/**
	 * @comment 插入站内信关系
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public int insertUserMessage(Message message);

	/**
	 * @comment 查询所有部门
	 * @return
	 * @version 1.0
	 */
	public List<UserGroupInfo> findUserGroup();

	/**
	 * @comment 根据groupId查询部门名
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	public String findGroupName(String groupId);

	/**
	 * @comment 根据groupId查询拥有用户
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	public String findUserIdsByGroupId(HashMap<Object, Object> map);

	/**
	 * @comment 全部标为已读
	 * @param toUser
	 * @return
	 * @version 1.0
	 */
	public int updateMyMessageState(UserMessage userMessage);

	/**
	 * @comment 根据userName模糊查询用户名
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	public String findUserNameByName(UserInfo user);

	/**
	 * @comment 根据userName查询userId
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	public Integer findUserIdByName(String userName);

	/**
	 * @comment 查询所有项目
	 * @return
	 * @version 1.0
	 */
	public List<ProjectInfo> findAllProject();

	/**
	 * @comment 查询项目组
	 * @param project
	 * @return
	 * @version 1.0
	 */
	public List<ProjectTeam> findProjectTeamByProjId(ProjectInfo project);

	/**
	 * @comment 根据teamId查询项目组名
	 * @param team
	 * @return
	 * @version 1.0
	 */
	public String findTeamNameById(ProjectTeam team);

	/**
	 * @comment 根据projId查询项目名
	 * @param proj
	 * @return
	 * @version 1.0
	 */
	public String findProjNameById(ProjectInfo proj);

	/**
	 * @comment 根据teamId查询拥有用户
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public String findUserIdsByTeamId(HashMap<Object, Object> map);

	/**
	 * @comment 根据ProjId查询拥有用户
	 * @param map
	 * @return
	 * @version 1.0
	 */
	public String findUserIdsByProjId(HashMap<Object, Object> map);

	/**
	 * @comment 查询站内信详情
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	public Message findMyMessageDetail(UserMessage userMessage);

	/**
	 * @comment 查询所有收件人
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	public String findToUserNames(Integer msgId);

	/**
	 * @comment 查询站内信父邮件
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	public Message findMyMessageParent(UserMessage userMessage);

	/**
	 * @comment 查询要回复的人
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public String findReplyUserNames(Message message);

	/**
	 * @comment 查询要回复的人Ids
	 * @param message
	 * @return
	 * @version 1.0
	 */
	public String findReplyUserIds(Message message);
	
	/**
	 * @comment 查询未读邮件
	 * @return
	 * @version 1.0
	 */
	public Integer findUnreadMail(UserInfo userInfo);

}
