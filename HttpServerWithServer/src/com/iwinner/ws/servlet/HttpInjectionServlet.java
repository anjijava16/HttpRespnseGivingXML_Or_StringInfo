package com.iwinner.ws.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HttpInjectionServlet
 */
public class HttpInjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpInjectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getPathInfo()+"  "+request.getQueryString());
		String req1=request.getParameter("param1");
		String req2=request.getParameter("param2");
		System.out.println("Query::"+request.getQueryString()+"  "+request.getHeaderNames());
		System.out.println("From Servlet");
		System.out.println(req1+"    "+req2);
		response.getWriter().write("End Of Servlet1234567890");
	}

	/**
	 * @see HttpServlet#execute(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getInputStream().read());
		String req1=request.getParameter("param1");
		String req2=request.getParameter("param2");
		System.out.println("Query::"+request.getQueryString()+"  "+request.getHeaderNames());
		System.out.println("From Servlet");
		System.out.println(req1+"    "+req2);
		response.getWriter().write("End Of Servlet1234567890");
	}
}
