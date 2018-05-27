/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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

    public void mouseMoved(MouseEvent e,double w,double h) throws RemoteException;

    public void mousePressed(MouseEvent e) throws RemoteException;

    public void mouseReleased(MouseEvent e) throws RemoteException;

    public void keyPressed(KeyEvent e) throws RemoteException;

    public void keyReleased(KeyEvent e) throws RemoteException;

}
