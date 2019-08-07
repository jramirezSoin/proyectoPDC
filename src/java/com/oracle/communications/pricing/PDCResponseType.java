
package com.oracle.communications.pricing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * PDC Webservice Response.
 * 
 * <p>Java class for PDCResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PDCResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="changeSet" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validationError" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pricingResult" type="{http://www.oracle.com/communications/pricing}PersistedPricingObject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PDCResponseType", propOrder = {
    "status",
    "changeSet",
    "validationError",
    "pricingResult"
})
public class PDCResponseType {

    @XmlElement(required = true)
    protected String status;
    @XmlElement(type = Long.class)
    protected List<Long> changeSet;
    protected List<String> validationError;
    protected List<PersistedPricingObject> pricingResult;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the changeSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the changeSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChangeSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getChangeSet() {
        if (changeSet == null) {
            changeSet = new ArrayList<Long>();
        }
        return this.changeSet;
    }

    /**
     * Gets the value of the validationError property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validationError property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidationError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValidationError() {
        if (validationError == null) {
            validationError = new ArrayList<String>();
        }
        return this.validationError;
    }

    /**
     * Gets the value of the pricingResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricingResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricingResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersistedPricingObject }
     * 
     * 
     */
    public List<PersistedPricingObject> getPricingResult() {
        if (pricingResult == null) {
            pricingResult = new ArrayList<PersistedPricingObject>();
        }
        return this.pricingResult;
    }

}
