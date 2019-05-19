package com.pactera.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class General {
    public static Configuration config;

    /**
     * 获取指定配置文件指定key对应的值（value）
     * 
     * @param 属性文件名字
     *            、文件中的key
     * @return 该key在属性文件中对应的值
     */
    public static String getConfigurationInformation(String propertiesFileName, String key) {
        String value = null;
        try {
            config = new PropertiesConfiguration(propertiesFileName);
            value = config.getString(key);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取指定配置文件的对象，可通过该对象的getString(String key)方法获取指定key对应的值（value）
     * 
     * @param 属性文件的名字
     * @return 指定配置文件的对象 返回值类型为org.apache.commons.configuration.Configuration
     */
    public static Configuration getConfiguration(String propertiesFileName) {
        try {
            config = new PropertiesConfiguration(propertiesFileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }
    
    /**
     * 补齐要生成的编码后若干位
     * @param length 
     * @param num
     * @return
     */
    public static String formatCode(int length, int num){
    	String str = String.valueOf(num);
    	int diff = length - str.length();
    	if (diff <= 0) {
			return str;
		}
    	StringBuilder builder = new StringBuilder();
    	for (int i = 0; i < diff; i++) {
			builder.append("0");
		}
    	builder.append(str);
    	return builder.toString();
    }

}
