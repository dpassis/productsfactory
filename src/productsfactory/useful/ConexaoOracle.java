/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Daniel
 */
public class ConexaoOracle {
    
    Connection con = null;
    private static ConexaoOracle instance = null;
    private InputStream arquivoURL = getClass().getResourceAsStream("/productsfactory/config/conexaoOracle.properties");
    private static Properties config = new Properties();

    /**
     * 
     */
    public ConexaoOracle() {
        loadDriver();
    }

    /**
     * 
     */
    public static void loadDriver() {

        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(driver);
            ConexaoOracle.setTnsAdmin();
            System.out.println("Driver carregado com sucesso !!!!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver nâo pode ser carregado!!!!");
        }
        
        
    }

    /**
     * 
     * @return 
     */
    public static ConexaoOracle getInstance() {
        
        if (instance == null) {
            instance = new ConexaoOracle();
        }
        return instance;
    }

    /**
     * 
     * @return Connection
     */
    public Connection getConnection() {
        
        
        try {
           
        config.load(arquivoURL);
        
        StringBuilder banco = new StringBuilder();
        banco.append(config.getProperty("banco"));
        banco.toString();
        
        
        StringBuilder url = new StringBuilder();
        url.append("jdbc:oracle:thin:@");
        url.append(banco);
        
            System.out.println(url);
         
            if (con == null || con.isClosed()) {
                
               con = DriverManager.getConnection(url.toString(),"MTAAPP6","MTAAPP6");
               System.out.println("Conectado com sucesso!"); 
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ConexaoOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    
    public static void setTnsAdmin() {
        String tnsAdmin = System.getenv("TNS_ADMIN");
        if (tnsAdmin == null) {
            String oracleHome = System.getenv("ORACLE_HOME");
          
            if (oracleHome == null) {
                System.out.println("failed load tns");
                return; //failed to find any useful env variables
            }
            tnsAdmin = oracleHome + File.separatorChar + "network" + File.separatorChar + "admin";
        }
        System.setProperty("oracle.net.tns_admin", tnsAdmin);
        
        System.out.println(System.getenv("TNS_ADMIN"));
        System.out.println(System.getenv("ORACLE_HOME"));
    }

    /**
     * 
     */
    public  void destroy() {
        try {
           if(con != null){
                con.close();
                con = null;
           }
            System.out.println("Conexão encerrada com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
