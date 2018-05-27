/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_client;

import RMI.RemoteDesktopServerInt;

/**
 *
 * @author Jon
 */
public class hook extends Thread {

    RemoteDesktopServerInt server;
    Client client;

    public hook(RemoteDesktopServerInt server, Client client) {
        this.server = server;
        this.client = client;

    }

    @Override
    public void run() {
        try {
         System.out.println(server.deleteClient(client.getName(), client));
        } catch (Exception e) {
        }
    }
}
