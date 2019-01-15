package com.shenzhen.teamway.cameraiprotocol.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {

	public static String getLocalIp() {

		try {
			InetAddress addr = InetAddress.getLocalHost();

			return addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}
