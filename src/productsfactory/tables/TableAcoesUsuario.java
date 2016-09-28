/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.tables;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import productsfactory.model.AcoesUsuarios;
import productsfactory.model.Usuario;
import productsfactory.useful.DataUteis;

/**
 *
 * @author Daniel
 */
public class TableAcoesUsuario extends AbstractTableModel {
    
    List<AcoesUsuarios> listAcoes;
    
   //codigo_ponto,descricãoponto,datacomleta,horaponto,data,usuario
   private String colunas[] = {"","Descricao","Data","Hora","",""};

    public void setAcoes(List<AcoesUsuarios> acao) {

       this.listAcoes = (List<AcoesUsuarios>) acao;
       //setLinhas((ArrayList) pagamentos);
       fireTableDataChanged();

      }

    @Override
    public int getRowCount() {
       if(listAcoes != null){
            return listAcoes.size();
        }else{
            return 0;
        }
    }

    @Override
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
                        return Date.class;
                case 3: 
                        return String.class;                               
                case 4:
                        return String.class;
                case 5:
                        return Usuario.class;
               
		default:
			// Se o índice da coluna não for válido, lança um
			// IndexOutOfBoundsException (Exceção de índice fora dos limites).
			// Não foi necessário verificar se o índice da linha é inválido,
			// pois o próprio ArrayList lança a exceção caso seja inválido.
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        AcoesUsuarios acao = listAcoes.get(rowIndex);

        switch(columnIndex){
            case 0:
                return acao.getCodigoAcao();
            case 1:
                return acao.getDescricaoAcao();
            case 2:
                return acao.getDataCompleta();
            case 3:
                return acao.getHoraAcao();
            case 4:
                return DataUteis.sdfFormat(acao.getDataAcao());
            case 5:
                return acao.getUsuarioAcao().getNomeUsuario();
            default:
                return null;

        }
    }
}
