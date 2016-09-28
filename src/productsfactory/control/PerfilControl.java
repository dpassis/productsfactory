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
import productsfactory.model.Fields;
import productsfactory.model.Offers;
import productsfactory.useful.ConexaoOracle;
import productsfactory.useful.DataUtil;

/**
 *
 * @author Daniel Paulo
 */
public class PerfilControl {
    
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
    
    
     public static String generateInsertsPerfil(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
         
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        int countQtdeInserts = 0;
        
        StringBuilder inserts = new StringBuilder();
        
        inserts.append("/*****************************************************************************************\n");
        inserts.append("*@Autor: Fábrica HITSS\n");
        inserts.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        inserts.append("*@Description:  ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        inserts.append("*****************************************************************************************/\n\n");
        
        inserts.append("");
        inserts.append("");
        
        
        System.out.println("/*****************************************************************************************\n");
        System.out.println("*@Autor: Fábrica HITSS\n");
        System.out.println("*@Date: "+DataUtil.getDateTime()+"\n");
        System.out.println("*@Description:  "+fields.getOsDesc()+" \n");
        System.out.println("*****************************************************************************************/\n\n");
        
        inserts.append("");
        inserts.append("");
        System.out.println("");
        System.out.println("");
                
        
        
        try {
        
            for(int i = 0; i < offers.size(); i++){ 
                inserts.append("--Inserts para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");
             
                System.out.println("--Inserts para a oferta "+ offers.get(i).getSocCD()+" - "+offers.get(i).getSocName());

                for(int j = 0; j < states.size(); j++){

                    pstmt = con.prepareStatement("SELECT offer.soc_cd, offer.soc_name, state.state FROM mtaapp6.csm_offer_state state " +
                                                    "INNER JOIN  mtaapp6.csm_offer offer " +
                                                    "ON (state.soc_cd = offer.soc_cd) WHERE offer.soc_cd = (?) AND state.state = (?)");
                    pstmt.setString(1,offers.get(i).getSocCD());
                    pstmt.setString(2,states.get(j));


                    rs = pstmt.executeQuery();

                    
                    if(rs.next()){
                        
                            
                    }else{
                        inserts.append("INSERT INTO MTAREFWORK.CSM_OFFER_STATE VALUES ('").append(offers.get(i).getSocCD()).append("','").append(states.get(j)).append("','*',SYSDATE,NULL,'").append(fields.getOperatorID()).append("','DPPC','").append(fields.getDlServiceCode()).append("',").append(fields.getDlUpdateStamp()).append(");\n");
                        System.out.println("INSERT INTO MTAREFWORK.CSM_OFFER_STATE VALUES ('"+offers.get(i).getSocCD()+"','"+states.get(j)+"','*',SYSDATE,NULL,'"+fields.getOperatorID()+"','DPPC','"+fields.getDlServiceCode()+"',"+fields.getDlUpdateStamp()+");");
                        
                        countQtdeInserts++;
                    }
                    

                  }
                
                inserts.append("-- ").append(countQtdeInserts).append(" registros inseridos\n\n");
                System.out.println("-- "+countQtdeInserts+" registros inseridos");
                inserts.append("");
                System.out.println("");
                countQtdeInserts = 0;
            }
        
            
         } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            return null;
            
        }finally{
              rs.close();
              pstmt.close();
         }
      
        return inserts.toString();
     }
     
     
     
     public static String generateRollbackPerfil(List<Offers> offers, List<String> states, Fields fields) throws SQLException{
         
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        int countQtdeDelets = 0;
        
        StringBuilder delete = new StringBuilder();
        
        
        delete.append("/*****************************************************************************************\n");
        delete.append("*@Autor: Fábrica HITSS\n");
        delete.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        delete.append("*@Description: Script de Rollback para :  ").append(fields.getOsDesc()).append(" \n");
        delete.append("*****************************************************************************************/\n\n");
        
        delete.append("");
        delete.append("");
        
        delete.append("");
        delete.append("");
        System.out.println("");
        System.out.println("");
                
        
        
        try {
        
            for(int i = 0; i < offers.size(); i++){ 
                delete.append("--Delete para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");
             
                System.out.println("--Delete para a oferta "+ offers.get(i).getSocCD()+" - "+offers.get(i).getSocName());

                for(int j = 0; j < states.size(); j++){

                    pstmt = con.prepareStatement("SELECT offer.soc_cd, offer.soc_name, state.state FROM mtaapp6.csm_offer_state state " +
                                                    "INNER JOIN  mtaapp6.csm_offer offer " +
                                                    "ON (state.soc_cd = offer.soc_cd) WHERE offer.soc_cd = (?) AND state.state = (?)");
                    pstmt.setString(1,offers.get(i).getSocCD());
                    pstmt.setString(2,states.get(j));


                    rs = pstmt.executeQuery();

                    
                    if(rs.next()){
                        
                            
                    }else{
                        delete.append("DELETE FROM MTAREFWORK.CSM_OFFER_STATE WHERE SOC_CD = '").append(offers.get(i).getSocCD()).append("' AND STATE = '").append(states.get(j)).append("';\n");
                        System.out.println("DELETE FROM MTAREFWORK.CSM_OFFER_STATE WHERE SOC_CD = '"+offers.get(i).getSocCD()+"' AND STATE = '"+states.get(j)+"';");
                        
                        
                        countQtdeDelets++;
                    }
                    

                  }
                
                delete.append("-- ").append(countQtdeDelets).append(" registros excluídos\n\n");
                System.out.println("-- "+countQtdeDelets+" registros excluídos\n\n");
                delete.append("");
                System.out.println("");
                countQtdeDelets = 0;
            }
        
            
         } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            return null;
            
        }finally{
              rs.close();
              pstmt.close();
         }
      
        return delete.toString();
     }
     
     
     public static String generateSelectPerfil(List<Offers> offers, List<String> states,Fields fields){
         
          StringBuilder select = new StringBuilder();
          int countOffers = 1;
          int countStates = 1; 
          
          
            select.append("/*****************************************************************************************\n");
            select.append("*@Autor: Fábrica HITSS\n");
            select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
            select.append("*@Description: Query de Validação para :  ").append(fields.getOsDesc()).append(" \n");
            select.append("*****************************************************************************************/\n\n");
          
          
          
          select.append("SELECT \n"
                        +"  state.* \n"
                      + "FROM \n"
                      + "   csm_offer_state state\n" +
                        "WHERE \n"
                     + "    state.soc_cd \n" +
                        "IN \n  (\n");
          for(Offers ofertas : offers){
              if(offers.size() == countOffers){
                  select.append("   '").append(ofertas.getSocCD()).append("'\n  )\n");
              }else {
                  select.append("   '").append(ofertas.getSocCD()).append("',\n");
              }
              countOffers++;
          }     
          
          select.append("AND \n"
                  + "   state.state \n"
                  + "IN \n  (\n");
          
          for(String estados : states){
              if(states.size() == countStates){
                    select.append("   '").append(estados.toString()).append("'\n  )\n");
              }else{ 
                    select.append("   '").append(estados.toString()).append("',\n");
              }
                  
            countStates++;
          }
          
          select.append("ORDER BY \n"
                  + "   state.soc_cd,\n"
                  + "   state.state;\n");
         
         
         return select.toString();
     }
}
