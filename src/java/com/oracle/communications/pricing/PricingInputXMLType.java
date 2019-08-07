
package com.oracle.communications.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PricingInputXMLType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricingInputXMLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xmlBinaryString" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="UserContext" type="{http://www.oracle.com/communications/pricing}UserContextType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricingInputXMLType", propOrder = {
    "xmlBinaryString",
    "userContext"
})
public class PricingInputXMLType {

    @XmlElement(required = true)
    protected byte[] xmlBinaryString;
    @XmlElement(name = "UserContext", required = true)
    protected UserContextType userContext;

    /**
     * Gets the value of the xmlBinaryString property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getXmlBinaryString() {
        return xmlBinaryString;
    }

    /**
     * Sets the value of the xmlBinaryString property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setXmlBinaryString(byte[] value) {
        this.xmlBinaryString = value;
    }

    /**
     * Gets the value of the userContext property.
     * 
     * @return
     *     possible object is
     *     {@link UserContextType }
     *     
     */
    public UserContextType getUserContext() {
        return userContext;
    }

    /**
     * Sets the value of the userContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserContextType }
     *     
     */
    public void setUserContext(UserContextType value) {
        this.userContext = value;
    }

}
