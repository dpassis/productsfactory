/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.util.List;
import productsfactory.control.BlOcParamControl;

/**
 *
 * @author Daniel Paulo
 */
public class BlOcParamModel {

    private String rate;
    private Offers offer;
    private int QtdeOffers;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }

    public int getQtdeOffers() {
        return QtdeOffers;
    }

    public void setQtdeOffers(int QtdeOffers) {
        this.QtdeOffers = QtdeOffers;
    }
    
    
    public static String generateUpdateBlOcParam(List<BlOcParamModel> offers, List<String> valores, Fields fields) {
        return BlOcParamControl.generateUpdateBlOcParam(offers, valores, fields);
    }
    
    
    public static String generateRollbackBlOcParam(List<BlOcParamModel> offers, List<String> valores, Fields fields) {
       return BlOcParamControl.generateRollbackBlOcParam(offers, valores, fields);
    }
    
     public static String generateSelectBlOcParam(List<BlOcParamModel> offers, List<String> valores, Fields fields) {
        return BlOcParamControl.generateSelectBlOcParam(offers, valores, fields);
    }
    
    
}
