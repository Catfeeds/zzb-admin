package com.hcb.zzb.util;

import java.math.BigDecimal;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;

import net.sourceforge.pinyin4j.PinyinHelper;  
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;  
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;  
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
  
public class ChineseToEnglish {  
    // 将汉字转换为全拼  
    public static String getPingYin(String src) {  
  
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
          
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches(  
                        "[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
                    t4 += t2[0];  
                } else  
                    t4 += java.lang.Character.toString(t1[i]);  
            }  
            // System.out.println(t4);  
            return t4;  
        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
            e1.printStackTrace();  
        }  
        return t4;  
    }  
  
    // 返回中文的首字母  
    public static String getPinYinHeadChar(String str) {  
  
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        }  
        return convert;  
    }  
  
    // 将字符串转移为ASCII码  
    public static String getCnASCII(String cnStr) {  
        StringBuffer strBuf = new StringBuffer();  
        byte[] bGBK = cnStr.getBytes();  
        for (int i = 0; i < bGBK.length; i++) {  
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));  
        }  
        return strBuf.toString();  
    }  
  
    public static void main(String[] args) {  
        System.out.println(getPingYin("陈扬"));  
        System.out.println(getPingYin("庾澄庆"));  
        System.out.println(getPingYin("翕动懂"));  
        System.out.println(getPinYinHeadChar("画画"));  
        String aa = getPinYinHeadChar("画画");
        aa = aa.toUpperCase();
        
        
        /*String bbb = getPingYin("陈");
        String[] aaa = {"chen","chen0","chen1"};
        String ddd = bbb;
        int employeeNum = 0;
		while(ddd.equals(aaa[employeeNum])&&employeeNum<aaa.length-1){
			String ccc = new String();
			ccc = bbb;
			ccc = ccc + String.valueOf(employeeNum);
			ddd = ccc;
			employeeNum++;
			System.out.println(ddd+"---");
		}
		if(employeeNum!=0){
			bbb = bbb + String.valueOf(employeeNum);
		}else{
			bbb = bbb;
		}
		System.out.println(bbb+"--");*/
    	long minute = 13;
		double minHour = minute/60;
		long hour = 0;
		BigDecimal bg = new BigDecimal(minHour);  
		minHour = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		minHour = minHour + hour;
		System.out.println(minHour);
    }  
}  