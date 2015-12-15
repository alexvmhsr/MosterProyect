/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Mochila;
import com.teamj.distribuidas.servicios.MochilaServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dennys
 */
@ViewScoped
@ManagedBean
public class MochilaBean {

    @EJB
    private MochilaServicio mochilaServicio;
    private List<Mochila> mochilas;
    private Mochila mochilaSeleccionada;
    private String nombre;
    private String descripcion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Mochila> getMochilas() {
        return mochilas;
    }

    public void setMochilas(List<Mochila> mochilas) {
        this.mochilas = mochilas;
    }

    public void setMochilaSeleccionada(Mochila mochilaSeleccionada) {
        this.mochilaSeleccionada = mochilaSeleccionada;
    }

    public Mochila getMochilaSeleccionada() {
        return mochilaSeleccionada;
    }

    @PostConstruct
    public void init() {
        mochilas = mochilaServicio.obtenerTodas();
    }

    public void agregarMochila() {
        if (nombre != null && !nombre.isEmpty() && descripcion != null && !descripcion.isEmpty()) {
            Mochila m = new Mochila();
            m.setNombre(nombre);
            m.setDescripcion(descripcion);
            mochilaServicio.insertar(m);
        }
    }

    public void eliminarMochila() {
        if (mochilaSeleccionada != null) {

            mochilaServicio.eliminar(mochilaSeleccionada.getId());
        }
    }
}
