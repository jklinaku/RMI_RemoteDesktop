/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_client;

import RMI.RemoteDesktopClientInt;
import RMI.RemoteDesktopServerInt;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Jon
 */
public class RMI_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
//        System.setProperty("java.security.policy", "policy.txt");
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        Registry myReg = LocateRegistry.getRegistry("127.0.0.1", 4099);
        RemoteDesktopServerInt server = (RemoteDesktopServerInt) myReg.lookup("server");
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        String width = "" + dim.getWidth();
        String height = "" + dim.getHeight();
        Rectangle rect = new Rectangle(dim);
        Robot robot = new Robot(gDev);
        Client client = new Client(robot, rect, server,"jonau5");
        System.out.println(server.registerClient("hello",  client, client.getName()));
       // Thread.sleep(1000);
        Runtime.getRuntime().addShutdownHook( new hook(server,client));
        Scanner s=new Scanner(System.in);
        while(true){
            if(s.nextLine().trim().toLowerCase().equals("x"))
                System.exit(0);
        }
        
    }

}
