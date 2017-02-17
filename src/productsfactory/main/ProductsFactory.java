/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.main;

import java.sql.SQLException;
import productsfactory.view.Login;

/**
 *
 * @author Daniel
 */
public class ProductsFactory {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        Login login = new Login();
        login.setVisible(true);
        
    }
}
