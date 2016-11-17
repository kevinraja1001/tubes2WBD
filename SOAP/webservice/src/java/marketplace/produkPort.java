/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import marketplace_client.Produk;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("produkport")
public class produkPort {

    private Produk port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of produkPort
     */
    public produkPort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method init
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("init/")
    public String getInit() {
        try {
            // Call Web Service Operation
            if (port != null) {
                int result = port.init();
                return new java.lang.Integer(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method hello
     * @param name resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("hello/")
    public String getHello(@QueryParam("name") String name) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.hello(name);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method product_of
     * @param penjual resource URI parameter
     * @return an instance of javax.xml.bind.JAXBElement<marketplace_client.Product_ofResponse>
     */
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    @Path("product_of/")
    public JAXBElement<marketplace_client.Product_ofResponse> getProductOf(@QueryParam("penjual") String penjual) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.util.List<marketplace_client.StringArray> result = port.productOf(penjual);

                class ProductOfResponse_1 extends marketplace_client.ProductOfResponse {

                    ProductOfResponse_1(java.util.List<marketplace_client.StringArray> _return) {
                        this._return = _return;
                    }
                }
                marketplace_client.ProductOfResponse response = new ProductOfResponse_1(result);
                return new marketplace_client.ObjectFactory().createProductOfResponse(response);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private Produk getPort() {
        try {
            // Call Web Service Operation
            marketplace_client.Produk_Service service = new marketplace_client.Produk_Service();
            marketplace_client.Produk p = service.getProdukPort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
