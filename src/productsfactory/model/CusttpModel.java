/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.model;

import java.util.List;
import productsfactory.control.CusttpControl;

/**
 *
 * @author Daniel Paulo
 */
public class CusttpModel {
    
    
    private String tipo;
    private String subTipo;
    private String descricao;
    private Offers offer;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }

    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static List<CusttpModel> getDescricaoTipoSubtipo(List<CusttpModel> tipo, List<CusttpModel> subTipo){
        
        return CusttpControl.getDescricaoTipoSubtipo(tipo, subTipo);
    }
}
