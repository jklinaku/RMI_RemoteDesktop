/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import RMI.RemoteDesktopClientInt;
import RMI.RemoteDesktopServerInt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jon
 */
public class Client extends UnicastRemoteObject implements RemoteDesktopClientInt {
    String name;
    Robot robot;
    Rectangle rect;
    RemoteDesktopServerInt server;

    public Client(Robot robot, Rectangle rect, RemoteDesktopServerInt server,String name) throws RemoteException {
        this.robot = robot;
        this.rect = rect;
        this.server = server;
        this.name = name;

    }

    
    public String getName(){
        return name;
    }
    

    @Override
    public byte[] captureScreen() throws RemoteException {
        byte[] imageInByte=null;
        try{
			
	BufferedImage originalImage = 
                              robot.createScreenCapture(rect);
			
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write( originalImage, "jpg", baos );
	baos.flush();
	 imageInByte = baos.toByteArray();
	baos.close();
			
	}catch(IOException e){
		System.out.println(e.getMessage());
	}		
        return imageInByte;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

   

    @Override
    public void mouseMoved(MouseEvent e,double w,double h) throws RemoteException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double xScale = rect.getSize().getWidth() /w;
        double yScale = rect.getSize().getHeight() /h;
        robot.mouseMove((int) (e.getX() * xScale), (int) (e.getY() * yScale));
//System.out.println("ajbwdkjawndkjawdnkjawd");
    }

    @Override
    public void mousePressed(int e) throws RemoteException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        robot.mousePress(e);
       // System.out.println("ajbwdkjawndkjawdnkjawd");
    }

    @Override
    public void mouseReleased(int e) throws RemoteException {
        robot.mouseRelease(e);
      //  System.out.println("ajbwdkjawndkjawdnkjawd");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) throws RemoteException {
        robot.keyPress(e.getKeyCode());
       // System.out.println("ajbwdkjawndkjawdnkjawd");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) throws RemoteException {
        robot.keyRelease(e.getKeyCode());
       // System.out.println("ajbwdkjawndkjawdnkjawd");

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void connectionTest() throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
