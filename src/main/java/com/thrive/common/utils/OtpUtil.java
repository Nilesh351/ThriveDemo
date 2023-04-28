package com.thrive.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.jboss.aerogear.security.otp.Totp;

import com.thrive.ui.core.BaseTestPage;

public class OtpUtil extends BaseTestPage {

	public static String getAuthCode() {
		
		String query = "select auth_secret_key from users where username = '"+superAdminUser+"'";
		String secretKey = DBUtils.getResultFromPostgresDB(query);
		String newOtp = null;

		try {
			Totp totp = new Totp(secretKey);
			String otp = totp.now();
			if (otp.length() < 6) {
				newOtp = StringUtils.leftPad(otp, 6, "0");
				return  newOtp;
			} else {
				return otp;
			}

		} catch (Exception e) {
			return e.getMessage();

		}

	}

}
