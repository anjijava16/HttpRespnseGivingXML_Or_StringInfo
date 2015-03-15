package com.iwinner.ws.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.iwinner.ws.form.Customer;

/**
 * Servlet implementation class InjectResponseServlet
 */
public class InjectResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final QName _Response_QNAME = new QName("http://www.mobixell.com", "response");
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InjectResponseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request,response);
	}

	/**
	 * @see HttpServlet#execute(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    responseXML(request,response);
		  	}
	public void responseXML(HttpServletRequest request,HttpServletResponse response){
	try{
	response.setContentType("text/xml;charset=UTF-8");
	PrintWriter writer = response.getWriter();
	writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	writer.append("<sales_description>");
    writer.append("<sale>");
    writer.append("<sale_id>").append("10").append("</sale_id>");
    writer.append("<home_id>").append("30").append("</home_id>");
    writer.append("<agent_id>").append("40").append("</agent_id>");
    writer.append("</sale>");
    writer.append("</sales_description>");
    response.getWriter().write(writer.toString());
	}catch(Exception ed){
		
	}
}
	
public void responseBackUp(HttpServletRequest request, HttpServletResponse response){
	try{
	response.setContentType("text/xml");
 OutputStream os = response.getOutputStream();
	
	Customer customer = new Customer();
	customer.setId(100);
	customer.setName("XXX");
	customer.setAge(29);
	File file = new File("D:\\file.xml");
	JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	// output pretty printed
	jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
	jaxbMarshaller.marshal(customer, file);
	jaxbMarshaller.marshal(customer, System.out);
	PrintStream result=System.out;
	String res=result.toString();
	System.out.println("From Servlet "+res+"  "+" Result::: "+System.out.toString());
	response.getWriter().write("");
	}catch(Exception e){
		e.printStackTrace();
	}

}
	private static void marshallResponse(Customer schemaResponse, OutputStream os)
		     
		  {
		try{
		JAXBContext JAXB_CONTEXT = JAXBContext.newInstance(Customer.class);
		     Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
		   marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
		     marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
		     marshaller.setProperty("jaxb.encoding", schemaResponse);
		     JAXBElement<Customer> responseElem = new JAXBElement(_Response_QNAME, Customer.class, null, schemaResponse);
		     marshaller.marshal(responseElem, os);
		    }catch(Exception e){
		    	
		    }
		  }
}            
