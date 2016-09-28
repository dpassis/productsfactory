/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParametrosAlunoAtivoInativo.java
 *
 * Created on 17/04/2013, 22:38:20
 */
package productsfactory.relatorios;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import productsfactory.useful.TelaUteis;

/**
 *
 * @author Daniel
 */
public class ParametrosDesistentesCurso extends javax.swing.JDialog {

    private static ParametrosDesistentesCurso jDialogParametros = null;

    public static ParametrosDesistentesCurso getDialogParametros(java.awt.Frame janela){

        if (jDialogParametros == null) {
            jDialogParametros = new ParametrosDesistentesCurso(janela,true);
        }
        return jDialogParametros;
    }
    /** Creates new form ParametrosAlunoAtivoInativo */
    public ParametrosDesistentesCurso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializa();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDPA1 = new interfacegrafica.dpa.panels.PanelDPA();
        btnFiltrar = new interfacegrafica.dpa.buttons.ButtonSimplesDPA();
        lblFiltroReport = new interfacegrafica.dpa.labels.LabelDPA();
        dtInicioPeriodo = new com.toedter.calendar.JDateChooser();
        dtTerminoPeriodo = new com.toedter.calendar.JDateChooser();
        lblInicioPeriodo = new interfacegrafica.dpa.labels.LabelDPA();
        lblTerminoPeriodo = new interfacegrafica.dpa.labels.LabelDPA();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemaoficinamusica/images/search_little.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        lblFiltroReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemaoficinamusica/images/filtrofreport.jpg"))); // NOI18N
        lblFiltroReport.setText("");

        lblInicioPeriodo.setText("Início Período:");

        lblTerminoPeriodo.setText("Fim Período:");

        javax.swing.GroupLayout panelDPA1Layout = new javax.swing.GroupLayout(panelDPA1);
        panelDPA1.setLayout(panelDPA1Layout);
        panelDPA1Layout.setHorizontalGroup(
            panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTerminoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtTerminoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(lblFiltroReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        panelDPA1Layout.setVerticalGroup(
            panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltroReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDPA1Layout.createSequentialGroup()
                        .addComponent(lblInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtInicioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDPA1Layout.createSequentialGroup()
                        .addComponent(lblTerminoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtTerminoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(panelDPA1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
// TODO add your handling code here:
    this.geraReport();
}//GEN-LAST:event_btnFiltrarActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private interfacegrafica.dpa.buttons.ButtonSimplesDPA btnFiltrar;
    private com.toedter.calendar.JDateChooser dtInicioPeriodo;
    private com.toedter.calendar.JDateChooser dtTerminoPeriodo;
    private interfacegrafica.dpa.labels.LabelDPA lblFiltroReport;
    private interfacegrafica.dpa.labels.LabelDPA lblInicioPeriodo;
    private interfacegrafica.dpa.labels.LabelDPA lblTerminoPeriodo;
    private interfacegrafica.dpa.panels.PanelDPA panelDPA1;
    // End of variables declaration//GEN-END:variables

    private void geraReport(){
        
        
            if(this.dtInicioPeriodo.getDate().after(this.dtTerminoPeriodo.getDate())){
                JOptionPane.showMessageDialog(this, "Período Inválido!\nVerifique as datas informadas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }else{
                RelatorioDesistentesCurso  relatorioDesistentesCurso = new RelatorioDesistentesCurso(this, true);
                this.dispose();
                relatorioDesistentesCurso.geraRelatorio(this.dtInicioPeriodo.getDate(), this.dtTerminoPeriodo.getDate());
            }

           
        
        
    }
     private void addWindowListener() {

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                System.gc();
                jDialogParametros = null;
            }
        });
    }
    
    private void inicializa(){
        this.addWindowListener();
        TelaUteis.locateOnScreen(this, 500 , 330);
    }


}
