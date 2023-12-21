package com.banking.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String convertDateToddMMyy(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
}