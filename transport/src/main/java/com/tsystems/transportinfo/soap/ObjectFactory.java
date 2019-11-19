
package com.tsystems.transportinfo.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tsystems.transportinfo.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdateDriversStat_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "updateDriversStat");
    private final static QName _UpdateTrucksStatResponse_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "updateTrucksStatResponse");
    private final static QName _UpdateTrucksStat_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "updateTrucksStat");
    private final static QName _DriversStat_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "driversStat");
    private final static QName _UpdateDriversStatResponse_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "updateDriversStatResponse");
    private final static QName _TrucksStat_QNAME = new QName("http://soap.transportinfo.tsystems.com/", "trucksStat");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tsystems.transportinfo.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateTrucksStatResponse }
     * 
     */
    public UpdateTrucksStatResponse createUpdateTrucksStatResponse() {
        return new UpdateTrucksStatResponse();
    }

    /**
     * Create an instance of {@link UpdateTrucksStat }
     * 
     */
    public UpdateTrucksStat createUpdateTrucksStat() {
        return new UpdateTrucksStat();
    }

    /**
     * Create an instance of {@link UpdateDriversStat }
     * 
     */
    public UpdateDriversStat createUpdateDriversStat() {
        return new UpdateDriversStat();
    }

    /**
     * Create an instance of {@link TrucksStat }
     * 
     */
    public TrucksStat createTrucksStat() {
        return new TrucksStat();
    }

    /**
     * Create an instance of {@link DriversStat }
     * 
     */
    public DriversStat createDriversStat() {
        return new DriversStat();
    }

    /**
     * Create an instance of {@link UpdateDriversStatResponse }
     * 
     */
    public UpdateDriversStatResponse createUpdateDriversStatResponse() {
        return new UpdateDriversStatResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDriversStat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.transportinfo.tsystems.com/", name = "updateDriversStat")
    public JAXBElement<UpdateDriversStat> createUpdateDriversStat(UpdateDriversStat value) {
        return new JAXBElement<UpdateDriversStat>(_UpdateDriversStat_QNAME, UpdateDriversStat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTrucksStatResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.transportinfo.tsystems.com/", name = "updateTrucksStatResponse")
    public JAXBElement<UpdateTrucksStatResponse> createUpdateTrucksStatResponse(UpdateTrucksStatResponse value) {
        return new JAXBElement<UpdateTrucksStatResponse>(_UpdateTrucksStatResponse_QNAME, UpdateTrucksStatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTrucksStat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.transportinfo.tsystems.com/", name = "updateTrucksStat")
    public JAXBElement<UpdateTrucksStat> createUpdateTrucksStat(UpdateTrucksStat value) {
        return new JAXBElement<UpdateTrucksStat>(_UpdateTrucksStat_QNAME, UpdateTrucksStat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DriversStat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.transportinfo.tsystems.com/", name = "driversStat")
    public JAXBElement<DriversStat> createDriversStat(DriversStat value) {
        return new JAXBElement<DriversStat>(_DriversStat_QNAME, DriversStat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDriversStatResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.transportinfo.tsystems.com/", name = "updateDriversStatResponse")
    public JAXBElement<UpdateDriversStatResponse> createUpdateDriversStatResponse(UpdateDriversStatResponse value) {
        return new JAXBElement<UpdateDriversStatResponse>(_UpdateDriversStatResponse_QNAME, UpdateDriversStatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrucksStat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.transportinfo.tsystems.com/", name = "trucksStat")
    public JAXBElement<TrucksStat> createTrucksStat(TrucksStat value) {
        return new JAXBElement<TrucksStat>(_TrucksStat_QNAME, TrucksStat.class, null, value);
    }

}
