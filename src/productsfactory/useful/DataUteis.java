/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class DataUteis {
    
    public static Date sdfParse(String data){
        
        Date dataReturn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
           
            dataReturn = new Date(sdf.parse(data).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(DataUteis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  dataReturn;
    }
    
    public static String sdfFormat(Object date){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataReturn = null;
        
        dataReturn = sdf.format(date);
        
        return dataReturn;
        
    }
    
    
    public static String sdfFormatDay(Object date){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String dataReturn = null;
        
        dataReturn = sdf.format(date);
        
        return dataReturn;
        
    }
    
    public static boolean validaHora(String hora){
        
        DateFormat df = new SimpleDateFormat("HH:mm");  
        try {  
            df.setLenient(false);  
            java.util.Date d = df.parse(hora);  
        } catch (ParseException e) {  
            return false;  
        }  
        return true;  
    }
    
 /**
 * Calcula a idade de acordo com a data passada.
 * 
 * @param data
 * @return
 * @author Isaias Pfaffenseller
 */
public static Integer getIdade(java.util.Date data) {
	Calendar dataNascimento = Calendar.getInstance();
	dataNascimento.setTime(data);
	Calendar dataAtual = Calendar.getInstance();

	Integer diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);
	Integer diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);
	Integer idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

	if(diferencaMes < 0	|| (diferencaMes == 0 && diferencaDia < 0)) {
		idade--;
	}
	
	return idade;
}

}
