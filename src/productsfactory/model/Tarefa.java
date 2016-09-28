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
public class Tarefa {
    
    private Integer codigoTarefa;
    private String descricaoTarefa;
    private Date dataTarefa;
    private String horaTarefa;
    private String statusTarefa;
    private Usuario usuario;
    private String donoTarefa;

    public Integer getCodigoTarefa() {
        return codigoTarefa;
    }

    public void setCodigoTarefa(Integer codigoTarefa) {
        this.codigoTarefa = codigoTarefa;
    }

    public Date getDataTarefa() {
        return dataTarefa;
    }

    public void setDataTarefa(Date dataTarefa) {
        this.dataTarefa = dataTarefa;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public String getHoraTarefa() {
        return horaTarefa;
    }

    public void setHoraTarefa(String horaTarefa) {
        this.horaTarefa = horaTarefa;
    }

    public String getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(String statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDonoTarefa() {
        return donoTarefa;
    }

    public void setDonoTarefa(String donoTarefa) {
        this.donoTarefa = donoTarefa;
    }
    
    
}
