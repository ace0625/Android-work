package com.example.naverproject;

import java.io.Serializable;

public class Item implements Serializable {
	
	private static final long serialVersionUID = 8300320365067065596L;
	
	private String K;
	private String S;
	private String V;
	
	public String getK() {
		return K;
	}
	public void setK(String k) {
		K = k;
	}
	public String getS() {
		return S;
	}
	public void setS(String s) {
		S = s;
	}
	public String getV() {
		return V;
	}
	public void setV(String v) {
		V = v;
	}
	@Override
	public String toString() {
		return "Item [K=" + K + ", S=" + S + ", V=" + V + "]";
	}
	
	

}

//<R1>
//<K>하수오</K>
//<S>+</S>
//<V>210</V>
//</R1>