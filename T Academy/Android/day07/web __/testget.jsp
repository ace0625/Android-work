<%@ page contentType="text/html; chartset=UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
GET 데이터 전송 Test 페이지<BR>
에코문자열<BR>
받은 이름 <%= request.getParameter("aname") %> <BR>
받은 주소 <%= request.getParameter("addr") %> <BR>
