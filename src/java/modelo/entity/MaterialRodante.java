/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kelvins Insua
 */
@Entity
@Table(name = "material_rodante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialRodante.findAll", query = "SELECT m FROM MaterialRodante m"),
    @NamedQuery(name = "MaterialRodante.findByIdMaterialRodante", query = "SELECT m FROM MaterialRodante m WHERE m.idMaterialRodante = :idMaterialRodante"),
    @NamedQuery(name = "MaterialRodante.findByNombreMaterialRodante", query = "SELECT m FROM MaterialRodante m WHERE m.nombreMaterialRodante = :nombreMaterialRodante"),
    @NamedQuery(name = "MaterialRodante.findByTipo", query = "SELECT m FROM MaterialRodante m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MaterialRodante.findBySubTipo", query = "SELECT m FROM MaterialRodante m WHERE m.subTipo = :subTipo"),
    @NamedQuery(name = "MaterialRodante.findByNumeroVagones", query = "SELECT m FROM MaterialRodante m WHERE m.numeroVagones = :numeroVagones"),
    @NamedQuery(name = "MaterialRodante.findByCapacidadPasajeros", query = "SELECT m FROM MaterialRodante m WHERE m.capacidadPasajeros = :capacidadPasajeros"),
    @NamedQuery(name = "MaterialRodante.findByKilometraje", query = "SELECT m FROM MaterialRodante m WHERE m.kilometraje = :kilometraje"),
    @NamedQuery(name = "MaterialRodante.findByMasa", query = "SELECT m FROM MaterialRodante m WHERE m.masa = :masa"),
    @NamedQuery(name = "MaterialRodante.findByLargo", query = "SELECT m FROM MaterialRodante m WHERE m.largo = :largo"),
    @NamedQuery(name = "MaterialRodante.findByAltoCara", query = "SELECT m FROM MaterialRodante m WHERE m.altoCara = :altoCara"),
    @NamedQuery(name = "MaterialRodante.findByAnchoCara", query = "SELECT m FROM MaterialRodante m WHERE m.anchoCara = :anchoCara"),
    @NamedQuery(name = "MaterialRodante.findByAceleracionMax", query = "SELECT m FROM MaterialRodante m WHERE m.aceleracionMax = :aceleracionMax"),
    @NamedQuery(name = "MaterialRodante.findByDesaceleracionMax", query = "SELECT m FROM MaterialRodante m WHERE m.desaceleracionMax = :desaceleracionMax"),
    @NamedQuery(name = "MaterialRodante.findByVelocidadDise\u00f1o", query = "SELECT m FROM MaterialRodante m WHERE m.velocidadDise\u00f1o = :velocidadDise\u00f1o"),
    @NamedQuery(name = "MaterialRodante.findByVelocidadOperativa", query = "SELECT m FROM MaterialRodante m WHERE m.velocidadOperativa = :velocidadOperativa")})
public class MaterialRodante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_material_rodante")
    private Integer idMaterialRodante;
    @Basic(optional = false)
    @Column(name = "nombre_material_rodante")
    private String nombreMaterialRodante;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "sub_tipo")
    private String subTipo;
    @Basic(optional = false)
    @Column(name = "numero_vagones")
    private int numeroVagones;
    @Basic(optional = false)
    @Column(name = "capacidad_pasajeros")
    private double capacidadPasajeros;
    @Basic(optional = false)
    @Column(name = "kilometraje")
    private double kilometraje;
    @Basic(optional = false)
    @Column(name = "masa")
    private double masa;
    @Basic(optional = false)
    @Column(name = "largo")
    private double largo;
    @Basic(optional = false)
    @Column(name = "alto_cara")
    private double altoCara;
    @Basic(optional = false)
    @Column(name = "ancho_cara")
    private double anchoCara;
    @Basic(optional = false)
    @Column(name = "aceleracion_max")
    private double aceleracionMax;
    @Basic(optional = false)
    @Column(name = "desaceleracion_max")
    private double desaceleracionMax;
    @Basic(optional = false)
    @Column(name = "velocidad_dise\u00f1o")
    private double velocidadDiseño;
    @Basic(optional = false)
    @Column(name = "velocidad_operativa")
    private double velocidadOperativa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialRodante")
    private List<CurvaEsfuerzo> curvaEsfuerzoList;

    public MaterialRodante() {
    }

    public MaterialRodante(Integer idMaterialRodante) {
        this.idMaterialRodante = idMaterialRodante;
    }

    public MaterialRodante(Integer idMaterialRodante, String nombreMaterialRodante, String tipo, String subTipo, int numeroVagones, double capacidadPasajeros, double kilometraje, double masa, double largo, double altoCara, double anchoCara, double aceleracionMax, double desaceleracionMax, double velocidadDiseño, double velocidadOperativa) {
        this.idMaterialRodante = idMaterialRodante;
        this.nombreMaterialRodante = nombreMaterialRodante;
        this.tipo = tipo;
        this.subTipo = subTipo;
        this.numeroVagones = numeroVagones;
        this.capacidadPasajeros = capacidadPasajeros;
        this.kilometraje = kilometraje;
        this.masa = masa;
        this.largo = largo;
        this.altoCara = altoCara;
        this.anchoCara = anchoCara;
        this.aceleracionMax = aceleracionMax;
        this.desaceleracionMax = desaceleracionMax;
        this.velocidadDiseño = velocidadDiseño;
        this.velocidadOperativa = velocidadOperativa;
    }

    public Integer getIdMaterialRodante() {
        return idMaterialRodante;
    }

    public void setIdMaterialRodante(Integer idMaterialRodante) {
        this.idMaterialRodante = idMaterialRodante;
    }

    public String getNombreMaterialRodante() {
        return nombreMaterialRodante;
    }

    public void setNombreMaterialRodante(String nombreMaterialRodante) {
        this.nombreMaterialRodante = nombreMaterialRodante;
    }

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

    public int getNumeroVagones() {
        return numeroVagones;
    }

    public void setNumeroVagones(int numeroVagones) {
        this.numeroVagones = numeroVagones;
    }

    public double getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(double capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAltoCara() {
        return altoCara;
    }

    public void setAltoCara(double altoCara) {
        this.altoCara = altoCara;
    }

    public double getAnchoCara() {
        return anchoCara;
    }

    public void setAnchoCara(double anchoCara) {
        this.anchoCara = anchoCara;
    }

    public double getAceleracionMax() {
        return aceleracionMax;
    }

    public void setAceleracionMax(double aceleracionMax) {
        this.aceleracionMax = aceleracionMax;
    }

    public double getDesaceleracionMax() {
        return desaceleracionMax;
    }

    public void setDesaceleracionMax(double desaceleracionMax) {
        this.desaceleracionMax = desaceleracionMax;
    }

    public double getVelocidadDiseño() {
        return velocidadDiseño;
    }

    public void setVelocidadDiseño(double velocidadDiseño) {
        this.velocidadDiseño = velocidadDiseño;
    }

    public double getVelocidadOperativa() {
        return velocidadOperativa;
    }

    public void setVelocidadOperativa(double velocidadOperativa) {
        this.velocidadOperativa = velocidadOperativa;
    }

    @XmlTransient
    public List<CurvaEsfuerzo> getCurvaEsfuerzoList() {
        return curvaEsfuerzoList;
    }

    public void setCurvaEsfuerzoList(List<CurvaEsfuerzo> curvaEsfuerzoList) {
        this.curvaEsfuerzoList = curvaEsfuerzoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterialRodante != null ? idMaterialRodante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialRodante)) {
            return false;
        }
        MaterialRodante other = (MaterialRodante) object;
        if ((this.idMaterialRodante == null && other.idMaterialRodante != null) || (this.idMaterialRodante != null && !this.idMaterialRodante.equals(other.idMaterialRodante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MaterialRodante[ idMaterialRodante=" + idMaterialRodante + " ]";
    }
    
}
