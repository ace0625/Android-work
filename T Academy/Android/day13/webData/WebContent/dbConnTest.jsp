<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="java.sql.*"%>
<HTML>
    <HEAD><TITLE>Conn. DB</TITLE></HEAD>
    <BODY>
    <H3>DB ���� �׽�Ʈ</H3>
    <%
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql", "root", "1234");
        if (conn != null) {
            out.println("db_tacademy �����ͺ��̽��� �����߽��ϴ�.<BR>");
            conn.close();
            out.println("db_tacademy �����ͺ��̽����� ������ �������ϴ�.<BR>");
        }
        else {
            out.println("db_tacademy �����ͺ��̽��� ������ �� �����ϴ�.<BR>");
        }
    %>
    </BODY>
</HTML>
