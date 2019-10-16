package com.dc.common.utils;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class UID {
	public static UID instance = null;

	private static final long ONE_STEP = 100000;
	private static long lastTime = System.currentTimeMillis();
	private static int count = 0;

	public synchronized static UID getInstanse() {
		if (instance == null) {
			instance = new UID();
		}
		return instance;
	}

	public synchronized String getUID() {
		if (count == ONE_STEP) {
			boolean done = false;
			while (!done) {
				long now = System.currentTimeMillis();
				if (now == lastTime) {
					try {
						Thread.sleep(1);
					} catch (java.lang.InterruptedException e) {
						now++;
					}
					continue;
				} else {
					lastTime = now;
					count = 0;
					done = true;
				}
			}
		}
		count++;
		String result=Integer.toString(count++);
		String zero="";	
		for(int i=5;i>result.length();i--)
			zero+="0";		
		result = lastTime + zero+result;
		result+=shortUUID();
		
		return result;
	}
	public String shortUUID(){
		UUID uuid=UUID.randomUUID();
		String sid=uuid.toString();
		sid=sid.replaceAll("-", "");   
        String[] chars = new String[]{   
            "10","11","12","13","14","15","16","17",   
            "18","19","20","21","22","23","24","25",
            "26","27","28","29","30","31","32","33",
            "34","35","36","37","38","39","40","41",
            "42","43","44","45","46","47","48","49",
            "50","51","52","53","54","55","56","57",
            "58","59","60","61","62","63","64","65",
            "66","67","68","69","70","71","72","73"
        }; 
        
        int hexLen = sid.length();   
        int subHexLen = hexLen / 8;   
        String[] ShortStr = new String[4];           
        for (int i = 0; i < subHexLen; i++) {   
            String outChars = "";   
            int j = i + 1;   
            String subHex = sid.substring(i * 8, j * 8);   
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);   
               
            for (int k = 0; k < 6; k++) {   
                int index = (int) (Long.valueOf("0000003D", 16) & idx);   
                outChars += chars[index];   
                idx = idx >> 5;   
            }   
            ShortStr[i] = outChars;   
        }
        sid=ShortStr[0];
        return sid;
	}
	
	public static String dateTimeUID(){
		StringBuilder dateUID=new StringBuilder();
		Calendar nowtime = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		
		dateUID.append(String.format("%04d", nowtime.get(Calendar.YEAR))); 
		dateUID.append(String.format("%02d", nowtime.get(Calendar.MONTH)+1)); 
		dateUID.append(String.format("%02d", nowtime.get(Calendar.DATE)));
		dateUID.append(String.format("%02d", nowtime.get(Calendar.HOUR_OF_DAY)));
		dateUID.append(String.format("%02d", nowtime.get(Calendar.MINUTE)));
		dateUID.append(String.format("%02d", nowtime.get(Calendar.SECOND)));
		dateUID.append(String.format("%03d", nowtime.get(Calendar.MILLISECOND)));
		return dateUID.toString();
	}
	
	public static String dateLongUID(){
		StringBuilder dateUID=new StringBuilder();
		Calendar nowtime = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		
		dateUID.append(String.format("%04d", nowtime.get(Calendar.YEAR))); 
		dateUID.append("-");
		dateUID.append(String.format("%02d", nowtime.get(Calendar.MONTH)+1)); 
		dateUID.append("-");
		dateUID.append(String.format("%02d", nowtime.get(Calendar.DATE)));
		dateUID.append("-");
		dateUID.append(String.format("%02d", nowtime.get(Calendar.HOUR_OF_DAY)));
		dateUID.append("-");
		dateUID.append(String.format("%02d", nowtime.get(Calendar.MINUTE)));
		return dateUID.toString();
	}
	public static String dateCommonUID(){
		StringBuilder dateUID=new StringBuilder();
		Calendar nowtime = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		dateUID.append(String.format("%04d", nowtime.get(Calendar.YEAR))); 
		dateUID.append("-");
		dateUID.append(String.format("%02d", nowtime.get(Calendar.MONTH)+1)); 
		dateUID.append("-");
		dateUID.append(String.format("%02d", nowtime.get(Calendar.DATE)));
		return dateUID.toString();
	}
	public static String dateShortUID(){
		StringBuilder dateUID=new StringBuilder();
		Calendar nowtime = Calendar.getInstance(TimeZone.getTimeZone("GMT+8")); 
		dateUID.append(String.format("%04d", nowtime.get(Calendar.YEAR))); 
		dateUID.append(String.format("%02d", nowtime.get(Calendar.MONTH)+1)); 
		dateUID.append(String.format("%02d", nowtime.get(Calendar.DATE)));
		return dateUID.toString();
	}
}
