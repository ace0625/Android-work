<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.sql.*"%>
<HTML>
    <HEAD><TITLE>Conn. DB</TITLE></HEAD>
    <BODY>
    <H3>DB 연결 테스트</H3>
    <%
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql", "root", "1234");
        if (conn != null) {
            out.println("db_tacademy 데이터베이스로 연결했습니다.<BR>");
            conn.close();
            out.println("db_tacademy 데이터베이스로의 연결을 끊었습니다.<BR>");
        }
        else {
            out.println("db_tacademy 데이터베이스로 연결할 수 없습니다.<BR>");
        }
    %>
    </BODY>
</HTML>
