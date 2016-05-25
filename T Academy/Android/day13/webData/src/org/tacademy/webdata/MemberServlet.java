package org.tacademy.webdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String str = "";
		if(action.equals("login")){
			str = doLogin(request);
		}else if(action.equals("insert")){
			str = doInsert(request);
		}else if( action.equals("check") ){
			str = doIDCheck(request);
		}
		
		out.println(str);
		out.flush();
		
	}
	String doInsert(HttpServletRequest request){
		String msg  = "";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String comment = request.getParameter("comment");
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(null, ?, ?, ?, ?, ?, ?, now()) ;";		
		int cnt = 0;
		System.out.println("name : " + name);
		System.out.println("address : " + address);
		try{
//			GenericObjectPool objectPool = new GenericObjectPool();
//			DriverManagerConnectionFactory connectionFactory 
//			= new DriverManagerConnectionFactory("jdbc:mysql://192.168.201.169:3306/mysql", "root", "1234");
//			//		    DriverManagerConnectionFactory connectionFactory 
//			//            = new DriverManagerConnectionFactory("jdbc:mysql://192.168.202.146:3306/mysql", "root", "1234");
//			new PoolableConnectionFactory(connectionFactory, objectPool, null, null, false, true);
//			PoolingDriver driver = new PoolingDriver();
//			driver.registerPool("/webdata", objectPool);
//			con = connectionFactory.createConnection();
			Class.forName("com.mysql.jdbc.Driver");
			  System.out.println("product con111 : " + con);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
		    System.out.println("product con2 : " + con);
			System.out.println("connect con : " + con);
			System.out.println("sql : " + sql);
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, address);
			pstmt.setString(6, comment);
			cnt = pstmt.executeUpdate();
			if(cnt == 1){
				msg = name;
			}else{
				msg = "fail";
			}
		}catch(Exception e){
			System.out.println("InsertChcek error : " + e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException e){}
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){}			
		}
		return msg ;
	}
	String doLogin(HttpServletRequest request){
		String msg  = "";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Connection con = null;
		Statement stmt = null;
		ResultSet rst = null;
		String sql = String.format("select name from member where id ='%s' and pw = '%s'", id, pw);		


		try{
//			GenericObjectPool objectPool = new GenericObjectPool();
//			DriverManagerConnectionFactory connectionFactory 
//			= new DriverManagerConnectionFactory("jdbc:mysql://192.168.202.146:3306/mysql", "root", "1234");
//			//		    DriverManagerConnectionFactory connectionFactory 
//			//            = new DriverManagerConnectionFactory("jdbc:mysql://192.168.202.146:3306/mysql", "root", "1234");
//			new PoolableConnectionFactory(connectionFactory, objectPool, null, null, false, true);
//			PoolingDriver driver = new PoolingDriver();
//			driver.registerPool("/webdata", objectPool);
//			con = connectionFactory.createConnection();
			Class.forName("com.mysql.jdbc.Driver");
			  System.out.println("product con111 : " + con);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
		    System.out.println("product con2 : " + con);
		    
			System.out.println("coonection  con : " + con);
			System.out.println("sql : " + sql);

			stmt = con.createStatement();
			rst = stmt.executeQuery(sql);
			if(rst.next()){
				msg = rst.getString(1);
			}else{
				msg = "fail";
			}
		}catch(Exception e){
			System.out.println("LoginChcek error : " + e);
		}finally{
			try{
				if(rst != null){
					rst.close();
				}
			}catch(SQLException e){}
			try{
				if(stmt != null){
					stmt.close();
				}
			}catch(SQLException e){}
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){}			
		}
		return msg ;
	}String doIDCheck(HttpServletRequest request){
		String msg  = "";
		String id = request.getParameter("id");
		Connection con = null;
		Statement stmt = null;
		ResultSet rst = null;
		String sql = String.format("select name from member where id ='%s'", id);		


		try{
//			GenericObjectPool objectPool = new GenericObjectPool();
//			DriverManagerConnectionFactory connectionFactory 
//			= new DriverManagerConnectionFactory("jdbc:mysql://192.168.202.146:3306/mysql", "root", "1234");
//			//		    DriverManagerConnectionFactory connectionFactory 
//			//            = new DriverManagerConnectionFactory("jdbc:mysql://192.168.202.146:3306/mysql", "root", "1234");
//			new PoolableConnectionFactory(connectionFactory, objectPool, null, null, false, true);
//			PoolingDriver driver = new PoolingDriver();
//			driver.registerPool("/webdata", objectPool);
//			con = connectionFactory.createConnection();
			Class.forName("com.mysql.jdbc.Driver");
			  System.out.println("product con111 : " + con);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
		    System.out.println("product con2 : " + con);
			System.out.println("id con : " + con);
			System.out.println("sql : " + sql);

			stmt = con.createStatement();
			rst = stmt.executeQuery(sql);
			if(rst.next()){
				msg = rst.getString(1);
			}else{
				msg = "fail";
			}
		}catch(Exception e){
			System.out.println("LoginChcek error : " + e);
		}finally{
			try{
				if(rst != null){
					rst.close();
				}
			}catch(SQLException e){}
			try{
				if(stmt != null){
					stmt.close();
				}
			}catch(SQLException e){}
			try{
				if(con != null){
					con.close();
				}
			}catch(SQLException e){}			
		}
		return msg ;
	}
}
