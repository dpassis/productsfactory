/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.useful;

import interfacegrafica.dpa.interfaces.ComponenteInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 * Classe que estende <code>FormattedTextField</code> com o objetivo de criar um componente com 
 * características próprias.
 * Cria um <code>JFormattedTextField</code> com uma máscara para datas no padrão dd/MM/yyyy
 * @author Daniel Paulo de Assis
 * @version 1.0
 * @since 07 Julho de 2012
 * @see JFormattedTextField
 */
public class JFormattedData extends JFormattedTextField implements ComponenteInterface, FocusListener {

    /**
     * Construtor da classe
     */
    public JFormattedData() {

        /** chamada ao construtor da classe pai */
        super();

        /** chamada ao método maskDate responsável por inserir a máscara
         *  no formato dd/MM/yyyy no componente
         */
        this.maskDate();

        /** 
         * chamada ao método caracteristicas() prototipado pela
         * interface ComponenteInterface
         */
        this.caracteristicas();
    }

    @Override
    public void focusGained(FocusEvent e) {

        /** alterando a cor de fundo do componente ao receber o foco */
        this.setBackground(Color.pink);
    }

    @Override
    public void focusLost(FocusEvent e) {

        /** alterando a cor de fundo do componente ao perder o foco */
        this.setBackground(new Color(204, 204, 204));
    }

    /**
     * Implementação do método caracteristicas() da <code>interface</code>
     * <code>ComponenteInterface</code>
     */
    @Override
    public final void caracteristicas() {

        /** altendo a cor de fundo do componente */
        this.setBackground(new Color(204, 204, 204));

        /** alterando a cor da fonte do componente */
        this.setForeground(Color.black);

        /** alterando a fonte (Nome da Fonte,Tipo,Tamanho) */
        this.setFont(new Font("Arial", Font.BOLD, 12));

        /** alterando o formato da borda do componente */
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        
        /**alinhando ao centro**/
        this.setHorizontalAlignment(JTextField.CENTER);

        /** adicionando FocusListener (controle do eventos quando o componente
        recebe ou perde foco*/
        this.addFocusListener(this);
    }

    /**
     * Método responsável pela criação da máscara do componente no 
     * formato dd/MM/yyyy
     * @return um objeto do tipo <code>MaskFormatter</code> 
     */
    public final MaskFormatter maskDate() {

        /** 
         * Instanciando um objeto do tipo MaskFormatter 
         * e inicializando com null
         */
        MaskFormatter maskDate = null;

        try {

            /** adicionando o formato da máscara */
            maskDate = new MaskFormatter("##/##/####");

            /** adicionando o caracter a ser substituido no momento da digitação */
            maskDate.setPlaceholderCharacter('_');

            /** "instalando" o objeto MaskFormatter na classe */
            maskDate.install(this);
        } catch (ParseException exc) {

            exc.getMessage();
        }

        /** retorno do método */
        return maskDate;
    }

    /**
     * Implementação não necessária para a 
     * classe <code>JFormattedData</code>
     * @return 
     */
    @Override
    public ImageIcon adicionaIcone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
