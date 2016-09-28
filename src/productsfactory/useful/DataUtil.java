/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.useful;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Daniel Paulo
 */
public class DataUtil {
    
    
    /**
     * Formata uma data
     * 
     * @return <code>String</code> com uma data por extenso
     */
    public static String getDateTime() {

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        Date date = new Date();
        return df.format(date);
    }
    
}
