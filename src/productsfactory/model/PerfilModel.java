/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.sql.SQLException;
import java.util.List;
import productsfactory.control.PerfilControl;

/**
 *
 * @author Daniel Paulo
 */
public class PerfilModel {
    
    private String perfil;
    private String statusPerfil;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getStatusPerfil() {
        return statusPerfil;
    }

    public void setStatusPerfil(String statusPerfil) {
        this.statusPerfil = statusPerfil;
    }
    
    
    
    /**
     * 
     * @param offers
     * @param perfis
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateInsertsPerfis(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException{
        
        return PerfilControl.generateInsertsPerfil(offers, perfis, fields);
    }
    
    
    /**
     * 
     * @param offers
     * @param perfis
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateRollbackPerfis(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException{
        
        return PerfilControl.generateRollbackPerfil(offers, perfis, fields);
    }
    
    /**
     * 
     * @param offers
     * @param perfis
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateSelectPerfil(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException{
        
        return PerfilControl.generateSelectPerfil(offers, perfis, fields);
    }
    
    
    /**
     * 
     * @param offers
     * @param perfis
     * @param fields
     * @return
     * @throws SQLException 
     */
    public static String generateSelectPerfilRollback(List<Offers> offers, List<PerfilModel> perfis, Fields fields) throws SQLException{
        
        return PerfilControl.generateSelectPerfilRollback(offers, perfis, fields);
    }
    
    
}
