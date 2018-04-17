/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.manage;

/**
 *
 * @author daleman
 */
public class Numeros {
    public static boolean isInteger(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isDouble(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
}
