package com.byzx.auth.dao;

import java.util.HashMap;
import java.util.List;

import com.byzx.auth.bean.TestExam;
import com.byzx.auth.bean.UserInfo;

public interface TestExamDao {
	public  List<TestExam>   findExamMonter(HashMap<Object, Object> map);
	public   int   findNumExamMonter(TestExam  test);
	public  List<TestExam>   findExamMember(HashMap<Object, Object> map);
	public  int   findNumExamMember(HashMap<Object, Object> map);
	public   int        insertExam(TestExam  exam);
	public  int     updateExam(TestExam  exam);
	public  int     updateExamAssign(TestExam  exam);
    public   int   deleteExam(TestExam  exam);
    public  List<UserInfo>  findTestMember();
}
