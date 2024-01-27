package com.egt.user.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.egt.user.constants.CommonConstants;
import com.egt.user.exception.InvalidUserRequest;

public class CommonUtils {

	public static boolean isBlankOrNull(String value) {
		return value == null || value.trim().length() == 0;
	}

	public static Long getAsLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return 0L;
		} catch (Exception e) {
			return 0L;
		}
	}

	public static String[] extractExtensionAndNumber(String phoneNumber) throws InvalidUserRequest {
		if (isBlankOrNull(phoneNumber))
			throw new InvalidUserRequest(CommonConstants.ERROR_INVALID_PHONE_NUMBER);
		var pattern = Pattern.compile("^\\+(\\d+)(?: (\\d+))?$");
		var matcher = pattern.matcher(phoneNumber);

		if (matcher.matches()) {
			var extension = matcher.group(1);
			var mainNumber = matcher.group(2);
			return new String[] { extension, mainNumber };
		} else {
			throw new InvalidUserRequest(CommonConstants.ERROR_INVALID_PHONE_NUMBER);
		}
	}

	public static String[] splitNames(String userName) throws InvalidUserRequest {
		if (isBlankOrNull(userName))
			throw new InvalidUserRequest(CommonConstants.ERROR_INVALID_USER_NAME);
		String names[] = userName.split("\\s");
		if (names.length <= 3)
			return names;
		var mn = new StringBuilder("");
		String middleName = null;
		for (int i = 1; i < names.length - 1; i++) {
			mn.append(names[i]);
			if (mn.length() > 29) {
				// trim middle name to 29 length
				middleName = mn.substring(0, 29);
				break;
			}
		}
		if (middleName == null)
			middleName = mn.toString();
		return new String[] { names[0], middleName.trim(), names[names.length - 1] };
	}

	public static Date getDate(String date, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

}
