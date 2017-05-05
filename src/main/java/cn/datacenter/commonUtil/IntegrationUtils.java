package cn.datacenter.commonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import cn.datacenter.pojo.Verify;
import cn.datacenter.pojo.Msg.VerifyVO;
import net.sf.json.JSONObject;

/**
 * 集成公共类
 * @author masin
 * @since 2017/2/15
 */
public class IntegrationUtils {

	/**
	 * 判断当前字符串是否为空
	 * @param str
	 * @return true表示是空的，false表示不为空。
	 */
	public static boolean isEmptyStr(String str) {
		if(str != null && str.trim().length() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 加密token并返回
	 * @param sysId，登录的账号
	 * @return
	 */
	public static String getToken(String userId, String time) {
		String token = null;
		try {
			token = CipherPlusBase64.encrypt(userId + "," + time, true);//用于生成密钥
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	/**
	 * 格式化日期时间
	 * @param date 需要被格式化的对象
	 * @return
	 */
	public static String formatDate(Date date) {
		if(date != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		}
		return "";//如果传入的Date为空，则返回空字符串
	}
	
	/**
	 * 验证token是否有效
	 * @param token 传入的Token参数
	 * @return 返回token中包含的用户信息
	 * @throws Exception 表示无效的token
	 */
	public static String validToken(String token) throws Exception{
		if(isEmptyStr(token)) {
			throw new Exception("无效的令牌" + token);
		}
		String userId = "";
		String str = null;
		try {
			str = CipherPlusBase64.decrypt(token, true);
			if(str.indexOf(",") <= 0) {//此种情况不符合自定的token格式
				throw new Exception("无效的令牌" + token);
			}
			String[] split = str.split(",");
			userId = split[0];
			String time = split[1];//token的格式为：add Token Time is :时间
			if(!isNumber(time)) {//如果token不为空，但不是数字则token有误
				throw new Exception("无效的令牌，时间戳不正确" + token);
			}
			boolean timeOut = validTimeOut(Long.valueOf(time));
			if(timeOut) {
				throw new Exception("无效的令牌，令牌已超时" + token);
			}
			if(userId == null || userId.trim().length() == 0) {
				throw new Exception("无效的令牌，用户Id为空" + token);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("无效的令牌" + e.getMessage());
		}
		return userId.toUpperCase();
	}
	
	/**
	 * 验证token是否超过有效期
	 * @param createTime
	 * @return true表示已经超时，false表示没有超时
	 */
	public static boolean validTimeOut(long createTime) {
		//读取token失效的时间转化为毫秒数 默认1天
		
		long timeout = Long.valueOf(PropertiesUtil.getStringByKey("timeout"));
		
		return (new Date()).getTime() - createTime > timeout*3600*1000;
	}
	
	/**
	 * @param c 目标对象的class类型
	 * @param object  数据源json对象
	 * @return 目标对象的实例
	 * @author waibao1
	 * 注：此方法只能适用于一层的json数据
	 */
	public static <T> Object jsonToObjectVo(Class<T> c, JSONObject object) {
		Object obj = null;
		Field[] fields = c.getDeclaredFields();
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = object.keys();
		try{
			obj = c.newInstance();//实例化对象
			while(iterator.hasNext()) {
				String key = iterator.next();
				for(int i = 0, len = fields.length; i < len; i++) {
					Field field = fields[i];
					String fieldName = field.getName();
					field.setAccessible(true);//开启私有属性访问权限
					if(key.equalsIgnoreCase(fieldName)) {
						field.set(obj, object.getString(key));
						break;
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 验证是否是数字字符串
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if(isEmptyStr(str)) {//如果str为空，则返回false
			return false;
		}
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 对指定的字符串进行token加密并返回
	 * 
	 * @return 返回token加密
	 */
	public static String getToken(String key) {
		String token = null;
		try {
			token = CipherPlusBase64.encrypt(key, true);// 用于生成密钥
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	/**
	 * 依据指定的生成规则来生成token验证码
	 * @param type
	 * @return
	 */
	public static  String getToken(VerifyVO  verify){
		//拼装参数生成16为的md5 加密字符串
		String token = null;
		try {
			String from = verify.getFroms();
			String random= String.valueOf(new java.util.Random().nextInt(101));  //生成随机三位数
			String project = verify.getProject();
			String key   = verify.getKey();
			token = MD5Util.string16MD5(from+random+project+key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	public static String validToken(Verify  verify) throws Exception{
		if(verify==null){
			throw new Exception("无效的令牌，请传入正确的令牌");
		}
		//令牌存在时，验证有效性
		boolean timeOut = validTimeOut(verify.getCreatetime());
		if(timeOut) {
			throw new Exception("无效的令牌，令牌已超时" + verify.getToken());
		}
		return null;
	}
	/**
	 * 解析GET  和 POST  請求的參數     application/x-www-form-urlencoded 表单格式
	 * @param request
	 * @return
	 */
	public static Map<String,String>  getDataFromRequest(HttpServletRequest request){
		
		Map<String, String> params_GET = new HashMap<String, String>();
		Map<String, String[]> params_POST = new HashMap<String, String[]>();
		Map<String,String>  hashmap = new HashMap<String, String>();
		if("GET".equals(request.getMethod())){
			String data = request.getParameter("json");
			JSONObject  jasonObject = JSONObject.fromObject(data);
			params_GET = (Map<String, String>)jasonObject;
			for (String key : params_GET.keySet()) {  
				String value = params_GET.get(key);  
				hashmap.put(key, value);
			}  
		}else if("POST".equals(request.getMethod())){
		//创建map用来存储请求发送的数据进行保存
			params_POST = request.getParameterMap();  
			for (String key : params_POST.keySet()) {  
				String[] values = params_POST.get(key);  
				for (int i = 0; i < values.length; i++) {  
					String value = values[i];  
					hashmap.put(key, value);
				}  
			}
		}
		return hashmap;
		
	}
	/**
	 * 解析GET  和 POST  請求的參數     application/json   json格式
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static Map<String,String>  getJsonDataFromRequest(HttpServletRequest request) throws Exception{
		Map<String, String> params_GET = new HashMap<String, String>();
		Map<String,String>  hashmap = new HashMap<String, String>();
		if("GET".equals(request.getMethod())){
			String data = request.getParameter("json");
			JSONObject  jasonObject = JSONObject.fromObject(data);
			params_GET = (Map<String, String>)jasonObject;
			for (String key : params_GET.keySet()) {  
				String value = params_GET.get(key);  
				hashmap.put(key, value);
			}  
		}else if("POST".equals(request.getMethod())){
		    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));  
	        String line = null;  
	        StringBuilder sb = new StringBuilder();  
		    while((line = br.readLine())!=null){  
		       sb.append(line);  
		    }  
		    String reqBody = sb.toString();
		    JSONObject  jsondata  = JSONObject.fromObject(reqBody);
		    hashmap = (Map<String,String>)jsondata;
		}
		return  hashmap;
		
	}
}