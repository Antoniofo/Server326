/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raposoesilvac
 */
@Entity
@Table(name = "informations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informations.findAll", query = "SELECT i FROM Informations i"),
    @NamedQuery(name = "Informations.findByIdInformations", query = "SELECT i FROM Informations i WHERE i.idInformations = :idInformations"),
    @NamedQuery(name = "Informations.findByDate", query = "SELECT i FROM Informations i WHERE i.date = :date"),
    @NamedQuery(name = "Informations.findByTemperature", query = "SELECT i FROM Informations i WHERE i.temperature = :temperature"),
    @NamedQuery(name = "Informations.findByHumidity", query = "SELECT i FROM Informations i WHERE i.humidity = :humidity")})
public class Informations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idInformations")
    private Integer idInformations;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "temperature")
    private long temperature;
    @Basic(optional = false)
    @Column(name = "humidity")
    private long humidity;
    @JoinColumn(name = "fk_user", referencedColumnName = "idUsers")
    @ManyToOne(optional = false)
    private Users fkUser;

    public Informations() {
    }

    public Informations(Integer idInformations) {
        this.idInformations = idInformations;
    }

    public Informations(Integer idInformations, Date date, long temperature, long humidity) {
        this.idInformations = idInformations;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Integer getIdInformations() {
        return idInformations;
    }

    public void setIdInformations(Integer idInformations) {
        this.idInformations = idInformations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public Users getFkUser() {
        return fkUser;
    }

    public void setFkUser(Users fkUser) {
        this.fkUser = fkUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInformations != null ? idInformations.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informations)) {
            return false;
        }
        Informations other = (Informations) object;
        if ((this.idInformations == null && other.idInformations != null) || (this.idInformations != null && !this.idInformations.equals(other.idInformations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Informations[ idInformations=" + idInformations + " ]";
    }
    
}
