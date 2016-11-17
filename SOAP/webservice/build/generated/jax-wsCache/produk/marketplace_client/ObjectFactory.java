
package marketplace_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the marketplace_client package. 
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

    private final static QName _Hello_QNAME = new QName("http://marketplace/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://marketplace/", "helloResponse");
    private final static QName _Init_QNAME = new QName("http://marketplace/", "init");
    private final static QName _InitResponse_QNAME = new QName("http://marketplace/", "initResponse");
    private final static QName _ProductOf_QNAME = new QName("http://marketplace/", "product_of");
    private final static QName _ProductOfResponse_QNAME = new QName("http://marketplace/", "product_ofResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Init }
     * 
     */
    public Init createInit() {
        return new Init();
    }

    /**
     * Create an instance of {@link InitResponse }
     * 
     */
    public InitResponse createInitResponse() {
        return new InitResponse();
    }

    /**
     * Create an instance of {@link ProductOf }
     * 
     */
    public ProductOf createProductOf() {
        return new ProductOf();
    }

    /**
     * Create an instance of {@link ProductOfResponse }
     * 
     */
    public ProductOfResponse createProductOfResponse() {
        return new ProductOfResponse();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Init }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "init")
    public JAXBElement<Init> createInit(Init value) {
        return new JAXBElement<Init>(_Init_QNAME, Init.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "initResponse")
    public JAXBElement<InitResponse> createInitResponse(InitResponse value) {
        return new JAXBElement<InitResponse>(_InitResponse_QNAME, InitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductOf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "product_of")
    public JAXBElement<ProductOf> createProductOf(ProductOf value) {
        return new JAXBElement<ProductOf>(_ProductOf_QNAME, ProductOf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductOfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "product_ofResponse")
    public JAXBElement<ProductOfResponse> createProductOfResponse(ProductOfResponse value) {
        return new JAXBElement<ProductOfResponse>(_ProductOfResponse_QNAME, ProductOfResponse.class, null, value);
    }

}
