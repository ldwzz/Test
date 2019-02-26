package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.byzx.auth.bean.BugInfo;
import com.byzx.auth.bean.BugReplay;
import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.BugInfoDao;
import com.byzx.auth.utils.PageBean;

@Service
public class BugServiceImp implements BugService {
	@Autowired
	BugInfoDao bug;

	public PageBean findBug(HttpServletRequest request, BugInfo buginfo) {
		int bugNum = 0;
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		StringBuffer buffer = new StringBuffer();
		if (buginfo.getProjId() != null && buginfo.getProjId() > 0) {
			buffer.append("&projId=").append(buginfo.getProjId().toString());
		}
		if (buginfo.getClassId() != null && buginfo.getClassId() > 0) {
			buffer.append("&classId=").append(buginfo.getClassId());
		}
		if (StringUtils.isNotBlank(buginfo.getBugLevel())) {
			buffer.append("&bugLevel=").append(buginfo.getBugLevel());
		}
		if (StringUtils.isNotBlank(buginfo.getBugState())) {
			buffer.append("&bugState=").append(buginfo.getBugState());
		}
		if (StringUtils.isNotBlank(buginfo.getBugTitle())) {
			buffer.append("&bugTitle=").append(buginfo.getBugTitle());
		}
		if (StringUtils.isNotBlank(buginfo.getStartTime())) {
			buffer.append("&startTime=").append(buginfo.getStartTime());
		}
		if (StringUtils.isNotBlank(buginfo.getEndTime())) {
			buffer.append("&endTime=").append(buginfo.getEndTime());
		}

		if (StringUtils.isNotBlank(buginfo.getBugNum())) {
			buffer.append("&bugNum=").append(buginfo.getBugNum());
		}
		if (StringUtils.isNotBlank(buginfo.getExecutor())) {
			buffer.append("&executor=").append(buginfo.getExecutor());
		}
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("buginfor", buginfo);
		List<BugInfo> list = bug.findBugByCondition(map);
		bugNum = bug.findBugNumByCondition(buginfo);
		page = new PageBean(pageNum, bugNum, currNo, list,
				"/bug/buginfo.action", buffer.toString());
		return page;
	}

	public int findBugNumByCondition(BugInfo buginfo) {
		return bug.findBugNumByCondition(buginfo);
	};

	@Override
	public int updateBug(BugInfo buginfo) {
		return bug.updateBug(buginfo);
	}

	@Override
	public int updateAssignBug(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bug.updateAssignBug(buginfo);
	}

	@Override
	public int updateBugState(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bug.updateBugState(buginfo);
	}

	@Override
	public int deleteBug() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBug(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bug.insertBug(buginfo);
	}

	@Override
	public int findBugNumExist(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bug.findBugNumExist(buginfo);
	}

	@Override
	public List<UserInfo> finduserByProj(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bug.finduserByProj(buginfo);
	}

	@Override
	public BugInfo findBugDetail(BugInfo buginfo) {
		// TODO Auto-generated method stub
		return bug.findBugDetail(buginfo);
	}

	@Override
	public int insertBugRemake(BugReplay play) {
		// TODO Auto-generated method stub
		return bug.insertBugRemake(play);
	}

	@Override
	public List<BugReplay> findBugRemake(HashMap map) {
		return bug.findBugRemake(map);
	}

	@Override
	public List<ProjectInfo> findProject(HttpServletRequest request) {
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("userInfo");
		String Rolecodes = (String) request.getSession().getAttribute(
				"Rolecodes");
		List<ProjectInfo> list = null;
		if (Rolecodes != null) {
			if (Rolecodes.contains("admin") || Rolecodes.contains("testleader")
					|| Rolecodes.contains("testmember")) {
				list = bug.findProjectadmin(user);

			} else if (Rolecodes.contains("project_manager")) {
				list = bug.findProjectManage(user);

			} else if (Rolecodes.contains("project_member")
					|| Rolecodes.contains("project_leader")) {
				list = bug.findProjectStaff(user);
			}
		}
		return list;
	}

	@Override
	public int deleteReply(BugReplay play) {
		// TODO Auto-generated method stub
		return bug.deleteReply(play);
	}

}
