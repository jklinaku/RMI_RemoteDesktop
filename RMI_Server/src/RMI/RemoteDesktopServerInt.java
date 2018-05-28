/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jon
 */
public interface RemoteDesktopServerInt extends Remote {

    public boolean registerClient(String password, RemoteDesktopClientInt client, String name) throws RemoteException;

    public boolean deleteClient(String name, RemoteDesktopClientInt client) throws RemoteException;

    // public void sendEvent(RemoteDesktopInt client) throws RemoteException;
    // public double getPanelSize();
}
