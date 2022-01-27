package com.servlet.shared;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GlobalFunc {
	public static Timestamp setFormatDate(Timestamp ts,String format) throws ParseException {
		//yyyy-MM-dd
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String s = formatter.format(ts);
		Date datecurrentts = formatter.parse(s);
        Timestamp tscurrentts = new Timestamp(datecurrentts.getTime());
		return tscurrentts;
	}
	
	
	public static Timestamp addDays(Timestamp ts,int days) throws ParseException {
		 Calendar cal = Calendar.getInstance();
	     cal.setTime(ts);// w ww.  j ava  2  s  .co m
	     cal.add(Calendar.DATE, days); //minus number would decrement the days
	     return new Timestamp(cal.getTime().getTime());
	}
	
	public static Timestamp addDaysByType(Timestamp ts,int days,String type) throws ParseException {
		 Calendar cal = Calendar.getInstance();
	     cal.setTime(ts);// w ww.  j ava  2  s  .co m
	     if(type.toUpperCase().equals("DATE")) {
	    	 cal.add(Calendar.DATE, days); //minus number would decrement the days
	     }else if(type.toUpperCase().equals("MONTH")) {
	    	 cal.add(Calendar.MONTH, days); //minus number would decrement the days
	     }else {
	    	 //YEAR
	    	 cal.add(Calendar.YEAR, days); //minus number would decrement the days
	     }
	     
	     return new Timestamp(cal.getTime().getTime());
	}
}
