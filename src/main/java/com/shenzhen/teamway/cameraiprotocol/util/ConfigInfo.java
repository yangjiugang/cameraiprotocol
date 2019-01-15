package com.shenzhen.teamway.cameraiprotocol.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ConfigInfo {

	public static Properties getProperties() {

		Properties appPpt = new Properties();
		String filenamePath;
		String fp = System.getProperty("config");

		File file = new File(System.getProperty("config"));

		if (file.isDirectory()) {
			filenamePath = fp + "/mysql.properties";
		} else {
			filenamePath = "config/mysql.properties";
		}
		File f = new File(filenamePath);

		if (f.exists()) {
			FileInputStream bis = null;
			try {
				bis = new FileInputStream(f);
				appPpt.load(bis);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				bis = null;
			}
		} else {
			InputStream is = null;
			try {
				is = ResourceLoader.getResAsStream(filenamePath);
				appPpt.load(is);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				is = null;
			}
		}

		return appPpt;
	}

	public static Properties getConfig() {

		Properties properties = new Properties();

		String sysConfigFile = System.getProperty("config");

		File file = null;
		if (sysConfigFile != null) {
			file = new File(System.getProperty("config"));
		}
		if (file == null || !file.isDirectory()) {
			java.net.URL url = ConfigInfo.class.getResource("/config");
			if (url != null) {
				String path = url.getPath().replaceAll("%20", " ");
				file = new File(path);
			}
		}

		if (file != null && file.exists()) {
			List<File> files = getFileList(file);

			for (File pfile : files) {

				mergePropertiesIntoMap(readProperties(pfile.getPath()),
						properties);

			}
		}

		if (properties.get("host_ip") != null&& properties.get("host_ip").toString().trim().length() > 0) {
			properties.put("ip", properties.get("host_ip"));
		} else {

			properties.put("ip", NetUtil.getLocalIp());
		}

		return properties;
	}

	public static void mergePropertiesIntoMap(Properties props, Map map) {
		if (map == null) {
			throw new IllegalArgumentException("Map must not be null");
		}
		if (props != null) {
			for (Enumeration en = props.propertyNames(); en.hasMoreElements();) {
				String key = (String) en.nextElement();
				Object value = props.getProperty(key);
				if (value == null) {
					// Potentially a non-String value...
					value = props.get(key);
				}
				map.put(key, value);
			}
		}
	}

	private static Properties readProperties(String filenamePath) {

		Properties appPpt = new Properties();

		File f = new File(filenamePath);

		if (f.exists()) {
			FileInputStream bis = null;
			try {
				bis = new FileInputStream(f);
				appPpt.load(bis);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				bis = null;
			}
		} else {
			InputStream is = null;
			try {
				is = ResourceLoader.getResAsStream(filenamePath);
				appPpt.load(is);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				is = null;
			}
		}

		return appPpt;
	}

	public static List<File> getFileList(File pfile) {

		List<File> fileList = new ArrayList<File>();

		File[] files = pfile.listFiles();

		if (files != null) {

			for (File file : files) {

				if (file.getName().indexOf("properties") > 0) {

					fileList.add(file);
				}
			}
		}

		return fileList;
	}

}
