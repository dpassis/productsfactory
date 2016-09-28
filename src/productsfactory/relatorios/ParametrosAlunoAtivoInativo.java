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
public class ParametrosAlunoAtivoInativo extends javax.swing.JDialog {

    private static ParametrosAlunoAtivoInativo jDialogParametros = null;
    private java.awt.Frame tela;

    public static ParametrosAlunoAtivoInativo getDialogParametros(java.awt.Frame janela) {

        if (jDialogParametros == null) {
            jDialogParametros = new ParametrosAlunoAtivoInativo(janela, true);
        }
        return jDialogParametros;
    }

    /** Creates new form ParametrosAlunoAtivoInativo */
    public ParametrosAlunoAtivoInativo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializa();
        tela = parent;
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
        cmbAtivoInativo = new interfacegrafica.dpa.combobox.ComboboxDPA();
        btnFiltrar = new interfacegrafica.dpa.buttons.ButtonSimplesDPA();
        lblFiltroReport = new interfacegrafica.dpa.labels.LabelDPA();
        lblStatus = new interfacegrafica.dpa.labels.LabelDPA();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbAtivoInativo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemaoficinamusica/images/search_little.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        lblFiltroReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemaoficinamusica/images/filtrofreport.jpg"))); // NOI18N
        lblFiltroReport.setText("");

        lblStatus.setText("Status do Aluno");

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
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbAtivoInativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        panelDPA1Layout.setVerticalGroup(
            panelDPA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDPA1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltroReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAtivoInativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        getContentPane().add(panelDPA1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
// TODO add your handling code here:
    String status = this.cmbAtivoInativo.getSelectedItem().toString();
    RelatorioAlunosAtivosInativos relatorioAlunosAtivosInativos = RelatorioAlunosAtivosInativos.getDialogAlunos(tela, true);
    relatorioAlunosAtivosInativos.geraRelatorio(status);
    this.dispose();
}//GEN-LAST:event_btnFiltrarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private interfacegrafica.dpa.buttons.ButtonSimplesDPA btnFiltrar;
    private interfacegrafica.dpa.combobox.ComboboxDPA cmbAtivoInativo;
    private interfacegrafica.dpa.labels.LabelDPA lblFiltroReport;
    private interfacegrafica.dpa.labels.LabelDPA lblStatus;
    private interfacegrafica.dpa.panels.PanelDPA panelDPA1;
    // End of variables declaration//GEN-END:variables

    private void addWindowListener() {

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evt) {
                System.gc();
                jDialogParametros = null;
            }

            @Override
            public void windowClosed(WindowEvent e) {
               System.gc();
               jDialogParametros = null;
            }
        });
    }

    private void inicializa() {
        this.addWindowListener();
        TelaUteis.locateOnScreen(this, 500, 310);
    }
}
