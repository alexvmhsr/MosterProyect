/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dennys
 */
public class MessageUtil {

    public static void showMessage(String message, String detail, FacesMessage.Severity severity) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, detail));
        //RequestContext.getCurrentInstance().up
    }

}
