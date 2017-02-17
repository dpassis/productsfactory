/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import productsfactory.control.BlOcParamControl;
import productsfactory.control.OfferControl;
import productsfactory.control.PerfilControl;

/**
 *
 * @author Daniel Paulo
 */
public class Verifyer {
    /**
     * 
     * @param offers
     * @return
     * @throws SQLException 
     */
    public static List<Offers> verifyOfferExists(List<String> offers) throws SQLException{
        
        return OfferControl.verifyOfferExists(offers);
        
    }
    
    public static List<BlOcParamModel> verifyOfferBlOcParamExists(List<String> offer) throws SQLException{
        
        return BlOcParamControl.verifyOfferBlOcParamExists(offer);
    }
    
    
     /**
     * 
     * @param perfis
     * @return
     * @throws SQLException 
     */
    public static List<PerfilModel> verifyPerfilExists(List<String> perfis) throws SQLException{
        
        return PerfilControl.verifyPerfilExists(perfis);
        
    }
    
    
    public static String verifyStateExists(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
        
       // return StateControl.verifyStateExists(offers, states, fields);
        
        return "";
        
    }
}
