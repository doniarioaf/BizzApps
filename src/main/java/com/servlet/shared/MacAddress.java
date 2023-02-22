package com.servlet.shared;

import java.util.HashMap;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MacAddress {
	
	public static HashMap<String, String> getAddress() {
		HashMap<String, String> map = new HashMap<String, String>();
		InetAddress ip;
		try {
				
			ip = InetAddress.getLocalHost();
//			System.out.println("Current IP address : " + ip.getHostAddress());
			
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();
				
//			System.out.print("Current MAC address : ");
				
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
//			System.out.println(sb.toString());
			map.put("ip_address", ip.getHostAddress());
			map.put("mac_address", sb.toString());
				
		} catch (UnknownHostException e) {
			
//			e.printStackTrace();
			map.put("ip_address", "");
			map.put("mac_address", "");
			
		} catch (SocketException e){
				
			map.put("ip_address", "");
			map.put("mac_address", "");
				
		}
		return map;
	}
}
