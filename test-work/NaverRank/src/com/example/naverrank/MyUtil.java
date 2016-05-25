package com.example.naverrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyUtil {
	public static String getData(InputStream is){
		String temp="";
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while((temp = br.readLine())!=null){
				sb.append(temp).append("\n");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return sb.toString();
	}
}
