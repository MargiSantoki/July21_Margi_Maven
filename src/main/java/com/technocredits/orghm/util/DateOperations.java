package com.technocredits.orghm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperations {

	public static String getTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyy_HH_mm_ss");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		return timestamp;
	}
}
