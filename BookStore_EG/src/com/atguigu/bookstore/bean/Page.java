package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 描述分页信息的类
 * @author 93
 *
 */
public class Page<T> implements Serializable{
	/**
	 * 分页需要显示的数据
	 * -根据index和size从数据库查询得到
	 */
	private List<T> data;
	
	/**
	 * 当前分页页码
	 * -用户从页面提交的页码参数
	 */
	private int pageNumber;
	/**
	 * 总页数
	 * -totalCount/size
	 */
	private int totalPage;
	/**
	 * 需要分页数据的总记录条数
	 * 	- 查询分页数据得到
	 */
	private int totalCount;
	/**
	 * 每页显示的记录条数
	 * 	- 在服务器中我们设置的
	 */
	private int size;
	/**
	 * 当前分页集合查询的起始索引
	 * 	- 根据pageNumber+size计算得到
	 * 		size*(pageNumber-1)
	 */
	private int index;
	
	
	public Page() {
		super();
	}


	public Page(List<T> data, int pageNumber, int totalPage, int totalCount, int size, int index) {
		super();
		this.data = data;
		this.pageNumber = pageNumber;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.size = size;
		this.index = index;
	}


	public List<T> getData() {
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}


	public int getPageNumber() {
		//检查页码范围
		if(pageNumber < 1 ) {
			pageNumber = 1;
		}else if(pageNumber > getTotalPage()) {//totalPage属性是页面中调用了getTotalPage()方法后才计算得到值
			pageNumber=totalPage;
		}
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		
		this.pageNumber = pageNumber;
	}


	public int getTotalPage() {
		//需要计算得到
		if(totalCount%size ==0 ) {
			//能够整除
			totalPage = totalCount/size;	
		}else {
			//不能整除
			totalPage = totalCount/size+1;
		}
		return totalPage;
	}
	
	/*
	 * public void setTotalPage(int totalPage) { this.totalPage = totalPage; }
	 */


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getIndex() {
		index = (getPageNumber()-1)*size;
		return index;
	}

	/*
	 * public void setIndex(int index) { this.index = index; }
	 */

	
}
