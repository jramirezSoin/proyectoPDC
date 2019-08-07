
package com.oracle.communications.pricing;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.oracle.communications.pricing package. 
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

    private final static QName _PDCResponse_QNAME = new QName("http://www.oracle.com/communications/pricing", "PDCResponse");
    private final static QName _PricingException_QNAME = new QName("http://www.oracle.com/communications/pricing", "PricingException");
    private final static QName _UpdatePublishPricingInputXML_QNAME = new QName("http://www.oracle.com/communications/pricing", "UpdatePublishPricingInputXML");
    private final static QName _CreatePricingInputXML_QNAME = new QName("http://www.oracle.com/communications/pricing", "CreatePricingInputXML");
    private final static QName _CreatePublishPricingInputXML_QNAME = new QName("http://www.oracle.com/communications/pricing", "CreatePublishPricingInputXML");
    private final static QName _UpdatePricingInputXML_QNAME = new QName("http://www.oracle.com/communications/pricing", "UpdatePricingInputXML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.oracle.communications.pricing
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PricingInputXMLType }
     * 
     */
    public PricingInputXMLType createPricingInputXMLType() {
        return new PricingInputXMLType();
    }

    /**
     * Create an instance of {@link PDCResponseType }
     * 
     */
    public PDCResponseType createPDCResponseType() {
        return new PDCResponseType();
    }

    /**
     * Create an instance of {@link PricingExceptionType }
     * 
     */
    public PricingExceptionType createPricingExceptionType() {
        return new PricingExceptionType();
    }

    /**
     * Create an instance of {@link PersistedPricingObject }
     * 
     */
    public PersistedPricingObject createPersistedPricingObject() {
        return new PersistedPricingObject();
    }

    /**
     * Create an instance of {@link GenericExceptionType }
     * 
     */
    public GenericExceptionType createGenericExceptionType() {
        return new GenericExceptionType();
    }

    /**
     * Create an instance of {@link UserContextType }
     * 
     */
    public UserContextType createUserContextType() {
        return new UserContextType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PDCResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/communications/pricing", name = "PDCResponse")
    public JAXBElement<PDCResponseType> createPDCResponse(PDCResponseType value) {
        return new JAXBElement<PDCResponseType>(_PDCResponse_QNAME, PDCResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingExceptionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/communications/pricing", name = "PricingException")
    public JAXBElement<PricingExceptionType> createPricingException(PricingExceptionType value) {
        return new JAXBElement<PricingExceptionType>(_PricingException_QNAME, PricingExceptionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingInputXMLType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/communications/pricing", name = "UpdatePublishPricingInputXML")
    public JAXBElement<PricingInputXMLType> createUpdatePublishPricingInputXML(PricingInputXMLType value) {
        return new JAXBElement<PricingInputXMLType>(_UpdatePublishPricingInputXML_QNAME, PricingInputXMLType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingInputXMLType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/communications/pricing", name = "CreatePricingInputXML")
    public JAXBElement<PricingInputXMLType> createCreatePricingInputXML(PricingInputXMLType value) {
        return new JAXBElement<PricingInputXMLType>(_CreatePricingInputXML_QNAME, PricingInputXMLType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingInputXMLType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/communications/pricing", name = "CreatePublishPricingInputXML")
    public JAXBElement<PricingInputXMLType> createCreatePublishPricingInputXML(PricingInputXMLType value) {
        return new JAXBElement<PricingInputXMLType>(_CreatePublishPricingInputXML_QNAME, PricingInputXMLType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PricingInputXMLType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.oracle.com/communications/pricing", name = "UpdatePricingInputXML")
    public JAXBElement<PricingInputXMLType> createUpdatePricingInputXML(PricingInputXMLType value) {
        return new JAXBElement<PricingInputXMLType>(_UpdatePricingInputXML_QNAME, PricingInputXMLType.class, null, value);
    }

}
