package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contants {
	public static final String FORMAT_DATE="yyyy-MM-dd";
	public static Date formatDate(Date dateTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = sdf.format(dateTime);

			return sdf.parse(dateString);
		} catch (ParseException e) {
			return null;
		}

	}
}
