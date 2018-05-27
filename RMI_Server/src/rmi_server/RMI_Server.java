/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_server;

import RMI.RemoteDesktopServerInt;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Jon
 */
public class RMI_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
//        System.setProperty("java.security.policy", "policy.txt");
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         Rectangle rect = new Rectangle(dim);
        //Rectangle rect = new Rectangle();
        String password = "hello";
        Server server = new Server( password);
        Registry myReg = LocateRegistry.createRegistry(4099);
        myReg.bind("server", (RemoteDesktopServerInt)server);
        JDesktopPane desktop = new JDesktopPane();
        JInternalFrame interFrame = new JInternalFrame("Server Screen", true, true, true);
        new CreateFrame(server, "50", "50");
        

    }

}
