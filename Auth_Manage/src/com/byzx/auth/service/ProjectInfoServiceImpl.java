/**
 * @filename ProjectInfoServiceImpl.java
 * @author Administrator
 * @date 2018��9��4�� ����10:19:45
 * @version 1.0
 * Copyright (C) 2018 
 */
package com.byzx.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byzx.auth.bean.ProjectDynamic;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.ProjectTeam;
import com.byzx.auth.bean.ProjectVersion;
import com.byzx.auth.bean.ProjectWhiteList;
import com.byzx.auth.bean.SysClass;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.ProjectInfoDao;
import com.byzx.auth.utils.PageBean;


/**
 * 
 *@comment
 *@author Administrator
 *@date 2018��9��4�� ����10:19:57
 *@modifyUser Administrator
 *@modifyDate 2018��9��4�� ����10:19:57
 *@modifyDesc  TODO
 *@version TODO
 */
@Service ("projectInfoService")
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Autowired ProjectInfoDao projectInfoDao;
    /**
     * 
     * @comment ��ҳ��ѯ��Ŀ��Ϣ
     * @return  ��Ŀ��Ϣ
     * @version 1.0
     */
	@Override
	public PageBean findProjects(HttpServletRequest request,
			ProjectInfo projectInfo) {
		        // ��ʼ������û���������
				int projectNum = 0;				
				UserInfo user = (UserInfo) request.getSession()
						.getAttribute("userInfo");
				// �Ե�ǰҳ������ֵ������
				int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
						.parseInt(request.getParameter("currNo"));
				// ��ÿҳ��ʾ��������
				int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
						.parseInt(request.getParameter("pageNum"));
				StringBuffer buffer = new StringBuffer();
				// ҳ��������ѯ����ƴ��
				if (StringUtils.isNotBlank(projectInfo.getProjName())) {
					buffer.append("&projName=").append(projectInfo.getProjName());
				}
				if (StringUtils.isNotBlank(projectInfo.getProjNum())) {
					buffer.append("&projNum=").append(projectInfo.getProjNum());
				}
				if (StringUtils.isNotBlank(projectInfo.getProjState())) {
					buffer.append("&projState=").append(projectInfo.getProjState());
				}
				if (StringUtils.isNotBlank(projectInfo.getStartTime())) {
					buffer.append("&startTime=").append(projectInfo.getStartTime());
				}
				if (StringUtils.isNotBlank(projectInfo.getEndTime())) {
					buffer.append("&endTime=").append(projectInfo.getEndTime());
				}
				if (StringUtils.isNotBlank(projectInfo.getProjNames())) {
					buffer.append("&projNames=").append(projectInfo.getProjNames());
				}
				
				// ����ÿҳ��ʾ��������ǰҳ��
				PageBean page = new PageBean(pageNum, currNo);
				HashMap<Object, Object> map = new HashMap<Object, Object>();
				map.put("page", page);
				map.put("projectInfo", projectInfo);
				map.put("userId", user.getUserId());
				// ��session���ó���ǰ�û��Ľ�ɫcode
				String userRoleCode = (String) request.getSession()
						.getAttribute("Rolecodes");
				String pinjie="," + userRoleCode + ",";
				// �����ǰҳ��ʾ������
				List<ProjectInfo> list = null;
				if (userRoleCode != null) {
					// ����ǰ�û���ɫcodeΪ��������Ա����ѯ�����û���Ϣ
					if (pinjie.indexOf(",admin,") >= 0) {
						list = projectInfoDao.findAllProject(map);
						projectNum = projectInfoDao.findAllProjectNum(projectInfo);
					} else if (pinjie.indexOf(",project_manager,") >= 0) {
						list = projectInfoDao.findSomeProject(map);
						projectNum = projectInfoDao.findSomeProjectNum(map);
					} else if (pinjie.indexOf(",project_member,") >= 0
							|| pinjie.indexOf(",project_leader,") >= 0) {
						list = projectInfoDao.findOnlyProject(map);
						projectNum = projectInfoDao.findOnlyProjectNum(map);
					}
				}
				// �����ݷ�װ��page������
				page = new PageBean(pageNum, projectNum, currNo, list,
						"/project/list.action", buffer.toString());
		return page;
	}
    /**
     * 
     * @comment ��ѯ��Ŀ����
     * @return  ��Ŀ����
     * @version 1.0
     */
	@Override
	public ProjectInfo findProjectInfoByProjId(int projId) {
		
		return projectInfoDao.findProjectInfoByProjId(projId);
	}
	
    /**
     * 
     * @comment ��ѯ��Ŀ��̬��ǰ10����
     * @return 
     * @version 1.0
     */
	
	@Override
	public List<ProjectDynamic> findProjectDynamic(int projId) {		
		List<ProjectDynamic> ProjectDynamic = projectInfoDao.findProjectDynamic(projId); 
		   return ProjectDynamic;
	}
	  /**
	   * 
	   * @comment ��ѯ��̬(�ݹ�)
	   * @return 
	   * @version 1.0
	   */
	
	@Override
	public List<ProjectDynamic> findProjectDynamicByName( ProjectDynamic projectDynamic,List<ProjectDynamic> lists) {
		//��ѯ�ö�̬���ӻظ���̬
		List<ProjectDynamic> list = projectInfoDao.findProjectDynamicByName(projectDynamic);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=null){
				//���ӻظ���̬���뼯����
				lists.add(list.get(i));
				//�ݹ��ѯ
				findProjectDynamicByName(list.get(i),lists);
		}
			
		}
		
		return lists ;
	}

	/**
	 * 
	 * @comment �½���Ŀ�� ����̬(����)
	 * @return 
	 * @version 1.0
	 */
	@Transactional
	@Override
	public int insertProjectInfo(HttpServletRequest request,ProjectInfo projectInfo) {
		//�½���Ŀ
		int a=projectInfoDao.insertProjectInfo(projectInfo);
		//����̬������з�װ
		ProjectDynamic projectDynamic=new ProjectDynamic();
		projectDynamic.setProjId(projectInfo.getProjId());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		projectDynamic.setUserName(userInfo.getUserName());
		projectDynamic.setDynamicDesc(userInfo.getUserName()+"������"+projectInfo.getProjName());
		//������̬
		int b=projectInfoDao.insertProjectDynamic(projectDynamic);
		return a+b;
	}
	/**
	   * 
	   * @comment ������Ŀ��̬
	   * @return  1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	@Override
	public int insertProjectDynamic(ProjectDynamic projectDynamic) {
		
		return projectInfoDao.insertProjectDynamic(projectDynamic);
	}
	/**
	   * 
	   * @comment ���۶�̬
	   * @return  1���ɹ���0��ʧ��
	   * @version 1.0
	   */
	@Override
	public int insertProjectDynamics(ProjectDynamic projectDynamic) {
		
		return projectInfoDao.insertProjectDynamics(projectDynamic);
	}
	/**
	 * 
	 * @comment ��ҳ��ѯ����Ŀ���ж�̬
	 * @return 
	 * @version 1.0
	 */
	@Override
	public PageBean findAllProjectDynamic(
	HttpServletRequest request,ProjectDynamic projectDynamic) {
		// ��ʼ������û���������
		int projectDynamicNum = 0;				
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		StringBuffer buffer = new StringBuffer();
		// ҳ��������ѯ����ƴ��
		if (StringUtils.isNotBlank(projectDynamic.getDynamicDesc())) {
			buffer.append("&dynamicDesc=").append(projectDynamic.getDynamicDesc());
		}
		if (StringUtils.isNotBlank(projectDynamic.getUserName())) {
			buffer.append("&userName=").append(projectDynamic.getUserName());
		}
		if (StringUtils.isNotBlank(projectDynamic.getUserNames())) {
			buffer.append("&userNames=").append(projectDynamic.getUserNames());
		}
		if (StringUtils.isNotBlank(projectDynamic.getStartTime())) {
			buffer.append("&startTime=").append(projectDynamic.getStartTime());
		}
		if (StringUtils.isNotBlank(projectDynamic.getEndTime())) {
			buffer.append("&endTime=").append(projectDynamic.getEndTime());
		}
		 
		// ����ÿҳ��ʾ��������ǰҳ��
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("projectDynamic", projectDynamic);
		// �����ǰҳ��ʾ������
		List<ProjectDynamic> lists =new ArrayList<ProjectDynamic>();
		projectDynamicNum = projectInfoDao.findAllProjectDynamicNum(projectDynamic);		
		List<ProjectDynamic> project_Dynamic = projectInfoDao.findAllProjectDynamic(map);		
		for (int i = 0; i < project_Dynamic.size(); i++) {
		    ProjectDynamic projectDynamics=new ProjectDynamic();
		    List<ProjectDynamic> lists1=new ArrayList<ProjectDynamic>();
		    //��ѯÿ����̬���µ���������
		    List<ProjectDynamic> list = findProjectDynamicByName(project_Dynamic.get(i),lists1);  
		    if(list!=null && list.size()>0){
		    	//��������̬����µ��������۷�װ��һ�����󣬷��뼯����
		    	projectDynamics.setComment(list); 
		    	projectDynamics.setProjId(project_Dynamic.get(i).getProjId());
		    	projectDynamics.setUserName(project_Dynamic.get(i).getUserName());
		    	projectDynamics.setDynamicDesc(project_Dynamic.get(i).getDynamicDesc());
		    	projectDynamics.setCreateTime(project_Dynamic.get(i).getCreateTime());
		    	projectDynamics.setDynamicId(project_Dynamic.get(i).getDynamicId());
		        lists.add(projectDynamics);
		        
		    }else{
		    	projectDynamics.setProjId(project_Dynamic.get(i).getProjId());
		    	projectDynamics.setUserName(project_Dynamic.get(i).getUserName());
		    	projectDynamics.setDynamicDesc(project_Dynamic.get(i).getDynamicDesc());
		    	projectDynamics.setCreateTime(project_Dynamic.get(i).getCreateTime());
		    	projectDynamics.setDynamicId(project_Dynamic.get(i).getDynamicId());
		        lists.add(projectDynamics);
		    	
		    }
	 	 
			}
		
		// �����ݷ�װ��page������
		page = new PageBean(pageNum, projectDynamicNum, currNo, lists,
				"/project/AllDynamicMessage.action", buffer.toString());
     return page;
		
		
	}
	/**
	   * 
	   * @comment �޸���Ŀ����(����)
	   * @return 
	   * @version 1.0
	   */
	@Transactional
	@Override
	public int updateProjectDetail(HttpServletRequest request,ProjectInfo projectInfo) {
		//�޸���Ŀ
		int a=projectInfoDao.updateProjectDetail(projectInfo);
		//����̬������з�װ
		ProjectDynamic projectDynamic=new ProjectDynamic();
		projectDynamic.setProjId(projectInfo.getProjId());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		projectDynamic.setUserName(userInfo.getUserName());
		projectDynamic.setDynamicDesc(userInfo.getUserName()+"�޸���"+projectInfo.getProjName());
		//������̬
		int b=projectInfoDao.insertProjectDynamic(projectDynamic);	
		
		
		
		
		return a+b;
	}
	/**
	   * 
	   * @comment �Զ���ȫ��ѯ��Ŀ����
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteByProjName(ProjectInfo projectInfo) {
		
		return projectInfoDao.findAutoCompleteByProjName(projectInfo);
	}
	/**
	   * 
	   * @comment �Զ���ȫ��ѯ�������û�
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteWhite(HttpServletRequest request) {
		String userName=request.getParameter("userName");
		int projId=Integer.parseInt(request.getParameter("projId"));
		HashMap map=new HashMap();
		map.put("userName", userName);
		map.put("projId", projId);
		String findAutoCompleteWhite = projectInfoDao.findAutoCompleteWhite(map);
		return findAutoCompleteWhite;
	}
	/**
	 * 
	 * @comment ���������
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int insertProjectWhite(HttpServletRequest request) {
		int a=0;
		ProjectWhiteList projectWhiteList=new ProjectWhiteList();
		//��ǰ���õ��������û����ַ�ƴ�Ӳ�����
		String nameListe=request.getParameter("nameListe");
		int projId=Integer.parseInt(request.getParameter("projId"));
		projectWhiteList.setProjId(projId);
		String[] split = nameListe.split(";");
		//�ֱ�����û��û���
		for (int i = 0; i < split.length; i++) {
			if(split[i]!=null && split[i]!="" ){
				int userId=projectInfoDao.findUserIdByUserName(split[i]);
				projectWhiteList.setUserId(userId);
				a+=projectInfoDao.insertProjectWhite(projectWhiteList);
			}
		}
		
		return a;
	}
	
	/**
	   * 
	   * @comment ������Ŀid��ѯ��Ŀ�İ������û�
	   * @return 
	   * @version 1.0
	   */
	@Override
	public List<ProjectWhiteList> findProjectWhiteListByProjectId(int id) {
		
		return projectInfoDao.findProjectWhiteListByProjectId(id);
	}
	
	 /**
	   * 
	   * @comment ����޸ĺ�İ�����(�������)
	   * @return 
	   * @version 1.0
	   */
	@Transactional
	@Override
	public int insertProjectWhiteByUpdate(HttpServletRequest request) {
		int b=0;
		ProjectWhiteList projectWhiteList=new ProjectWhiteList();
		//��ǰ���õ���Ŀid�Ͱ������û���ƴ�ӵ��ַ���������
		int projId=Integer.parseInt(request.getParameter("projId"));
		String userIds=request.getParameter("userIds");
		projectWhiteList.setProjId(projId);
		//��ɾ������Ŀ�󶨵����а�����
		int a=projectInfoDao.deleteProjectWhite(projId);
		String[] split = userIds.split(",");
		//����Ӱ�����
		for (int i = 0; i < split.length; i++) {
			if(split[i]!=null && split[i]!=""){
			projectWhiteList.setUserId(Integer.parseInt(split[i]));
			b=projectInfoDao.insertProjectWhiteByUpdate(projectWhiteList);
			}
			}
		
		return a+b;
	}
	/**
	   * 
	   * @comment �Զ���ȫ��ѯ��̬���ơ���̬�����ˡ���̬�ظ���
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteByProjectDynamic(ProjectDynamic projectDynamic) {
		
		return projectInfoDao.findAutoCompleteByProjectDynamic(projectDynamic);
	}
	/**
	 * 
	 * @comment ��ѯ���е���Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findAllProjectTeam(ProjectInfo projectInfo) {
		
		return projectInfoDao.findAllProjectTeam( projectInfo);
	}
	  /**
	   * 
	   * @comment ��ѯ����Ŀ�󶨵���Ŀ��
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public List<ProjectTeam> findAllProjectTeamByProId(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		return projectInfoDao.findAllProjectTeamByProId(projectInfo);
	}
	/**
	 * 
	 * @comment �����Ŀ��Ŀ��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int deleteProjectTeamToPro(ProjectTeam projectTeam) {
		// TODO Auto-generated method stub
		return 0;
	}
	  /**
	   * 
	   * @comment ����Ŀ������Ŀ��
	   * @return 
	   * @version 1.0
	   */
	@Transactional
	@Override
	public int insertProjectTeamToPro(HttpServletRequest request) {
		String teamIds = request.getParameter("teamId");
		int projId=Integer.parseInt(request.getParameter("projId"));
		ProjectTeam projectTeam=new ProjectTeam();
        projectTeam.setProjId(projId);
        ProjectInfo projectInfo=new ProjectInfo();
        projectInfo.setProjId(projId);
        int b=0;
        //����ո���Ŀ�İ󶨵�������Ŀ��
        List<ProjectTeam> list = projectInfoDao.findAllProjectTeamByProId(projectInfo);
		  for (int i = 0; i < list.size(); i++) {
			  b+=projectInfoDao.deleteProjectTeamToPro(list.get(i));
		}          
        //��ѡ�е���Ŀ��idƴ�ӳ��ַ���
		String[] split = teamIds.split(",");
		int a=0;
		//������Ŀ��
		for (int i = 0; i < split.length; i++) {
			projectTeam.setTeamId(Integer.parseInt(split[i]));
			b+=projectInfoDao.insertProjectTeamToPro(projectTeam);
		}
		
		return  a+b;
		
	}
	/**
	 * 
	 * @comment ���ֿ��õ���Ŀ����
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<SysClass> findProjectName() {
		
		return projectInfoDao.findProjectName();
	}
	/**
	 * 
	 * @comment ��ѯ��Ŀ����ʱ��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public Double  findConsumption(int projId) {
		
		return projectInfoDao.findConsumption( projId);
	}	
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ����̬ģ��id�Ƿ����
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findClassId(int projId) {
		
		return projectInfoDao.findClassId(projId);
	}
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ����̬ģ��id
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findClassIdss(int projId) {
		
		return projectInfoDao.findClassIdss(projId);
	}
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ���汾ģ��id�Ƿ����
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findVersionClassId(int projId) {
		
		return projectInfoDao.findVersionClassId(projId);
	}
	  /**
	   * 
	   * @comment ͨ����Ŀid��ѯ���¶�Ӧ��ɾ���汾ģ��id
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findVersionClassIdss(int projId) {
		
		return projectInfoDao.findVersionClassIdss(projId);
	}
	/**
	 * 
	 * @comment �鿴�汾��Ϣ
	 * @return 
	 * @version 1.0
	 */
	@Override
	public PageBean findProjectVersions(HttpServletRequest request,
			ProjectVersion projectVersion) {
		ProjectVersion projectVersions=new ProjectVersion();
		int projId=Integer.parseInt(request.getParameter("projId"));
		projectVersions.setProjId(projId);
		  // ��ʼ������û���������
		int projectNum = 0;				
		// �Ե�ǰҳ������ֵ������
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// ��ÿҳ��ʾ��������
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// ����ÿҳ��ʾ��������ǰҳ��
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);		
		map.put("projectVersion", projectVersions);
		List<ProjectVersion> list = projectInfoDao.findAllProjectVession(map);
		projectNum = projectInfoDao.findAllProjectVessionNum(projectVersion);

		// �����ݷ�װ��page������
		page = new PageBean(pageNum, projectNum, currNo, list,
				"/project/projectVession.action","projId="+projId+"");
        return page;
	}
	/**
	 * 
	 * @comment �޸İ汾��Ϣ
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int updateProjVersin(ProjectVersion projectVersion) {
		
		return projectInfoDao.updateProjVersin(projectVersion);
	}
	/**
	 * 
	 * @comment ��֤�汾��
	 * @return 
	 * @version 1.0
	 */
	@Override
	public String findVersionNum(ProjectVersion projectVersion) {
		
		return projectInfoDao.findVersionNum(projectVersion);
	}
	/**
	 * 
	 * @comment �½��汾
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int insertVersion(ProjectVersion projectVersion) {
		
		return projectInfoDao.insertVersion(projectVersion);
	}
    //*************************************************
	@Override
	public List<ProjectInfo> findAllPro(Map map) {
		// TODO Auto-generated method stub
		return projectInfoDao.findAllPro(map);
	}
	@Override
	public List<ProjectInfo> findOneselfPro(Map map) {
		// TODO Auto-generated method stub
		return projectInfoDao.findOneselfPro(map);
	}
	@Override
	public String projIdsByProjName(String projName) {
		// TODO Auto-generated method stub
		return projectInfoDao.projIdsByProjName(projName);
	}
	@Override
	public int deleteProjectWhite(int id) {
		// TODO Auto-generated method stub
		return projectInfoDao.deleteProjectWhite(id);
	}






	
}

