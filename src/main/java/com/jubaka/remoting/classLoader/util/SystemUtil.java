package com.jubaka.remoting.classLoader.util;

import com.jubaka.remoting.classLoader.util.funct.P0;
import com.jubaka.remoting.classLoader.util.funct.P1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


public class SystemUtil {
	static BufferedReader br;
	public static String readLine() {
		if (br==null) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		try {
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void onReturn(final P1<String> p1) {
		ThreadUtil.runStrong(new P0() {
			public void e() {
				while (true) {
					try {
						String readLine = readLine();
						p1.e(readLine);
					} catch (Exception e1) {
						return;
					}
				}
			}
		});
	}
}

