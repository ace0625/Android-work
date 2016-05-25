<%@ page contentType="text/html; chartset=UTF-8" %>
<%
request.setCharacterEncoding("UTF-8");
%>
POST 로그인 데이터 전송 Test 페이지<BR>
받은 ID <%= request.getParameter("aid") %> <BR>
받은 암호 <%= request.getParameter("apw") %> <BR>
