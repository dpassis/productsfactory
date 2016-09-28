/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import productsfactory.model.Usuario;

/**
 *
 * @author Daniel
 */
public class TableUsuarios extends AbstractTableModel {
    
   List<Usuario> listUsuarios;
   /**{codigo,nome,senha,categoria,email,status}**/
   private String colunas[] = {"","Usuário","","Categoria","",""};

    public void setUsuarios(List<Usuario> usuario) {

       this.listUsuarios = (List<Usuario>) usuario;
       
       fireTableDataChanged();

      }

    public int getRowCount() {
       if(listUsuarios != null){
            return listUsuarios.size();
        }else{
            return 0;
        }
    }

    public int getColumnCount() {
       return colunas.length;
    }

    @Override
    public String getColumnName(int column){
        return colunas[column];

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
		// Retorna a classe referente a coluna especificada.
		// Aqui é feito um switch para verificar qual é a coluna
		// e retornar o tipo adequado. As colunas são as mesmas
		// que foram especificadas no array "colunas".

		switch (columnIndex) {

		case 0:
			return Integer.class;
		case 1:
			return String.class;
                case 2:
                        return String.class;
                case 3: 
                        return String.class;
                case 4:
                        return String.class;
                case 5:
                        return String.class;

		default:
			// Se o índice da coluna não for válido, lança um
			// IndexOutOfBoundsException (Exceção de índice fora dos limites).
			// Não foi necessário verificar se o índice da linha é inválido,
			// pois o próprio ArrayList lança a exceção caso seja inválido.
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Usuario usuario = listUsuarios.get(rowIndex);

        switch(columnIndex){
            case 0:
                return usuario.getCodigoUsuario();
            case 1:
                return usuario.getNomeUsuario();
            case 2:
                return usuario.getSenhaUsuario();
            case 3:
                return usuario.getCategoriaUsuario();
            case 4:
                return usuario.getEmailUsuario();
            case 5:
                return usuario.getStatusUsuario();
                
            default:
                return null;

        }
    }
}
