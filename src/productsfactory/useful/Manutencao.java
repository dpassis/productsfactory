/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Manutencao {
    
    public boolean userManutencao(String user, String senha){
        
        try {
            String usuario = HashUtil.stringToMD5(user);
            String password =  HashUtil.stringToMD5(senha);
            
          
                Properties config = new Properties();
                InputStream configuracao = getClass().getResourceAsStream("/productsfactory/config/loginmanut.properties");
                config.load(configuracao);
                if(usuario.equals(config.getProperty("user")) && password.equals(config.getProperty("senha"))){
                     
                     return true;
                 }

            } catch (IOException ex) {
                Logger.getLogger(Manutencao.class.getName()).log(Level.SEVERE, null, ex);

            
           
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Manutencao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return false;
    }
}
