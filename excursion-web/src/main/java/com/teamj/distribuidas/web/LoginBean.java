/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import com.teamj.distribuidas.model.Usuario;
import com.teamj.distribuidas.servicios.UsuarioServicio;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean
public class LoginBean implements Serializable {

    private String nombre;
    private String clave;
    @EJB
    private UsuarioServicio usuarioServicio;

    private boolean logeado = false;

//    @PostConstruct
//    public void init() {
//        this.usuarioServicio = new UsuarioServicio();
//    }
    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Usuario u = new Usuario();
        u.setNombre(nombre);

        Usuario loggedUser = usuarioServicio.login(u, clave);

        if (loggedUser != null) {
            logeado = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", loggedUser);
//            try {
//            } catch (IOException ex) {
//                System.out.println("eror no " + ex);
//            }

            //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombre);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "faces/mochila";
        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error",
                    "Credenciales no válidas");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "";
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }
}
