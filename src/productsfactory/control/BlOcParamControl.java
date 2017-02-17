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
import productsfactory.model.BlOcParamModel;
import productsfactory.model.Fields;
import productsfactory.model.Offers;
import productsfactory.useful.ConexaoOracle;
import productsfactory.useful.DataUtil;

/**
 *
 * @author Daniel Paulo
 */
public class BlOcParamControl {

    /**
     *
     * @param offers
     * @param valores
     * @param fields
     * @return
     */
    public static String generateUpdateBlOcParam(List<BlOcParamModel> offers,List<String> valores, Fields fields) {

       
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
        for (int i = 0; i < offers.size(); i++) {
            inserts.append("--Update para a oferta ").append(offers.get(i).getOffer().getSocCD()).append(" - ").append(offers.get(i).getOffer().getSocName()).append("\n");
            
            System.out.println(inserts);
            
          
                
                inserts.append("UPDATE MTAREFWORK.BILL_OFFER_OC_PARAM SET SYS_UPDATE_DATE = SYSDATE, OPERATOR_ID = ").append(fields.getOperatorID()).append(", APPLICATION_ID = 'DPPC', DL_SERVICE_CODE = '").append(fields.getDlServiceCode())
                .append("', DL_UPDATE_STAMP = ").append(fields.getDlUpdateStamp()).append(", RATE = ").append(valores.get(i)).append(" WHERE OFFER = '").append(offers.get(i).getOffer().getSocCD()).append("' AND EXPIRATION_DATE IS NULL OR EXPIRATION_DATE > SYSDATE;\n");
                System.out.println(inserts.toString());
                
                countQtdeInserts++;
                countQtdeInsertsTotal++;
                
           
            
            inserts.append("-- ").append(countQtdeInserts).append(" registro(s) inserido(s)\n\n");
            System.out.println("-- " + countQtdeInserts + " registros inseridos");
            inserts.append("");
            System.out.println("");
            countQtdeInserts = 0;
        }
        inserts.append("-- ").append(countQtdeInsertsTotal).append(" registro(s) inserido(s) no total\n\n");

        return inserts.toString();
    }

    /**
     *
     * @param offers
     * @param valores
     * @param fields
     * @return
     */
    public static String generateRollbackBlOcParam(List<BlOcParamModel> offers,List<String> valores, Fields fields) {

        
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


            for (int i = 0; i < offers.size(); i++) {
                delete.append("--Delete para a oferta ").append(offers.get(i).getOffer().getSocCD()).append(" - ").append(offers.get(i).getOffer().getSocName()).append("\n");

                System.out.println(delete);

                for (int j = 0; j < valores.size(); j++) {
                        delete.append("DELETE FROM MTAREFWORK.BL7_OFFER_ANATEL WHERE OFFER_CD = ").append(offers.get(i).getOffer().getSocCD()).append(" AND GEO_ZONE = '").append(valores.get(j)).append("' AND ANATEL_CD = '").append(valores.get(i)).append("';\n");
                        System.out.println(delete);

                        countQtdeDelets++;
                        countQtdeDeletsTotal++;
                }

                delete.append("-- ").append(countQtdeDelets).append(" registro(s) excluído(s)\n\n");
                System.out.println("-- " + countQtdeDelets + " registro(s) excluído(s)\n\n");
                delete.append("");
                System.out.println("");
                countQtdeDelets = 0;
            }
            
            delete.append("-- ").append(countQtdeDeletsTotal).append(" registros excluídos no total\n\n");


        return delete.toString();
    }

    /**
     *
     * @param offers
     * @param valores
     * @param fields
     * @return
     */
    public static String generateSelectBlOcParam(List<BlOcParamModel> offers,List<String> valores, Fields fields) {

        StringBuilder select = new StringBuilder();
        int countOffers = 1;
        int countStates = 1;
        int countAnatelCodes = 1;

        select.append("/*****************************************************************************************\n");
        select.append("*@Autor: Fábrica HITSS\n");
        select.append("*@Date: ").append(DataUtil.getDateTime()).append("\n");
        select.append("*@Description: Query de Validação para :  ").append(fields.getOsDesc()).append(" \n");
        select.append("*****************************************************************************************/\n\n");

        select.append("SELECT \n"
                + "  anatel.* \n"
                + "FROM \n"
                + "   BL7_OFFER_ANATEL anatel\n"
                + "WHERE \n"
                + "    anatel.offer_cd \n"
                + "IN \n  (\n");
        for (BlOcParamModel ofertas : offers) {
            if (offers.size() == countOffers) {
                select.append("   '").append(ofertas.getOffer().getSocCD()).append("'\n  )\n");
            } else {
                select.append("   '").append(ofertas.getOffer().getSocCD()).append("',\n");
            }
            countOffers++;
        }

        select.append("AND \n"
                + "   anatel.geo_zone \n"
                + "IN \n  (\n");

        for (String values : valores) {
            if (valores.size() == countStates) {
                select.append("   '").append(values).append("'\n  )\n");
            } else {
                select.append("   '").append(values).append("',\n");
            }

            countStates++;
        }

        select.append("ORDER BY \n"
                + "   anatel.offer_cd,\n"
                + "   anatel.geo_zone;\n");

        return select.toString();
    }
    
    
public static List<BlOcParamModel> verifyOfferBlOcParamExists(List<String> offer) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = ConexaoOracle.getInstance().getConnection();

        List<BlOcParamModel> listOffers = new ArrayList<>();

        try {

            for (int i = 0; i < offer.size(); i++) {

                pstmt = con.prepareStatement("SELECT \n" +
                                            "     DISTINCT(OFFER.SOC_CD)\n" +
                                            "    ,OFFER.SOC_NAME\n" +
                                            "    ,BL_OFFER.RATE \n" +
                                            "FROM \n" +
                                            "    BILL_OFFER_OC_PARAM BL_OFFER \n" +
                                            "INNER JOIN \n" +
                                            "    CSM_OFFER OFFER\n" +
                                            "ON \n" +
                                            "    (OFFER.SOC_CD = BL_OFFER.OFFER)\n" +
                                            "WHERE \n" +
                                            "    OFFER = (?) \n" +
                                            "    AND EXPIRATION_DATE IS NULL \n" +
                                            "    OR EXPIRATION_DATE > SYSDATE");
                pstmt.setString(1, offer.get(i).replace("\r", ""));

                rs = pstmt.executeQuery();

                while (rs.next()) {

                    Offers offers = new Offers();
                    BlOcParamModel bl = new BlOcParamModel();
                    

                    if (rs.getString("soc_cd") != null) {

                        offers.setSocCD(rs.getString("soc_cd"));
                        offers.setSocName(rs.getString("soc_name"));
                        offers.setStatusOffer("OK!");
                        bl.setOffer(offers);
                        bl.setRate(rs.getString("rate"));
                    } else {
                        offers.setSocCD(offer.get(i));
                        offers.setSocName("-");
                        offers.setStatusOffer("NOK!");
                    }

                    listOffers.add(bl);

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

        return listOffers;
    }
}
