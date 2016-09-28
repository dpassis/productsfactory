/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.model;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class AcoesUsuarios {
    
    private Integer codigoAcao;
    private String descricaoAcao;
    private Date dataAcao;
    private String horaAcao;
    private String dataCompleta;
    private Usuario usuarioAcao;

    public Integer getCodigoAcao() {
        return codigoAcao;
    }

    public void setCodigoAcao(Integer codigoAcao) {
        this.codigoAcao = codigoAcao;
    }

    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }

    public String getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(String dataCompleta) {
        this.dataCompleta = dataCompleta;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }

    public String getHoraAcao() {
        return horaAcao;
    }

    public void setHoraAcao(String horaAcao) {
        this.horaAcao = horaAcao;
    }

    public Usuario getUsuarioAcao() {
        return usuarioAcao;
    }

    public void setUsuarioAcao(Usuario usuarioAcao) {
        this.usuarioAcao = usuarioAcao;
    }
    
    
    

    
}
