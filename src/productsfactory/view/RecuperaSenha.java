/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RecuperaSenha.java
 *
 * Created on Feb 8, 2013, 12:30:08 PM
 */
package productsfactory.view;

import productsfactory.useful.TelaUteis;

/**
 *
 * @author Daniel
 */
public class RecuperaSenha extends javax.swing.JDialog {
     
     private static RecuperaSenha jdialogRecuperaSenha = null;

    public static RecuperaSenha getDialogRecuperaSenha(){

        if (jdialogRecuperaSenha == null) {
            jdialogRecuperaSenha = new RecuperaSenha(null, true);
        }
        return jdialogRecuperaSenha;
    }
    /** Creates new form RecuperaSenha */
    public RecuperaSenha(java.awt.Frame parent, boolean modal) {
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

        pnlEsqueceuSuaSenha = new interfacegrafica.dpa.panels.PanelTituloDPA("Esqueceu sua senha?");
        lblInformacao = new interfacegrafica.dpa.labels.LabelDPA();
        lblInformacao3 = new interfacegrafica.dpa.labels.LabelDPA();
        lblInformacao1 = new interfacegrafica.dpa.labels.LabelDPA();
        lblInformacao2 = new interfacegrafica.dpa.labels.LabelDPA();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Esqueceu sua senha?");

        lblInformacao.setForeground(new java.awt.Color(255, 0, 51));
        lblInformacao.setText("Contate o administrador do sistema!");

        lblInformacao3.setForeground(new java.awt.Color(0, 51, 255));
        lblInformacao3.setText("para o email cadastrado na criação do usuário.");

        lblInformacao1.setForeground(new java.awt.Color(0, 51, 255));
        lblInformacao1.setText("Lembre-se um email com seu usuário e senha");

        lblInformacao2.setForeground(new java.awt.Color(0, 51, 255));
        lblInformacao2.setText("foi enviado ");

        javax.swing.GroupLayout pnlEsqueceuSuaSenhaLayout = new javax.swing.GroupLayout(pnlEsqueceuSuaSenha);
        pnlEsqueceuSuaSenha.setLayout(pnlEsqueceuSuaSenhaLayout);
        pnlEsqueceuSuaSenhaLayout.setHorizontalGroup(
            pnlEsqueceuSuaSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEsqueceuSuaSenhaLayout.createSequentialGroup()
                .addGroup(pnlEsqueceuSuaSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEsqueceuSuaSenhaLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEsqueceuSuaSenhaLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlEsqueceuSuaSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblInformacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInformacao3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEsqueceuSuaSenhaLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(lblInformacao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pnlEsqueceuSuaSenhaLayout.setVerticalGroup(
            pnlEsqueceuSuaSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEsqueceuSuaSenhaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblInformacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInformacao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblInformacao3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        getContentPane().add(pnlEsqueceuSuaSenha, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private interfacegrafica.dpa.labels.LabelDPA lblInformacao;
    private interfacegrafica.dpa.labels.LabelDPA lblInformacao1;
    private interfacegrafica.dpa.labels.LabelDPA lblInformacao2;
    private interfacegrafica.dpa.labels.LabelDPA lblInformacao3;
    private interfacegrafica.dpa.panels.PanelTituloDPA pnlEsqueceuSuaSenha;
    // End of variables declaration//GEN-END:variables

    private void inicializa(){
        
        TelaUteis.locateOnScreen(this, 409, 300);
    }
}
