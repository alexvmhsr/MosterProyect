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
    List<Mochila> mochilas;

    public List<Mochila> getMochilas() {
        return mochilas;
    }

    public void setMochilas(List<Mochila> mochilas) {
        this.mochilas = mochilas;
    }

    @PostConstruct
    public void init() {
        mochilas = mochilaServicio.obtenerTodas();
    }

}
