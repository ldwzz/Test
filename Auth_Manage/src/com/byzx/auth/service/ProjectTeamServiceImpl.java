/**
 * @filename projectTeamServiceImpl.java
 * @author Administrator
 * @date 2018��9��7�� ����11:18:53
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
     * @comment ��ҳ��ѯ��Ŀ����Ϣ
     * @return 
     * @version 1.0
     */
	@Override
	public PageBean findProjectTeams(HttpServletRequest request,
			ProjectTeam projectTeam) {
		
		String codes= (String) request.getSession().getAttribute("Rolecodes");
		  UserInfo userInfo=(UserInfo) request.getSession().getAttribute("userInfo");
		  
		  
		  
		  
		  
		  
		            // ��ʼ������û���������
					int projectNum = 0;				
					// �Ե�ǰҳ������ֵ������
					int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
							.parseInt(request.getParameter("currNo"));
					// ��ÿҳ��ʾ��������
					int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
							.parseInt(request.getParameter("pageNum"));
					StringBuffer buffer = new StringBuffer();
					// ҳ��������ѯ����ƴ��
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
					
					// ����ÿҳ��ʾ��������ǰҳ��
					PageBean page = new PageBean(pageNum, currNo);
					HashMap<Object, Object> map = new HashMap<Object, Object>();
					map.put("page", page);
					map.put("projectTeam", projectTeam);
					map.put("userInfo", userInfo);
					 List<ProjectTeam> list=new ArrayList<ProjectTeam>();
                    if(codes.indexOf("admin")!=-1){
                    	// �����ǰҳ��ʾ������
  					  list = projectTeamDao.findAllProjectTeam(map);
  					projectNum = projectTeamDao.findAllProjectTeamNum(projectTeam);	
					}
                    else if(codes.indexOf("project_manager")!=-1){
                    	// �����ǰҳ��ʾ������
  					  list = projectTeamDao.findSomeProjectTeam(map);
  					projectNum = projectTeamDao.findSomeProjectTeamNum(map);	
					}else if(codes.indexOf("project_leader")!=-1){
						projectTeam.setProjChief(userInfo.getUserId());
						list = projectTeamDao.findOnlyProjectTeam(map);
	  					projectNum = projectTeamDao.findOnlyProjectTeamNum(projectTeam);	
					}
					
					
					// �����ݷ�װ��page������
					page = new PageBean(pageNum, projectNum, currNo, list,
							"/projectTeam/list.action", buffer.toString());
			return page;
		
	}
	/**
	 * 
	 * @comment  ��ѯ��Ŀ���Ա��Ϣ
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
		 
		   // ��ʼ������û���������
			int projectNum = 0;				
			// �Ե�ǰҳ������ֵ������
			int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
					.parseInt(request.getParameter("currNo"));
			// ��ÿҳ��ʾ��������
			int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
					.parseInt(request.getParameter("pageNum"));
			StringBuffer buffer = new StringBuffer();
			// ҳ��������ѯ����ƴ��
			if (StringUtils.isNotBlank(teamMember.getUserName())) {
				buffer.append("&userName=").append(teamMember.getUserName());
			}
			if(teamId>0){
				buffer.append("&teamId=").append(teamId);
			}
			// ����ÿҳ��ʾ��������ǰҳ��
			PageBean page = new PageBean(pageNum, currNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("page", page);
			map.put("teamMember", teamMember);
			// �����ǰҳ��ʾ������

			 List<TeamMember> list = projectTeamDao.findAllTeamMember(map);

			 //map.put("TeamName", list.get(1).getTeamName());
			projectNum = projectTeamDao.findAllTeamMemberNum(teamMember);
			// �����ݷ�װ��page������
			page = new PageBean(pageNum, projectNum, currNo, list,
					"/projectTeam/findTeamMembers.action", buffer.toString());
	return page;		 
	
	}
	/**
	   * 
	   * @comment ��Ŀ�������Զ���ȫ
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteProjTeamChief(String name) {
		
		return projectTeamDao.findAutoCompleteProjTeamChief(name);
	}
	/**
	 * 
	 * @comment �½���Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int insertProjectTeam(ProjectTeam ProjectTeam) {
		
		return projectTeamDao.insertProjectTeam(ProjectTeam);
	}
	/**
	 * 
	 * @comment ��Ŀ��codeΨһУ��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findProjectTeamCode(ProjectTeam ProjectTeam) {
		
		return projectTeamDao.findProjectTeamCode(ProjectTeam);
	}
	/**
	 * 
	 * @comment ��ѯ���е���Ŀ
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectInfo> findAllProjects() {
		
		return projectTeamDao.findAllProjects();
	}
	/**
	 * 
	 * @comment ��Ŀ���Ա�����Զ���ȫ
	 * @return 
	 * @version 1.0
	 */
	@Override
	public String findAutoCompleteProjTeamUserName(TeamMember teamMember) {
		
		return projectTeamDao.findAutoCompleteProjTeamUserName(teamMember);
	}
	/**
	 * 
	 * @comment �½���Ŀ���Ա
	 * @return 
	 * @version 1.0
	 */	
	@Override
	public int insertProjectTeamMember(TeamMember teamMember) {
		
		return projectTeamDao.insertProjectTeamMember(teamMember);
	}
	/**
	 * 
	 * @comment ��ѯ��������������Ŀ����������Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findAllotPeople(TeamMember teamMember) {
		
		return projectTeamDao.findAllotPeople(teamMember);
	}
	/**
	 * 
	 * @comment �жϣ���������Ƿ�>0,ʵ������ʱ��>0��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int findJudgeTask(TeamMember teamMember) {
		
		return projectTeamDao.findJudgeTask(teamMember);
	}
	/**
	 * 
	 * @comment �����û���
	 * @return 
	 * @version 1.0
	 */
	@Transactional
	@Override
	public int updateProjectTeam(HttpServletRequest request) {
		 int teamId=Integer.parseInt(request.getParameter("teamId")) ;
		 String userName= request.getParameter("userName");
		 //��ִ��������Ϊ�գ������������޸�Ϊ����ͣ
		 int a=projectTeamDao.updateProjTask(userName);
		 TeamMember teamMember=new TeamMember();
		 teamMember.setTeamId(teamId);
		 teamMember.setUserName(userName);
		 //�ٽ��е���
		 int b=projectTeamDao.updateProjectTeam(teamMember);
		 
		return a+b;
	}
	

}

