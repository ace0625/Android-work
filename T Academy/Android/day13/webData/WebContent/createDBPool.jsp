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
    <HEAD><TITLE>DBCP 생성</TITLE></HEAD>
    <BODY>
        <H3>DBCP 생성</H3>
        데이터베이스 커넥션 풀을 생성하고 등록하였습니다. <BR><BR>
        풀 이름: /db_tacademy_pool
    </BODY>
</HTML>
