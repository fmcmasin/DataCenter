package cn.datacenter.commonUtil;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**
 * 集成公共类
 * @author meisheng
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
			boolean timeOut = validTimeOut(time);
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
	public static boolean validTimeOut(String createTime) {
		//假设等0天超时，当前的时间-生成token的时间
		return (new Date()).getTime() - Long.parseLong(createTime) > 86400000;
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
}