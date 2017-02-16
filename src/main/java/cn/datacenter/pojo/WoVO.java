package cn.datacenter.pojo;

import java.io.Serializable;
import java.util.Date;




/**
 * 工单POJO
 * @author meisheng
 *
 */
public class WoVO implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private  String  serviceorderno;  //服务单号
	private  String  operator;  //操作人
	private  String  option;  //操作内容
	private  Date   optiontime;  //操作时间
	private  String  worktype; //工作类型
	private  Date  appointmentTime; //预约时间
	private  String  instancy;   //紧急程度
	private  String  description; //故障描述
	private  boolean  isguarantee;  //是否保修
	private  Date  wocreatetime;  //工单创建时间
	private  Date  actstarttime;  //实际开始时间
	private  Date  actendtime; //实际结束时间
	private  String content;  //服务申请内容
	private  String wostatus;  //工单状态
	private  String  requestby; //申请人
	private  String  contact;  //联系人
	private  String tel; //联系电话
	private  String buid; //楼盘编码
	private  String location;// 位置编码
	private  String remarks; //备注
	public String getServiceorderno() {
		return serviceorderno;
	}
	public void setServiceorderno(String serviceorderno) {
		this.serviceorderno = serviceorderno;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Date getOptiontime() {
		return optiontime;
	}
	public void setOptiontime(Date optiontime) {
		this.optiontime = optiontime;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getInstancy() {
		return instancy;
	}
	public void setInstancy(String instancy) {
		this.instancy = instancy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIsguarantee() {
		return isguarantee;
	}
	public void setIsguarantee(boolean isguarantee) {
		this.isguarantee = isguarantee;
	}
	public Date getWocreatetime() {
		return wocreatetime;
	}
	public void setWocreatetime(Date wocreatetime) {
		this.wocreatetime = wocreatetime;
	}
	public Date getActstarttime() {
		return actstarttime;
	}
	public void setActstarttime(Date actstarttime) {
		this.actstarttime = actstarttime;
	}
	public Date getActendtime() {
		return actendtime;
	}
	public void setActendtime(Date actendtime) {
		this.actendtime = actendtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWostatus() {
		return wostatus;
	}
	public void setWostatus(String wostatus) {
		this.wostatus = wostatus;
	}
	public String getRequestby() {
		return requestby;
	}
	public void setRequestby(String requestby) {
		this.requestby = requestby;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBuid() {
		return buid;
	}
	public void setBuid(String buid) {
		this.buid = buid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
