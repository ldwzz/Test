/**
 * @filename MessageServiceImpl.java
 * @author ldwzz
 * @date 2018年9月4日 上午11:01:04
 * @version 1.0
 * Copyright (C) 2018
 */
package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.Message;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.UserGroupInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.bean.UserMessage;
import com.byzx.auth.dao.MessageDao;

/**
 * @comment 站内信业务层实现类
 * @author ldwzz
 * @date 2018年9月4日 上午11:04:16
 * @modifyUser ldwzz
 * @modifyDate 2018年9月4日 上午11:04:16
 * @modifyDesc TODO
 * @version TODO
 */
@Service
public class MessageServiceImpl implements MessageService {

	// 注入站内信dao层接口
	@Autowired
	private MessageDao messageDao;

	/**
	 * @comment 查询我的站内信
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<Message> findMyMessage(HashMap<Object, Object> map) {

		return messageDao.findMyMessage(map);
	}

	/**
	 * @comment 查询我的站内信条数
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public int getMyMessageNum(Message message) {

		return messageDao.getMyMessageNum(message);
	}

	/**
	 * @comment 查询已发送站内信条数
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public int getMessageNum(Message message) {

		return messageDao.getMessageNum(message);
	}

	/**
	 * @comment 查询已发送站内信
	 * @param user
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<Message> findMessage(HashMap<Object, Object> map) {

		return messageDao.findMessage(map);
	}

	/**
	 * @comment 根据userIds查询用户名
	 * @param userIds
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserName(String userId) {

		return messageDao.findUserName(userId);
	}

	/**
	 * @comment 查询所有用户
	 * @param
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<UserInfo> findUserInfo(UserInfo userInfo) {

		return messageDao.findUserInfo(userInfo);
	}

	/**
	 * @comment 插入站内信内容
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public int insertMessage(Message message) {

		return messageDao.insertMessage(message);
	}

	/**
	 * @comment 插入站内信关系
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public int insertUserMessage(Message message) {

		return messageDao.insertUserMessage(message);
	}

	/**
	 * @comment 查询所有部门
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<UserGroupInfo> findUserGroup() {

		return messageDao.findUserGroup();
	}

	/**
	 * @comment 根据groupId查询部门名
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findGroupName(String groupId) {

		return messageDao.findGroupName(groupId);
	}

	/**
	 * @comment 根据groupId查询拥有用户
	 * @param groupId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserIdsByGroupId(HashMap<Object, Object> map) {

		return messageDao.findUserIdsByGroupId(map);
	}

	/**
	 * @comment 全部标为已读
	 * @param toUser
	 * @return
	 * @version 1.0
	 */
	@Override
	public int updateMyMessageState(UserMessage userMessage) {

		return messageDao.updateMyMessageState(userMessage);
	}

	/**
	 * @comment 根据userName模糊查询用户名
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserNameByName(UserInfo user) {

		return messageDao.findUserNameByName(user);
	}

	/**
	 * @comment 根据userName查询userId
	 * @param userName
	 * @return
	 * @version 1.0
	 */
	@Override
	public Integer findUserIdByName(String userName) {

		return messageDao.findUserIdByName(userName);
	}

	/**
	 * @comment 查询所有项目
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<ProjectInfo> findAllProject() {

		return messageDao.findAllProject();
	}

	/**
	 * @comment 查询项目组
	 * @param project
	 * @return
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findProjectTeamByProjId(ProjectInfo project) {

		return messageDao.findProjectTeamByProjId(project);
	}

	/**
	 * @comment 根据teamId查询项目组名
	 * @param team
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findTeamNameById(ProjectTeam team) {

		return messageDao.findTeamNameById(team);
	}

	/**
	 * @comment 根据projId查询项目名
	 * @param proj
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findProjNameById(ProjectInfo proj) {

		return messageDao.findProjNameById(proj);
	}

	/**
	 * @comment 根据teamId查询拥有用户
	 * @param map
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserIdsByTeamId(HashMap<Object, Object> map) {

		return messageDao.findUserIdsByTeamId(map);
	}

	/**
	 * @comment 根据ProjId查询拥有用户
	 * @param map
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findUserIdsByProjId(HashMap<Object, Object> map) {

		return messageDao.findUserIdsByProjId(map);
	}

	/**
	 * @comment 查询站内信详情
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	@Override
	public Message findMyMessageDetail(UserMessage userMessage) {

		return messageDao.findMyMessageDetail(userMessage);
	}

	/**
	 * @comment 查询所有收件人
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findToUserNames(Integer msgId) {

		return messageDao.findToUserNames(msgId);
	}

	
	/**
	 * @comment 查询站内信父邮件
	 * @param msgId
	 * @return
	 * @version 1.0
	 */
	@Override
	public Message findMyMessageParent(UserMessage userMessage) {
		
		return messageDao.findMyMessageParent(userMessage);
	}

	/**
	 * @comment 查询要回复的人
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findReplyUserNames(Message message) {
		
		return messageDao.findReplyUserNames(message);
	}

	/**
	 * @comment 查询要回复的人Ids
	 * @param message
	 * @return
	 * @version 1.0
	 */
	@Override
	public String findReplyUserIds(Message message) {
		
		return messageDao.findReplyUserIds(message);
	}

	/**
	 * @comment 查询未读邮件
	 * @return
	 * @version 1.0
	 */
	@Override
	public Integer findUnreadMail(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return messageDao.findUnreadMail(userInfo);
	}
}
