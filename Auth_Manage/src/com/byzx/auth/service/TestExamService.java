package com.byzx.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byzx.auth.bean.TestExam;
import com.byzx.auth.bean.UserInfo;
import com.byzx.auth.utils.PageBean;


public interface TestExamService {
	public PageBean findTestExam(HttpServletRequest request,TestExam  exam);
	public   int        insertExam(TestExam  exam);
	public  int     updateExam(TestExam  exam);
	public  int     updateExamAssign(TestExam  exam);
    public   int   deleteExam(TestExam  exam);
    public  List<UserInfo>  findTestMember();
}
