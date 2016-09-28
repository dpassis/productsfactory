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
import java.util.List;
import productsfactory.model.Usuario;
import productsfactory.useful.Conexao;

/**
 *
 * @author Daniel
 */
public class UsuarioControl {

    /**
     * Insere um usuário na tabela de usuário do sistema
     * 
     * @param usuario
     * @return boolean de confirmação true(inserido) false(não inserido)
     */
    public boolean inserirUsuario(Usuario usuario) {

        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("inserirUsuario");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para inserir");

        try {

           // if (this.inserirUsuarioBD(usuario)) {
                con.setAutoCommit(false);
                pstmt = con.prepareStatement("INSERT INTO tb_usuario (nome_usuario, senha_usuario, categoria_usuario,email_usuario,"
                        + "status_usuario) values ((?),md5(?),(?),(?),(?));");

                pstmt.setString(1, usuario.getNomeUsuario());
                pstmt.setString(2, usuario.getSenhaUsuario());
                pstmt.setString(3, usuario.getCategoriaUsuario());
                pstmt.setString(4, usuario.getEmailUsuario());
                pstmt.setString(5, usuario.getStatusUsuario());
                pstmt.execute();
                pstmt.close();
                con.commit();
            //} else {
             //   return false;
            //}
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * Atualiza dados na tabela de usuários do sistema
     * 
     * @param usuario
     * @return boolean de confirmação true(atualizado) false(não atualizado)
     */
    public boolean atualizarUsuario(Usuario usuario) {

        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("atualizarUsuario");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para atualizar");
        try {
          if(usuario.getSenhaUsuario() == null){
               
                con.setAutoCommit(false);
                pstmt = con.prepareStatement("UPDATE tb_usuario SET categoria_usuario =(?),"
                        + " status_usuario = (?), email_usuario = (?) WHERE codigo_usuario = (?);");

                pstmt.setString(1, usuario.getCategoriaUsuario());
                pstmt.setString(2, usuario.getStatusUsuario());
                pstmt.setString(3, usuario.getEmailUsuario());
                pstmt.setInt(4, usuario.getCodigoUsuario());
                pstmt.executeUpdate();
                pstmt.close();
                con.commit();
               
           }else{
            
            //if (this.alterarUsuarioBD(usuario)) {
                con.setAutoCommit(false);
                pstmt = con.prepareStatement("UPDATE tb_usuario SET senha_usuario = md5(?), categoria_usuario =(?),"
                        + " status_usuario = (?), email_usuario = (?) WHERE codigo_usuario = (?);");


                pstmt.setString(1, usuario.getSenhaUsuario());
                pstmt.setString(2, usuario.getCategoriaUsuario());
                pstmt.setString(3, usuario.getStatusUsuario());
                pstmt.setString(4, usuario.getEmailUsuario());
                pstmt.setInt(5, usuario.getCodigoUsuario());
                pstmt.executeUpdate();
                pstmt.close();
                con.commit();
            //} else {
             ///   return false;
           // }
           }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza a senha do usuário na tabela de usuários do sistema
     * 
     * @param usuario
     * @return  boolean de confirmação true(atualizado) false(não atualizado)
     */
    public boolean atualizarSenhaUsuario(Usuario usuario) {

        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("atualizarUsuario");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para atualizar");
        try {
            //if (this.alterarSenhaUsuarioBD(usuario)) {
                con.setAutoCommit(false);
                pstmt = con.prepareStatement("UPDATE tb_usuario SET senha_usuario = md5(?) WHERE nome_usuario = (?);");


                pstmt.setString(1, usuario.getSenhaUsuario());
                pstmt.setString(2, usuario.getNomeUsuario());

                pstmt.executeUpdate();
                pstmt.close();
                con.commit();
            //} else {
            //    return false;
            //}
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Exclui um usário da tabela de usuários do sistema
     * 
     * @param usuario
     * @return  boolean de confirmação true(excluído) false(não excluído) 
     */
    public boolean excluirUsuario(Usuario usuario) {

        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("excluirUsuario");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para excluir");
        try {
           // if (this.excluirUsuarioDB(usuario)) {
                con.setAutoCommit(false);
                pstmt = con.prepareStatement("DELETE FROM tb_acoes_usuarios WHERE fk_usuario = (?);");
                pstmt.setInt(1, usuario.getCodigoUsuario());
                pstmt.execute();
                pstmt = con.prepareStatement("DELETE FROM tb_ponto_funcionario WHERE fk_usuario = (?);");
                pstmt.setInt(1, usuario.getCodigoUsuario());
                pstmt.execute();
                
                pstmt = con.prepareStatement("DELETE FROM tb_usuario WHERE codigo_usuario = (?);");

                pstmt.setInt(1, usuario.getCodigoUsuario());
                pstmt.execute();
                pstmt.close();
                con.commit();
            //} else {
            //    return false;
           // }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Retorna um lista de usuários do sistema
     * 
     * @return
     * @throws SQLException 
     */
    public static List<Usuario> listaUsuarios() throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());

        System.out.println("conectado e preparando listagem");

        List<Usuario> listUsuarios = new ArrayList<Usuario>();
        try {

            pstmt = con.prepareStatement("SELECT codigo_usuario,nome_usuario,senha_usuario, "
                    + " categoria_usuario,email_usuario,status_usuario FROM tb_usuario ORDER BY nome_usuario");

            rs = pstmt.executeQuery();

            while (rs.next()) {

                
                Usuario usuario = new Usuario();

                if(!rs.getString("categoria_usuario").equals("root")){
                    usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
                    usuario.setNomeUsuario(rs.getString("nome_usuario"));
                    usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                    usuario.setEmailUsuario(rs.getString("email_usuario"));
                    usuario.setStatusUsuario(rs.getString("status_usuario"));
                    usuario.setCategoriaUsuario(rs.getString("categoria_usuario"));

                    listUsuarios.add(usuario);
                }

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();

        }
        return listUsuarios;
    }
    
    
     public static Usuario getUsuario(Usuario user) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());

        System.out.println("conectado e preparando listagem");
            
         Usuario usuario = new Usuario();
      
        try {

            pstmt = con.prepareStatement("SELECT codigo_usuario, nome_usuario, senha_usuario, "
                    + " categoria_usuario, email_usuario, status_usuario FROM "
                    + "tb_usuario  WHERE nome_usuario = (?);");
            pstmt.setString(1, user.getNomeUsuario());

            rs = pstmt.executeQuery();
            
            rs.next();

                usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setStatusUsuario(rs.getString("status_usuario"));
                usuario.setCategoriaUsuario(rs.getString("categoria_usuario"));

             

           
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();

        }
        return usuario;
    }
     
     
       /**
     * Retorna um lista de usuários do sistema
     * 
     * @return
     * @throws SQLException 
     */
    public static List<Usuario> ComboUsuarios() throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());

        System.out.println("conectado e preparando listagem");

        List<Usuario> listUsuarios = new ArrayList<Usuario>();
        try {

            pstmt = con.prepareStatement("SELECT codigo_usuario,nome_usuario FROM tb_usuario ORDER BY nome_usuario");

            rs = pstmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
           
                listUsuarios.add(usuario);

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();

        }
        return listUsuarios;
    }
    
    
       /**
     * Retorna um lista de usuários do sistema
     * para tarefas do sistema
     * @return
     * @throws SQLException 
     */
    public static List<Usuario> ComboUsuariosTarefa() throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());

        System.out.println("conectado e preparando listagem");

        List<Usuario> listUsuarios = new ArrayList<Usuario>();
        try {

            pstmt = con.prepareStatement("SELECT codigo_usuario,nome_usuario FROM tb_usuario WHERE categoria_usuario != 'root' ORDER BY nome_usuario");

            rs = pstmt.executeQuery();

            while (rs.next()) {

            
                Usuario usuario = new Usuario();

                usuario.setCodigoUsuario(rs.getInt("codigo_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
           
                listUsuarios.add(usuario);
               

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;

        } finally {
            rs.close();
            pstmt.close();

        }
        return listUsuarios;
    }

    /**
     * Verifica se já existe um usuário com o mesmo nome na tabela
     * de usuários do sistema para impedir criação duplicada
     * 
     * @param usuario
     * @return  boolean de confirmação true(existe) false(não existe)
     * @throws SQLException 
     */
    public boolean verificaUsuarioExistente(Usuario usuario) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());

        pstmt = con.prepareStatement("SELECT nome_usuario FROM tb_usuario WHERE nome_usuario = (?)");
        pstmt.setString(1, usuario.getNomeUsuario());
        rs = pstmt.executeQuery();

        if (rs.next()) {
            rs.close();
            pstmt.close();
        } else {
            rs.close();
            pstmt.close();
            return false;
        }
        return true;

    }

    public static boolean verificarSenha(Usuario usuario) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());

        pstmt = con.prepareStatement("SELECT * FROM tb_usuario WHERE nome_usuario = ? AND senha_usuario = md5(?) "
                + " AND status_usuario = 'Ativo';");
        pstmt.setString(1, usuario.getNomeUsuario());
        pstmt.setString(2, usuario.getSenhaUsuario());
        rs = pstmt.executeQuery();
        if (rs.next()) {
            rs.close();
            pstmt.close();
        } else {
            rs.close();
            pstmt.close();
            return false;
        }
        return true;


    }

    /**
     * Cria um nova ROLE no banco de dados
     * 
     * @param usuario
     * @return  boolean de confirmação true(criado) false(não criado)
     */
    public boolean inserirUsuarioBD(Usuario usuario) {

        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("inserirUsuarioBD");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para inserir");

        try {
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("CREATE ROLE " + usuario.getNomeUsuario() + ""
                    + " LOGIN ENCRYPTED PASSWORD '" + String.valueOf(usuario.getSenhaUsuario()) + "' "
                    + "SUPERUSER INHERIT NOCREATEDB CREATEROLE;");


            pstmt.execute();
            pstmt.close();
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza uma ROLE no banco de Dados
     * 
     * @param usuario
     * @return  boolean de confirmação true(atualizado) false(não atualizado)
     */
    public boolean alterarUsuarioBD(Usuario usuario) {
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("atualizarUsuarioBD");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para atualizar");

        try {
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("ALTER ROLE " + usuario.getNomeUsuario() + " ENCRYPTED PASSWORD '" + String.valueOf(usuario.getSenhaUsuario()) + "';");

            pstmt.execute();
            pstmt.close();
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Altera a senha de um ROLE no banco de dados
     * 
     * @param recovery
     * @return  boolean de confirmação true(alterado) false(não alterado)
     */
    public boolean alterarSenhaUsuarioBD(Usuario recovery) {
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("atualizarUsuarioBD");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para atualizar");

        try {
            con.setAutoCommit(false);

            pstmt = con.prepareStatement("ALTER ROLE " + recovery.getNomeUsuario() + " ENCRYPTED PASSWORD '" + recovery.getSenhaUsuario() + "';");

            pstmt.execute();
            pstmt.close();
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Exclui uma ROLE da base de dados
     * 
     * @param usuario
     * @return  boolean de confirmação true(excluído) false(não excluído)
     */
    public boolean excluirUsuarioDB(Usuario usuario) {
        Connection con = null;
        PreparedStatement pstmt = null;


        System.out.println("excluirUsuarioBD");
        con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
        System.out.println("conectado e preparando para inserir");

        try {
            con.setAutoCommit(false);


            pstmt = con.prepareStatement("DROP ROLE " + usuario.getNomeUsuario() + ";");


            pstmt.execute();
            pstmt.close();
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
