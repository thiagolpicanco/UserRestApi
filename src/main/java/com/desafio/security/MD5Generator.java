package com.desafio.security;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;


public final class MD5Generator {

	public static String getMd5HashCode(String requestParameters) {
		return DigestUtils.md5DigestAsHex(requestParameters.getBytes());
	}

}