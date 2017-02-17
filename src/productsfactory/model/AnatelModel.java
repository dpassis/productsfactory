/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import productsfactory.control.AnatelControl;

/**
 *
 * @author Daniel Paulo
 */
public class AnatelModel {
    
    private String codigoAnatel;
    private StateModel state;
    private Offers offer;
    
    public Map<Offers,AnatelModel>  teste;
    

    public String getCodigoAnatel() {
        return codigoAnatel;
    }

    public void setCodigoAnatel(String codigoAnatel) {
        this.codigoAnatel = codigoAnatel;
    }

    public StateModel getState() {
        return state;
    }

    public void setState(StateModel state) {
        this.state = state;
    }

    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }
    
    
    public static String generateInsertsAnatel(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields) throws SQLException{
        
        return AnatelControl.generateInsertsAnatel(offers, anatelCodes, states, fields);
    }
    
    public static String generateRollbackAnatel(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields){
        
        return AnatelControl.generateRollbackAnatel(offers, anatelCodes, states, fields);
    }
    
    public static String generateSelectAnatel(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields){
        
        return AnatelControl.generateSelectAnatel(offers, anatelCodes, states, fields);
    }
    
    
    public static String generateSelectAnatelRollback(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields){
        
        return AnatelControl.generateSelectAnatelRollback(offers, anatelCodes, states, fields);
    }
    
    
    
}
