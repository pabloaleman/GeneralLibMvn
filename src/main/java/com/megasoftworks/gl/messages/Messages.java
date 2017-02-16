/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.messages;

import java.util.HashMap;
import com.megasoftworks.gl.manage.Archivos;

/**
 *
 * @author daleman
 */
public class Messages {
    
    private String errorPath;
    private String warnPath;
    private String regex;
    
    public static HashMap<Integer, Message> ERRO = new HashMap();
    public static HashMap<Integer, Message> WARN = new HashMap();
    
    public Messages(String regex, String errorPath, String warnPath) {
        this.regex = regex;
        this.errorPath = errorPath;
        this.warnPath = warnPath;
        leerMensajes();
    }
    private void leerMensajes() {
        String contenido = Archivos.leerArchivo(errorPath);
        String[] lineas = contenido.split("\n");
        for (String linea : lineas) {
            String [] atributos = linea.split(regex);
            Message message = new Message();
            message.id = Integer.parseInt(atributos[0]);
            message.asunto = atributos[1];
            message.texto = atributos[2];
            
            ERRO.put(message.id, message);
        }
        
        contenido = Archivos.leerArchivo(warnPath);
        lineas = contenido.split("\n");
        for (String linea : lineas) {
            String [] atributos = linea.split(regex);
            Message message = new Message();
            message.id = Integer.parseInt(atributos[0]);
            message.asunto = atributos[1];
            message.texto = atributos[2];
            
            WARN.put(message.id, message);
        }
    }
}
