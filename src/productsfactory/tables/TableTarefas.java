/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.tables;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import productsfactory.model.Tarefa;
import productsfactory.useful.DataUteis;

/**
 *
 * @author Daniel
 */
public class TableTarefas extends AbstractTableModel {
    
    List<Tarefa> listTarefas;

   private String colunas[] = {"","Descrição","Data","Hora","Situação","Quem criou","Quem deve executar"};
   

    public void setLancamentos(List<Tarefa> tarefa) {

       this.listTarefas = (List<Tarefa>) tarefa;
       //setLinhas((ArrayList) pagamentos);
       fireTableDataChanged();

      }

    @Override
    public int getRowCount() {
       if(listTarefas != null){
            return listTarefas.size();
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
                    return String.class;
                case 6:
                    return String.class;
               
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
        
        Tarefa tarefa = listTarefas.get(rowIndex);

        switch(columnIndex){
            case 0:
                return tarefa.getCodigoTarefa();
            case 1:
                return tarefa.getDescricaoTarefa();
            case 2:
                return DataUteis.sdfFormat(tarefa.getDataTarefa());
            case 3:
                return tarefa.getHoraTarefa();
            case 4:
                return tarefa.getStatusTarefa();
            case 5:
                return tarefa.getDonoTarefa();
            case 6:
                return tarefa.getUsuario().getNomeUsuario();
            default:
                return null;

        }
    }
}
