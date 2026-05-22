package com.servlet.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GlobalFunc {
	public static long getDiffDate(Long datefrom,Long dateto) {
		long diffInMillies = Math.abs(dateto - datefrom);
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}
	
	public static String getDateLongToString(Long date,String format) throws ParseException {
		//dd-MMM-yyyy
		Timestamp currentts = new Timestamp(date);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String s = new SimpleDateFormat(format).format(currentts);
		return s;
	}
	
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

	public static Long addDays(Long lgdate,int days) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(lgdate));// w ww.  j ava  2  s  .co m
		cal.add(Calendar.DATE, days); //minus number would decrement the days
		return cal.getTime().getTime();
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
	
	public static boolean checkString(String value,boolean bolehkosong) {
		if(value == null) {
			return false;
		}else if(value.trim().equals("") && !bolehkosong) {
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isDate(Long strNum) {
		if (strNum == null) {
	        return false;
	    }
		
		try {
	        new Timestamp(strNum);
	    } catch (Exception nfe) {
	        return false;
	    }
		return true;
	}

	public static boolean checkIsDecimal(double nilai) {
		//jika true, berati bukan decimal
		//jika false , decimal. Ex, 3.1,2.3 dst
		double nilaiComma = round(nilai,2);
		return nilaiComma % 1 == 0;
	}

	private static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static double jumlahDesimal(double value, int places) {
		String strvalue  = String.valueOf(value);
		if(strvalue.contains(".")){
			String[] arrSplit = strvalue.split("\\.");
			String number = arrSplit[0];
			String desimal = arrSplit[1];
			if(desimal.length() > places){
				desimal = desimal.substring(0, places);
			}
			double finalValue = Double.valueOf(number+"."+desimal);
			return finalValue;
		}
		return value;

	}

	public static Double pembulatanNilai(Double nilai, boolean isdown, int numberdesimal){
		//contoh hasil
//		2.3445445  → desimal 1 → 2.3
//		2.3999999  → desimal 1 → 2.3  (tidak dibulatkan ke 2.4)
//		2.3445445  → desimal 2 → 2.34
//		1.005      → desimal 2 → 1.00
		if (nilai == null) return 0.0;
		if (numberdesimal < 0) return nilai;

		BigDecimal bd = new BigDecimal(Double.toString(nilai));
		bd = bd.setScale(numberdesimal, RoundingMode.DOWN);
		return bd.doubleValue();

//		double pembagian = 10;
//		if(numberdesimal == 2){
//			pembagian = 100;
//		}else if(numberdesimal == 3){
//			pembagian = 1000;
//		}
//
//		String[] splitComma = String.valueOf(nilai).split("\\.");
//		String valNilai = String.valueOf(nilai);
//		if(splitComma.length > numberdesimal){
//			int start = 0;
//			int end = numberdesimal+1;
//			String desimal = splitComma[1] != null?new String(splitComma[1]).substring(start,end):"0";
//			valNilai = splitComma[0]+"."+desimal;
//		}
////        if(isdown){
////            return Math.floor(Double.valueOf(valNilai) * pembagian) / pembagian;
////        }
////        return Math.ceil(Double.valueOf(valNilai) * pembagian) / pembagian;
//
//		return Math.round(Double.valueOf(valNilai) * pembagian) / pembagian;
//
	}

	public static Double convertGramToKG(Double nilaigr){
		if(nilaigr != null){
			return nilaigr / 1000;
		}
		return 0.0;
	}

	public static HashMap<String,Integer> getMonthYearDate(Long time){
		HashMap<String,Integer> hash = new HashMap<>();
		if(time != null){
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time);
			hash.put("year",cal.get(Calendar.YEAR));
			//0 = januari, 1 = februari dst.., jadi jika ingin sesuai bulan kalender month di plus 1
			hash.put("month",cal.get(Calendar.MONTH) + 1);
			hash.put("date",cal.get(Calendar.DATE));
			return hash;
		}
		return null;
	}

	public static Timestamp getTimeForCalcSaldo(Timestamp date){
		//untuk tsCd ini sengaja, soalnya pas query di sql, walaupun sama, tapi ga ke detect
		//jadi solusinya di tambahin sedikit, sekitar beberapa 1 detik, biar sedikit lebih gede
		Timestamp tsCd = date;

		// ambil millisecond
		int ms = tsCd.getNanos() / 1000000;

		// reset ke detik
		tsCd.setNanos(0);

		// jika ada ms → naikkan, 1 detik = 1000
		if (ms > 0) {
			tsCd.setTime(tsCd.getTime() + 1000);
		}
		return tsCd;
	}
}
