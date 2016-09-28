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
import productsfactory.useful.TelaUteis;

/**
 *
 * @author Daniel
 */
public class ParametrosAlunosBoleto extends javax.swing.JDialog {

    private static ParametrosAlunosBoleto jDialogParametros = null;

    public static ParametrosAlunosBoleto getDialogParametros(java.awt.Frame janela){

        if (jDialogParametros == null) {
            jDialogParametros = new ParametrosAlunosBoleto(janela,true);
        }
        return jDialogParametros;
    }
    /** Creates new form ParametrosAlunoAtivoInativo */
    public ParametrosAlunosBoleto(java.awt.Frame parent, boolean modal) {
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
        lblSituacaoBoletos = new interfacegrafica.dpa.labels.LabelDPA();
        cmbSituacaoBoletos = new interfacegrafica.dpa.combobox.ComboboxDPA();

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

        lblSituacaoBoletos.setText("Situação dos Boletos:");

        cmbSituacaoBoletos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Não emitidos", "Emitidos 6 meses", "Emitidos 12 meses" }));

        javax.swing.GroupLayout panelDPA1Layout = new javax.swing.GroupLayout(panelDPA1);
        panelDPA1.setLayout(panelDPA1Layout);
        panelDPA1Layout.setHorizontalGroup(
            panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addGroup(panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDPA1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(lblFiltroReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDPA1Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSituacaoBoletos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSituacaoBoletos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDPA1Layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        panelDPA1Layout.setVerticalGroup(
            panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltroReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(lblSituacaoBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSituacaoBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        getContentPane().add(panelDPA1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
// TODO add your handling code here:
    String situacao_boleto = this.cmbSituacaoBoletos.getSelectedItem().toString();
    RelatorioAlunosBoleto relatorioAlunosBoleto = new RelatorioAlunosBoleto(this,true);
    relatorioAlunosBoleto.geraRelatorio(situacao_boleto);
    this.dispose();
}//GEN-LAST:event_btnFiltrarActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private interfacegrafica.dpa.buttons.ButtonSimplesDPA btnFiltrar;
    private interfacegrafica.dpa.combobox.ComboboxDPA cmbSituacaoBoletos;
    private interfacegrafica.dpa.labels.LabelDPA lblFiltroReport;
    private interfacegrafica.dpa.labels.LabelDPA lblSituacaoBoletos;
    private interfacegrafica.dpa.panels.PanelDPA panelDPA1;
    // End of variables declaration//GEN-END:variables

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
        TelaUteis.locateOnScreen(this, 500 , 310);
    }


}
