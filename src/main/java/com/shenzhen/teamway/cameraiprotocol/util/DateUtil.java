package com.shenzhen.teamway.cameraiprotocol.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date getDateAfterYears(int years) {
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+years);
		return curr.getTime();
	}
}
