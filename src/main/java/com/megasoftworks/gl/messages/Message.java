/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.messages;

/**
 *
 * @author daleman
 */
public class Message {
    int id;
    String asunto;
    String texto;
    
    @Override
    public String toString() {
        return "Asunto: " + asunto + "\nTexto: " + texto + "\n";
    }
}
