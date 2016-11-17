
package marketplace_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for product_of complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="product_of"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="penjual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "product_of", propOrder = {
    "penjual"
})
public class ProductOf {

    protected String penjual;

    /**
     * Gets the value of the penjual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenjual() {
        return penjual;
    }

    /**
     * Sets the value of the penjual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenjual(String value) {
        this.penjual = value;
    }

}
