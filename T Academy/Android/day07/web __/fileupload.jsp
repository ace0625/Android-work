<%@ page contentType="text/html; chartset=UTF-8" %>
<%@ page import= "java.io.*" %>
<%@ page import= "com.oreilly.servlet.*" %>
<%@ page import= "com.oreilly.servlet.multipart.*" %>
<%
request.setCharacterEncoding("UTF-8");
String dir = application.getRealPath("/");
String fDir = dir + "/down";
int max = 5*1024*1024;
String pname = "aaa";
String price = "bbb";
String fname = "ccc";
try{
	MultipartRequest mr = new MultipartRequest(request, fDir, max, "KSC5601" ,new DefaultFileRenamePolicy());
	pname = mr.getParameter("pname");
	price = mr.getParameter("price");
	fname = mr.getFilesystemName("fname");
}catch(Exception e){
}
%>
POST 파일 업로드 데이터 전송 Test 페이지<BR>
받은 상품명 <%= pname %> <BR>
받은 가격 <%= price %> <BR>
받은 상품이미지파일명 <%= fname %> <BR>
