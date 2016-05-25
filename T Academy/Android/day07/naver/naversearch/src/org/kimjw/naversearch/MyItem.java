package org.kimjw.naversearch;

import java.io.Serializable;

public class MyItem implements Serializable {

	private static final long serialVersionUID = 1385012938433446540L;
	
	private String k;
	private String S;
	private String v;
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getS() {
		return S;
	}
	public void setS(String s) {
		S = s;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
}
