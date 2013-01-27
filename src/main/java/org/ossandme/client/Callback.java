package org.ossandme.client;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;

@WebService
public class Callback {

    @RequestWrapper(localName = "sayHiResponse", targetNamespace = "http://apache.org/hello_world_soap_zmq/types")
    public void callback(@WebParam(targetNamespace = "http://apache.org/hello_world_soap_zmq/types", name = "responseType") String callbackMessage) {
        System.out.println("ASYNC REPLY RECEIVED FROM SERVER: " + callbackMessage);
        System.exit(0);
    }
}
