
package com.tsystems.transportinfo.soap;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "NotificationsServiceLocal", targetNamespace = "http://soap.transportinfo.tsystems.com/", wsdlLocation = "http://ti-info:8080/info/NotificationsServiceLocal?wsdl")
public class NotificationsServiceLocal
    extends Service
{

    private final static URL NOTIFICATIONSSERVICELOCAL_WSDL_LOCATION;
    private final static WebServiceException NOTIFICATIONSSERVICELOCAL_EXCEPTION;
    private final static QName NOTIFICATIONSSERVICELOCAL_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "NotificationsServiceLocal");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ti-info:8080/info/NotificationsServiceLocal?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NOTIFICATIONSSERVICELOCAL_WSDL_LOCATION = url;
        NOTIFICATIONSSERVICELOCAL_EXCEPTION = e;
    }

    public NotificationsServiceLocal() {
        super(__getWsdlLocation(), NOTIFICATIONSSERVICELOCAL_QNAME);
    }

    public NotificationsServiceLocal(WebServiceFeature... features) {
        super(__getWsdlLocation(), NOTIFICATIONSSERVICELOCAL_QNAME, features);
    }

    public NotificationsServiceLocal(URL wsdlLocation) {
        super(wsdlLocation, NOTIFICATIONSSERVICELOCAL_QNAME);
    }

    public NotificationsServiceLocal(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NOTIFICATIONSSERVICELOCAL_QNAME, features);
    }

    public NotificationsServiceLocal(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NotificationsServiceLocal(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Notifications
     */
    @WebEndpoint(name = "HttpNotificationsImplPort")
    public Notifications getHttpNotificationsImplPort() {
        return super.getPort(new QName("http://soap.transportinfo.tsystems.com/", "HttpNotificationsImplPort"), Notifications.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Notifications
     */
    @WebEndpoint(name = "HttpNotificationsImplPort")
    public Notifications getHttpNotificationsImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.transportinfo.tsystems.com/", "HttpNotificationsImplPort"), Notifications.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NOTIFICATIONSSERVICELOCAL_EXCEPTION!= null) {
            throw NOTIFICATIONSSERVICELOCAL_EXCEPTION;
        }
        return NOTIFICATIONSSERVICELOCAL_WSDL_LOCATION;
    }

}
