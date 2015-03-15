package com.iwinner.ws.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import com.iwinner.ws.form.Customer;

/**
 * Servlet implementation class InjectionServlet
 */
public class InjectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JAXBContext JAXB_CONTEXT = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InjectionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#execute(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		    
		System.out
				.println("#################Start of  InjectionServlet #################");
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Customer customer = (Customer)jaxbUnmarshaller.unmarshal(request.getInputStream());

		System.out.println(customer.getAge()+"  "+customer.getId()+"  "+customer.getName());
		/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is=request.getInputStream();
		pipe(request.getInputStream(), baos);
		baos.close();
	    byte[] requestBytes = baos.toByteArray();
		ByteArrayInputStream xmlFileIs = new ByteArrayInputStream(requestBytes);
		System.out.println(is.toString()+"  ");
		System.out.println("Some Text Here"+request.getInputStream()+"  "+is.read());
		new SchemaValidator("").validate(xmlFileIs, "AdGatewayRequest.xsd");
*/
	//	requestXmlIs = new ByteArrayInputStream(requestBytes);
		System.out.println(writer.toString());
		//response.getWriter().write(writer.toString());
		System.out.println("#################End of  InjectionServlet #################");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void generateXML(){
			  
		  }
	public static void pipe(InputStream is, OutputStream os) throws IOException {
		byte buffer[] = new byte[8192];
		int len;
		do {
			len = is.read(buffer, 0, 8192);
			if (len > 0) {
				os.write(buffer, 0, len);
			}
		} while (len >= 0);
	}
}
