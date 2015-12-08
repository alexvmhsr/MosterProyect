/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
/**
 *
 * @author Dennys
 */
@Entity(name = "USUARIO")
public class Usuario implements Serializable{
    @Id
    @Column(name = "ID_USUARIO")
    private Integer id;
    
    @Column(name = "NOMBRE_USUARIO")
    private String nombre;
    
    @Column(name = "CORREO_USUARIO")
    private String correo;
    
    @Column(name = "PASSWORD_USUARIO")
    private String password;
    
    @Column(name = "TELEFONO_USUARIO")
    private String telefono;
    
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    
    @Column(name = "ACERCA_DE")
    private String acercaDe;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAcercaDe() {
        return acercaDe;
    }

    public void setAcercaDe(String acercaDe) {
        this.acercaDe = acercaDe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", password=" + password + ", telefono=" + telefono + ", nombreCompleto=" + nombreCompleto + ", acercaDe=" + acercaDe + '}';
    }
    
    
    
}
