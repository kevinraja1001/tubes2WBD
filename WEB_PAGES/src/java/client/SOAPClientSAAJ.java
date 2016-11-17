package client;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;

/**
 *
 * @author user
 */
public class SOAPClientSAAJ {

    public static void main(String args[]) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://192.168.43.97:8080/webservice/produk";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest("113","get_sales_purchase","x","nugsky","mode","purchases",null,null,null,null,null,null,null,null,null,null,null,null), url);
            //SOAPMessage soapResponse = soapConnection.call(createSOAPRequest("init",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null), url);
            // Process the SOAP Response
            printSOAPResponse(soapResponse);
            
            System.out.println("");
            System.out.println("=====");
            
            //String[][] hasil = getarray(soapResponse,"init");
            String[][] hasil = getarray(soapResponse,"get_sales_purchase");
            for (int i=0;i<hasil.length;i++){
                for (int j=0;j<hasil[i].length;j++){
                    System.out.print(hasil[i][j]+" - ");
                }
                System.out.println();
            }

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }
    
    public static String getStringFromSOAPRespond(String token, String namaMethod, String param1name, String param1val, String param2name, String param2val, String param3name, String param3val, String param4name, String param4val, String param5name, String param5val, String param6name, String param6val, String param7name, String param7val, String param8name, String param8val) throws Exception {
        String hasil;
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://192.168.43.97:8080/webservice/produk";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(token,namaMethod,param1name,param1val,param2name,param2val,param3name,param3val,param4name,param4val,param5name,param5val,param6name,param6val,param7name,param7val,param8name,param8val), url);
            hasil = getmessage(soapResponse, namaMethod);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            hasil=null;
        }
        return hasil;
    }
    
    public static String[][] getArrayFromSOAPRespond(String token, String namaMethod, String param1name, String param1val, String param2name, String param2val, String param3name, String param3val, String param4name, String param4val, String param5name, String param5val, String param6name, String param6val, String param7name, String param7val, String param8name, String param8val) throws Exception {
        String[][] hasil;
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://192.168.43.97:8080/webservice/produk";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(token,namaMethod,param1name,param1val,param2name,param2val,param3name,param3val,param4name,param4val,param5name,param5val,param6name,param6val,param7name,param7val,param8name,param8val), url);
            hasil = getarray(soapResponse, namaMethod);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            hasil=null;
        }
        return hasil;
    }
    
    private static SOAPMessage createSOAPRequest(String token, String namaMethod, String param1name, String param1val, String param2name, String param2val, String param3name, String param3val, String param4name, String param4val, String param5name, String param5val, String param6name, String param6val, String param7name, String param7val, String param8name, String param8val) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        
        soapMessage.getSOAPPart().getEnvelope().setPrefix("S");
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://schemas.xmlsoap.org/soap/envelope/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("SOAP-ENV", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("S");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + namaMethod);
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        Name bodyName  = soapFactory.createName(namaMethod,"ns2","http://marketplace/");
        SOAPElement body = soapBody.addChildElement(bodyName);
        if(token!=null){
            SOAPElement soapBodyElem = body.addChildElement("token");
            soapBodyElem.addTextNode(token);
        }
        if(param1name != null && param1val != null){
            SOAPElement soapBodyElem1 = body.addChildElement(param1name);
            soapBodyElem1.addTextNode(param1val);
            if(param2name != null && param2val != null){
                SOAPElement soapBodyElem2 = body.addChildElement(param2name);
                soapBodyElem2.addTextNode(param2val);
                if(param3name != null && param3val != null){
                    SOAPElement soapBodyElem3 = body.addChildElement(param3name);
                    soapBodyElem3.addTextNode(param3val);
                    if(param4name != null && param4val != null){
                        SOAPElement soapBodyElem4 = body.addChildElement(param4name);
                        soapBodyElem4.addTextNode(param4val);
                        if(param5name != null && param5val != null){
                            SOAPElement soapBodyElem5 = soapBody.addChildElement(param5name);
                            soapBodyElem5.addTextNode(param5val);
                            if(param6name != null && param6val != null){
                                SOAPElement soapBodyElem6 = soapBody.addChildElement(param6name);
                                soapBodyElem6.addTextNode(param6val);
                                if(param7name != null && param7val != null){
                                    SOAPElement soapBodyElem7 = soapBody.addChildElement(param7name);
                                    soapBodyElem7.addTextNode(param7val);
                                    if(param8name != null && param8val != null){
                                        SOAPElement soapBodyElem8 = soapBody.addChildElement(param8name);
                                        soapBodyElem8.addTextNode(param8val);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

    private static String[][] getarray(SOAPMessage s,String method) throws Exception {
        XPath xPath = XPathFactory.newInstance().newXPath();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String xml = soapMessageToString(s);
        System.out.println(xml);
        Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        String expression = "/Envelope/Body/"+method+"Response/return";

        //read a nodelist using xpath
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        System.out.println();
        System.out.println(expression);
        String[][] hasil = new String[nodeList.getLength()][];
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList temp = nodeList.item(i).getChildNodes();
            hasil[i] = new String[temp.getLength()];
            for (int j=0;j<temp.getLength();j++){
                hasil[i][j] = temp.item(j).getFirstChild().getNodeValue();
            }
        }
        return hasil;
    }
    
    private static String getmessage(SOAPMessage s,String method) throws Exception {
        XPath xPath = XPathFactory.newInstance().newXPath();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String xml = soapMessageToString(s);
        System.out.println(xml);
        Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        String expression = "/Envelope/Body/"+method+"Response/return";

        //read a nodelist using xpath
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        System.out.println();
        System.out.println(expression);
        String hasil = nodeList.item(0).getFirstChild().getNodeValue();
        return hasil;
    }

    public static String soapMessageToString(SOAPMessage message) {
        String result = null;

        if (message != null) {
            ByteArrayOutputStream baos = null;
            try {
                baos = new ByteArrayOutputStream();
                message.writeTo(baos);
                result = baos.toString();
            } catch (Exception e) {
            } finally {
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException ioe) {
                    }
                }
            }
        }
        return result;
    }
}
