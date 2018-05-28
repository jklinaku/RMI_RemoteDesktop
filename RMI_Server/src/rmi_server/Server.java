/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_server;

import RMI.RemoteDesktopClientInt;
import RMI.RemoteDesktopServerInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Jon
 */
public class Server extends UnicastRemoteObject implements RemoteDesktopServerInt {

    // Robot robot;
    // Rectangle rect;
    ConcurrentHashMap<String, RemoteDesktopClientInt> clients = new ConcurrentHashMap<String, RemoteDesktopClientInt>();
    String password;

    public Server(String password) throws RemoteException {
        //this.robot = robot;
        //this.rect = rect;
        // this.server = server;
        this.password = password;

    }

    @Override
    public boolean registerClient(String password, RemoteDesktopClientInt client, String name) throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return false;
        if (this.password.equals(password)) {
            if (clients.get(name) == null) {
                clients.put(name, client);
                return true;
            } else {
                //duhet quar error tek klienti!
            }
        }
        return false;
    }

    @Override
    public boolean deleteClient(String name, RemoteDesktopClientInt client) throws RemoteException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        System.out.println(clients.remove(name));
        return true;

    }

//    @Override
//    public double getPanelSize() {
//        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        return rect.getSize().getHeight();
//    }
//
//    public void setPanelSize(Dimension dim){
//        rect.setSize(dim);
//    }
}
