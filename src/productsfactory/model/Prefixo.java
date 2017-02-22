/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Paulo
 */
public class Prefixo {
    
   
    /**
     * Método que retorna lista de Prefixos
     * para nomenclatura dos Scripts
     * @return <code>List<String></code>
     */
    public static List<String> getPrefixos(){
        
        List<String> listPrefixos = new ArrayList<>();
        listPrefixos.add("Selecione");
        listPrefixos.add("TST");
        listPrefixos.add("TST_RELEASE");
        listPrefixos.add("TST_MANTIS");
        listPrefixos.add("PRD");
        listPrefixos.add("PRD_RELEASE");
        listPrefixos.add("PRD_MANTIS");
        
        
        return listPrefixos;
    }
    
    
}
