package com.chaucer.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static Boolean checkVerifyCode(HttpServletRequest request) {
		String verifycodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifycodeActural = HttpServletRequestUtil.getString(request, "verifyCodeActural");
		if(verifycodeActural==null||!verifycodeExpected.equals(verifycodeActural)){
			return false;
		}
		return true;
	}

}
