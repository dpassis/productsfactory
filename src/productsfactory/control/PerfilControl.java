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
import productsfactory.model.PerfilModel;
import productsfactory.useful.ConexaoOracle;
import productsfactory.useful.DataUtil;

/**
 *
 * @author Daniel Paulo
 */
public class PerfilControl {
    
    
    

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

                pstmt = con.prepareStatement("SELECT soc_channel_code FROM mtaappc.csm_offer_sale_channel@bcv_fm WHERE soc_channel_code = (?) GROUP BY soc_channel_code");
                pstmt.setString(1, perfil.get(i).replace("\r", ""));

                rs = pstmt.executeQuery();
                PerfilModel perfilObj = new PerfilModel();
                
                if (rs.next()) {

                   

                    if (rs.getString("soc_channel_code") != null) {

                        perfilObj.setPerfil(rs.getString("soc_channel_code"));
                        perfilObj.setStatusPerfil("OK!");
                    } 
                }else {
                        perfilObj.setPerfil(perfil.get(i));
                        perfilObj.setStatusPerfil("NOK!");
                    }

                    listPerfis.add(perfilObj);

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

    public static String generateInsertsPerfil(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        int countQtdeInserts = 0;
        int countQtdeInsertsTotal = 0;

        StringBuilder inserts = new StringBuilder();

        inserts.append("/*****************************************************************************************\n");
        inserts.append("*@Autor: ").append(fields.getUserName()).append("\n");
        inserts.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        inserts.append("*@Description:  ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        inserts.append("* ").append(fields.getDescFuncional()).append(" \n");
        inserts.append("*****************************************************************************************/\n\n");

        try {

            for (int i = 0; i < offers.size(); i++) {
                inserts.append("--Inserts para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                for (int j = 0; j < perfis.size(); j++) {

                    pstmt = con.prepareStatement("SELECT soc_cd, soc_channel_code FROM mtaappc.csm_offer_sale_channel@bcv_fm sale "
                            + "WHERE sale.soc_cd = (?) AND sale.soc_channel_code = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, perfis.get(j).getPerfil());

                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        inserts.append("INSERT INTO mtarefwork.csm_offer_sale_channel (soc_cd, soc_channel_code, sys_creation_date, sys_update_date, operator_id, application_id, dl_service_code, dl_update_stamp, updateable_offer_ind, updateable_param_ind) VALUES ('").append(offers.get(i).getSocCD()).append("','").append(perfis.get(j).getPerfil()).append("',SYSDATE,NULL, ").append(fields.getOperatorID()).append(",'DPPC','").append(fields.getDlServiceCode()).append("',").append(fields.getDlUpdateStamp()).append(",NULL, NULL);\n");
                        
                        countQtdeInserts++;
                        countQtdeInsertsTotal++;
                    }
                }

                inserts.append("-- ").append(countQtdeInserts).append(" registros inseridos\n\n");
                countQtdeInserts = 0;
            }
            
            inserts.append("-- ").append(countQtdeInsertsTotal).append(" registros inseridos no total\n\n");
            System.out.println(inserts.toString());

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
        delete.append("*@Autor: ").append(fields.getUserName()).append("\n");
        delete.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        delete.append("*@Description: Script de Rollback para: ").append("\n");
        delete.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");        
        delete.append("*****************************************************************************************/\n\n");

        try {

            for (int i = 0; i < offers.size(); i++) {
                delete.append("--Delete para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                for (int j = 0; j < perfis.size(); j++) {

                   pstmt = con.prepareStatement("SELECT soc_cd, soc_channel_code FROM mtaappc.csm_offer_sale_channel@bcv_fm sale "
                            + "WHERE sale.soc_cd = (?) AND sale.soc_channel_code = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, perfis.get(j).getPerfil());

                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        delete.append("DELETE FROM mtarefwork.csm_offer_sale_channel WHERE soc_cd = '").append(offers.get(i).getSocCD()).append("' AND soc_channel_code = '").append(perfis.get(j).getPerfil()).append("';\n");

                        countQtdeDelets++;
                        countQtdeDeletsTotal++;
                    }
                }

                delete.append("-- ").append(countQtdeDelets).append(" registros excluídos\n\n");
                countQtdeDelets = 0;
            }
            
                delete.append("-- ").append(countQtdeDeletsTotal).append(" registros excluídos no total\n\n");
                System.out.println(delete.toString());

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();
        }

        return delete.toString();
    }

    public static String generateSelectPerfil(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        
        List<Offers> offersReal = new ArrayList<>();
        List<PerfilModel> perfisReal = new ArrayList<>();
        
        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countPerfis = 1;
        

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: ").append(fields.getUserName()).append("\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para o script gerado para: ").append("\n");
        select.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        select.append("SELECT \n"
                + "  sale.* \n"
                + "FROM \n"
                + "   csm_offer_sale_channel sale\n"
                + "WHERE \n"
                + "    (sale.soc_cd \n"
                + "IN \n  (\n");
            for (int i = 0; i < offers.size(); i++) {
             

                for (int j = 0; j < perfis.size(); j++) {

                    pstmt = con.prepareStatement("SELECT soc_cd, soc_channel_code FROM mtaappc.csm_offer_sale_channel@bcv_fm sale "
                            + "WHERE sale.soc_cd = (?) AND sale.soc_channel_code = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, perfis.get(j).getPerfil());

                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        if(!offersReal.contains(offers.get(i)))
                            offersReal.add(offers.get(i));
                        
                        if(!perfisReal.contains(perfis.get(j)))
                            perfisReal.add(perfis.get(j));
                    }
                }
            }
        
        
        
        for (Offers ofertas : offersReal) {
            if (offersReal.size() == countOffers) {
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

        for (PerfilModel perfilObj : perfisReal) {
            if (perfisReal.size() == countPerfis) {
                select.append("   '").append(perfilObj.getPerfil()).append("'\n  )\n");
            } else {
                select.append("   '").append(perfilObj.getPerfil()).append("',\n");
            }

            countPerfis++;
        }

        select.append("ORDER BY \n"
                + "   sale.soc_cd,\n"
                + "   sale.soc_channel_code;\n");
        
        select.append("-- ").append(((countOffers-1) * (countPerfis-1))).append(" registros selecionados no total\n\n");
        
        System.out.println(select.toString());

        return select.toString();
    }
    
    
    public static String generateSelectPerfilRollback(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countPerfis = 1;
        int countResult = 0;
        
        List<Offers> offersReal = new ArrayList<>();
        List<PerfilModel> perfisReal = new ArrayList<>();

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: ").append(fields.getUserName()).append("\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para o Rollback gerado para o Script: ").append("\n");
        select.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        select.append("SELECT \n"
                + "  sale.* \n"
                + "FROM \n"
                + "   csm_offer_sale_channel sale\n"
                + "WHERE \n"
                + "    (sale.soc_cd \n"
                + "IN \n  (\n");
            for (int i = 0; i < offers.size(); i++) {
             

                for (int j = 0; j < perfis.size(); j++) {

                    pstmt = con.prepareStatement("SELECT soc_cd, soc_channel_code FROM mtaappc.csm_offer_sale_channel@bcv_fm sale "
                            + "WHERE sale.soc_cd = (?) AND sale.soc_channel_code = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, perfis.get(j).getPerfil());

                    rs = pstmt.executeQuery();

                    if (!rs.next()){
                         countResult++;
                        if(!offersReal.contains(offers.get(i)))
                            offersReal.add(offers.get(i));
                        
                        if(!perfisReal.contains(perfis.get(j)))
                            perfisReal.add(perfis.get(j));
                    }
                }
            }
        
        for (Offers ofertas : offersReal) {
            if (offersReal.size() == countOffers) {
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

        for (PerfilModel perfilObj : perfisReal) {
            if (perfisReal.size() == countPerfis) {
                select.append("   '").append(perfilObj.getPerfil()).append("'\n  )\n");
            } else {
                select.append("   '").append(perfilObj.getPerfil()).append("',\n");
            }

            countPerfis++;
        }

        select.append("ORDER BY \n"
                + "   sale.soc_cd,\n"
                + "   sale.soc_channel_code;\n");
        
        select.append("-- ").append(((countOffers-1) * (countPerfis-1)) - countResult).append(" registros selecionados no total\n\n");
        
        System.out.println(select.toString());

        return select.toString();
    }
}
