/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Kelvins Insua
 */
@Embeddable
public class EstacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_linea")
    private int idLinea;
    @Basic(optional = false)
    @Column(name = "id_nombre_estacion")
    private String idNombreEstacion;

    public EstacionPK() {
    }

    public EstacionPK(int idLinea, String idNombreEstacion) {
        this.idLinea = idLinea;
        this.idNombreEstacion = idNombreEstacion;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getIdNombreEstacion() {
        return idNombreEstacion;
    }

    public void setIdNombreEstacion(String idNombreEstacion) {
        this.idNombreEstacion = idNombreEstacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLinea;
        hash += (idNombreEstacion != null ? idNombreEstacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstacionPK)) {
            return false;
        }
        EstacionPK other = (EstacionPK) object;
        if (this.idLinea != other.idLinea) {
            return false;
        }
        if ((this.idNombreEstacion == null && other.idNombreEstacion != null) || (this.idNombreEstacion != null && !this.idNombreEstacion.equals(other.idNombreEstacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EstacionPK[ idLinea=" + idLinea + ", idNombreEstacion=" + idNombreEstacion + " ]";
    }
    
}
