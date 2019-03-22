// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyUtils.java

package net.top.framework.util;

import com.opensymphony.util.TextUtils;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class MyUtils extends StringUtils {

	public MyUtils() {
	}

	public static boolean isBlank(String str) {
		return str == null || str.trim().length() <= 0;
	}

	public static boolean isBlank(Object objs[]) {
		return objs == null || objs.length <= 0;
	}

	public static boolean isBlank(Collection obj) {
		return obj == null || obj.size() <= 0;
	}

	public static boolean isBlank(Set obj) {
		return obj == null || obj.size() <= 0;
	}

	public static boolean isBlank(Serializable obj) {
		return obj == null;
	}

	public static boolean isBlank(Map obj) {
		return obj == null || obj.size() <= 0;
	}

	public static String htmlEncode(String dist) {
		dist = TextUtils.htmlEncode(dist);
		dist = replace(dist, "\r\n", "<br>");
		dist = replace(dist, "\n", "<br>");
		return dist;
	}

	public static String join(String arg0, Collection arg1) {
		return TextUtils.join(arg0, arg1);
	}

	public static void main(String args[]) {
		System.out.println(MyUtils.class.getName());
	}

}
