/**
 * @filename ProjectInfoServiceImpl.java
 * @author Administrator
 * @date 2018年9月4日 上午10:19:45
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
 *@date 2018年9月4日 上午10:19:57
 *@modifyUser Administrator
 *@modifyDate 2018年9月4日 上午10:19:57
 *@modifyDesc  TODO
 *@version TODO
 */
@Service ("projectInfoService")
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Autowired ProjectInfoDao projectInfoDao;
    /**
     * 
     * @comment 分页查询项目信息
     * @return  项目信息
     * @version 1.0
     */
	@Override
	public PageBean findProjects(HttpServletRequest request,
			ProjectInfo projectInfo) {
		        // 初始化查出用户条数变量
				int projectNum = 0;				
				UserInfo user = (UserInfo) request.getSession()
						.getAttribute("userInfo");
				// 对当前页进行拿值、处理
				int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
						.parseInt(request.getParameter("currNo"));
				// 对每页显示数量处理
				int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
						.parseInt(request.getParameter("pageNum"));
				StringBuffer buffer = new StringBuffer();
				// 页面条件查询参数拼接
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
				
				// 设置每页显示数量，当前页码
				PageBean page = new PageBean(pageNum, currNo);
				HashMap<Object, Object> map = new HashMap<Object, Object>();
				map.put("page", page);
				map.put("projectInfo", projectInfo);
				map.put("userId", user.getUserId());
				// 从session中拿出当前用户的角色code
				String userRoleCode = (String) request.getSession()
						.getAttribute("Rolecodes");
				String pinjie="," + userRoleCode + ",";
				// 查出当前页显示的数据
				List<ProjectInfo> list = null;
				if (userRoleCode != null) {
					// 若当前用户角色code为超级管理员，查询所有用户信息
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
				// 将数据封装至page对象中
				page = new PageBean(pageNum, projectNum, currNo, list,
						"/project/list.action", buffer.toString());
		return page;
	}
    /**
     * 
     * @comment 查询项目详情
     * @return  项目详情
     * @version 1.0
     */
	@Override
	public ProjectInfo findProjectInfoByProjId(int projId) {
		
		return projectInfoDao.findProjectInfoByProjId(projId);
	}
	
    /**
     * 
     * @comment 查询项目动态（前10条）
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
	   * @comment 查询动态(递归)
	   * @return 
	   * @version 1.0
	   */
	
	@Override
	public List<ProjectDynamic> findProjectDynamicByName( ProjectDynamic projectDynamic,List<ProjectDynamic> lists) {
		//查询该动态的子回复动态
		List<ProjectDynamic> list = projectInfoDao.findProjectDynamicByName(projectDynamic);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=null){
				//将子回复动态放入集合中
				lists.add(list.get(i));
				//递归查询
				findProjectDynamicByName(list.get(i),lists);
		}
			
		}
		
		return lists ;
	}

	/**
	 * 
	 * @comment 新建项目并 发表动态(事务)
	 * @return 
	 * @version 1.0
	 */
	@Transactional
	@Override
	public int insertProjectInfo(HttpServletRequest request,ProjectInfo projectInfo) {
		//新建项目
		int a=projectInfoDao.insertProjectInfo(projectInfo);
		//给动态对象进行封装
		ProjectDynamic projectDynamic=new ProjectDynamic();
		projectDynamic.setProjId(projectInfo.getProjId());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		projectDynamic.setUserName(userInfo.getUserName());
		projectDynamic.setDynamicDesc(userInfo.getUserName()+"创建了"+projectInfo.getProjName());
		//发布动态
		int b=projectInfoDao.insertProjectDynamic(projectDynamic);
		return a+b;
	}
	/**
	   * 
	   * @comment 发表项目动态
	   * @return  1：成功，0：失败
	   * @version 1.0
	   */
	@Override
	public int insertProjectDynamic(ProjectDynamic projectDynamic) {
		
		return projectInfoDao.insertProjectDynamic(projectDynamic);
	}
	/**
	   * 
	   * @comment 评论动态
	   * @return  1：成功，0：失败
	   * @version 1.0
	   */
	@Override
	public int insertProjectDynamics(ProjectDynamic projectDynamic) {
		
		return projectInfoDao.insertProjectDynamics(projectDynamic);
	}
	/**
	 * 
	 * @comment 分页查询该项目所有动态
	 * @return 
	 * @version 1.0
	 */
	@Override
	public PageBean findAllProjectDynamic(
	HttpServletRequest request,ProjectDynamic projectDynamic) {
		// 初始化查出用户条数变量
		int projectDynamicNum = 0;				
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		StringBuffer buffer = new StringBuffer();
		// 页面条件查询参数拼接
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
		 
		// 设置每页显示数量，当前页码
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("projectDynamic", projectDynamic);
		// 查出当前页显示的数据
		List<ProjectDynamic> lists =new ArrayList<ProjectDynamic>();
		projectDynamicNum = projectInfoDao.findAllProjectDynamicNum(projectDynamic);		
		List<ProjectDynamic> project_Dynamic = projectInfoDao.findAllProjectDynamic(map);		
		for (int i = 0; i < project_Dynamic.size(); i++) {
		    ProjectDynamic projectDynamics=new ProjectDynamic();
		    List<ProjectDynamic> lists1=new ArrayList<ProjectDynamic>();
		    //查询每条动态底下的所有评论
		    List<ProjectDynamic> list = findProjectDynamicByName(project_Dynamic.get(i),lists1);  
		    if(list!=null && list.size()>0){
		    	//将该条动态其底下的所有评论封装到一个对象，放入集合中
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
		
		// 将数据封装至page对象中
		page = new PageBean(pageNum, projectDynamicNum, currNo, lists,
				"/project/AllDynamicMessage.action", buffer.toString());
     return page;
		
		
	}
	/**
	   * 
	   * @comment 修改项目详情(事务)
	   * @return 
	   * @version 1.0
	   */
	@Transactional
	@Override
	public int updateProjectDetail(HttpServletRequest request,ProjectInfo projectInfo) {
		//修改项目
		int a=projectInfoDao.updateProjectDetail(projectInfo);
		//给动态对象进行封装
		ProjectDynamic projectDynamic=new ProjectDynamic();
		projectDynamic.setProjId(projectInfo.getProjId());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		projectDynamic.setUserName(userInfo.getUserName());
		projectDynamic.setDynamicDesc(userInfo.getUserName()+"修改了"+projectInfo.getProjName());
		//发布动态
		int b=projectInfoDao.insertProjectDynamic(projectDynamic);	
		
		
		
		
		return a+b;
	}
	/**
	   * 
	   * @comment 自动补全查询项目名称
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteByProjName(ProjectInfo projectInfo) {
		
		return projectInfoDao.findAutoCompleteByProjName(projectInfo);
	}
	/**
	   * 
	   * @comment 自动补全查询白名单用户
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
	 * @comment 插入白名单
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int insertProjectWhite(HttpServletRequest request) {
		int a=0;
		ProjectWhiteList projectWhiteList=new ProjectWhiteList();
		//从前端拿到白名单用户名字符拼接并解析
		String nameListe=request.getParameter("nameListe");
		int projId=Integer.parseInt(request.getParameter("projId"));
		projectWhiteList.setProjId(projId);
		String[] split = nameListe.split(";");
		//分别插入用户用户表
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
	   * @comment 根据项目id查询项目的白名单用户
	   * @return 
	   * @version 1.0
	   */
	@Override
	public List<ProjectWhiteList> findProjectWhiteListByProjectId(int id) {
		
		return projectInfoDao.findProjectWhiteListByProjectId(id);
	}
	
	 /**
	   * 
	   * @comment 添加修改后的白名单(添加事务)
	   * @return 
	   * @version 1.0
	   */
	@Transactional
	@Override
	public int insertProjectWhiteByUpdate(HttpServletRequest request) {
		int b=0;
		ProjectWhiteList projectWhiteList=new ProjectWhiteList();
		//从前端拿到项目id和白名单用户名拼接的字符串并解析
		int projId=Integer.parseInt(request.getParameter("projId"));
		String userIds=request.getParameter("userIds");
		projectWhiteList.setProjId(projId);
		//先删除该项目绑定的所有白名单
		int a=projectInfoDao.deleteProjectWhite(projId);
		String[] split = userIds.split(",");
		//再添加白名单
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
	   * @comment 自动补全查询动态名称、动态创建人、动态回复人
	   * @return 
	   * @version 1.0
	   */
	@Override
	public String findAutoCompleteByProjectDynamic(ProjectDynamic projectDynamic) {
		
		return projectInfoDao.findAutoCompleteByProjectDynamic(projectDynamic);
	}
	/**
	 * 
	 * @comment 查询所有的项目组
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<ProjectTeam> findAllProjectTeam(ProjectInfo projectInfo) {
		
		return projectInfoDao.findAllProjectTeam( projectInfo);
	}
	  /**
	   * 
	   * @comment 查询该项目绑定的项目组
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
	 * @comment 清除项目项目组
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
	   * @comment 给项目新增项目组
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
        //先清空该项目的绑定的所有项目组
        List<ProjectTeam> list = projectInfoDao.findAllProjectTeamByProId(projectInfo);
		  for (int i = 0; i < list.size(); i++) {
			  b+=projectInfoDao.deleteProjectTeamToPro(list.get(i));
		}          
        //将选中的项目组id拼接成字符集
		String[] split = teamIds.split(",");
		int a=0;
		//分配项目组
		for (int i = 0; i < split.length; i++) {
			projectTeam.setTeamId(Integer.parseInt(split[i]));
			b+=projectInfoDao.insertProjectTeamToPro(projectTeam);
		}
		
		return  a+b;
		
	}
	/**
	 * 
	 * @comment 发现可用的项目名称
	 * @return 
	 * @version 1.0
	 */
	@Override
	public List<SysClass> findProjectName() {
		
		return projectInfoDao.findProjectName();
	}
	/**
	 * 
	 * @comment 查询项目消耗时间
	 * @return 
	 * @version 1.0
	 */
	@Override
	public Double  findConsumption(int projId) {
		
		return projectInfoDao.findConsumption( projId);
	}	
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除动态模块id是否存在
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findClassId(int projId) {
		
		return projectInfoDao.findClassId(projId);
	}
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除动态模块id
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findClassIdss(int projId) {
		
		return projectInfoDao.findClassIdss(projId);
	}
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除版本模块id是否存在
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findVersionClassId(int projId) {
		
		return projectInfoDao.findVersionClassId(projId);
	}
	  /**
	   * 
	   * @comment 通过项目id查询底下对应的删除版本模块id
	   * @return 
	   * @version 1.0
	   */	
	@Override
	public int findVersionClassIdss(int projId) {
		
		return projectInfoDao.findVersionClassIdss(projId);
	}
	/**
	 * 
	 * @comment 查看版本信息
	 * @return 
	 * @version 1.0
	 */
	@Override
	public PageBean findProjectVersions(HttpServletRequest request,
			ProjectVersion projectVersion) {
		ProjectVersion projectVersions=new ProjectVersion();
		int projId=Integer.parseInt(request.getParameter("projId"));
		projectVersions.setProjId(projId);
		  // 初始化查出用户条数变量
		int projectNum = 0;				
		// 对当前页进行拿值、处理
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		// 对每页显示数量处理
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		// 设置每页显示数量，当前页码
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);		
		map.put("projectVersion", projectVersions);
		List<ProjectVersion> list = projectInfoDao.findAllProjectVession(map);
		projectNum = projectInfoDao.findAllProjectVessionNum(projectVersion);

		// 将数据封装至page对象中
		page = new PageBean(pageNum, projectNum, currNo, list,
				"/project/projectVession.action","projId="+projId+"");
        return page;
	}
	/**
	 * 
	 * @comment 修改版本信息
	 * @return 
	 * @version 1.0
	 */
	@Override
	public int updateProjVersin(ProjectVersion projectVersion) {
		
		return projectInfoDao.updateProjVersin(projectVersion);
	}
	/**
	 * 
	 * @comment 验证版本号
	 * @return 
	 * @version 1.0
	 */
	@Override
	public String findVersionNum(ProjectVersion projectVersion) {
		
		return projectInfoDao.findVersionNum(projectVersion);
	}
	/**
	 * 
	 * @comment 新建版本
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

