/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import productsfactory.model.Usuario;

/**
 *
 * @author Daniel
 */
public class Conexao {

    Connection con = null;
    private static Conexao instance = null;
    private InputStream arquivoURL = getClass().getResourceAsStream("/productsfactory/config/conexao.properties");
    private static Properties config = new Properties();

    /**
     * 
     */
    public Conexao() {
        loadDriver();
    }

    /**
     * 
     */
    public static void loadDriver() {

        String driver = "org.postgresql.Driver";
        try {
            Class.forName(driver);
            System.out.println("Driver carregado com sucesso !!!!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver nâo pode ser carregado!!!!");
        }
    }

    /**
     * 
     * @return 
     */
    public static Conexao getInstance() {
        
        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    /**
     * 
     * @param user
     * @return 
     */
    public Connection getConnection(Usuario user) {
        try {
       
        config.load(arquivoURL);
        
        StringBuilder banco = new StringBuilder();
        banco.append(config.getProperty("banco"));
        banco.toString();
        
        StringBuilder url = new StringBuilder();
        url.append("jdbc:postgresql://");
        url.append(config.getProperty("ip"));
        url.append(":5432/");
        url.append(banco);
         
            if (con == null || con.isClosed()) {
                
                con = DriverManager.getConnection(url.toString(),"factory","thedoors");
                
                System.out.println("Conectado ao banco!!!!\nUsuario : " + user.getNomeUsuario());
                 System.out.println("Conectado ao banco!!!!\nIP : " + config.getProperty("ip"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    /**
     * 
     */
    public void destroy() {
        try {
            con.close();
            con = null;
            System.out.println("Conexão encerrada com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
