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
import java.util.List;
import productsfactory.model.AnatelModel;
import productsfactory.model.Fields;
import productsfactory.model.Offers;
import productsfactory.model.StateModel;
import productsfactory.useful.ConexaoOracle;
import productsfactory.useful.DataUtil;

/**
 *
 * @author Daniel Paulo
 */
public class AnatelControl {

    /**
     *
     * @param offers
     * @param anatelCodes
     * @param states
     * @param fields
     * @return
     */
    public static String generateInsertsAnatel(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields) throws SQLException {
        
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
        inserts.append("*@Description: ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        inserts.append("* ").append(fields.getDescFuncional()).append(" \n");
        inserts.append("*****************************************************************************************/\n\n");

        inserts.append("");
        inserts.append("");
        inserts.append("");
        inserts.append("");
        
        
        for (int i = 0; i < offers.size(); i++) {
            inserts.append("--Inserts para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append(" e Código Anatel").append(anatelCodes.get(i).getCodigoAnatel()).append("\n");
            
           
            
            for (int j = 0; j < states.size(); j++) {
                
                
                    pstmt = con.prepareStatement("SELECT " +
                                                    "  anatel.offer_cd, anatel.geo_zone,anatel.anatel_cd " +
                                                    "FROM " +
                                                    "   mtaappc.bl7_offer_anatel@bcv_fm anatel " +
                                                    "WHERE " +
                                                    "    anatel.offer_cd = (?) " +
                                                    "AND " +
                                                    "   anatel.geo_zone = (?) " +
                                                    "AND " +
                                                    "   anatel.anatel_cd =(?) " +
                                                    "ORDER BY " +
                                                    "   anatel.offer_cd, " +
                                                    "   anatel.geo_zone");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, states.get(j).getState());
                    pstmt.setString(3, anatelCodes.get(j).getCodigoAnatel());
                    rs = pstmt.executeQuery();
                    
                 if (rs.next()) {
                        //do nothing 
                 } else {   
                
                    inserts.append("INSERT INTO MTAREFWORK.BL7_OFFER_ANATEL (OFFER_CD, GEO_ZONE, SYS_CREATION_DATE, SYS_UPDATE_DATE, OPERATOR_ID, APPLICATION_ID, DL_SERVICE_CODE, DL_UPDATE_STAMP, ANATEL_CD) VALUES (").append(offers.get(i).getSocCD()).append(",'").append(states.get(j).getState()).append("',SYSDATE,NULL,").append(fields.getOperatorID()).append(",'DPPC','").append(fields.getDlServiceCode()).append("',").append(fields.getDlUpdateStamp()).append(", '").append(anatelCodes.get(i).getCodigoAnatel()).append("');\n");
                    countQtdeInserts++;
                    countQtdeInsertsTotal++;
                }
                
                
                
                
            }
            
            inserts.append("-- ").append(countQtdeInserts).append(" registros inseridos\n\n");
            inserts.append("");
           
            countQtdeInserts = 0;
        }
        inserts.append("-- ").append(countQtdeInsertsTotal).append(" registros inseridos no total\n\n");
        System.out.println(inserts.toString());

        return inserts.toString();
    }

    /**
     *
     * @param offers
     * @param anatelCodes
     * @param states
     * @param fields
     * @return
     */
    public static String generateRollbackAnatel(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields) {

        
        int countQtdeDelets = 0;
        int countQtdeDeletsTotal = 0;

        StringBuilder delete = new StringBuilder();

        delete.append("/*****************************************************************************************\n");
        delete.append("*@Autor: ").append(fields.getUserName()).append("\n");
        delete.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        delete.append("*@Description: Script de Rollback para: ").append("\n");
        delete.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        delete.append("*****************************************************************************************/\n\n");

        delete.append("");
        delete.append("");
        delete.append("");
        delete.append("");
       


            for (int i = 0; i < offers.size(); i++) {
                delete.append("--Delete para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                for (int j = 0; j < states.size(); j++) {
                        delete.append("DELETE FROM MTAREFWORK.BL7_OFFER_ANATEL WHERE OFFER_CD = ").append(offers.get(i).getSocCD()).append(" AND GEO_ZONE = '").append(states.get(j).getState()).append("' AND ANATEL_CD = '").append(anatelCodes.get(i).getCodigoAnatel()).append("';\n");
                        System.out.println("DELETE FROM MTAREFWORK.BL7_OFFER_ANATEL WHERE SOC_CD = '" + offers.get(i).getSocCD() + "' AND GEO_ZONE = '" + states.get(j).getState() + "';");

                        countQtdeDelets++;
                        countQtdeDeletsTotal++;
                }

                delete.append("-- ").append(countQtdeDelets).append(" registros excluídos\n\n");
                delete.append("");
                countQtdeDelets = 0;
            }
            
            delete.append("-- ").append(countQtdeDeletsTotal).append(" registros excluídos no total\n\n");
            System.out.println(delete.toString());


        return delete.toString();
    }

    /**
     * Gera  query de validação para o script gerado
     * 
     * @param offers
     * @param anatelCodes
     * @param states
     * @param fields
     * @return
     */
    public static String generateSelectAnatel(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields) {

        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countStates = 1;
        int countAnatelCodes = 1;

        select.append("/************************************************************************************************************************\n");
        select.append("*@Autor: ").append(fields.getUserName()).append("\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para o script gerado para: ").append("\n");
        select.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        select.append("***********************************************************************************************************************/\n\n");

        select.append("SELECT \n"
                + "  anatel.* \n"
                + "FROM \n"
                + "   BL7_OFFER_ANATEL anatel\n"
                + "WHERE \n"
                + "    anatel.offer_cd \n"
                + "IN \n  (\n");
        for (Offers ofertas : offers) {
            if (offers.size() == countOffers) {
                select.append("   '").append(ofertas.getSocCD()).append("'\n  )\n");
            } else {
                select.append("   '").append(ofertas.getSocCD()).append("',\n");
            }
            countOffers++;
        }

        select.append("AND \n"
                + "   anatel.geo_zone \n"
                + "IN \n  (\n");

        for (StateModel estados : states) {
            if (states.size() == countStates) {
                select.append("   '").append(estados.getState()).append("'\n  )\n");
            } else {
                select.append("   '").append(estados.getState()).append("',\n");
            }

            countStates++;
        }
        
        select.append("AND \n"
                + "   anatel.anatel_cd \n"
                + "IN \n  (\n");
        
        for (AnatelModel codes : anatelCodes) {
            if (anatelCodes.size() == countAnatelCodes) {
                select.append("   '").append(codes.getCodigoAnatel()).append("'\n  )\n");
            } else {
                select.append("   '").append(codes.getCodigoAnatel()).append("',\n");
            }

            countAnatelCodes++;
        }

        select.append("ORDER BY \n"
                + "   anatel.offer_cd,\n"
                + "   anatel.geo_zone;\n");
        select.append("-- ").append(((countOffers-1) * (countStates-1))).append(" registros selecionados no total\n\n");
        System.out.println(select.toString());

        return select.toString();
    }
    
    /**
     * Gera  query de validação para o script de Rollback gerado
     * 
     * @param offers
     * @param anatelCodes
     * @param states
     * @param fields
     * @return
     */
    public static String generateSelectAnatelRollback(List<Offers> offers,List<AnatelModel> anatelCodes, List<StateModel> states, Fields fields) {

        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countStates = 1;
        int countAnatelCodes = 1;

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: ").append(fields.getUserName()).append("\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para o Rollback gerado para: ").append("\n");
        select.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        select.append("SELECT \n"
                + "  anatel.* \n"
                + "FROM \n"
                + "   BL7_OFFER_ANATEL anatel\n"
                + "WHERE \n"
                + "    anatel.offer_cd \n"
                + "IN \n  (\n");
        for (Offers ofertas : offers) {
            if (offers.size() == countOffers) {
                select.append("   '").append(ofertas.getSocCD()).append("'\n  )\n");
            } else {
                select.append("   '").append(ofertas.getSocCD()).append("',\n");
            }
            countOffers++;
        }

        select.append("AND \n"
                + "   anatel.geo_zone \n"
                + "IN \n  (\n");

        for (StateModel estados : states) {
            if (states.size() == countStates) {
                select.append("   '").append(estados.getState()).append("'\n  )\n");
            } else {
                select.append("   '").append(estados.getState()).append("',\n");
            }

            countStates++;
        }
        
        select.append("AND \n"
                + "   anatel.anatel_cd \n"
                + "IN \n  (\n");
        
        for (AnatelModel codes : anatelCodes) {
            if (anatelCodes.size() == countAnatelCodes) {
                select.append("   '").append(codes.getCodigoAnatel()).append("'\n  )\n");
            } else {
                select.append("   '").append(codes.getCodigoAnatel()).append("',\n");
            }

            countAnatelCodes++;
        }

        select.append("ORDER BY \n"
                + "   anatel.offer_cd,\n"
                + "   anatel.geo_zone;\n");
        select.append("-- ").append("0 registros selecionados no total\n\n");
        System.out.println(select.toString());

        return select.toString();
    }
}
