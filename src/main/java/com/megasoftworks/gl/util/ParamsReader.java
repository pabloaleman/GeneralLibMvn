/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.megasoftworks.gl.manage.Archivos;

/**
 *
 * @author pablo
 */
public class ParamsReader {
    public HashMap<String, String> params = new HashMap<>();
    
    public String separadorIgual = "=";
    
    static Logger logger = Logger.getLogger(ParamsReader.class);

    public ParamsReader(String path) {
        readParams(path);
    }
    
    public ParamsReader(String path, String separadorIgual) {
        this.separadorIgual = separadorIgual;
        readParams(path);
    }
    
    private void readParams(String path) {
        String archivoParams = Archivos.leerArchivo(path);
        String[] lineas = archivoParams.split("\n");
        logger.info("tamanio " + lineas.length);
        if (lineas.length > 1) {
            for (String linea : lineas) {
                if (!linea.startsWith("#") && linea.length() > 2) {
                    String[] datos = linea.split(separadorIgual);
                    if (datos.length == 2) {
                        params.put(datos[0], datos[1]);
                    } else {
                        logger.fatal("Linea " + linea + " no cumple con el formato ATRIBUTO=VALOR");
                    }
                }
            }
        } else {
            logger.fatal("El archivo " + path + " no tiene parametros...");
        }
        
    }
    
    public int getInt(String paramName) {
        int retorno = 0;
        if (params.containsKey(paramName)) {
            retorno = Integer.parseInt(params.get(paramName));
            logger.info(paramName + " = " + retorno);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
    public String get(String paramName) {
        String retorno = "0";
        if (params.containsKey(paramName)) {
            retorno = params.get(paramName);
            logger.info(paramName + " = " + retorno);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
    public String get(String paramName, boolean visible) {
        String retorno = "0";
        if (params.containsKey(paramName)) {
            retorno = params.get(paramName);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
    public String getNoVisible(String paramName) {
        String retorno = "0";
        if (params.containsKey(paramName)) {
            retorno = params.get(paramName);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
    public Double getDouble(String paramName) {
        double retorno = 0;
        if (params.containsKey(paramName)) {
            retorno = Double.parseDouble(params.get(paramName));
            logger.info(paramName + " = " + retorno);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
    public boolean getBoolean(String paramName) {
        boolean retorno = true;
        if (params.containsKey(paramName)) {
            retorno = params.get(paramName).equals("true") ? true : false;
            logger.info(paramName + " = " + retorno);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
    public ArrayList<String> getArrayList(String paramName, String separator) {
        ArrayList<String> retorno = new ArrayList<>();
        if (params.containsKey(paramName)) {
            String[] datos = params.get(paramName).split(separator);
            retorno.addAll(Arrays.asList(datos));
            logger.info(paramName + " = " + retorno);
        } else {
            logger.fatal("Parametro " + paramName + " NO existe");
        }
        return retorno;
    }
    
//    public Connection createConnection(String servidor, String bdd, String user,
//            String pass) {
//        logger.info("Creando conexion");
//        try {
//            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
//            Connection conexion = DriverManager.getConnection ("jdbc:mysql://"
//                        + servidor  + "/"
//                        + bdd,
//                        user,
//                        pass);
//            return conexion;
//        } catch (SQLException ex) {
//            logger.error("Problema al conectar a la base de datos");
//            logger.error(ex.getMessage());
//            return null;
//        }
//    }
    
}
