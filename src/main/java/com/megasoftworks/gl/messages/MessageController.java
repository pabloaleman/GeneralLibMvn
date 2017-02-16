/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.messages;

import java.util.List;
import com.megasoftworks.gl.mail.SendMail;

/**
 *
 * @author daleman
 */
public class MessageController {
    public static void sendMessage(int id, String tipo, SendMail mail, List<String> to, Object ... args) {
        Message message;
        if (tipo.equals("erro")) {
            message = Messages.ERRO.get(id);
        } else {
            message = Messages.WARN.get(id);
        }
        
        String asunto = String.format(message.asunto, args);
        mail.to = to;
        mail.subject = asunto;
        mail.text = message.texto;
        
        mail.enviaCorreo();
    }
}
