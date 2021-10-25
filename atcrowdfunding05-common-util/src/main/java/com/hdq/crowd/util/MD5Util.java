package com.hdq.crowd.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public class MD5Util {
	/**
	 *
	 * @param inStr
	 * @return
	 */
	public static String string2MD5(String inStr) {

		//判断inStr字符串是否为空。抛异常
		if (inStr.length() == 0 || inStr == null){
			throw new RuntimeException(CrowdConstant.ATTR_NAME_EXCEPTION);
		}

		/*
		生成MD5对象
		 */
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			/*
		将传过来的字符串装换成字节
		 */
			byte[] byteArray = inStr.getBytes();
		/*
		然后使用MD5加密
		 */
			byte[] md5Bytes = md5.digest(byteArray);
			//以符号大小转换成big,即ASCII码值
			BigInteger bigInteger = new BigInteger(1, md5Bytes);
			int radix = 16;
			//按照16进制将biginteger转换为字符串
			String md5code = bigInteger.toString(radix).toUpperCase(Locale.ROOT);
			return md5code;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

	}
	/***
	 * 自己规则加密
	 * @param inStr
	 * @return
	 */
	public static String MD5(String inStr){
		String xy = "abc";
		String finalStr="";
		if(inStr!=null){
			String fStr = inStr.substring(0, 1);
			String lStr = inStr.substring(1, inStr.length());
			finalStr = string2MD5( fStr+xy+lStr);  
		}else{
			finalStr = string2MD5(xy);
		}
		return finalStr;  
	}
}
