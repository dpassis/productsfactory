/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.model;

import java.sql.SQLException;
import productsfactory.control.UsuarioControl;

/**
 *
 * @author Daniel
 */
public class Usuario {
    
    
    private Integer codigoUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private String categoriaUsuario;
    private String emailUsuario;
    private String statusUsuario;
    private String novaSenhaUsuario;
    
    private static Usuario instanceUsuario;
    
    public static Usuario getUserInstance(){
        
        if(instanceUsuario == null){
            
            instanceUsuario = new Usuario();
        }
        
        return instanceUsuario;
    }

    public String getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public void setCategoriaUsuario(String categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(String statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public String getNovaSenhaUsuario() {
        return novaSenhaUsuario;
    }

    public void setNovaSenhaUsuario(String novaSenhaUsuario) {
        this.novaSenhaUsuario = novaSenhaUsuario;
    }
    
    
    public static Usuario getFullName(Usuario usuario) throws SQLException{
        
        return UsuarioControl.getNomeUsuario(usuario);
    }
    
    
    @Override
    public String toString(){
        
        return nomeUsuario;
    }
    
    
}
