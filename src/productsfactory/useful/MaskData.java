/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.useful;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Daniel
 */
public class MaskData implements FocusListener{
   MaskFormatter data;
   MaskFormatter hora;
   JFormattedTextField ftfData;
   JFormattedTextField ftfHora;

  public JFormattedTextField ftfData() {
    
    try{
      data = new MaskFormatter("##/##/####");
      data.setPlaceholderCharacter('_');
      
    }
    catch(ParseException excp){
        excp.getErrorOffset();
    }

 
    //ftfData.setHorizontalAlignment(JFormattedTextField.CENTER);
    //ftfData.addFocusListener(this);
    
    ftfData = new JFormattedData();

    return ftfData;
 }
  
  
  public JFormattedTextField ftfHora(){
    
    try{
      hora = new MaskFormatter("##:##");
      hora.setPlaceholderCharacter('_');
    }
    catch(ParseException excp){
        excp.getErrorOffset();
    }

    ftfHora = new JFormattedTextField(hora);

    return ftfHora;
 }
public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusLost(FocusEvent e) {
        ftfData.setFocusLostBehavior(JFormattedTextField.COMMIT);
    }

 
}
