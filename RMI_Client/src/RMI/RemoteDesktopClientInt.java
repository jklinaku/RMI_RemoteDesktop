/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jon
 */
public interface RemoteDesktopClientInt extends Remote {

    public byte[] captureScreen() throws RemoteException;

    // public void sendEvent(RemoteDesktopInt client) throws RemoteException;
    //public Dimension getPanelSize();
    public void mouseMoved(MouseEvent e, double w, double h) throws RemoteException;

    public void mousePressed(int e) throws RemoteException;

    public void mouseReleased(int e) throws RemoteException;

    public void keyPressed(KeyEvent e) throws RemoteException;

    public void keyReleased(KeyEvent e) throws RemoteException;

    public void connectionTest() throws RemoteException;

    public void mouseWheelMoved(MouseWheelEvent e) throws RemoteException;

}
