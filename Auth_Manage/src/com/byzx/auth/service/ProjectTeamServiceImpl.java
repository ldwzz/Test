/**
 * @filename projectTeamServiceImpl.java
 * @author Administrator
 * @date 2018年9月7日 下午11:18:53
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.TeamMember;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.ProjectTeamDao;
import com.byzx.auth.utils.PageBean;

@Service ("projectTeamService")
public class ProjectTeamServiceImpl implements ProjectTeamService {
	 @Autowired ProjectTeamDao projectTeamDao;
    /**
     * 
     * @comment 分页查询项目组信息
     * @return 
     * @version 1.0
     */
	@Override
	public PageBean findProjectTeams(HttpServletRequest request,
			ProjectTeam projectTeam) {
		
		String codes= (String) request.getSession().getAttribute("Rolecodes");
		  UserInfo userInfo=(UserInfo) request.getSession().getAttribute("userInfo");
		  
		  
		  
		  
		  
		  
		            // 初始化查出用户条数变量
					int projectNum = 0;				
					// 对当前页进行拿值、处理
					int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
							.parseInt(request.getParameter("currNo"));
					// 对每页显示数量处理
					int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
							.parseInt(request.getParameter("pageNum"));
					StringBuffer buffer = new StringBuffer();
					// 页面条件查询参数拼接
					if (StringUtils.isNotBlank(projectTeam.getTeamCode())) {
						buffer.append("&teamCode=").append(projectTeam.getTeamCode());
					}
					if (StringUtils.isNotBlank(projectTeam.getTeamName())) {
						buffer.append("&teamName=").append(projectTeam.getTeamName());
					}
					if (StringUtils.isNotBlank(projectTeam.getProjName())) {
						buffer.append("&projName=").append(projectTeam.getProjName());
					}
					if (StringUtils.isNotBlank(projectTeam.getUserName())) {
						buffer.append("&userName=").append(projectTeam.getUserName());
					}
					
					// 设置每页显示数量，当前页码
					PageBean page = new PageBean(pageNum, currNo);
					HashMap<Object, Object> map = new HashMap<Object, Object>();
					map.put("page", page);
					map.put("projectTeam", projectTeam);
					map.put("userInfo", userInfo);
					 List<ProjectTeam> list=new ArrayList<ProjectTeam>();
                    if(codes.indexOf("admin")!=-1){
                    	// 查出当前页显示的数据
  					  list = projectTeamDao.findAllProjectTeam(map);
  					projectNum = projectTeamDao.findAllProjectTeamNum(projectTeam);	
					}
                    else if(codes.indexOf("project_manager")!=-1){
                    	// 查出当前页显示的数据
  					  list = projectTeamDao.findSomeProjectTeam(map);
  					projectNum = projectTeamDao.findSomeProjectTeamNum(map);	
					}else if(codes.indexOf("project_leader")!=-1){
						projectTeam.setProjChief(userInfo.getUserId());
						list = projectTeamDao.findOnlyProjectTeam(map);
	  					projectNum = projectTeamDao.findOnlyProjectTeamNum(projectTeam);	
					}
					
					
					// 将数据封装至page对象中
					page = new PageBean(pageNum, projectNum, currNo, list,
							"/projectTeam/list.action", buffer.toString());
			return page;
		
	}
	/**
	 * 
	 * @comment  查询项目组成员信息
	 * @return 
	 * @version 1.0
	 */
	@Override
	public PageBean findTeamMembers(HttpServletRequest request,
			TeamMember teamMember) {
		  int teamId=0;
		  String  teamName=null; 
			teamId=Integer.parseInt(request.getParameter("teamId"));
			teamName=request.getParameter("teamName");

			request.getSession().setAttribute("teamId", teamId);
			request.getSession().setAttribute("teamName", teamName);
		 
		   // 初始化查出用户条数变量
			int projectNum = 0;				
			// 对当前页进行拿值、处理
			int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
					.parseInt(request.getParameter("currNo"));
			// 对每页显示数量处理
			int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
					.parseInt(request.getParameter("pageNum"));
			StringBuffer buffer = new StringBuffer();
			// 页面条件查询参数拼接
			if (StringUtils.isNotBlank(teamMember.getUserName())) {
				buffer.append("&userName=").append(teamMember.getUserName());
			}
			if(teamId>0){
				buffer.append("&teamId=").append(teamId);
			}
			// 设置每页显示数量，当前页码
			PageBean page = new PageBean(pageNum, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("teamMember", teamMember);
			// 查出当前页显示的数据

			 List<TeamMember> list = projectTeamDao.findAllTeamMember(map);

			 //map.put("TeamName", list.get(1).getTeamName());
			projectNum = projectTeamDao.findAllTeamMemberNum(teamMember);
			// 将数据封装至page对象中
			page = new PageBean(pageNum, projectNum, currNo, list,
					"/projectTeam/findTeamMembers.action", buffer.toString());
	return page;		 
	
	}
	/**
	   * 
	   * @comment 项目负责人自动补全
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteProjTeamChief(String name) {
		
		return projectTeamDao.findAutoCompleteProjTeamChief(name);
	}
	/**
	 * 
	 * @comment 新建项目组
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int insertProjectTeam(ProjectTeam ProjectTeam) {
		
		return projectTeamDao.insertProjectTeam(ProjectTeam);
	}
	/**
	 * 
	 * @comment 项目组code唯一校验
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findProjectTeamCode(ProjectTeam ProjectTeam) {
		
		return projectTeamDao.findProjectTeamCode(ProjectTeam);
	}
	/**
	 * 
	 * @comment 查询所有的项目
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectInfo> findAllProjects() {
		
		return projectTeamDao.findAllProjects();
	}
	/**
	 * 
	 * @comment 项目组成员名称自动补全
	 * @return 
	 * @version 1.0
	 */
	@Override
	public String findAutoCompleteProjTeamUserName(TeamMember teamMember) {
		
		return projectTeamDao.findAutoCompleteProjTeamUserName(teamMember);
	}
	/**
	 * 
	 * @comment 新建项目组成员
	 * @return 
	 * @version 1.0
	 */	
	@Override
	public int insertProjectTeamMember(TeamMember teamMember) {
		
		return projectTeamDao.insertProjectTeamMember(teamMember);
	}
	/**
	 * 
	 * @comment 查询除了自身所属项目组外其他项目组
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findAllotPeople(TeamMember teamMember) {
		
		return projectTeamDao.findAllotPeople(teamMember);
	}
	/**
	 * 
	 * @comment 判断（任务进度是否>0,实际消耗时间>0）
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int findJudgeTask(TeamMember teamMember) {
		
		return projectTeamDao.findJudgeTask(teamMember);
	}
	/**
	 * 
	 * @comment 分配用户组
	 * @return 
	 * @version 1.0
	 */
	@Transactional
	@Override
	public int updateProjectTeam(HttpServletRequest request) {
		 int teamId=Integer.parseInt(request.getParameter("teamId")) ;
		 String userName= request.getParameter("userName");
		 //先执行人重置为空，进行中任务修改为已暂停
		 int a=projectTeamDao.updateProjTask(userName);
		 TeamMember teamMember=new TeamMember();
		 teamMember.setTeamId(teamId);
		 teamMember.setUserName(userName);
		 //再进行调配
		 int b=projectTeamDao.updateProjectTeam(teamMember);
		 
		return a+b;
	}
	

}

