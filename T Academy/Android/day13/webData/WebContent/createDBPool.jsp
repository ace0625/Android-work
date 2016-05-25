<%@page contentType="text/html; charset=euc-kr"%>
<%@page import="org.apache.commons.dbcp.*"%>
<%@page import="org.apache.commons.pool.impl.*"%>
<%@page import="java.sql.*"%>
<%
    GenericObjectPool objectPool = new GenericObjectPool();
    DriverManagerConnectionFactory connectionFactory 
                    = new DriverManagerConnectionFactory("jdbc:mysql://127.0.0.1:3306/mysql", "root", "1234");
    new PoolableConnectionFactory(connectionFactory, objectPool, null, null, false, true);
    PoolingDriver driver = new PoolingDriver();
    driver.registerPool("/webdata", objectPool);
    Connection con = connectionFactory.createConnection();
    System.out.println("con : " + con);
    Statement stmt = con.createStatement();
    
    ResultSet rst = stmt.executeQuery("select user from user");
    rst.next();
    System.out.println(rst.getString(1));
%>        
<HTML>
    <HEAD><TITLE>DBCP ����</TITLE></HEAD>
    <BODY>
        <H3>DBCP ����</H3>
        �����ͺ��̽� Ŀ�ؼ� Ǯ�� �����ϰ� ����Ͽ����ϴ�. <BR><BR>
        Ǯ �̸�: /db_tacademy_pool
    </BODY>
</HTML>
