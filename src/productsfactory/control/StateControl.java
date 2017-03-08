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
import productsfactory.model.StateModel;
import productsfactory.useful.ConexaoOracle;
import productsfactory.useful.DataUtil;

/**
 *
 * @author Daniel Paulo
 */
public class StateControl {

    /**
     * Gera uma <code>String</code> com os valores de Insert para States
     * 
     * @param offers
     * @param states
     * @param fields
     * @return <code>String</code>
     * @throws SQLException
     */
    public static String generateInsertsStates(List<Offers> offers, List<StateModel> states, Fields fields) throws SQLException {

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

        inserts.append("");
        inserts.append("");
        inserts.append("");
        inserts.append("");
        
        try {

            for (int i = 0; i < offers.size(); i++) {
                inserts.append("--Inserts para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                for (int j = 0; j < states.size(); j++) {

                    pstmt = con.prepareStatement("SELECT offer.soc_cd, offer.soc_name, state.state FROM mtaappc.csm_offer_state@bcv_fm state "
                            + "INNER JOIN  mtaappc.csm_offer@bcv_fm offer "
                            + "ON (state.soc_cd = offer.soc_cd) WHERE offer.soc_cd = (?) AND state.state = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, states.get(j).getState());

                    rs = pstmt.executeQuery();

                    if (rs.next()) {

                    } else {
                        inserts.append("INSERT INTO mtarefwork.csm_offer_state (soc_cd, state, market_company, sys_creation_date, sys_update_date, operator_id, application_id, dl_service_code, dl_update_stamp) VALUES ('").append(offers.get(i).getSocCD()).append("','").append(states.get(j).getState()).append("','*',SYSDATE,NULL,").append(fields.getOperatorID()).append(",'DPPC','").append(fields.getDlServiceCode()).append("',").append(fields.getDlUpdateStamp()).append(");\n");

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

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();
        }

        return inserts.toString();
    }

    /**
     *
     * @param offers
     * @param states
     * @param fields
     * @return
     * @throws SQLException
     */
    public static String generateRollbackStates(List<Offers> offers, List<StateModel> states, Fields fields) throws SQLException {

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

        delete.append("");
        delete.append("");

        delete.append("");
        delete.append("");
        System.out.println("");
        System.out.println("");

        try {

            for (int i = 0; i < offers.size(); i++) {
                delete.append("--Delete para a oferta ").append(offers.get(i).getSocCD()).append(" - ").append(offers.get(i).getSocName()).append("\n");

                for (int j = 0; j < states.size(); j++) {

                    pstmt = con.prepareStatement("SELECT offer.soc_cd, offer.soc_name, state.state FROM mtaappc.csm_offer_state@bcv_fm state "
                            + "INNER JOIN  mtaappc.csm_offer@bcv_fm offer "
                            + "ON (state.soc_cd = offer.soc_cd) WHERE offer.soc_cd = (?) AND state.state = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, states.get(j).getState());

                    rs = pstmt.executeQuery();

                    if (rs.next()) {

                    } else {
                        delete.append("DELETE FROM mtarefwork.csm_offer_state WHERE soc_cd = '").append(offers.get(i).getSocCD()).append("' AND state = '").append(states.get(j).getState()).append("';\n");
                        System.out.println("DELETE FROM MTAREFWORK.CSM_OFFER_STATE WHERE SOC_CD = '" + offers.get(i).getSocCD() + "' AND STATE = '" + states.get(j).getState() + "';");

                        countQtdeDelets++;
                        countQtdeDeletsTotal++;
                    }

                }

                delete.append("-- ").append(countQtdeDelets).append(" registros excluídos\n\n");
                delete.append("");
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

    /**
     *
     * @param offers
     * @param states
     * @param fields
     * @return
     */
    public static String generateSelectState(List<Offers> offers, List<StateModel> states, Fields fields) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        
        List<Offers> offersReal = new ArrayList<>();
        List<StateModel> statesReal = new ArrayList<>();
        
        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countStates = 1;

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: ").append(fields.getUserName()).append("\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para o script gerado para: ").append("\n");
        select.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        
               for (int i = 0; i < offers.size(); i++) {

                for (int j = 0; j < states.size(); j++) {

                    pstmt = con.prepareStatement("SELECT offer.soc_cd, offer.soc_name, state.state FROM mtaappc.csm_offer_state@bcv_fm state "
                            + "INNER JOIN  mtaappc.csm_offer@bcv_fm offer "
                            + "ON (state.soc_cd = offer.soc_cd) WHERE offer.soc_cd = (?) AND state.state = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, states.get(j).getState());

                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                         if(!offersReal.contains(offers.get(i)))
                            offersReal.add(offers.get(i));
                        
                        if(!statesReal.contains(states.get(j)))
                            statesReal.add(states.get(j));
                    }
                }

            }
        
        
        select.append("SELECT \n"
                + "  state.* \n"
                + "FROM \n"
                + "   csm_offer_state state\n"
                + "WHERE \n"
                + "    (state.soc_cd \n"
                + "IN \n  (\n");
        for (Offers ofertas : offersReal) {
            if (offersReal.size() == countOffers) {
                select.append("   '").append(ofertas.getSocCD()).append("'\n  ))\n");
            } else {
                select.append("   '").append(ofertas.getSocCD()).append("',\n");
            }
            if(countOffers%999 == 0){
               select.append("   '").append(ofertas.getSocCD()).append("'\n  )\n"); 
               select.append(" OR state.soc_cd ").append("IN( \n");
            }
            
            countOffers++;
        }

        select.append("AND \n"
                + "   state.state \n"
                + "IN \n  (\n");

        for (StateModel estados : statesReal) {
            if (statesReal.size() == countStates) {
                select.append("   '").append(estados.getState()).append("'\n  )\n");
            } else {
                select.append("   '").append(estados.getState()).append("',\n");
            }

            countStates++;
        }

        select.append("ORDER BY \n"
                + "   state.soc_cd,\n"
                + "   state.state;\n");
        
        select.append("-- ").append(((countOffers-1) * (countStates-1))).append(" registros selecionados no total\n\n");
        System.out.println(select.toString());

        return select.toString();
    }
    
    
     /**
     *
     * @param offers
     * @param states
     * @param fields
     * @return
     */
    public static String generateSelectStateRollback(List<Offers> offers, List<StateModel> states, Fields fields) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();
        
        List<Offers> offersReal = new ArrayList<>();
        List<StateModel> statesReal = new ArrayList<>();
        
        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countStates = 1;
        int countResult = 0;

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: ").append(fields.getUserName()).append("\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para o Rollback gerado para o Script: ").append("\n");
        select.append("* ").append(fields.getDlServiceCode()).append(fields.getDlUpdateStamp()).append(" - ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        for (int i = 0; i < offers.size(); i++) {

                for (int j = 0; j < states.size(); j++) {

                    pstmt = con.prepareStatement("SELECT offer.soc_cd, offer.soc_name, state.state FROM mtaappc.csm_offer_state@bcv_fm state "
                            + "INNER JOIN  mtaappc.csm_offer@bcv_fm offer "
                            + "ON (state.soc_cd = offer.soc_cd) WHERE offer.soc_cd = (?) AND state.state = (?)");
                    pstmt.setString(1, offers.get(i).getSocCD());
                    pstmt.setString(2, states.get(j).getState());

                    rs = pstmt.executeQuery();

                    if (!rs.next()) {
                        countResult++;
                         if(!offersReal.contains(offers.get(i)))
                            offersReal.add(offers.get(i));
                        
                        if(!statesReal.contains(states.get(j)))
                            statesReal.add(states.get(j));
                    }
                }

            }
        
        
        select.append("SELECT \n"
                + "  state.* \n"
                + "FROM \n"
                + "   csm_offer_state state\n"
                + "WHERE \n"
                + "    (state.soc_cd \n"
                + "IN \n  (\n");
        for (Offers ofertas : offersReal) {
            if (offersReal.size() == countOffers) {
                select.append("   '").append(ofertas.getSocCD()).append("'\n  ))\n");
            } else {
                select.append("   '").append(ofertas.getSocCD()).append("',\n");
            }
            if(countOffers%999 == 0){
               select.append("   '").append(ofertas.getSocCD()).append("'\n  )\n"); 
               select.append(" OR state.soc_cd ").append("IN( \n");
            }
            
            countOffers++;
        }

        select.append("AND \n"
                + "   state.state \n"
                + "IN \n  (\n");

        for (StateModel estados : statesReal) {
            if (statesReal.size() == countStates) {
                select.append("   '").append(estados.getState()).append("'\n  )\n");
            } else {
                select.append("   '").append(estados.getState()).append("',\n");
            }

            countStates++;
        }

        select.append("ORDER BY \n"
                + "   state.soc_cd,\n"
                + "   state.state;\n");
        
        select.append("-- ").append(((countOffers-1) * (countStates-1)) - countResult).append(" registros selecionados no total\n\n");
        System.out.println(select.toString());

        return select.toString();
    }
}
