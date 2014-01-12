import javax.imageio.ImageIO;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GUI extends Applet
        implements MouseListener, MouseMotionListener {

    int width, height;
    int mx, my;  // the mouse coordinates
    boolean isButtonPressed = false;
    Image a;
    URL url = null;


    public void init() {
        readFile();
        width = getSize().width;
        height = getSize().height;
        setBackground( Color.black );
        mx = width/2;
        my = height/2;

        addMouseListener( this );
        addMouseMotionListener( this );
    }

    public void readFile(){

        try {
            // Until I work out a dedicated server to store the files, will use imgur
            url = new URL("http://i.imgur.com/NbXCP4K.png");
        } catch (MalformedURLException e) {
        }
        try {
            a = ImageIO.read(url);
        } catch (IOException e) {
        }
    }

    public void mouseEntered( MouseEvent e ) {
        // called when the pointer enters the applet's rectangular area
    }
    public void mouseExited( MouseEvent e ) {
        // called when the pointer leaves the applet's rectangular area
    }
    public void mouseClicked( MouseEvent e ) {
        // called after a press and release of a mouse button
        // with no motion in between
        // (If the user presses, drags, and then releases, there will be
        // no click event generated.)
    }
    public void mousePressed( MouseEvent e ) {  // called after a button is pressed down
        isButtonPressed = true;
        //setBackground( Color.gray );
        repaint();
        // "Consume" the event so it won't be processed in the
        // default manner by the source which generated it.
        e.consume();
    }
    public void mouseReleased( MouseEvent e ) {  // called after a button is released
        isButtonPressed = false;
        //setBackground( Color.black );
        repaint();
        e.consume();
    }
    public void mouseMoved( MouseEvent e ) {  // called during motion when no buttons are down
        mx = e.getX();
        my = e.getY();
        showStatus( "Mouse at (" + mx + "," + my + ")" );
        repaint();
        e.consume();
    }
    public void mouseDragged( MouseEvent e ) {  // called during motion with buttons down
        mx = e.getX();
        my = e.getY();
        showStatus( "Mouse at (" + mx + "," + my + ")" );
        repaint();
        e.consume();
    }

    // The code below was found at
    // http://www.planet-source-code.com/vb/scripts/ShowCode.asp?txtCodeId=261&lngWId=2
    // Anti-Flicker
    // Draws the image offscreen then copies over to avoid flicker
    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;
    public final synchronized void update (Graphics g) {
        Dimension d = size();
        if((offScreenImage == null) || (d.width != offScreenSize.width) || (d.height != offScreenSize.height)) {
            offScreenImage = createImage(d.width, d.height);
            offScreenSize = d;
            offScreenGraphics = offScreenImage.getGraphics();
        }
        offScreenGraphics.clearRect(0, 0, d.width, d.height);
        paint(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    public void paint( Graphics g ) {
        g.drawImage(a,mx-10,my-10,null);
    }
}