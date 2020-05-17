package com.xiaobi.bean;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author Nifury
 * 分页查询功能基础类
 *
 */
@Component
public class Query<T> {
	/**当前页数，默认为1*/
    private int currentPage = 1;
      /**每页显示条数，默认为20*/
    private int pageSize = 20;
    /**查到的总条数，默认为0*/
    private long totalRecord=0;
    /**查到的总条数可以分多少页*/
    private int totalPage=1;
    /**
     * 将要查询的类的条件放到相应类的对象属性中
     * 例如要通过userId查询User,则创建一个User对象，并设置其userId属性，即可通过userId来查询
     */
    private HashMap<String,String> queryParameters;

	public HashMap<String, String> getQueryParameters() {
		return queryParameters;
	}
	public void setQueryParameters(HashMap<String, String> queryParameters) {
		this.queryParameters = queryParameters;
	}
	/**返回状态码，[0:查询成功;-1:系统出错;其他:自定义查询失败码]*/
    private int errcode;
    /**返回状态码说明，如查询成功则为ok,否则为错误原因*/
    private String errmsg;
    /**成功查询后的数据结果*/
    private List<T> dataList;
    public Query(){

    }
    public Query(int currentPage, int pageSize){
      this.currentPage = currentPage;
      this.pageSize = pageSize;
    }
	@Override
	public String toString() {
		return "Query:{\"currentPage\":\"" + currentPage + "\", \"pageSize\":\"" + pageSize + "\", \"totalRecord\":\""
				+ totalRecord + "\", \"totalPage\":\"" + totalPage + "\", \"queryParameters\":\"" + queryParameters
				+ "\", \"errcode\":\"" + errcode + "\", \"errmsg\":\"" + errmsg + "\", \"dataList\":\"" + dataList
				+ "\"}";
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		 this.totalRecord = totalRecord;
	     totalPage = (int)(totalRecord+pageSize-1)/pageSize;
	     this.totalPage=(this.totalPage<1?1:this.totalPage);
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage=(totalPage<1?1:totalPage);
	}
	/**
	 * 返回存放查询条件的键值对对象
	 */
	/*public HashMap<String,String> getQueryParameters() {
		return (this.queryParameters==null?new HashMap<String,String>():this.queryParameters);
	}
	public void setQueryParameters(HashMap<String,String> queryParameters) {
		this.queryParameters = (queryParameters==null?new HashMap<String,String>():queryParameters);
	}*/
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}