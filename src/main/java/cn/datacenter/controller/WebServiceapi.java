package cn.datacenter.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.datacenter.commonUtil.IntegrationConstans;
import cn.datacenter.commonUtil.IntegrationUtils;
import cn.datacenter.pojo.ResultVO;



@Controller
@RequestMapping("/o2o")
public class WebServiceapi {
	
	private static Logger logger = LoggerFactory.getLogger(WebServiceapi.class);
	
	/**
	 * 获取token  o2o+公司编码+小区编码+角色编码+角色编号
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getToken(HttpServletRequest request, Model model) {
		String  commpany = request.getParameter("company");  //公司编码
		String  buid = request.getParameter("buid");   //小区编码
		String  role = request.getParameter("role");   //角色编码
		String  no = request.getParameter("no");       //编号
		
		if(logger.isInfoEnabled()){
			logger.info("getToken:"+commpany+"--"+buid+"--"+role+"--"+no);
		}
		ResultVO resultVO = new ResultVO();
		resultVO.setServerDate(IntegrationUtils.formatDate(new Date()));//设置服务响应时间
		if(IntegrationUtils.isEmptyStr(commpany)||IntegrationUtils.isEmptyStr(buid)) {//如果参数为空则直接返回
			if(logger.isDebugEnabled()){
				logger.info("getToken result:Invalid request");
			}
			resultVO.setStatus(IntegrationConstans.ERROR_MESSAGE_CODE);
			resultVO.setDescribe("无效的请求参数");
			return JSONObject.fromObject(resultVO).toString();
		}
		//TODO 验证传入参数的合法性
		
		
		String times =  String.valueOf(System.currentTimeMillis());
		//token生成
		String token = IntegrationUtils.getToken(commpany+buid+role+no, times);
		resultVO.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
		JSONObject object = JSONObject.fromObject(resultVO);//创建一个JSON对象
		object.put("token", token);
		if(logger.isInfoEnabled()){
			logger.info("getToken result:" + object.toString());
		}
		return object.toString();
	}
	/**
	 * 提交报事报修工单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/affair/create", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String rpWoCreate(HttpServletRequest request, Model model){
		//创建map用来存储请求发送的数据进行保存
		 Map<String,String>  hashmap = new HashMap<String, String>();
		 Map<String, String[]> params = request.getParameterMap();  
	        for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = values[i];  
	                hashmap.put(key, value);
	            }  
	        }
	      //进行token验证
	        
	        
	        ResultVO    result = new ResultVO();
	        result.setServerDate(IntegrationUtils.formatDate(new Date()));
	     try{
	    	 //TODO  调用业务层代码进行数据保存
	    	 
	    	 
	    	 
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	     }catch(Exception e){
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	    	 result.setDescribe("创建报事报修工单失败！:"+e.getMessage());
	    	 if(logger.isErrorEnabled()){
	    		 logger.error("创建报事报修工单失败！ :"+e.getMessage());
	    	 }
	     }
		return (JSONObject.fromObject(result)).toString();
	}
	
	/**
	 * 提交投诉建议工单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/suggest/create", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String caWoCreate(HttpServletRequest request, Model model){
		//创建map用来存储请求发送的数据进行保存
		 Map<String,String>  hashmap = new HashMap<String, String>();
		 Map<String, String[]> params = request.getParameterMap();  
	        for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = values[i];  
	                hashmap.put(key, value);
	            }  
	        }
	        //进行token验证
	        
	        
	        ResultVO    result = new ResultVO();
	        result.setServerDate(IntegrationUtils.formatDate(new Date()));
	     try{
	    	 //TODO  调用业务层代码进行数据保存
	    	 
	    	 
	    	 
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	     }catch(Exception e){
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	    	 result.setDescribe("创建投诉建议工单失败！:"+e.getMessage());
	    	 if(logger.isErrorEnabled()){
	    		 logger.error("创建投诉建议工单失败！ :"+e.getMessage());
	    	 }
	     }
		return (JSONObject.fromObject(result)).toString();
	}
	
	/**
	 * 查看报事报修工单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/affair/update", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String rpWoQuery(HttpServletRequest request, Model model){
		//创建map用来存储请求发送的数据进行保存
		 Map<String,String>  hashmap = new HashMap<String, String>();
		 Map<String, String[]> params = request.getParameterMap();  
	        for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = values[i];  
	                hashmap.put(key, value);
	            }  
	        }
	        //进行token验证
	        
	        
	        ResultVO    result = new ResultVO();
	        result.setServerDate(IntegrationUtils.formatDate(new Date()));
	     try{
	    	 //TODO  调用业务层代码进行数据保存
	    	 
	    	 
	    	 
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	     }catch(Exception e){
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	    	 result.setDescribe("查看报事报修工单失败！:"+e.getMessage());
	    	 if(logger.isErrorEnabled()){
	    		 logger.error("查看报事报修工单失败！ :"+e.getMessage());
	    	 }
	     }
		return (JSONObject.fromObject(result)).toString();
	}
	
	/**
	 * 查看投诉建议工单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/suggest/update", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String caWoQuery(HttpServletRequest request, Model model){
		//创建map用来存储请求发送的数据进行保存
		 Map<String,String>  hashmap = new HashMap<String, String>();
		 Map<String, String[]> params = request.getParameterMap();  
	        for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = values[i];  
	                hashmap.put(key, value);
	            }  
	        }
	        //进行token验证
	        
	        
	        ResultVO    result = new ResultVO();
	        result.setServerDate(IntegrationUtils.formatDate(new Date()));
	     try{
	    	 //TODO  调用业务层代码进行数据保存
	    	 
	    	 
	    	 
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	     }catch(Exception e){
	    	 result.setStatus(IntegrationConstans.RIGHT_MESSAGE_CODE);
	    	 result.setDescribe("查看投诉建议工单失败！:"+e.getMessage());
	    	 if(logger.isErrorEnabled()){
	    		 logger.error("查看投诉建议工单失败！ :"+e.getMessage());
	    	 }
	     }
		return (JSONObject.fromObject(result)).toString();
	}
}
