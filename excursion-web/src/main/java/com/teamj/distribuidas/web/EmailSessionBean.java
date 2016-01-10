/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.web;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Dennys
 */
@SessionScoped
@ManagedBean
public class EmailSessionBean implements Serializable{

    public void sendEmail(String to, String subject, String body, String path) {
        //Email email = new SimpleEmail();
        MultiPartEmail email = new MultiPartEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);

        email.setAuthenticator(new DefaultAuthenticator("dennysaurio", "tuclave"));
        // email.setSSLOnConnect(true);
        email.setDebug(true);
//email.setStartTLSEnabled(true);

        try {
            email.getMailSession().getProperties().put("mail.smtps.auth", "true");
            email.getMailSession().getProperties().put("mail.debug", "true");
            email.getMailSession().getProperties().put("mail.smtps.port", "587");
            email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
            email.getMailSession().getProperties().put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
            email.getMailSession().getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
            EmailAttachment attachment = new EmailAttachment();
//            #{facesContext.externalContext.requestContextPath}
            attachment.setPath("D:\\IMG_20151101_213514644.jpg");
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Picture of Anita");
            attachment.setName("anita.jpg");
            //email.setTLS(true);

            /**
             * smtp.host=smtp.gmail.com smtp.port=587 smtp.ssl=yes
             * props.put("mail.smtp.starttls.enable", "true");
             * props.put("mail.smtp.auth", "true"); smtp.user="me@gmail.com"
             * smtp.password="myPassword"
             */
            email.setFrom("dennysaurio@gmail.com");

            email.setSubject(subject);

            email.setMsg(body);

            email.addTo(to);
            email.attach(attachment);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
