/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import productsfactory.model.Tarefa;
import productsfactory.model.Usuario;
import productsfactory.useful.Conexao;
import productsfactory.useful.UtilToSqlTeste;

/**
 *
 * @author Daniel
 */
public class TarefasControl {
    
    
    public static boolean inserirTarefa(Tarefa tarefa){
        
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("inserirTarefa");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para inserir");
        
        try {
            con.setAutoCommit(false);
            pstmt =  con.prepareStatement("INSERT INTO tb_tarefas(descricao_tarefa, data_tarefa, hora_tarefa, "
                    + "status_tarefa, usuario, usuario_dono) VALUES ((?),(?),(?),(?),(?),(?));");
            
            pstmt.setString(1,tarefa.getDescricaoTarefa());
            pstmt.setDate(2,UtilToSqlTeste.getSqlDate(tarefa.getDataTarefa()));
            pstmt.setString(3, tarefa.getHoraTarefa());
            pstmt.setString(4, tarefa.getStatusTarefa());
            pstmt.setString(5, tarefa.getUsuario().getNomeUsuario());
            pstmt.setString(6, tarefa.getDonoTarefa());
            
         
            pstmt.execute();
            pstmt.close();
            con.commit();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
       
    }
    
    public static boolean atualizarTarefa(Tarefa tarefa){
        
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("atualizarTarefa");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para atualizar");
        try {
            con.setAutoCommit(false);
            pstmt =  con.prepareStatement("UPDATE tb_tarefas SET descricao_tarefa = (?), data_tarefa = (?), "
                    + "hora_tarefa = (?), status_tarefa = (?), usuario = (?) WHERE codigo_tarefa = (?);");
            
            pstmt.setString(1,tarefa.getDescricaoTarefa());
            pstmt.setDate(2,UtilToSqlTeste.getSqlDate(tarefa.getDataTarefa()));
            pstmt.setString(3, tarefa.getHoraTarefa());
            pstmt.setString(4, tarefa.getStatusTarefa());
            pstmt.setString(5, tarefa.getUsuario().getNomeUsuario());
            pstmt.setInt(6, tarefa.getCodigoTarefa());
            
            pstmt.executeUpdate();
            pstmt.close();
            con.commit();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirTarefa(Tarefa tarefa){
        
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("excluirTarefa");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para excluir");
        try {
            con.setAutoCommit(false);
            pstmt =  con.prepareStatement("DELETE FROM tb_tarefas WHERE codigo_tarefa = (?);");
          
            pstmt.setInt(1, tarefa.getCodigoTarefa());
            pstmt.execute();
            pstmt.close();
            con.commit();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static List<Tarefa> listaTarefasData(Date dataInicio, Date dataFinal, Usuario user) throws SQLException{
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        
        System.out.println("conectado e preparando listagem");
        
       

        List<Tarefa> listTarefas = new ArrayList<Tarefa>();
        try {
            
            pstmt = con.prepareStatement("SELECT * FROM tb_tarefas WHERE data_tarefa BETWEEN (?) "
                    + "AND (?)  ORDER BY data_tarefa;");
            
            pstmt.setDate(1,UtilToSqlTeste.getSqlDate(dataInicio));
            pstmt.setDate(2, UtilToSqlTeste.getSqlDate(dataFinal));
          
           
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                

                if(user.getNomeUsuario().equals(rs.getString("usuario_dono")) || user.getNomeUsuario().equals(rs.getString("usuario")) ){
                    Tarefa tarefa = new Tarefa();

                    tarefa.setCodigoTarefa(rs.getInt("codigo_tarefa"));
                    tarefa.setDescricaoTarefa(rs.getString("descricao_tarefa"));
                    tarefa.setDataTarefa(rs.getDate("data_tarefa"));
                    tarefa.setHoraTarefa(rs.getString("hora_tarefa"));
                    tarefa.setStatusTarefa(rs.getString("status_tarefa"));

                    Usuario usuario = new Usuario();
                    usuario.setNomeUsuario(rs.getString("usuario"));
                    tarefa.setUsuario(usuario);
                    tarefa.setDonoTarefa(rs.getString("usuario_dono"));

                    listTarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            return null;
            
        }finally{
            rs.close();
            pstmt.close();

        }
       return listTarefas;
    }
    public static List<Tarefa> listaTarefasStatus(Date dataInicio, Date dataFinal,String status,Usuario usuario) throws SQLException{
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        
        System.out.println("conectado e preparando listagem");

        List<Tarefa> listTarefas = new ArrayList<Tarefa>();
        try {
            
            pstmt = con.prepareStatement("SELECT * FROM tb_tarefas WHERE data_tarefa BETWEEN (?) "
                    + "AND (?) AND status_tarefa = (?)  ORDER BY data_tarefa;");
            pstmt.setDate(1,UtilToSqlTeste.getSqlDate(dataInicio));
            pstmt.setDate(2, UtilToSqlTeste.getSqlDate(dataFinal));
            pstmt.setString(3, status);
            
           
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
               if(usuario.getNomeUsuario().equals(rs.getString("usuario_dono")) || usuario.getNomeUsuario().equals(rs.getString("usuario")) ){
                Tarefa tarefa = new Tarefa();
                
                tarefa.setCodigoTarefa(rs.getInt("codigo_tarefa"));
                tarefa.setDescricaoTarefa(rs.getString("descricao_tarefa"));
                tarefa.setDataTarefa(rs.getDate("data_tarefa"));
                tarefa.setHoraTarefa(rs.getString("hora_tarefa"));
                tarefa.setStatusTarefa(rs.getString("status_tarefa"));
                
                Usuario user = new Usuario();
                user.setNomeUsuario(rs.getString("usuario"));
                tarefa.setUsuario(user);
                tarefa.setDonoTarefa(rs.getString("usuario_dono"));
                
                listTarefas.add(tarefa);
               }
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            return null;
            
        }finally{
            rs.close();
            pstmt.close();

        }
       return listTarefas;
    }

    
   public static Integer listaTarefasDia(Date dataInicio, Date dataFinal,  Usuario usuario) throws SQLException{
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Integer contTarefas = 0;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        
        System.out.println("cotando tarefas");

    
        try {
            
            pstmt = con.prepareStatement("SELECT * FROM tb_tarefas WHERE data_tarefa BETWEEN (?) "
                    + "AND (?) AND usuario = (?) AND status_tarefa = 'Ativa';");
            pstmt.setDate(1,UtilToSqlTeste.getSqlDate(dataInicio));
            pstmt.setDate(2, UtilToSqlTeste.getSqlDate(dataFinal));
            pstmt.setString(3, usuario.getNomeUsuario());
            
           
            rs = pstmt.executeQuery();
            
            while (rs.next()) {

               contTarefas+=1;
               
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            return null;
            
        }finally{
            rs.close();
            pstmt.close();

        }
       return contTarefas;
    }

}
