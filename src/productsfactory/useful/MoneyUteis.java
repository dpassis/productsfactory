/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Daniel
 */
public class MoneyUteis {
    
    public static BigDecimal converteMoney(String valor) {
       BigDecimal valorConvertido = null;
       
       try{
        DecimalFormat df = new DecimalFormat ("#,###.##", new DecimalFormatSymbols (new Locale ("pt", "BR"))); // esta é metade da mágica...  
        df.setParseBigDecimal (true);
        valorConvertido = (BigDecimal) df.parse (valor);
        
       }catch(ParseException e){
           e.getMessage();
       }
        return valorConvertido;
    }
    

}
