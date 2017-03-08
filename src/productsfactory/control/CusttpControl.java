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
import java.util.logging.Level;
import java.util.logging.Logger;
import productsfactory.model.CusttpModel;
import productsfactory.model.Fields;
import productsfactory.model.Offers;
import productsfactory.model.PerfilModel;
import productsfactory.useful.ConexaoOracle;
import productsfactory.useful.DataUtil;

/**
 *
 * @author Daniel Paulo
 */
public class CusttpControl {
    
    
    public static List<CusttpModel> getDescricaoTipoSubtipo(List<CusttpModel> tipo, List<CusttpModel> subTipo){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();

        List<CusttpModel> listDescricao = new ArrayList<>();  
         try {
                for(int i = 0; i < tipo.size(); i++){

                   for(int j = 0; j < subTipo.size(); j++){
                       
                       
                            pstmt = con.prepareStatement("SELECT " +
                                                         "ct.customer_type " +
                                                         ",cst.cust_sub_type " +
                                                          ",CONCAT(ct.customer_type||'/'||cst.cust_sub_type||'- '||ct.custtp_desc||' - ',cst.description) AS descricao " +
                                                          "FROM " +
                                                          "mtaappc.customer_type@bcv_fm ct " +
                                                          "LEFT JOIN " +
                                                          "mtaappc.customer_sub_type@bcv_fm cst " +
                                                          "ON (ct.customer_type = cst.customer_type) "+
                                                          "WHERE ct.customer_type = (?) AND cst.cust_sub_type = (?) ");
                           pstmt.setString(1, tipo.get(i).getTipo());
                           pstmt.setString(2, subTipo.get(j).getSubTipo());
                           
                           System.out.println(tipo.get(i).getTipo()+"-"+ subTipo.get(j).getSubTipo());
                           
                           rs = pstmt.executeQuery();

                        if (rs.next()) {
                            
                            CusttpModel custtp = new CusttpModel();
                            custtp.setDescricao(rs.getString("descricao"));
                            
                            listDescricao.add(custtp);
                        }
                   }

                }
           } catch (SQLException ex) {
                    Logger.getLogger(CusttpControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return listDescricao;
    }

    /**
     *
     * @param perfil
     * @return
     * @throws SQLException
     */
    public static List<PerfilModel> verifyPerfilExists(List<String> perfil) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();

        List<PerfilModel> listPerfis = new ArrayList<>();

        try {

            for (int i = 0; i < perfil.size(); i++) {

                pstmt = con.prepareStatement("SELECT sph_profile_id FROM mtaobjp.vw_security_profile_header@bcv_lnk WHERE sph_profile_id = (?)");
                pstmt.setString(1, perfil.get(i).replace("\r", ""));

                rs = pstmt.executeQuery();

                while (rs.next()) {

                    PerfilModel perfilObj = new PerfilModel();

                    if (rs.getString("sph_profile_id") != null) {

                        perfilObj.setPerfil(rs.getString("sph_profile_id"));
                        perfilObj.setStatusPerfil("OK!");
                    } else {
                        perfilObj.setPerfil(perfil.get(i));
                        perfilObj.setStatusPerfil("NOK!");
                    }

                    listPerfis.add(perfilObj);

                }

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;

        } finally {
            rs.close();
            pstmt.close();
        }

        return listPerfis;
    }

    public static String generateInsertsCusttp(List<Offers> offers, List<CusttpModel> tipos, List<CusttpModel> subTipos, Fields fields) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        int countQtdeInserts = 0;
        int countQtdeInsertsTotal = 0;

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
        System.out.println("*@Date: " + DataUtil.getDateTime() + "\n");
        System.out.println("*@Description:  " + fields.getOsDesc() + " \n");
        System.out.println("*****************************************************************************************/\n\n");

        inserts.append("");
        inserts.append("");
        System.out.println("");
        System.out.println("");

        try {

            for (int i = 0; i < offers.size(); i++) {
                inserts.append("--Inserts para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                System.out.println("--Inserts para a oferta " + offers.get(i).getSocCD() + " - " + offers.get(i).getSocName());

                for (int j = 0; j < tipos.size(); j++) {
                    
                    for (int k = 0; k < subTipos.size(); k++) {
                    
                    pstmt = con.prepareStatement("SELECT \n" +
                                                "    SOC_CD FROM CSM_OFFER_CUSTTP\n" +
                                                "WHERE \n" +
                                                "    SOC_CD  = (?)\n" +
                                                "    AND CUSTOMER_TYPE = (?)\n" +
                                                "    AND CUSTOMER_SUB_TYPE = (?);");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, tipos.get(j).getTipo());
                    pstmt.setString(3, subTipos.get(k).getSubTipo());

                    rs = pstmt.executeQuery();

                    if (rs.next()) {

                    } else {
                        inserts.append("INSERT INTO MTAREFWORK.CSM_OFFER_CUSTTP (SOC_CD, SOC_CHANNEL_CODE, SYS_CREATION_DATE, SYS_UPDATE_DATE, OPERATOR_ID, APPLICATION_ID, DL_SERVICE_CODE, DL_UPDATE_STAMP, UPDATEABLE_OFFER_IND, UPDATEABLE_PARAM_IND) VALUES ('").append(offers.get(i).getSocCD()).append("','").append(subTipos.get(j).getSubTipo()).append("',SYSDATE,NULL, ").append(fields.getOperatorID()).append(",'DPPC','").append(fields.getDlServiceCode()).append("',").append(fields.getDlUpdateStamp()).append(",NULL, NULL);\n");
                        System.out.println("INSERT INTO MTAREFWORK.CSM_OFFER_SALE_CHANNEL VALUES ('" + offers.get(i).getSocCD() + "','" + tipos.get(j).getTipo() + "',SYSDATE,NULL,'" + fields.getOperatorID() + "','DPPC','" + fields.getDlServiceCode() + "'," + fields.getDlUpdateStamp() + ",NULL, NULL);");

                        countQtdeInserts++;
                        countQtdeInsertsTotal++;
                    }
                    
                   }

                }

                inserts.append("-- ").append(countQtdeInserts).append(" registros inseridos\n\n");
                System.out.println("-- " + countQtdeInserts + " registros inseridos");
                inserts.append("");
                System.out.println("");
                countQtdeInserts = 0;
            }
            
            inserts.append("-- ").append(countQtdeInsertsTotal).append(" registros inseridos no total\n\n");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();
        }

        return inserts.toString();
    }

    public static String generateRollbackPerfil(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        int countQtdeDelets = 0;
        int countQtdeDeletsTotal = 0;

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

            for (int i = 0; i < offers.size(); i++) {
                delete.append("--Delete para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                System.out.println("--Delete para a oferta " + offers.get(i).getSocCD() + " - " + offers.get(i).getSocName());

                for (int j = 0; j < perfis.size(); j++) {

                   pstmt = con.prepareStatement("SELECT soc_cd, soc_channel_code FROM mtaappc.csm_offer_sale_channel@bcv_lnk sale "
                            + "WHERE sale.soc_cd = (?) AND sale.soc_channel_code = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, perfis.get(j).getPerfil());

                    rs = pstmt.executeQuery();

                    if (rs.next()) {

                    } else {
                        delete.append("DELETE FROM MTAREFWORK.CSM_OFFER_SALE_CHANNEL WHERE SOC_CD = '").append(offers.get(i).getSocCD()).append("' AND SOC_CHANNEL_CODE = '").append(perfis.get(j).getPerfil()).append("';\n");
                        System.out.println("DELETE FROM MTAREFWORK.CSM_OFFER_SALE_CHANNEL WHERE SOC_CD = '" + offers.get(i).getSocCD() + "' AND SOC_CHANNEL_CODE = '" + perfis.get(j).getPerfil() + "';");

                        countQtdeDelets++;
                        countQtdeDeletsTotal++;
                    }

                }

                delete.append("-- ").append(countQtdeDelets).append(" registros excluídos\n\n");
                System.out.println("-- " + countQtdeDelets + " registros excluídos\n\n");
                delete.append("");
                System.out.println("");
                countQtdeDelets = 0;
            }
            
                delete.append("-- ").append(countQtdeDeletsTotal).append(" registros excluídos no total\n\n");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();
        }

        return delete.toString();
    }

    public static String generateSelectPerfil(List<Offers> offers, List<PerfilModel> perfis, Fields fields) {

        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countStates = 1;

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: Fábrica HITSS\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para :  ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        select.append("SELECT \n"
                + "  sale.* \n"
                + "FROM \n"
                + "   csm_offer_sale_channel sale\n"
                + "WHERE \n"
                + "    (sale.soc_cd \n"
                + "IN \n  (\n");
        for (Offers ofertas : offers) {
            if (offers.size() == countOffers) {
                select.append("   '").append(ofertas.getSocCD()).append("'\n  ))\n");
            } else {
                select.append("   '").append(ofertas.getSocCD()).append("',\n");
            }
            
            if(countOffers%999 == 0){
               select.append("   '").append(ofertas.getSocCD()).append("'\n  )\n"); 
               select.append(" OR sale.soc_cd ").append("IN( \n");
            }
            
            countOffers++;
        }

        select.append("AND \n"
                + "   sale.soc_channel_code \n"
                + "IN \n  (\n");

        for (PerfilModel perfilObj : perfis) {
            if (perfis.size() == countStates) {
                select.append("   '").append(perfilObj.getPerfil()).append("'\n  )\n");
            } else {
                select.append("   '").append(perfilObj.getPerfil()).append("',\n");
            }

            countStates++;
        }

        select.append("ORDER BY \n"
                + "   sale.soc_cd,\n"
                + "   sale.soc_channel_code;\n");

        return select.toString();
    }
}
