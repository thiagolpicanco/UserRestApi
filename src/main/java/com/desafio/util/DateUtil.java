package com.desafio.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final Integer HALF_HOUR = 30;

	/**
	 * Método responsavel por obter data com acrescimo de 30 minutos
	 * 
	 * @param date
	 * @return Data adiantada 30min
	 */
	public static Date getDateAfter30Min(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, HALF_HOUR);
		return cal.getTime();

	}

}
