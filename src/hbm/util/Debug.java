package hbm.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Debug {

	// Default show
	public static void show(String str) {
		System.out.println(str);
	}
	
	// Error show
	public static void error(String str) {
		System.err.println(str);
	}
	
	/*
	 * Show List to DB-Table
	 */
	public static void showListToTable(List<Map<String, Object>> list) {
		Map<String, Integer> colsLength = new HashMap<>();
		Iterator<String> itr = null;
		String keyAttr = null;
		String value = null;
		int width = -1;
		int barLength = 0;
		StringBuffer bar = null;
		
		// Get KeySet
		if(!list.isEmpty()) {
			itr = list.get(0).keySet().iterator();
			while(itr.hasNext()) {
				keyAttr = itr.next();
				colsLength.put(keyAttr, keyAttr.length());
			}
		} else {
			System.out.println("There is no item in list.");
			return;
		}

		// Get Columns Max Length
		for(int i = 0; i < list.size(); i++) {
			itr = colsLength.keySet().iterator();
			while(itr.hasNext()) {
				keyAttr = itr.next();
				int valueLen = -1;
				if(list.get(i).get(keyAttr) != null) {
					try {
//						valueLen = list.get(i).get(keyAttr).toString().length();
						valueLen = list.get(i).get(keyAttr).toString().getBytes("MS949").length;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if(valueLen > colsLength.get(keyAttr))
						colsLength.replace(keyAttr, valueLen);
				}
			}
		}
		
		// Print Column Names
		itr = colsLength.keySet().iterator();
		while(itr.hasNext()) {
			keyAttr = itr.next();
			value = colsLength.get(keyAttr).toString();
			width = colsLength.get(keyAttr);
			for(int j = 0; j < value.length(); j++) {
				if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", value.subSequence(j, j+1)))
					width -= 1;
			}
			System.out.printf(" %"+width+"s |", keyAttr);
			barLength += width + 3;
		}
		System.out.println();
		
		// Print Division Bar
		bar = new StringBuffer();
		for(int i = 0; i < barLength; i++)
			bar.append("-");
		System.out.println(bar.toString());

		// Print Column Values
		for(int i = 0; i < list.size(); i++) {
			itr = colsLength.keySet().iterator();
			while(itr.hasNext()) {
				keyAttr = itr.next();
				value = null;
				width = colsLength.get(keyAttr);
				if(list.get(i).get(keyAttr) != null) {
					value = list.get(i).get(keyAttr).toString();
					for(int j = 0; j < value.length(); j++) {
						if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", value.subSequence(j, j+1)))
							width -= 1;
					}
					System.out.printf(" %"+width+"s |", value);
				} else {
					value = "null";
					System.out.printf(" %"+width+"s |", value);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
