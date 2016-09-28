/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class UtilToSqlTeste {

    /**
     * 
     * @param data
     * @return 
     */
    public static java.sql.Date getSqlDate(Date data) {
        if (data == null) {
            return null;
        }
        return java.sql.Date.valueOf(dateToStr(data, "yyyy-MM-dd"));
    }
    /**
     * 
     * @param data
     * @return 
     */
    public static java.sql.Time getSqlHora(Date data) {
        if (data == null) {
            return null;
        }
        return java.sql.Time.valueOf(dateToStr(data, "HH:mm"));
    }

    /**
     * 
     * @param date
     * @param format
     * @return 
     */
    public static String dateToStr(Date date, String format) {

        String retorno = null;

        if ((null != date) && (null != format)) {

            SimpleDateFormat formater = new SimpleDateFormat(format);
            retorno = formater.format(date);
        }

        return retorno;
    }
}
