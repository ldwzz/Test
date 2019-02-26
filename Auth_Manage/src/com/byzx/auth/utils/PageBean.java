/**
 * @filename PageBean.java
 * @author lg
 * @date 2017年11月9日 上午9:39:25
 * @version 1.0
 * Copyright (C) 2017 
 */

package com.byzx.auth.utils;

import java.util.List;

/**
 * 分页实体bean
 * 
 * @author lg
 * 
 */
public class PageBean {

	// 每页显示条数
	private Integer pageNum = 10;
	// 总条数
	private Integer totalNum = 0;
	// 当前页码
	private Integer currNo = 1;
	// 总页数
	private Integer totalPage = 0;
	// 查询下标位置，limit 中的第一个参数
	private Integer pageIndex = 0;
	// 存储返回的集合对象
	private List<?> resultList;
	// 请求地址, user/list.action
	private String url;
	// 请求参数(搜索条件参数的封装), &userCode=admin&userType=1&userState=1
	private String params;

	public PageBean() {

	}


	public PageBean(int pageNum, int currNo) {
		super();
		this.pageNum = pageNum;
		this.currNo = currNo;
	}
	

	public PageBean(List<?> resultList) {
		super();
		this.resultList = resultList;
	}


	public PageBean(int pageNum, int totalNum, int currNo, List<?> resultList,
			String url, String params) {
		super();
		this.pageNum = pageNum;
		this.totalNum = totalNum;
		this.currNo = currNo;
		this.resultList = resultList;
		this.url = url;
		this.params = params;
	}

	

	public PageBean(Integer pageNum, Integer totalNum, Integer currNo,
		Integer totalPage, Integer pageIndex, List<?> resultList, String url,
		String params) {
	super();
	this.pageNum = pageNum;
	this.totalNum = totalNum;
	this.currNo = currNo;
	this.totalPage = totalPage;
	this.pageIndex = pageIndex;
	this.resultList = resultList;
	this.url = url;
	this.params = params;
}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	// 当前页码
	public int getCurrNo() {
		if (currNo == 0) {
			currNo = 1;
		}
		return currNo;
	}

	public void setCurrNo(int currNo) {
		this.currNo = currNo;
	}

	// 总页数
	public int getTotalPage() {
		totalPage = (totalNum % pageNum == 0) ? (totalNum / pageNum)
				: (totalNum / pageNum) + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	// 查询下标位置
	public int getPageIndex() {
		return pageNum * (currNo - 1);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "PageBean [pageNum=" + pageNum + ", totalNum=" + totalNum
				+ ", currNo=" + currNo + ", totalPage=" + totalPage
				+ ", pageIndex=" + pageIndex + ", resultList=" + resultList
				+ ", url=" + url + ", params=" + params + "]";
	}



	

}
