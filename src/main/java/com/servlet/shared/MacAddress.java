package com.servlet.shared;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
//			map.put("ip_address", "");
//			map.put("mac_address", "");
			
		} catch (SocketException e){
				
//			map.put("ip_address", "");
//			map.put("mac_address", "");
				
		}
		return map;
	}
	
	public static HashMap<String, Object> getAllMacAddress() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		InetAddress ip;
		try {
				
			ip = InetAddress.getLocalHost();
			List<String> listMac = new ArrayList<String>();
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface ni = networkInterfaces.nextElement();
				byte[] hardwareAddress = ni.getHardwareAddress();
				if (hardwareAddress != null) {
					String[] hexadecimalFormat = new String[hardwareAddress.length];
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < hardwareAddress.length; i++) {
						hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
						sb.append(String.format("%02X%s", hardwareAddress[i], (i < hardwareAddress.length - 1) ? "-" : ""));
					}
					listMac.add(sb.toString());
				}
			}
			
			map.put("ip_address", ip.getHostAddress());
			map.put("mac_address", listMac);
				
		} catch (UnknownHostException e) {
			
//			e.printStackTrace();
//			map.put("ip_address", "");
//			map.put("mac_address", "");
			
		} catch (SocketException e){
				
//			map.put("ip_address", "");
//			map.put("mac_address", "");
				
		}
		return map;
	}
	
	public static boolean checkMacAddress(String macAddress) {
		HashMap<String, Object> map = getAllMacAddress();
		if(map.get("mac_address") != null) {
			List<String> listMac = (List<String>) map.get("mac_address"); 
			for(String s : listMac) {
				if(s.equals(macAddress)) {
					return true;
				}
			}
		}
		return false;
		
	}
}
