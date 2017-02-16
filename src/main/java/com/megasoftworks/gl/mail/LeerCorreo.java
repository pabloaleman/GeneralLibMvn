/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megasoftworks.gl.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author daleman
 */
public class LeerCorreo {

    /**
     * Funcion que lee correos electronicos.
     * @param host el servidor de correo
     * @param username el nombre de usuario
     * @param password las contrasena
     * @param folder la carpeta a leer
     */
    public List<Message> leeCorreo(String host, String username, String password,
            String folder) {
        List<Message> retorno = new ArrayList<Message>();
        try {
            Properties props = new Properties();
            Session sess = Session.getDefaultInstance(props, null);
            // Get the store
            Store store = sess.getStore("pop3");
            store.connect(host, username, password);
            // Get folder
            Folder fold = store.getFolder(folder);
            fold.open(Folder.READ_ONLY);
            // Get directory
            Message[] messages = fold.getMessages();
            
            retorno.addAll(Arrays.asList(messages));
            /*for (int i = 0, n = messages.length; i < n; i++) {
                System.out.println(i + ": " + messages[i].getFrom()[0] + "\t"
                        + messages[i].getSubject());
            }*/
            fold.close(false);
            store.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return retorno;

    }

}
