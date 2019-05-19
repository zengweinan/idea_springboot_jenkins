package com.pactera.util;

import org.apache.commons.lang.StringEscapeUtils;

import java.security.MessageDigest;

public class MD5Util {
	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString().toUpperCase();  
  
    }  
  
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }
    
    /**
     * 对字符串md5加密(大写+数字)
     *
     * @param str 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    
    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
 
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
  
    // 测试主函数  
    public static void main(String args[]) {
    	//<Attribute><data>{"IncidentMemo":"444444444444444444","CusRelationshipID":763,"IncidentDetails":[{"ReplyContent":"5555555555555","RespondentsMan":"黄药师","RepDate":"2018-09-12 11:07:08","ReplyResult":"2","ServiceQuality":"5","IncidentID":"132905000000000633","ReplyMan":"艾雯偲"},{"ReplyContent":"222222222222","RespondentsMan":"黄药师","RepDate":"2018-09-12 11:07:08","ReplyResult":"2","ServiceQuality":"5","IncidentID":"132905000000000634","ReplyMan":"艾雯偲"}]}</data></Attribute>2018091220180917KG
        String s = new String("<Attribute><data>{\"IncidentMemo\":\"444444444444444444\",\"CusRelationshipID\":763,\"IncidentDetails\":[{\"ReplyContent\":\"5555555555555\",\"RespondentsMan\":\"黄药师\",\"RepDate\":\"2018-09-12 11:07:08\",\"ReplyResult\":\"2\",\"ServiceQuality\":\"5\",\"IncidentID\":\"132905000000000633\",\"ReplyMan\":\"艾雯偲\"},{\"ReplyContent\":\"222222222222\",\"RespondentsMan\":\"黄药师\",\"RepDate\":\"2018-09-12 11:07:08\",\"ReplyResult\":\"2\",\"ServiceQuality\":\"5\",\"IncidentID\":\"132905000000000634\",\"ReplyMan\":\"艾雯偲\"}]}</data></Attribute>2018091220180917KG");  
        System.out.println("原始：" + s);
        s = StringEscapeUtils.unescapeJavaScript(s);
        //149A72A69026C7A256509931F0A338A2
        //149a72a69026c7a256509931f0a338a2
        System.out.println("MD5后：" + MD5(s));  
        //System.out.println("加密的：" + convertMD5(s));  
        //System.out.println("解密的：" + convertMD5(convertMD5(s)));  
  
    }  
}
