package com.ucap.ms.base.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * 工具类
 * @author lizhiyin
 */
public class BaseTools {
	private static Logger logger = Logger.getLogger(BaseTools.class);
	
	/**
	 * 格式化以字符串存储的数字
	 * @param strNum 数字字符串
	 * @param decimalDigits 小数位数
	 * @param prefix 前缀
	 * @return 格式化后的字符串
	 */
	public static String formatStrNum(String strNum, int decimalDigits, String prefix)
	{
		try 
		{
			if (strNum == null) return "";
			else
				return prefix + String.format("%1$,1." + decimalDigits + "f", Double.valueOf(strNum.trim()));
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage(), e);
			return "";
		}
	}
	/**
	 * 逗号分隔的字符串转List,类型:String
	 * @param str 逗号分隔的字符串
	 * @return List
	 */
	public static List<String> strToList(String str)
	{
		if (checkEmpty(str)) return new ArrayList<String>();
		String[] strs = str.split(",");
		List<String> result = new ArrayList<String>();
		for (int i = 0;i < strs.length;)
		{
			String s = strs[i];
			if(s.indexOf("(") >= 0 
					&& i + 1 < strs.length
					&& (strs[i + 1].indexOf(")") >= 0 && strs[i + 1].indexOf("(") < 0))
			{//括号内含一个逗号的处理：合并
				String r = s + "," + strs[i + 1];
				int posAs1 = r.indexOf(" as ");
				int posAs2 = r.indexOf(" AS ");
				if(posAs1 > 0) r = r.substring(posAs1 + 4);
				else if(posAs2 > 0) r = r.substring(posAs2 + 4);
				result.add(r);
				i += 2;
			}
			else
			{
				int posAs1 = s.indexOf(" as ");
				int posAs2 = s.indexOf(" AS ");
				if(posAs1 > 0) s = s.substring(posAs1 + 4);
				else if(posAs2 > 0) s = s.substring(posAs2 + 4);
				result.add(s);
				i++;
			}
		}
		return result;
	}
	/**
	 * 逗号分隔的字符串转List,类型:Long
	 * @param str 逗号分隔的字符串
	 * @return List
	 */
	public static List<Long> strToLongList(String str)
	{
		if (checkEmpty(str)) return new ArrayList<Long>();
		String[] strs = str.split(",");
		List<Long> result = new ArrayList<Long>();
		for (String s : strs)
		{
			result.add(Long.valueOf(s));
		}
		return result;
	}
	/**
	 * List转逗号分隔的字符串
	 * @param list List
	 * @return String
	 */
	public static String listToStr(List list)
	{
		String str = "";
		
		if(list != null && list.size() > 0) 
		{
			for (Object obj : list)
			{
				str += "," + String.valueOf(obj);
			}
			str = str.substring(1);
		}
		
		return str;
	}
	/**
	 * 处理字符串，若空则返回默认值
	 * @param str 字符串
	 * @param defaultStr 默认值
	 * @return String
	 */
	public static String getNullDefault(String str, String defaultStr)
	{
		if (checkEmpty(str)) return defaultStr;
		return str;
	}

	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return boolean true/false
	 */
	public static boolean checkEmpty(String str)
	{
		return BaseValidUtil.isNull(str);
	}
	
	/**
	 * 处理字符串,若为null,返回空串
	 * @param str 字符串
	 * @return String
	 */
	public static String processNull(Object str)
	{
		if(str == null) return "";
		return String.valueOf(str);
	}
	
	/**
	 * 根据格式：yyyy-MM-dd HH-mm:ss输出当前时间
	 */
	public static String getTimeNow()
	{
		try
		{
			return getFormatTimeNow("yyyy-MM-dd HH:mm:ss");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			return "";
		}
	}
	
	/**
	 * 根据参数格式化输出当前时间
	 * @param strFormat 日期格式
	 */
	public static String getFormatTimeNow(String strFormat)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(strFormat);
			return format.format(Calendar.getInstance().getTime());
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			return "";
		}
	}
	
	/**
	 * 根据参数格式化输出指定时间
	 * @param strTime			时间文本
	 * @param strFormatBefore	时间文本输入格式
	 * @param strFormatAfter	时间文本输出格式
	 * @return	格式化输出的时间
	 */
	public static String getFormatTime(String strTime,String strFormatBefore,String strFormatAfter)
	{
		if(checkEmpty(strTime)) return "";
		try
		{
			SimpleDateFormat formatBefore = new SimpleDateFormat(strFormatBefore);
			SimpleDateFormat formatAfter = new SimpleDateFormat(strFormatAfter);
			
			return formatAfter.format(formatBefore.parse(strTime).getTime());
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			return "";
		}
	}
	
    /**
	 * 使用Md5加密密码
	 * @param password 明码
	 * @return 密码
	 */
	public static String MD5(String password)
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String newStr = toHex(md5.digest(password.getBytes("UTF-8")));
			return newStr;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
    /**
     * byte数组转16进制字符串
     * @param buffer byte数组
     * @return 16进制字符串
     */
	private static String toHex(byte[] buffer)
	{
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++)
		{
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}
	
	/**
	 * 从classpath下配置文件获取配置属性
	 * @param propName 配置属性名
	 * @return 属性值
	 */
	public static String getCommonProp(String propName)
	{
		try 
		{
			InputStream is = BaseTools.class.getResourceAsStream("/common.properties");
			Properties prop = new Properties();
			prop.load(is);
			
			if(is != null) is.close();
			
			String value = prop.getProperty(propName,"");
			value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
			
			return value;
				
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage(),e);
			return "";
		}
	}

    
    /**
     * 修改了BeanUtils的的属性拷贝逻辑，仅拷贝不相等的属性，并由
     * 调用方决定是否拷贝Null属性
     * @param dest	目标对象
     * @param orig	来源对象
     * @param copyNull 是否复制null属性
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyProperties(Object dest, Object orig, boolean copyNull)
            throws IllegalAccessException, InvocationTargetException 
    {
    	if(orig == null) return;
    	
        PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(orig);

        for (int i = 0; i < origDescriptors.length; i++) 
        {
            String name = origDescriptors[i].getName();
            if ("class".equals(name)) 
            {
                continue;
            }
            if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) 
            {
                try 
                {
                    Object value = PropertyUtils.getSimpleProperty(orig, name);
                    if (copyNull || value != null)
                    {
                        if(value == null)
                            BeanUtils.copyProperty(dest, name, value);
                        else
                        {
                            Object destValue = PropertyUtils.getSimpleProperty(dest, name);
                            if(!value.equals(destValue))
                                BeanUtils.copyProperty(dest, name, value);
                        }
                    }
                } 
                catch (NoSuchMethodException e) {}
            }
        }
    }

    /**
     * 比较两个字符串的相识度
     * 核心算法：用一个二维数组记录每个字符串是否相同，如果相同记为0，不相同记为1，每行每列相同个数累加
     * 则数组最后一个数为不相同的总数，从而判断这两个字符的相识度
     *
     * @param str
     * @param target
     * @return
     */
    private static int compare(String str, String target) {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }


    /**
     * 获取最小的值
     */
    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     */
    public static float getSimilarityRatio(String str, String target) {
        int max = Math.max(str.length(), target.length());
        return 1 - (float) compare(str, target) / max;
    }

	public static void main(String[] args) {
		System.out.println(MD5("2019-07-11_06:19:17.227本市非税系统yjeypjjx"));
	}


}
