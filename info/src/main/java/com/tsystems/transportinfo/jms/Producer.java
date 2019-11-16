package com.tsystems.transportinfo.jms;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/jms")
public class Producer extends HttpServlet {
     
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:/ConnectionFactory")
    ConnectionFactory cf;
 
    @Resource(lookup = "java:/queue/TestQueue")
    private Queue queue;
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            send();
            PrintWriter out = response.getWriter();
            out.println("Message sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
 
    public void send() throws Exception {
		Connection connection =  null;
		connection = cf.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer publisher = session.createProducer(queue);
		connection.start();
		
		TextMessage message = session.createTextMessage("Hello!");
		publisher.send(message);  
    }
 
}
