package rmi_server;

import RMI.RemoteDesktopClientInt;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument;
import org.w3c.dom.events.MouseEvent;

class ReceiveScreen extends Thread implements KeyListener, MouseListener, MouseMotionListener {

    private ObjectInputStream cObjectInputStream = null;
    private JPanel cPanel = null;
    private boolean continueLoop = true;
    Server server;
    RemoteDesktopClientInt c;
    String ccn;
    Image image1 = null;
    CreateFrame f;
    ArrayList<JButton> clients = new ArrayList<JButton>();

    public ReceiveScreen(Server s, JPanel p, CreateFrame f) {
        //oin = in;
        server = s;
        cPanel = p;
        // setClient("jon");

        this.f = f;
        start();

    }

    public void setClient(String name) {
        ccn = name;
        c = server.clients.get(ccn);
    }

    public void run() {
        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);
        cPanel.addMouseMotionListener(this);

        //Read screenshots of the client and then draw them
        while (continueLoop) {
            try {
                Set<String> it = server.clients.keySet();
                if (it.size() != f.mb.getMenuCount()) {
                    f.mb.removeAll();
                    for (Iterator<String> iterator = it.iterator(); iterator.hasNext();) {
                        String next = iterator.next();
                        JButton jmi = new JButton(next);
                        clients.add(jmi);
                        f.mb.add(jmi);
                        jmi.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                setClient(jmi.getText());
                                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        f.reValidate();

                    }
                }
                //System.out.println(Arrays.toString(it.toArray()));
                if (c == null) {
                    //Set<String> it = server.clients.keySet();
                    if (!it.isEmpty()) {
                        System.out.println(Arrays.toString(it.toArray()));
                        ccn = (String) it.toArray()[0];
                        c = server.clients.get(ccn);

                    }

                }
                try {
                    c.connectionTest();
                } catch (RemoteException e) {
                    server.deleteClient(ccn, c);
                    c = null;
                }
                image1 = ImageIO.read(new ByteArrayInputStream(c.captureScreen()));

                image1 = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);

                //Draw the received screenshots
                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
            } catch (Exception e) {
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            // System.out.println("halo");
            c.keyPressed(e);
        } catch (Exception ex) {
        }
        //System.out.println("halo");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            c.keyReleased(e);
        } catch (Exception ex) {
        }

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
         int button = e.getButton();
		int xButton = 16;
		if(button==3){
			xButton = 4;
		}
        try {
            
		
            System.out.println("hi");
            c.mousePressed(xButton);
            System.out.println("hi1");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("hi2");
        }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
       int button = e.getButton();
		int xButton = 16;
		if(button==3){
			xButton = 4;
		}
        try {
            c.mouseReleased(xButton);
        } catch (Exception ex) {
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        try {
            c.mouseMoved(e, (double) cPanel.getWidth(), (double) cPanel.getHeight());
        } catch (Exception ex) {
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
