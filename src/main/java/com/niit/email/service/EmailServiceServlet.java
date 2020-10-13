package com.niit.email.service;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EmailServiceServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		    resp.setContentType("text/html");
	        resp.getWriter().println("Hello world");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String strName = req.getParameter("name");
        String strMessage = req.getParameter("message");
        String strEmail = req.getParameter("email");
        String strSubject=req.getParameter("subject");
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String msgBody = "Hi "+strName + "\n" + strMessage + "\n";
        
         try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("craftor0001@gmail.com","Admin"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(strEmail, strName));
            msg.setSubject(strSubject);
            msg.setText(msgBody);
            Transport.send(msg);
            
        } catch (Exception e) {
            resp.setContentType("text/plain");
            resp.getWriter().println("Something went wrong. Please try again.");
            throw new RuntimeException(e);
        }
         
        resp.setContentType("text/html");
        resp.getWriter().println("Your email is sent. <a href='sendemail.html'>Compose Another Email</a>");
	}
}