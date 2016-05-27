/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.beans;

import ec.edu.espe.arqui.modelo.MonsUser;
import ec.edu.espe.arqui.servicios.UsuarioServicio;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author PabloA
 */
@ViewScoped
@ManagedBean(name="loginBean")
public class loginBean  extends CrudBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UsuarioServicio usuarioServicio;

    private SessionBean sessionBean;
    private MonsUser usuario;
    private String nombre;
    private String clave;
    private boolean logeado = false;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;

    public MonsUser getUsuario() {
        return usuario;
    }

    public void setUsuario(MonsUser usuario) {
        this.usuario = usuario;
    }
    
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

    public loginBean() {
       
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
        {
            nombre=httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
        }
    }
    
    
     public String login() {

        FacesMessage msg = null;
        if (nombre != null && !nombre.isEmpty() && clave != null && !clave.isEmpty()) {

            MonsUser u = new MonsUser();
            u.setUserNombre(nombre);

            MonsUser loggedUser = usuarioServicio.login(u, clave);
            try {
                if (loggedUser != null) {

                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", loggedUser);
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombre);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return this.sessionBean.login(loggedUser);
                   
                } else {

                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error",
                            "Credenciales no válidas");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } catch (Exception e) {
            }
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Llene todos los campos para continuar");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
        return null;
    }
    
    public void cerrarSession()
    {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();

        try {
    // Usar el contexto de JSF para invalidar la sesiÃ³n,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

    // RedirecciÃ³n de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallarÃ¡.
            // Sin embargo, como ya estÃ¡ fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
  
}
