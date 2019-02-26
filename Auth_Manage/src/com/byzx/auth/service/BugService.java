package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.byzx.auth.bean.BugInfo;
import com.byzx.auth.bean.BugReplay;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.utils.PageBean;

public interface BugService {

	public List<ProjectInfo> findProject(HttpServletRequest request);

	public PageBean findBug(HttpServletRequest request, BugInfo buginfo);

	public int updateBug(BugInfo buginfo);

	public int updateAssignBug(BugInfo buginfo);

	public int updateBugState(BugInfo buginfo);

	public int deleteBug();

	public int insertBug(BugInfo buginfo);

	public int findBugNumExist(BugInfo buginfo);

	public List<UserInfo> finduserByProj(BugInfo buginfo);

	public BugInfo findBugDetail(BugInfo buginfo);

	public int insertBugRemake(BugReplay play);

	public List<BugReplay> findBugRemake(HashMap map);

	public int deleteReply(BugReplay play);

	public int findBugNumByCondition(BugInfo buginfo);

}
