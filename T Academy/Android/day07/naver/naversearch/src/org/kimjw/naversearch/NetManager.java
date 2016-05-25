package org.kimjw.naversearch;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.params.ConnRouteParamBean;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetManager {
	static HttpClient httpclient = null;
	//HttpClient 객체 넘겨주는 메소드
	public static HttpClient getHttpClient(){
		if(httpclient == null){
			//timeout 10초설정
			HttpParams hp = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(hp,10000);
			HttpConnectionParams.setSoTimeout(hp,10000) ;    
			httpclient = new DefaultHttpClient(hp);
			//이곳의 프록시 서버 IP, 포트 설정(방화벽 안에 있는 경우 아래 두줄 삽입)
			HttpHost proxy = new HttpHost("192.168.61.252",8080,"http");
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		return httpclient ;
	}
	public static HttpGet getGet(String url){ 	//HttpGet 헤더설정메소드
		if(httpclient == null){	httpclient =  getHttpClient();}
		HttpGet httpGet =  new HttpGet( url );
		httpGet.setHeader("Connection","Keep-Alive"); 
		httpGet.setHeader("Accept","application/xml"); 
		httpGet.setHeader("Content-Type","application/xml"); 
		httpGet.setHeader("User-Agent","ANDROID");
		httpGet.setHeader("Pragma","no-cache"); 
		httpGet.setHeader("Cache-Control","no-cache,must-reval!idate"); 
		httpGet.setHeader("Expires","0"); 
		return httpGet;
	}
	//HttpPost xml 접근 객체 넘겨주는 메소드
	public static HttpPost getPost1(String url){
		if(httpclient == null){
			httpclient =  getHttpClient();
		}
		HttpPost post =  new HttpPost( url );
		post.setHeader("Connection","Keep-Alive"); 
		post.setHeader("Accept","application/xml"); 
		post.setHeader("Content-Type","application/xml"); 
		post.setHeader("User-Agent","ANDROID"); 
		return post;
	}
	//HttpPost 객체 넘겨주는 메소드
	public static HttpPost getPost(String url){
		if(httpclient == null){
			httpclient =  getHttpClient();
		}
		HttpPost post =  new HttpPost( url );
		post.setHeader("User-Agent","ANDROID"); 
		return post;
	}
}
