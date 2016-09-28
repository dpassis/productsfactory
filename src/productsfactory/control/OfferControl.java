/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import productsfactory.model.Offers;
import productsfactory.useful.ConexaoOracle;

/**
 *
 * @author Daniel Paulo
 */
public class OfferControl {
    
    
        public static List<Offers> verifyOfferExists(List<String> offer) throws SQLException{
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        
        
        List<Offers> listOffers = new ArrayList<>();
        
          try {
           
          for(int i = 0; i < offer.size(); i++){
              
           
            pstmt = con.prepareStatement("SELECT soc_cd, soc_name FROM mtaapp6.csm_offer WHERE soc_cd = (?)");
            pstmt.setString(1,offer.get(i).replace("\r", ""));
            
            
            rs = pstmt.executeQuery();
          
            while(rs.next()){
           
                 
                 Offers offers = new Offers();
                 
                 if(rs.getString("soc_cd") != null){
                     
                     offers.setSocCD(rs.getString("soc_cd"));
                     offers.setSocName(rs.getString("soc_name"));
                     offers.setStatusOffer("OK!");
                 }else{
                     offers.setSocCD(offer.get(i));
                     offers.setSocName("-");
                     offers.setStatusOffer("NOK!");
                 }
                 
                 listOffers.add(offers);
                 
            }
           
          }
            
         } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
            
        }finally{
              rs.close();
              pstmt.close();
         }
      
        return listOffers;
    }
}
