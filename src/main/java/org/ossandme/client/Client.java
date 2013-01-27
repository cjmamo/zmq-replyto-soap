package org.ossandme.client;

import org.apache.cxf.ws.addressing.AddressingBuilder;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.hello_world_soap_zmq.SOAPService;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.AddressingFeature;
import java.util.Map;

import static org.apache.cxf.ws.addressing.JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES;

public class Client {

    public static void doOperation() throws Exception {

        // Start callback service
        Endpoint.publish("zmq:(tcp://127.0.0.1:5555?socketOperation=bind&socketType=pull)", new Callback());

        // Create client
        SOAPService service = new SOAPService(Client.class.getResource("/hello_world_addr.wsdl").toURI().toURL());
        Greeter port = service.getSoapPort(new WebServiceFeature[]{new AddressingFeature()});

        // Add ReplyTo property to request
        Map<String, Object> requestContext = ((BindingProvider) port).getRequestContext();
        requestContext.put(CLIENT_ADDRESSING_PROPERTIES, createMaps());

        // Dispatch request
        port.sayHiAsync();
        System.out.println("CLIENT DISPATCHED REQUEST");
    }

    private static AddressingProperties createMaps() {
        AddressingProperties maps = AddressingBuilder.getAddressingBuilder().newAddressingProperties();
        EndpointReferenceType ert = new EndpointReferenceType();

        AttributedURIType replyTo = new AttributedURIType();
        replyTo.setValue("zmq:(tcp://127.0.0.1:5555?socketOperation=connect&socketType=push)");
        ert.setAddress(replyTo);

        maps.setReplyTo(ert);

        return maps;
    }

}
