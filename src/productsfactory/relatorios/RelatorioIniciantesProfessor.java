/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RelatorioTodosAlunos.java
 *
 * Created on 17/04/2013, 21:44:39
 */
package productsfactory.relatorios;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import productsfactory.model.Usuario;
import productsfactory.useful.Conexao;

/**
 *
 * @author Daniel
 */
public class RelatorioIniciantesProfessor extends javax.swing.JDialog {

    
    /** Creates new form RelatorioTodosAlunos */
    public RelatorioIniciantesProfessor(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
  
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
public void geraRelatorio(Date dataInicio, Date dataFim) {  
        try {
            
               Connection con = Conexao.getInstance().getConnection(Usuario.getUserInstance());
           
               HashMap parametros = new HashMap();
               JasperPrint jp;
               URL arquivo = getClass().getResource("/sistemaoficinamusica/report/alunosIniciantesProfessor.jasper");
               JasperReport jr = (JasperReport) JRLoader.loadObject(arquivo);
               
               URL img = getClass().getResource("/sistemaoficinamusica/images/logo_report.jpg");

               String imagem = img.toString();
               
              
               parametros.put("logo", imagem);
               parametros.put("dataInicio", dataInicio);
               parametros.put("dataFim", dataFim);


               jp = JasperFillManager.fillReport(jr,parametros ,con);
               
               if (jp.getPages().isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Não há conteúdo no relatório. A visualização foi cancelada", "Relatório vazio", JOptionPane.INFORMATION_MESSAGE);
               }
               else{
                   JasperViewer jpv = new JasperViewer(jp, false);
                   jpv.setTitle("Alunos Iniciantes por Professor");
                   jpv.setAlwaysOnTop(true);
                   jpv.setVisible(true);
               }
        } catch (JRException ex) {
            Logger.getLogger(RelatorioIniciantesProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}


}