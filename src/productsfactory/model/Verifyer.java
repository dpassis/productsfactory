/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import productsfactory.control.OfferControl;

/**
 *
 * @author Daniel Paulo
 */
public class Verifyer {
    
    public static List<Offers> verifyOfferExists(List<String> offers) throws SQLException{
        
        return OfferControl.verifyOfferExists(offers);
        
    }
    
    
    public static String verifyStateExists(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
        
       // return StateControl.verifyStateExists(offers, states, fields);
        
        return "";
        
    }
}
