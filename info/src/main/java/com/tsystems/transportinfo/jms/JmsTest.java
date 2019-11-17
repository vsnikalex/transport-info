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

    private static final Logger log = Logger.getLogger(JmsTest.class.getName());

    // Set up all the default values
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/test";
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
     
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    ConnectionFactory cf;
 
    @Resource(lookup = "java:jboss/exported/jms/queue/test")
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
        Context namingContext = null;

        try {
            String userName = System.getProperty("username", DEFAULT_USERNAME);
            String password = System.getProperty("password", DEFAULT_PASSWORD);

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
            log.info("Attempting to acquire destination \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            try (JMSContext context = connectionFactory.createContext(userName, password)) {
                log.info("Sending message...");

                Message message = context.createTextMessage(
                        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.transportinfo.tsystems.com/\">\n" +
                           "   <soapenv:Header/>\n" +
                           "   <soapenv:Body>\n" +
                           "      <soap:echo>\n" +
                           "         <!--Optional:-->\n" +
                           "         <arg0>Hello!</arg0>\n" +
                           "      </soap:echo>\n" +
                           "   </soapenv:Body>\n" +
                           "</soapenv:Envelope>");
                message.setStringProperty("SOAPJMS_contentType", "text/xml");
                message.setStringProperty("SOAPJMS_requestURI", "http-remoting://127.0.0.1:8080");

                context.createProducer().send(destination, message);
            }
        } catch (NamingException e) {
            log.severe(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    log.severe(e.getMessage());
                }
            }
        }
    }
 
}
