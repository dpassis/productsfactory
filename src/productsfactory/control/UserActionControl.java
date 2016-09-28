/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import productsfactory.model.Usuario;
import productsfactory.useful.Conexao;

/**
 *
 * @author Daniel
 */
public class UserActionControl {
    
    public void pontoFuncionario(){
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("inserirUsuario");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para inserir");

        try {

            
                con.setAutoCommit(false);
                pstmt = con.prepareStatement("INSERT INTO tb_ponto_funcionario (fk_funcionario, "
                        + "data_hora_ponto, motivo_ponto, descricao_motivo_ponto,tipo_entrada_ponto )"
                        + " values ((?),(?),(?),(?));");

//                pstmt.setString(1, usuario.getNomeUsuario());
//                pstmt.setString(2, usuario.getSenhaUsuario());
//                pstmt.setString(3, usuario.getCategoriaUsuario());
//                pstmt.setString(4, usuario.getEmailUsuario());
                pstmt.execute();
                pstmt.close();
                con.commit();
                
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
          
        }
    }
    
}
