package com.uns.ftn.master.e2.app.security.utils;

import static java.net.InetAddress.getByName;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressUtil {

	private InetAddressUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isInRange(String ipToTest, String ipBegin, String ipEnd) {

		long ipLo;
		long ipHi;
		long ipTest;
		try {
			ipLo = ipToLong(getByName(ipBegin));
			ipHi = ipToLong(getByName(ipEnd));
			ipTest = ipToLong(getByName(ipToTest));
		} catch (UnknownHostException e) {
			return false;
		}

		return ipTest >= ipLo && ipTest <= ipHi;

	}

	private static long ipToLong(InetAddress ip) {
		byte[] octets = ip.getAddress();
		long result = 0;
		for (byte octet : octets) {
			result <<= 8;
			result |= octet & 0xff;
		}
		return result;
	}

}
