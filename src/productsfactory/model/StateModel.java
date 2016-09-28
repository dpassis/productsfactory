/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.sql.SQLException;
import java.util.List;
import productsfactory.control.StateControl;

/**
 *
 * @author Daniel Paulo
 */
public class StateModel {
    
    /**
     * 
     * @param offers
     * @param states
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateInsertsStates(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
        
        return StateControl.generateInsertsStates(offers, states, fields);
    }
    
    
    /**
     * 
     * @param offers
     * @param states
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateRollbackStates(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
        
        return StateControl.generateRollbackStates(offers, states, fields);
    }
    
    
    /**
     * 
     * @param offers
     * @param states
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateSelectState(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
        
        return StateControl.generateSelectState(offers, states, fields);
    }
    
}
