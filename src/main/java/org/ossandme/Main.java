package org.ossandme;

import org.ossandme.client.Client;
import org.ossandme.server.Server;

public class Main {

    public static void main(String args[]) throws Exception {
        Server.start();
        Client.doOperation();

        // Wait for callback to receive async reply
        Thread.sleep(5 * 60 * 1000);
    }

}
