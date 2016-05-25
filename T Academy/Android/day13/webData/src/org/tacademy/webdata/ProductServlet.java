package org.tacademy.webdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String key = request.getParameter("key");
		String category = request.getParameter("category");
		byte[] arr = key.getBytes("iso-8859-1");
		String tKey = new String(arr,"UTF-8");
		System.out.println("tKey : " + tKey);
		Connection con = null;
		Statement stmt = null;
		ResultSet rst = null;
		String sql = "";		
		StringBuilder sb = new StringBuilder("select title,count,price,image,category  from product ");
		
		try{
//		    GenericObjectPool objectPool = new GenericObjectPool();
//		    DriverManagerConnectionFactory connectionFactory 
//		                    = new DriverManagerConnectionFactory("jdbc:mysql://192.168.201.169:3306/mysql", "root", "1234");
////		    DriverManagerConnectionFactory connectionFactory 
////            = new DriverManagerConnectionFactory("jdbc:mysql://192.168.202.146:3306/mysql", "root", "1234");
//		    new PoolableConnectionFactory(connectionFactory, objectPool, null, null, false, true);
//		    PoolingDriver driver = new PoolingDriver();
//		    driver.registerPool("/webdata", objectPool);
//		    System.out.println("product con1 : " + con);
//		    con = connectionFactory.createConnection();
			Class.forName("com.mysql.jdbc.Driver");
			  System.out.println("product con111 : " + con);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "1234");
		    System.out.println("product con2 : " + con);
		    if(!key.equals("empty")){
		    	sb.append("where title like '%" + tKey + "%' ");
		    	if(!category.equals("0")){
		    		sb.append("and category = '" + category + "'");
		    	}
		    }else{
		    	if(!category.equals("0")){
		    		sb.append("where category = '" + category + "'");
		    	}
		    }
		    sql = sb.toString();
			System.out.println("product con : " + con);
			System.out.println("sql : " + sql);
			
			stmt = con.createStatement();
			rst = stmt.executeQuery(sql);
			String image = "";
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<pList>");
			while(rst.next()){
				out.println("	<item>");
				out.println("		<title>" +rst.getString(1) + "</title>");
				out.println("		<count>" +rst.getString(2) + "</count>");
				out.println("		<price>" +rst.getString(3) + "</price>");
				image =rst.getString(4);
				if(image == null || image.trim().length() ==0){
					out.println("		<image />");
				}else{
					out.println("		<image>" + image + "</image>");
				}				
				out.println("		<category>" +rst.getString(5) + "</category>");
				out.println("	</item>");
			}
			out.println("</pList>");
			out.flush();
		}catch(Exception e){
			System.out.println("p error : " + e);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
