package cn.datacenter.pojo;

import java.io.Serializable;

import cn.datacenter.commonUtil.IntegrationConstans;



/*
 * 用于公用的返回结果集VO
 */
public class ResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7917873684569426419L;

	/*
	 * 状态码,默认返回000，代表请求成功
	 */
	private String status = IntegrationConstans.RIGHT_MESSAGE_CODE;
	
	/*
	 * 服务时间
	 */
	private String serverDate;

	/*
	 * 错误信息描述
	 */
	private String describe;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServerDate() {
		return serverDate;
	}

	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
