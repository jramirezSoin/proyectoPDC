
package com.oracle.communications.pricing;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenericSeverityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GenericSeverityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NCName">
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="FATAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GenericSeverityType")
@XmlEnum
public enum GenericSeverityType {

    ERROR,
    FATAL;

    public String value() {
        return name();
    }

    public static GenericSeverityType fromValue(String v) {
        return valueOf(v);
    }

}
