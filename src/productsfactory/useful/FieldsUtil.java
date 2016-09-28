/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.useful;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Paulo
 */
public class FieldsUtil {
    
        /**
     * 
     * @param texto
     * @return 
     */
    public static List<String> getEdtTextByLine(String texto){
        List<String> linhas = new ArrayList<>();
        List<String> listOffers = new ArrayList<>();
        String[] breakLine = null ;
       
        linhas.add(texto);
        
        for(int i = 0;i < linhas.size(); i++){
           breakLine = linhas.get(i).split("\n");
            for (String brkLine : breakLine) {
                listOffers.add(brkLine);
            }
        }
        
        return listOffers;     
    }
}
