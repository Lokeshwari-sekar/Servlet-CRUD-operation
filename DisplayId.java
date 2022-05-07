package com.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayId
 */
@WebServlet("/DisplayId")
public class DisplayId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("sid"));
		Connection conn=null;
		PreparedStatement pst;
		ResultSet rst;
		try
		{
			conn=DbConnection.getconnection();
			String sel="select * from studentdata where sid=?";
			pst=conn.prepareStatement(sel);
			pst.setInt(1, id);
			rst=pst.executeQuery();
			
			if(rst.next())
			{
			out.println("<!DOCTYPE html>");
			out.println("<head><title>Student Details</title></head>");
			out.println("<body>");
			out.println("<table border='1'>");
			out.println("<tr><th>SID</th><th>SNAME</th><th>SPASS</th><th>SAGE</th><th>SFEES</th></tr>");
			out.println("<tr><td>"+rst.getInt(1)+"</td>");
			out.println("<td>"+rst.getString(2)+"</td>");
			out.println("<td>"+rst.getString(3)+"</td>");
			out.println("<td>"+rst.getInt(4)+"</td>");
			out.println("<td>"+rst.getFloat(5)+"</td></tr>");
		    out.println("</table>");
			out.println("</body>");
			out.println("</html>");
			}
			else
			{
				out.println("Not Exists");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	}


