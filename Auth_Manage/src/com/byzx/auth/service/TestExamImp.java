package com.byzx.auth.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.auth.bean.ProjectInfo;
import com.byzx.auth.bean.TestExam;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.dao.TestExamDao;
import com.byzx.auth.utils.PageBean;

@Service
public class TestExamImp implements TestExamService {
	@Autowired
	TestExamDao test;

	@Override
	public PageBean findTestExam(HttpServletRequest request, TestExam exam) {
		int num = 0;
		String flag = request.getParameter("flag");
		UserInfo user = (UserInfo) request.getSession()
				.getAttribute("userInfo");
		int currNo = ((request.getParameter("currNo")) == null) ? 1 : Integer
				.parseInt(request.getParameter("currNo"));
		int pageNum = ((request.getParameter("pageNum")) == null) ? 5 : Integer
				.parseInt(request.getParameter("pageNum"));
		StringBuffer buffer = new StringBuffer();
		if (exam.getProjId() != null && exam.getProjId() > 0) {
			buffer.append("&projId=").append(exam.getProjId().toString());
		}
		if (StringUtils.isNotBlank(exam.getTestType())) {
			buffer.append("&testType=").append(exam.getTestType());
		}
		if (StringUtils.isNotBlank(exam.getTestState())) {
			buffer.append("&testState=").append(exam.getTestState());
		}
		PageBean page = new PageBean(pageNum, currNo);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("page", page);
		map.put("team", exam);
		map.put("user", user);
		String Rolecodes = (String) request.getSession().getAttribute(
				"Rolecodes");
		List<TestExam> list = null;
		if (Rolecodes != null) {
			if (Rolecodes.contains("testleader")) {
				num = test.findNumExamMonter(exam);
				list = test.findExamMonter(map);
			} else if (Rolecodes.contains("testmember")) {
				num = test.findNumExamMember(map);
				list = test.findExamMember(map);
			}
		}
		page = new PageBean(pageNum, num, currNo, list,
				"/test/testlist.action", buffer.toString());
		return page;
	}

	@Override
	public int insertExam(TestExam exam) {
		// TODO Auto-generated method stub
		return test.insertExam(exam);
	}

	@Override
	public int updateExam(TestExam exam) {
		// TODO Auto-generated method stub
		return test.updateExam(exam);
	}

	@Override
	public int updateExamAssign(TestExam exam) {
		// TODO Auto-generated method stub
		return test.updateExamAssign(exam);
	}

	@Override
	public int deleteExam(TestExam exam) {
		// TODO Auto-generated method stub
		return test.deleteExam(exam);
	}

	@Override
	public List<UserInfo> findTestMember() {
		// TODO Auto-generated method stub
		return test.findTestMember();
	}

}
