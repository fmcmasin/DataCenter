package cn.datacenter.commonUtil;

/**
 * 集成接口的常量类
 * @author masin
 *
 */
public interface IntegrationConstans {
	
	/*
	 * 如果传入参数为空的处理提示信息
	 */
	public static final String PARAMETER_NULL_MESSAGE = "传入的参数不能为空";
	
	/*
	 * 程序逻辑出现异常的code值
	 */
	public static final String ERROR_MESSAGE_CODE = "ERROR";
	
	/*
	 * 程序逻辑正常的code值
	 */
	public static final String RIGHT_MESSAGE_CODE = "OK";
	
	/*
	 * 没有找到登录用户的提示信息
	 */
	public static final String USER_NOT_EXISTS_MESSAGE = "无效的用户";
	
	/*
	 * 查询报事报修工单
	 */
	public static final String SR_AFFAIR ="AFFAIR";  
	
	/*
	 * 查看投诉建议工单
	 */
	public static final String SR_SUGGEST="SUGGEST";  
	
}
