package com.tsystems.transportinfo.jms;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Logger;

@WebServlet("/jms")
public class JmsTest extends HttpServlet {

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

        TextMessage message = session.createTextMessage(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.transportinfo.tsystems.com/\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <soap:echo>\n" +
                    "         <arg0>Hello!</arg0>\n" +
                    "      </soap:echo>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>");
        message.setStringProperty("SOAPJMS_contentType", "text/xml");
        message.setStringProperty("SOAPJMS_requestURI", "tcp://ti-activemq:61616");

        publisher.send(message);
    }

}
