/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.awt.Dimension;
import java.awt.Window;

/**
 *
 * @author Daniel
 */
public class TelaUteis {
    
    public static void locateOnScreen(Window window,int width, int height) {
        
        window.setSize(width, height);
        
        final Dimension paneSize = window.getSize();
        final Dimension screenSize = window.getToolkit().getScreenSize();
        
        window.setLocation((screenSize.width - paneSize.width) / 2,
                (screenSize.height - paneSize.height) / 2);
    }
}
