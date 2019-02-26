/**
 * @filename projectTeamController.java
 * @author Administrator
 * @date 2018��9��7�� ����11:20:56
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.TeamMember;
import com.byzx.auth.service.ProjectTeamService;
import com.byzx.auth.utils.PageBean;

@Controller
@RequestMapping("/projectTeam")
public class projectTeamController {
	@Autowired ProjectTeamService projectTeamService;
	/**
	 * 
	 * @comment  ��ѯ��Ŀ��
	 * @param request
	 * @param projectTeam
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/list")
	public ModelAndView getAllProjects(HttpServletRequest request,
	HttpSession session,		ProjectTeam projectTeam) {
		//��ѯ���е���Ŀ����session
		List<ProjectInfo> list = projectTeamService.findAllProjects();		
		System.out.println(list.toString());
		session.setAttribute("allproject", list);		
		PageBean page = projectTeamService.findProjectTeams(request, projectTeam);
		// �����ݴ�����ʾ����
		ModelAndView view = new ModelAndView("project-team");
		view.addObject("page", page);
		return view;
	}
	/**
	 * 
	 * @comment ��ѯ��Ŀ���Ա
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findTeamMembers")
	public ModelAndView findTeamMembers(HttpServletRequest request,
			TeamMember teamMember) {
		
		PageBean page = projectTeamService.findTeamMembers(request, teamMember);
		// �����ݴ�����ʾ����
		ModelAndView view = new ModelAndView("team-member");
		view.addObject("page", page);
		
		
		return view;
	}
	/**
	 * 
	 * @comment �Զ���ȫ��Ŀ�鸺����
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoCompleteProjTeamChief")
	@ResponseBody
	public JSONObject findAutoCompleteProjTeamChief(HttpServletRequest request) {
		String name=request.getParameter("Name");
		String names= projectTeamService.findAutoCompleteProjTeamChief(name);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// ���������ǰ��
		return json;
	}
	
	/**
	 * 
	 * @comment �½���Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/insertprojectTeam")
	@ResponseBody
	public JSONObject insertprojectTeam(ProjectTeam projectTeam) {
	  List<ProjectTeam> list = projectTeamService.findProjectTeamCode(projectTeam);
	  JSONObject json = new JSONObject();		
	  if(list.size()>0){
		  json.put("msg", "1");
	  }else{
		int a= projectTeamService.insertProjectTeam(projectTeam);
		if(a>0){
			json.put("msg", "2");
		}
	  }
		// ���������ǰ��
		return json;
	}
	
	/**
	 * 
	 * @comment �Զ���ȫ��Ŀ���Ա
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/findAutoCompleteProjTeamUserName")
	@ResponseBody
	public JSONObject findAutoCompleteProjTeamUserName(TeamMember teamMember) {
		System.out.println(teamMember.getTeamName());
		System.out.println(teamMember.getUserName());
		String names= projectTeamService.findAutoCompleteProjTeamUserName(teamMember);
		JSONObject json = new JSONObject();		

			json.put("msg", names);
		
		// ���������ǰ��
		return json;
	}
	
	/**
	 * 
	 * @comment �½���Ŀ���Ա
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/insertProjectTeamMember")
	@ResponseBody
	public JSONObject insertProjectTeamMember(TeamMember teamMember) {
	 int a= projectTeamService.insertProjectTeamMember(teamMember);
	  JSONObject json = new JSONObject();		
	 
		if(a>0){
			json.put("msg", "2");
		}
	  
		// ���������ǰ��
		return json;
	}
	/**
	 * 
	 * @comment ��ѯ��������������Ŀ����������Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/allotPeoples")
	@ResponseBody
	public JSONObject allotPeoples(TeamMember teamMember) {
	  List<ProjectTeam> list = projectTeamService.findAllotPeople(teamMember);
	  JSONObject json = new JSONObject();		
	  //��ѯ�����е�����
	  int a = projectTeamService.findJudgeTask(teamMember);
	  Integer [] shu1 = new Integer[list.size()];
	  String [] shu2 = new String[list.size()];
	  for (int i = 0; i < list.size(); i++) {
		shu1[i]=list.get(i).getTeamId();
		shu2[i]=list.get(i).getTeamName();
	}
		
			json.put("shu1", shu1);
			json.put("shu2", shu2);
			json.put("msg", a);
	  
		// ���������ǰ��
		return json;
	}
	
	/**
	 * 
	 * @comment ������Ŀ�飬��Ա����
	 * @return 
	 * @version 1.0
	 */
	@RequestMapping("/updateallotPeoples")
	@ResponseBody
	public JSONObject updateallotPeoples(HttpServletRequest request) {
	  int a= projectTeamService.updateProjectTeam(request);
	  JSONObject json = new JSONObject();			 
		if(a>0){
			json.put("msg", "2");
		}
	  
		// ���������ǰ��
		return json;
	}
}

