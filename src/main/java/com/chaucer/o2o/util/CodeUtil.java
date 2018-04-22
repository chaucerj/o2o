package com.chaucer.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static Boolean checkVerifyCode(HttpServletRequest request) {
		String verifycodeExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifycodeActual = HttpServletRequestUtil.getString(request,
				"verifyCodeActual");
		if (verifycodeActual == null
				|| !verifycodeExpected.equals(verifycodeActual)) {
			return false;
		}
		return true;
	}

}
