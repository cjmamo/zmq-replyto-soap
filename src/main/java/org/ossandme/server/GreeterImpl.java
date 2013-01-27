package org.ossandme.server;

import javax.xml.ws.soap.Addressing;

@Addressing
public class GreeterImpl implements Greeter {

    public String sayHi() {
        System.out.println("SERVER EXECUTING OPERATION sayHi...");
        return "Bonjour";
    }

}
