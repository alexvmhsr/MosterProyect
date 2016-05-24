/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PabloA
 */
@Entity
@Table(name = "mons_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsUser.findAll", query = "SELECT m FROM MonsUser m"),
    @NamedQuery(name = "MonsUser.findByUserId", query = "SELECT m FROM MonsUser m WHERE m.userId = :userId"),
    @NamedQuery(name = "MonsUser.findByUserPasword", query = "SELECT m FROM MonsUser m WHERE m.userPasword = :userPasword"),
    @NamedQuery(name = "MonsUser.findByUserNombre", query = "SELECT m FROM MonsUser m WHERE m.userNombre = :userNombre"),
    @NamedQuery(name = "MonsUser.findByUserGrupo", query = "SELECT m FROM MonsUser m WHERE m.userGrupo = :userGrupo"),
    @NamedQuery(name = "MonsUser.findByUserCorreo", query = "SELECT m FROM MonsUser m WHERE m.userCorreo = :userCorreo")})
public class MonsUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_PASWORD")
    private String userPasword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_NOMBRE")
    private String userNombre;
    @Size(max = 10)
    @Column(name = "USER_GRUPO")
    private String userGrupo;
    @Size(max = 50)
    @Column(name = "USER_CORREO")
    private String userCorreo;

    public MonsUser() {
    }

    public MonsUser(Integer userId) {
        this.userId = userId;
    }

    public MonsUser(Integer userId, String userPasword, String userNombre) {
        this.userId = userId;
        this.userPasword = userPasword;
        this.userNombre = userNombre;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPasword() {
        return userPasword;
    }

    public void setUserPasword(String userPasword) {
        this.userPasword = userPasword;
    }

    public String getUserNombre() {
        return userNombre;
    }

    public void setUserNombre(String userNombre) {
        this.userNombre = userNombre;
    }

    public String getUserGrupo() {
        return userGrupo;
    }

    public void setUserGrupo(String userGrupo) {
        this.userGrupo = userGrupo;
    }

    public String getUserCorreo() {
        return userCorreo;
    }

    public void setUserCorreo(String userCorreo) {
        this.userCorreo = userCorreo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsUser)) {
            return false;
        }
        MonsUser other = (MonsUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arqui.modelo.MonsUser[ userId=" + userId + " ]";
    }
    
}
