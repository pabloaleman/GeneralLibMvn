/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.manage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.megasoftworks.gl.exceptions.MoverArchivosException;


/**
 *
 * @author pablo
 */
public class Archivos {

    static Logger logger = Logger.getLogger(Archivos.class);

    /**
     * escribirArchivo. Funcion que crea un archivo si existe caso contrario lo
     * sobre-escribe en un path dado
     *
     * @param path El path donde se guardará el archivo
     * @param escritura El texto a grabar en el archivo dado
     * @return ok una bandera que indica si se pudo o no grabar el archivo
     */
    public static boolean escribirArchivo(String path, String escritura) {
        boolean suc = true;
        FileWriter salida;
        try {
            salida = new FileWriter(path);
            salida.write(escritura);
            salida.close();
            logger.info("Se ha guardado el archivo: " + path);
        } catch (IOException ex) {
            logger.error("Error al escribir el archivo: " + path + "\n"
                    + ex.toString());
            logger.error(ex.getMessage());
            suc = false;
        }
        return suc;
    }

    /**
     * leerArchivo.
     * Funcion que le un archivo y retorna un String con el contenido
     * @param path el path donde se encuentra el archivo
     * @return un String con el contenido del archivo
     */
    public static String leerArchivo(String path) {
        File f = new File(path);
        StringBuilder lectura = new StringBuilder("");
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(f));
            if (f.exists()) {
                while (entrada.ready()) {
                    String linea = entrada.readLine() + "\n";
                    lectura.append(linea);
                }
            } else {
                logger.error("El archivo: " + path + " no existe\n");
            }
            entrada.close();
        } catch (IOException ex) {
            logger.error("Error al leer el archivo: " + path + "\n"
                    + ex.toString());
            return lectura.toString();
        }
        return lectura.toString();
    }
    
    /**
     * leerArchivo.
     * Funcion que le un archivo y retorna un String con el contenido
     * @param f el archivo
     * @return un String con el contenido del archivo
     */
    public static String leerArchivo(File f) {
        StringBuilder lectura = new StringBuilder("");
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(f));
            if (f.exists()) {
                while (entrada.ready()) {
                    String linea = entrada.readLine() + "\n";
                    lectura.append(linea);
                }
            } else {
                logger.error("El archivo: " + f.getAbsolutePath() + " no existe\n");
            }
            entrada.close();
        } catch (IOException ex) {
            logger.error("Error al leer el archivo: " + f.getAbsolutePath() + "\n"
                    + ex.toString());
            return lectura.toString();
        }
        return lectura.toString();
    }
    
    public static void fileMove(String source, String destination) throws MoverArchivosException {
        try {
        	FileUtils.moveFile(new File(source), new File(destination));
        } catch (IOException ex) {
        	throw new MoverArchivosException(ex.getMessage());
        }
        
    }
    
    /**
     * getFileNamesRec.
     * Funcion que retorna un List con los nombres de los archivos que existen
     * dentro del fichero de manera recursiva
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si hubo problemas o no
     */
    public static List<String> getFileNamesRec(String path) {
        List<String> vector = new ArrayList<>();
        File fichero = new File(path);
        if (fichero.exists()) {
            String[] lista = fichero.list();
            if (lista.length != 0) {
                for (String file : lista) {
                    File ficheroHijo = new File(path + File.separator + file);
                    if (ficheroHijo.isDirectory()) {
                        vector.addAll(getFileNamesRec(ficheroHijo.getAbsolutePath()));
                    }
                    if (ficheroHijo.isFile()) {
                        vector.add(ficheroHijo.getAbsolutePath());
//                        logger.info("agregado archivo: "
//                                + ficheroHijo.getAbsolutePath());
                    }
                }
            } else {
                logger.info("El fichero " + path + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }
    
     /**
     * getFileNamesNoR.
     * Funcion que retorna un List con los nombres de los archivos que existen
     * dentro del fichero
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si hubo problemas o no
     */
    public static List<String> getFileNamesNoR(String path) {
        List<String> vector = new ArrayList<>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    vector.add(lista[i].getAbsolutePath());
                }
            } else {
                logger.info("El fichero " + path + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }
    
    /**
     * getFileNamesNoRecOmiStart.
     * Funcion que retorna un List con los nombres de los archivos que existen
     * dentro del fichero omitiendo el inicio de su nombre
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si hubo problemas o no
     */
    public static List<String> getFileNamesNoRecOmiStart(String path, String start) {
        //List todos = getFileNamesNoR(path);
        List<String> vector = new ArrayList<>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].getName().startsWith(start)) {
                        vector.add(lista[i].getAbsolutePath());
                    }
                }
            } else {
                logger.error("Problemas con la carga del fichero " + path
                        + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * getFilesArbolNoRec.
     * Funcion que retorna los nombres de los archivos ordenados alfabeticamente
     * dentro de un path dato NO es recursivo
     * @param path el path a buscar archivos
     * @return el mapa con los nombres de los archivos
     */
    public static TreeMap<String, String> getFilesArbol(String path) {
        TreeMap<String, String> mapa = new TreeMap<>();
        File fichero = new File(path);
        if (fichero.exists()) {
            String[] lista;
            lista = fichero.list();
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    File ficheroHijo = new File(path + lista[i]);
                    if (ficheroHijo.isFile()) {
                        mapa.put(ficheroHijo.getName(), ficheroHijo.getAbsolutePath());
                    }
                }

            } else {
                logger.error("El fichero " + path + "esta vacio");
            }
        }

//        for (Iterator it = mapa.keySet().iterator(); it.hasNext();) {
//            String s = (String) it.next();
//            //vector.add(mapa.get(s));
//            System.out.println(s);
//            //System.out.println(mapa.get(s));
//        }
        return mapa;
    }

    /**
     * retornaNombresArchivosNoRec.
     * Funcion que retorna los nombres de los archivos ordenados alfabeticamente
     * dentro de un path dato NO es recursivo y omite la extension o un patron
     * de fin de nombre de archivo.
     * @param path el path a buscar los archivos
     * @param extensiones el patron de fin de nombre de archivo
     * @return 
     */
    public static TreeMap<String, String> retornaNombresArchivosNoRecOmiteFin(
            String path, String[] extensiones) {
        TreeMap<String, String> mapa = new TreeMap<>();
        File fichero = new File(path);
        if (fichero.exists()) {
            String[] lista;
            lista = fichero.list();
            if (!path.endsWith(File.separator)) {
                path += File.separator;
            }
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    File ficheroHijo = new File(path + lista[i]);
                    if (ficheroHijo.isFile()) {
                        for (String extension : extensiones) {
                            if (ficheroHijo.getName().endsWith(extension)) {
                                mapa.put(ficheroHijo.getName(), ficheroHijo.getAbsolutePath());
                            }
                        }
                    }
                }

            } else {
                logger.error("El fichero " + path + "esta vacio");
            }
        }

//        for (Iterator it = mapa.keySet().iterator(); it.hasNext();) {
//            String s = (String) it.next();
//            //vector.add(mapa.get(s));
//            System.out.println(s);
//            //System.out.println(mapa.get(s));
//        }
        return mapa;
    }
    
    

    

   

    

    /**
     * Funcion que comprueba que el fichero dado existe y llama a la funcion
     * buscaArchivosVector(path) para que cargue los archivos que existen dentro
     * del fichero de manera recursiva
     *
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si existe o no el archivo
     */
    public static List<String> retornaNombresArchivosVectorNoRecOmiteOrdena(String path, String start) {
        java.util.TreeMap<String, String> mapa = new TreeMap<String, String>();
        List<String> vector = new ArrayList<String>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].getName().startsWith(start)) {
                        mapa.put(lista[i].getName(), lista[i].getAbsolutePath());
                    }
                }
                logger.info("" + mapa.size());
                for (Iterator<String> it2 = mapa.keySet().iterator(); it2.hasNext();) {
                    String s = (String) it2.next();
                    vector.add(mapa.get(s));
                    //EmisionEmpresa emision = mapa.get(s)
                }
                //vector.add(lista[i].getAbsolutePath());
                //}
            } else {
                logger.error("Problemas con la carga del fichero " + path
                        + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }

    /**
     * Funcion que comprueba que el fichero dado existe y llama a la funcion
     * buscaArchivosVector(path) para que cargue los archivos que existen dentro
     * del fichero de manera recursiva
     *
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si existe o no el archivo
     */
    public static List<String> retornaNombresArchivosVectorNoRecOmiteOrdenaOmiteEnd(String path, String end) {
        java.util.TreeMap<String, String> mapa = new TreeMap<String, String>();
        List<String> vector = new ArrayList<String>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].getName().endsWith(end)) {
                        mapa.put(lista[i].getName(), lista[i].getAbsolutePath());
                    }
                }
                logger.info("" + mapa.size());
                for (Iterator<String> it2 = mapa.keySet().iterator(); it2.hasNext();) {
                    String s = (String) it2.next();
                    vector.add(mapa.get(s));
                }
            } else {
                logger.error("Problemas con la carga del fichero " + path
                        + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }

    /**
     * Funcion que comprueba que el fichero dado existe y llama a la funcion
     * buscaArchivosVector(path) para que cargue el absolute path de los
     * archivos que existen dentro del fichero de manera recursiva
     *
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si existe o no el archivo
     */
    public static List<String> retornaNombresArchivosVectorNoRecOmiteEnd(String path, String end) {
        List<String> vector = new ArrayList<String>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].getName().endsWith(end)) {
                        vector.add(lista[i].getAbsolutePath());
                    }
                }
            } else {
                logger.error("Problemas con la carga del fichero " + path
                        + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }

    /**
     * Funcion que comprueba que el fichero dado existe y llama a la funcion
     * buscaArchivosVector(path) para que cargue el nombre (sin el path
     * completo) archivos que existen dentro del fichero de manera recursiva
     *
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si existe o no el archivo
     */
    public static List<String> retornaNombresArchivosVectorNoRecOmiteEndNoAP(String path, String end) {
        List<String> vector = new ArrayList<String>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].getName().endsWith(end)) {
                        vector.add(lista[i].getName());
                    }
                }
            } else {
                logger.error("Problemas con la carga del fichero " + path
                        + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }

    /**
     * Funcion que comprueba que el fichero dado existe y llama a la funcion
     * buscaArchivosVector(path) para que cargue los archivos que existen dentro
     * del fichero de manera recursiva
     *
     * @param path el path dado para cargar los archivos
     * @return Ok una bandera que indica si existe o no el archivo
     */
    public static List<String> retornaNombresArchivosVectorNoRecOmiteStartEnd(String path, String start, String end) {
        List<String> vector = new ArrayList<String>();
        path = path.substring(0, path.length() - 1);
        File fichero = new File(path);
        if (fichero.exists()) {
            File[] lista;
            lista = fichero.listFiles();
            if (lista.length != 0) {
                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].getName().endsWith(end) && lista[i].getName().startsWith(start)) {
                        vector.add(lista[i].getAbsolutePath());
                    }
                }
            } else {
                logger.error("Problemas con la carga del fichero " + path
                        + " esta vacio.");
            }
        } else {
            logger.error("Problemas con la carga del fichero " + path
                    + " no existe.");
        }
        return vector;
    }

    

    /*public static void comprimirTar(String origen, String destino, boolean elimina) {
        Runtime aplicacion = Runtime.getRuntime();

        String comando = "tar -zcvf " + destino + " " + origen;
        try {
            aplicacion.exec(comando);
        } catch (IOException ex) {
            logger.error("Problema con el comando " + comando);
        }

        if (elimina) {
            comando = "rm " + origen;
            try {
                aplicacion.exec(comando);
            } catch (IOException ex) {
                logger.error("Problema eliminando el archivo " + origen);
            }

        }
    }

    public static void comprimirZip(String origen, String destino, boolean elimina) {
        Runtime aplicacion = Runtime.getRuntime();

        String comando = "zip " + destino + " " + origen;
        try {
            aplicacion.exec(comando);
            logger.info("Comando " + comando + " ejecutado");
        } catch (IOException ex) {
            logger.error("Problema con el comando " + comando);
        }

        if (elimina) {
            comando = "rm " + origen;
            try {
                aplicacion.exec(comando);
            } catch (IOException ex) {
                logger.error("Problema eliminando el archivo " + origen);
            }

        }
    }

    public static void copiaArchivo(String origen, String destino, boolean imprime) {
        Runtime aplicacion = Runtime.getRuntime();

        String comando = "cp " + origen + " " + destino;
        try {
            aplicacion.exec(comando);
            if (imprime) {
                logger.info("Comando " + comando + " ejecutado");
            }
        } catch (IOException ex) {
            logger.error("Problema con el comando " + comando);
        }
    }
    
    
    /**
     * buscarArchivoVector.
     * Funcion que carga en un List los nombres de todos
     * los archivos que estan dentro de un fichero dado de manera recursiva y
     * los retorna en un vector
     *
     * @param path el path donde se buscarán los archivos de manera recursiva
     */
    /*public static List buscaArchivoVector(String path) {
        List vector = new ArrayList();
        File fichero = new File(path);
        String[] lista;
        lista = fichero.list();
        if (lista.length != 0) {
            for (int i = 0; i < lista.length; i++) {
                File ficheroHijo = new File(path + "/" + lista[i]);
                if (ficheroHijo.isDirectory()) {
                    buscaArchivoVector(ficheroHijo.getAbsolutePath());
                }
                if (ficheroHijo.isFile()) {
                    vector.add(ficheroHijo.getAbsolutePath());
                    logger.info("agregado archivo: "
                            + ficheroHijo.getAbsolutePath());
                }
            }
        }
        return vector;
    }*/
}
