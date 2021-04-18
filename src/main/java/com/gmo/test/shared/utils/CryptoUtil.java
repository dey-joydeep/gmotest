package com.gmo.test.shared.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

	public static String createMD5Hash(String plainText) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes());
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
