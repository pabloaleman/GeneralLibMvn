/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megasoftworks.gl.mail;

import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;


/**
 *
 * @author daleman
 */
public class SendMail {
    
    
    private String host;
    private String fromA;
    private String nombreDespliega;
    private String fromU;
    private String pass;
    private int portSMTP;
    public List < String > to;
    public String subject;
    public String text;
    private List < String > file;
    private boolean starttls;
    
    public String messageSendingResponse;
    static Logger logger = Logger.getLogger(SendMail.class);
    
    
    /**
     * Constructor con parametros generales.
     * @param host el servidor
     * @param fromA La direccion del remitente
     * @param nombreDespliega el nombe a desplegar en from del correo
     * @param fromU el nombre de usuario del remitente
     * @param pass el password
     * @param portSMTP el puerto smtp
     * @param file el archivo adjunto
     * @param starttls si existe seguridad starttls
     */
    public SendMail(String host, String fromA,
            String nombreDespliega, String fromU, String pass, int portSMTP,
            List < String > file, boolean starttls) {
        this.host = host;
        this.fromA = fromA;
        this.nombreDespliega = nombreDespliega;
        this.fromU = fromU;
        this.pass = pass;
        this.portSMTP = portSMTP;
        this.file = file;
        this.starttls = starttls;
    }
    
    
    /**
     * Constructor por default.
     */
    public SendMail() {
    }

    /**
     * Funcion que envia el correo.
     * @param host el servidor
     * @param fromA La direccion del remitente
     * @param nombreDespliega el nombe a desplegar en from del correo
     * @param fromU el nombre de usuario del remitente
     * @param pass el password
     * @param portSMTP el puerto smtp
     * @param to a quien
     * @param subject el asunto
     * @param text el texto
     * @param file el archivo adjunto
     * @param starttls si existe seguridad starttls
     * @return si se envio o no
     */
    public boolean enviaCorreo(String host, String fromA,
            String nombreDespliega, String fromU, String pass, int portSMTP,
            List < String > to, String subject, String text,
            List < String > file, boolean starttls) {
        boolean retorno;
        try {
            Properties props = new Properties();
            if (starttls) {
                props.setProperty("mail.smtp.starttls.enable", "true");
            }

            props.setProperty("mail.smtp.port", "" + portSMTP);
            props.setProperty("mail.smtp.user", fromU);
            props.setProperty("mail.smtp.auth", "true");
            props.put("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromA, nombreDespliega));
            for (String tosS : to) {
            message.addRecipient(Message.RecipientType.BCC,
              new InternetAddress(tosS));
            }
            message.setSubject(subject);

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(text, "ISO-8859-1", "html");
            mp.addBodyPart(mbp);

            if (!file.isEmpty()) {
                for (String files : file) {
                    MimeBodyPart mbp2 = new MimeBodyPart();
                    mbp2.setDataHandler(new DataHandler(
                            new FileDataSource(files)));
                    mbp2.setFileName(files);
                    mp.addBodyPart(mbp2);
                }
            }
            message.setContent(mp);
            Transport t = session.getTransport("smtp");
            t.connect(fromU, pass);
            t.sendMessage(message, message.getAllRecipients());
            logger.info("Message sent to: " + to);
            retorno = true;
        } catch (Exception exp) {
            messageSendingResponse = exp.getMessage();
            retorno = false;
        }
        return retorno;
    }
    
    /**
     * Funcion que envia el correo.
     * @return si se envio o no
     */
    public boolean enviaCorreo() {
        boolean retorno;
        try {
            Properties props = new Properties();
            if (starttls) {
                props.setProperty("mail.smtp.starttls.enable", "true");
            }

            props.setProperty("mail.smtp.port", "" + portSMTP);
            props.setProperty("mail.smtp.user", fromU);
            props.setProperty("mail.smtp.auth", "true");
            props.put("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromA, nombreDespliega));
            for (String tosS : to) {
            message.addRecipient(Message.RecipientType.BCC,
              new InternetAddress(tosS));
            }
            message.setSubject(subject);

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(text, "ISO-8859-1", "html");
            mp.addBodyPart(mbp);

            if (!file.isEmpty()) {
                for (String files : file) {
                    MimeBodyPart mbp2 = new MimeBodyPart();
                    mbp2.setDataHandler(new DataHandler(
                            new FileDataSource(files)));
                    mbp2.setFileName(files);
                    mp.addBodyPart(mbp2);
                }
            }
            message.setContent(mp);
            Transport t = session.getTransport("smtp");
            t.connect(fromU, pass);
            t.sendMessage(message, message.getAllRecipients());
            logger.info("Message sent to: " + to);
            retorno = true;
        } catch (Exception exp) {
            messageSendingResponse = exp.getMessage();
            retorno = false;
        }
        return retorno;
    }
    
    /**
     * Funcion que envia el correo.
     * @param to a quien
     * @param subject el asunto
     * @param text el texto
     * @return si se envio o no
     */
    public boolean enviaCorreo(List < String > to, String subject, String text) {
        boolean retorno;
        try {
            Properties props = new Properties();
            if (starttls) {
                props.setProperty("mail.smtp.starttls.enable", "true");
            }

            props.setProperty("mail.smtp.port", "" + portSMTP);
            props.setProperty("mail.smtp.user", fromU);
            props.setProperty("mail.smtp.auth", "true");
            props.put("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromA, nombreDespliega));
            for (String tosS : to) {
            message.addRecipient(Message.RecipientType.BCC,
              new InternetAddress(tosS));
            }
            message.setSubject(subject);

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(text, "ISO-8859-1", "html");
            mp.addBodyPart(mbp);

            if (!file.isEmpty()) {
                for (String files : file) {
                    MimeBodyPart mbp2 = new MimeBodyPart();
                    mbp2.setDataHandler(new DataHandler(
                            new FileDataSource(files)));
                    mbp2.setFileName(files);
                    mp.addBodyPart(mbp2);
                }
            }
            message.setContent(mp);
            Transport t = session.getTransport("smtp");
            t.connect(fromU, pass);
            t.sendMessage(message, message.getAllRecipients());
            logger.info("Message sent to: " + to);
            retorno = true;
        } catch (Exception exp) {
            messageSendingResponse = exp.getMessage();
            retorno = false;
        }
        return retorno;
    }
}
