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
public class ConexaoMySql {

    Connection con = null;
    private static ConexaoMySql instance = null;
    private InputStream arquivoURL = getClass().getResourceAsStream("/productsfactory/config/conexaoMySql.properties");
    private static Properties config = new Properties();

    /**
     * 
     */
    public ConexaoMySql() {
        loadDriver();
    }

    /**
     * 
     */
    public static void loadDriver() {

        String driver = "com.mysql.jdbc.Driver";
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
    public static ConexaoMySql getInstance() {
        
        if (instance == null) {
            instance = new ConexaoMySql();
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
        url.append("jdbc:mysql://");
        url.append(config.getProperty("ip"));
        url.append(":3306/");
        url.append(banco);
        
            System.out.println(url.toString());
         
            if (con == null || con.isClosed()) {
                
                con = DriverManager.getConnection(url.toString(),"hitss-admin","hitss-admin");
                
                System.out.println("Conectado ao banco!!!!\nUsuario : " + user.getNomeUsuario());
                 System.out.println("Conectado ao banco!!!!\nIP : " + config.getProperty("ip"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ConexaoMySql.class.getName()).log(Level.SEVERE, null, ex);
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
