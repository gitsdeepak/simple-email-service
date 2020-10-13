package com.niit.email.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties; 
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session; 
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class ReceiveEmailServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(ReceiveEmailServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		    resp.setContentType("text/plain");
	       doPost(req, resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp)
	            throws IOException {
		PrintWriter out = resp.getWriter();
	        Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);
	        try {
	            MimeMessage message = new MimeMessage(session, req.getInputStream());
	            Object content = message.getContent();
	            out.println("Received an email message (" + message.getContentType() + "): " + content);
	            log.warning("Received an email message (" + message.getContentType() + "): " + content);
	        }   
	         catch (MessagingException e) {
	            log.warning("MessagingException: " + e);
	        }
	    }
}