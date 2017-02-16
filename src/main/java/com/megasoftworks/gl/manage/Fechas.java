/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasoftworks.gl.manage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import com.megasoftworks.gl.constantes.Valores;
import com.megasoftworks.gl.constantes.Enteros;

/**
 *
 * @author pablo
 */
public class Fechas {
    
    static Logger logger = Logger.getLogger(Fechas.class);
    
    public static String locale = "en";
    
    /**
     * El formato por default.
     */
    public static String formatoNormal = "yyyy MM dd HH:mm:ss";
    public static String formatoMysql = "yyyy/MM/dd HH:mm:ss";
    public static String formatoMysqlOut = "yyyy-MM-dd HH:mm:ss";
    public static String formatoPrime = "yyyy-MM-dd HH:mm:ss";
    public static String formatoOutPrimeMinutos = "%y/%m/%d %H:%M";
    public static String formatoOutPrimeHoras = "%y/%m/%d %H:%M";
    public static String formatoOutPrimeDia = "%y/%m/%d";
    
    /**
     * stringToDate.
     * Funcion que recibe un string que representa una fecha
     * y un formato de fecha para ser convertida en tipo de dato Date
     * @param s String que representa una fecha
     * @param  formato el formato del string a convertir a fecha
     * @return la fecha en tipo de dato Date
     */
    public static Date stringToDate(String s, String formato) {
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        Date myDate = null;
        try {
            myDate = myDateFormat.parse(s);
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + s);
        }
        return myDate;
    }

    /**
     * dateToString.
     * Funcion que recibe un Date y un formato y retorna un string en el
     * formato dado
     * @param date la fecha en formato Date a ser convertida
     * @param  formato el formato a retornar en el string
     * @return String de la fecha en el formato dado
     */
    public static String dateToString(Date date, String formato) {
        SimpleDateFormat dateformat = new SimpleDateFormat(formato, new Locale(locale));
        StringBuilder fecha = new StringBuilder(dateformat.format(date));
        return fecha.toString();
    }

    /**
     * cambioFormato.
     * Funcion que recibe un string que representa una fecha,
     * un formato de la fecha del primer parametro
     * y un formato a convertir.
     * @param fecha la fecha a convertir
     * @param formatoOriginal el formato de la fecha de input
     * @param formatoConvertir el formato de la fecha output.
     * @return la fecha en el formato de output
     */
    public static String cambioFormato(String fecha, String formatoOriginal,
            String formatoConvertir) {
        Date date = Fechas.stringToDate(fecha, formatoOriginal);
        return Fechas.dateToString(date, formatoConvertir);
    }
    
    /**
     * aumentoUnMes.
     * Funcion que recive un Date y un numero de meses y retorna un Date
     * incrementado el numero de meses
     * @param actual La fecha actual
     * @return El date aumentado los meses
     */
    public static Date aumentoUnMes(Date actual) {
        int anio = getAnio(actual);
        int mes = getMes(actual);
        if (mes == Valores.DICIEMBRE) {
            anio++;
            mes = Valores.ENERO;
        } else {
            mes++;
        }
        return stringToDate(anio + "/" + mes + "/01", "yyyy/MM/dd");
    }

    /**
     * aumentoMeses.
     * Funcion que recive un Date y un numero de meses y retorna un Date
     * incrementado el numero de meses
     * @param actual La fecha actual
     * @param meses  El numero de meses a aumentar.
     * @return El date aumentado los meses
     */
    public static Date aumentoMeses(Date actual, int meses) {
        for (int n = 0; n < meses; n++) {
            actual = aumentoUnMes(actual);
        }
        return actual;
    }
    
    /**
     * aumentoDias.
     * Funcion que recive un Date y un numero de dias y retorna un Date
     * incrementado el numero de dias
     * @param actual La fecha actual
     * @param dias el numero de dias a aumentar
     * @return El date aumentado los dias
     */
    public static Date aumentoDias(Date actual, int dias) {
       Date aux = aumentoMinutos(actual, dias * 1440);
       return aux;
    }

    /**
     * aumentoHoras.
     * Funcion que recive un Date y un numero de horas y retorna un Date
     * incrementado el numero de minutos.
     * @param actual La fecha actual
     * @param horas el numero de horas a aumentar
     * @return El date aumentado las horas
     */
    public static Date aumentoHoras(Date actual, int horas) {
       Date aux = aumentoMinutos(actual, horas * 60);
       return aux;
    }
    
    /**
     * aumentoMinutos.
     * Funcion que recive un Date y un numero de minutos y retorna un Date
     * incrementado el numero de minutos.
     * @param actual La fecha actual
     * @param minutos el numero de minutos a aumentar
     * @return El date aumentado los minutos
     */
    public static Date aumentoMinutos(Date actual, int minutos) {
       Date aux = new Date();
       aux.setTime(actual.getTime() + (60000 * minutos));
       return aux;
    }

    /**
     * getAnio.
     * Funcion que recibe una fecha y un string de formato y retorna el año
     * @param fech la fecha en String a ser cambiada
     * @param formato formato de la fecha que se pasa como argumento
     * @return el año de la fecha dada
     */
     public static int getAnio(String fech, String formato) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        DateFormat myDateFormat2 = new SimpleDateFormat("yyyy");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }
     
     /**
      * getAnio.
      * Funcion que retorna el año de una fecha dada
      * @param fecha (date)
      * @return el anio
      */
    public static int getAnio(Date fecha) {
        String fechaS = dateToString(fecha, formatoNormal);
        return getAnio(fechaS, formatoNormal);
    }

     /**
      * getMes.
     * Funcion que recibe una fecha y un string de formato y retorna el mes
     * @param fech la fecha en String a ser cambiada
     * @param formato formato de la fecha que se pasa como argumento
     * @return el mes de la fecha dada
     */
     public static int getMes(String fech, String formato) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        DateFormat myDateFormat2 = new SimpleDateFormat("MM");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }
     
     /**
     * getMes.
      * Funcion que retorna el mes de una fecha dada
      * @param fecha (date)
      * @return el mes
      */
    public static int getMes(Date fecha) {
        String fechaS = dateToString(fecha, formatoNormal);
        return getMes(fechaS, formatoNormal);
    }
    
    /**
     * getMes00.
     * Funcion que retorna el mes de una fecha dada pero en formato ##
     * @param fecha la fecha a obtener el mes
     * @return el mes en formato ##
     */
    public static String getMes00(Date fecha) {
        int mes = getMes(fecha);
        return (mes <= 9) ? "0" + mes : ""  + mes;
    }

    /**
     * getDia.
     * Funcion que recibe una fecha y un string de formato y retorna el dia
     * @param fech la fecha en String a ser cambiada
     * @param formato formato de la fecha que se pasa como argumento
     * @return el dia de la fecha dada
     */
    public static int getDia(String fech, String formato) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        DateFormat myDateFormat2 = new SimpleDateFormat("dd");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }
    
    /**
     * getDia.
      * Funcion que retorna el dia de una fecha dada
      * @param fecha (date)
      * @return el dia
      */
    public static int getDia(Date fecha) {
        String fechaS = dateToString(fecha, formatoNormal);
        return getDia(fechaS, formatoNormal);
    }
    
    /**
     * getDia00.
     * Funcion que retorna el dia de una fecha dada pero en formato ##
     * @param fecha la fecha a obtener el dia
     * @return el dia en formato ##
     */
    public static String getDia00(Date fecha) {
        int mes = getDia(fecha);
        return (mes <= 9) ? "0" + mes : ""  + mes;
    }

    /**
     * getHora.
     * Funcion que recibe una fecha y un string de formato y retorna la hora
     * @param fech la fecha en String a ser cambiada
     * @param formato formato de la fecha que se pasa como argumento
     * @return la hora de la fecha dada
     */
    public static int getHora(String fech, String formato) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        DateFormat myDateFormat2 = new SimpleDateFormat("HH");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }
    
    /**
      * getHora.
      * Funcion que retorna la hora de una fecha dada
      * @param fecha (date)
      * @return la hora
      */
    public static int getHora(Date fecha) {
        String fechaS = dateToString(fecha, formatoNormal);
        return getHora(fechaS, formatoNormal);
    }

    /**
     * getMinuto.
     * Funcion que recibe una fecha y un string de formato y retorna el minuto
     * @param fech la fecha en String a ser cambiada
     * @param formato formato de la fecha que se pasa como argumento
     * @return el minuto de la fecha dada
     */
    public static int getMinuto(String fech, String formato) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        DateFormat myDateFormat2 = new SimpleDateFormat("mm");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }
    
     /**
      * getMinuto.
      * Funcion que retorna los minutos de una fecha dada
      * @param fecha (date)
      * @return el dia
      */
    public static int getMinuto(Date fecha) {
        String fechaS = dateToString(fecha, formatoNormal);
        return getMinuto(fechaS, formatoNormal);
    }

    /**
     * getSegundos.
     * Funcion que recibe una fecha y un string de formato y retorna
     * los segundos
     * @param fech la fecha en String a ser cambiada
     * @param formato formato de la fecha que se pasa como argumento
     * @return los segundos de la fecha dada
     */
    public static int getSegundos(String fech, String formato) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        DateFormat myDateFormat2 = new SimpleDateFormat("ss");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }

    /**
     * getSegundos.
      * Funcion que retorna los segundos de una fecha dada
      * @param fecha (date)
      * @return el dia
      */
    public static int getSegundos(Date fecha) {
        String fechaS = dateToString(fecha, formatoNormal);
        return getSegundos(fechaS, formatoNormal);
    }

    /**
     * getMesLetras.
     * Funcion que retorna el mes en letras de la fecha dada
     * @param fecha la fecha a obtener el mes
     * @return el mes en letras
     */
    public static String getMesLetras(Date fecha) {
        String retorno;

        int mes = getMes(fecha);
        switch(mes) {
            case Valores.ENERO:
                retorno = "enero";
                break;
            case Valores.FEBRERO:
                retorno = "febrero";
                break;
            case Valores.MARZO:
                retorno = "marzo";
                break;
            case Valores.ABRIL:
                retorno = "abril";
                break;
            case Valores.MAYO:
                retorno = "mayo";
                break;
            case Valores.JUNIO:
                retorno = "junio";
                break;
            case Valores.JULIO:
                retorno = "julio";
                break;
            case Valores.AGOSTO:
                retorno = "agosto";
                break;
            case Valores.SEPTIEMBRE:
                retorno = "septiembre";
                break;
            case Valores.OCTUBRE:
                retorno = "octubre";
                break;
            case Valores.NOVIEMBRE:
                retorno = "noviembre";
                break;
            case Valores.DICIEMBRE:
                retorno = "diciembre";
                break;
            default:
                retorno = "";
                break;
        }
        return retorno;
    }

    /**
     * getDiaespanol.
     * Funcion que retorna el nombre del dia de la fecha dada
     * @param date la fecha a obtener el dia
     * @return el dia en letras
     */
    public static String getDiaEspanol(Date date) {
        String r = dateToString(date, "EEE");
        r = r.toUpperCase();
        if (r.startsWith("MON")) {
            r = "lunes";
        }
        if (r.startsWith("TUE")) {
            r = "martes";
        }
        if (r.startsWith("WED")) {
            r = "miércoles";
        }
        if (r.startsWith("THU")) {
            r = "jueves";
        }
        if (r.startsWith("FRI")) {
            r = "viernes";
        }
        if (r.startsWith("SAT")) {
            r = "sabado";
        }
        if (r.startsWith("SUN")) {
            r = "domingo";
        }
        return r;
    }
    
    /**
     * getDiaEspanolWeb.
     * Funcion que retorna el nombre del dia de la fecha dada
     * en formato para web
     * @param date la fecha a obtener el dia
     * @return el dia en letras
     */
    public static String getDiaEspanolWeb(Date date) {
        String r = dateToString(date, "EEE");
        r = r.toUpperCase();
        if (r.startsWith("WED")) {
            r = "mi&eacute;rcoles";
        } else {
            r = getDiaEspanol(date);
        }
        return r;
    }


    /**
     * fechaFormato.
     * FUncion que recibe una fecha y un formato de retorno
     * @param date la fecha a cambiar
     * @param formatoRetorno formato de retorno de la fecha
     * @param separador el separador del formato.
     * @return la fecha en el formato
     */
    public static String fechaFormato(Date date, String formatoRetorno,
            String separador) {
        String[] formatos = formatoRetorno.split(separador);
        String retorno = "";
        for (int i = 0; i < formatos.length; i++) {
            if (formatos[i].equals("anio")) {
                retorno = retorno + getAnio(date);
            } else {
                if (formatos[i].equals("mes")) {
                    retorno = retorno + getMes(date);
                } else {
                    if (formatos[i].equals("dia")) {
                        retorno = retorno + getDia(date);
                    } else {
                        if (formatos[i].equals("hora")) {
                            retorno = retorno + getHora(date);
                        } else {
                            if (formatos[i].equals("minuto")) {
                                retorno = retorno + getMinuto(date);
                            } else {
                                if (formatos[i].equals("segundos")) {
                                    retorno = retorno + getSegundos(date);
                                } else {
                                    if (formatos[i].equals("diaLW")) {
                                        retorno = retorno
                                                + getDiaEspanolWeb(date);
                                    } else {
                                        if (formatos[i].equals("mesL")) {
                                            retorno = retorno
                                                    + getMesLetras(date);
                                        } else {
                                            if (formatos[i].equals("diaLN")) {
                                                retorno = retorno
                                                        + getDiaEspanol(date);
                                            } else {
                                                retorno = retorno + formatos[i];
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return retorno;
    }

    /**
     * getDaysOfMonths.
     * Funcion que retorna el numero de dias que tiene un mes dado
     * @param year el año del mes
     * @param month el mes
     * @return el numero de dias que tiene el mes
     */
    public static int getDaysOfMonths(int year, int month) {
        int retorno = 0;
        try {
            if (year == 0) { retorno = 0; }
            if (month == 0) { retorno = 0; }

            if (month == Valores.ENERO || month == Valores.MARZO
                    || month == Valores.MAYO || month == Valores.JULIO
                    || month == Valores.AGOSTO || month == Valores.OCTUBRE
                    || month == Valores.DICIEMBRE) {
                retorno = Enteros.TREINTA_Y_UNO;
            }

            if (month == Valores.ABRIL || month == Valores.JUNIO
                    || month == Valores.SEPTIEMBRE
                    || month == Valores.NOVIEMBRE) {
                retorno = Enteros.TREINTA;
            }

            if (month == Valores.FEBRERO
                    && (year % Enteros.CUATRO == 0
                    && year % Enteros.CIEN != 0)) {
                retorno = Enteros.VEINTE_Y_NUEVE;
            }
            if (month == Valores.FEBRERO
                    && !(year % Enteros.CUATRO == 0
                    && year % Enteros.CIEN != 0)) {
                retorno = Enteros.VEINTE_Y_OCHO;
            }
        } catch (Exception e) {
            retorno = 0;
        }
        return retorno;
    }

    /**
     * getDaysOfMonths.
     * Funcion que retorna el numero de dias que tiene un mes de una fecha
     * @param date la fecha de la cual se quiere saber el mes
     * @return el numero de dias que tiene el mes.
     */
    public static int getDaysOfMonths(Date date) {
        return getDaysOfMonths(getAnio(date), getMes(date));
    }
    
    /**
     * getDaysOfYear.
     * Funcion que retorna el numero de dias qe tiene el anio
     * @param year el anio a evaluar
     * @return los dias que tiene el anio
     */
    public static int getDaysOfYear(int year) {
        int retorno;
        try {
            if (year == 0) {
                retorno = 0;
            } else {
                if (year % Enteros.CUATRO == 0 && year % Enteros.CIEN != 0) {
                    retorno = Enteros.TRESCIENTOS_SESENTA_Y_SEIS;
                } else {
                    retorno = Enteros.TRESCIENTOS_SESENTA_Y_CINCO;
                }
            }
        } catch (Exception e) {
            retorno = 0;
        }
        return retorno;
    }

    /**
     * getDaysOfYear.
     * Funcion que retorna el numero de dias que tiene el anio.
     * @param date la fecha del anio
     * @return  los dias q tiene el anio
     */
    public static int getDaysOfYear(Date date) {
        return getDaysOfYear(getAnio(date));
    }

    /**
     * isFirstDay.
     * Funcion que indica si es el primer dia del mes.
     * @param date la fecha a comprobar
     * @return si es o no el primer dia.
     */
    public static boolean isFirstDay(Date date) {
        return (getDia(date) == 1) ? true : false;
    }

    /**
     * Funcion que comprueba si un String ingresado es una fecha valida.
     * @param fecha el string con la fecha
     * @param formato el formato de la fecha
     * @return si es valida o no
     */
    public static boolean isDate(String fecha, String formato) {
        boolean ok = true;
        DateFormat myDateFormat = new SimpleDateFormat(formato, new Locale(locale));
        Date myDate = null;
        try {
            myDate = myDateFormat.parse(fecha);
        } catch (ParseException e) {
             ok = false;
        }
        return ok;
    }
    
    public static Date fechaCero() {
        Date fecha = new Date();
        String fechaS = dateToString(fecha, formatoNormal);
        fechaS = fechaS.substring(0, fechaS.length() - 8) + "00:00:00";
        return stringToDate(fechaS, formatoNormal);
    }
    
    public static Date fechaLast() {
        Date fecha = new Date();
        String fechaS = dateToString(fecha, formatoNormal);
        fechaS = fechaS.substring(0, fechaS.length() - 8) + "23:59:00";
        return stringToDate(fechaS, formatoNormal);
    }
    
    /**
     * stringToDate.
     * Funcion que recibe un string que reresenta una fecha en formato 
     * "yyyy MM dd HH:mm:ss" para convertirlo en tipo de dato Date
     * @param s Cadena a ser convertida "yyyy MM dd HH:mm:ss"
     * @return la fecha en tipo de dato Date
     */
    /*public static Date stringToDate(String s) {
        DateFormat myDateFormat = new SimpleDateFormat(formatoNormal);
        Date myDate = null;
        try {
            myDate = myDateFormat.parse(s);
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + s);
        }
        return myDate;
    }*/
    
        /**
     * dateToString.
     * Funcion que recibe un Date y lo retorna un string en formato
     * "yyyy MM dd HH:mm:ss"
     * @param date la fecha Date a ser convertida
     * @return String de la fecha en formato "yyyy MM dd HH:mm:ss"
     */
   /* public static String dateToString(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat(formatoNormal);
        StringBuilder fecha = new StringBuilder(dateformat.format(date));
        return fecha.toString();
    }*/
    
    
        /**
     * dateToStringSoloFecha.
     * Funcion que recibe un Date y lo retorna unString en formato "yyyy/MM/dd"
     * @param date la fecha en formato Date a ser convertida
     * @return String de la fecha en formato "yyyy/MM/dd"
     */
    /*public static String dateToStringSoloFecha(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
        StringBuilder fecha = new StringBuilder(dateformat.format(date));
        return fecha.toString();
    }*/
    
        /**
     * dateToStringSoloFecha.
     * Funcion que retorna en formato "yyyy/MM/dd" un String con la fecha actual
     * @return String de la fecha en formato "yyyy/MM/dd"
     */
    /*public static String dateToStringSoloFecha() {
        Date date = new Date();
         SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
        StringBuilder fecha = new StringBuilder(dateformat.format(date));
        return fecha.toString();
    }*/

    /**
     * dateToStringHoras.
     * Funcion que recibe un Date y lo retorna en formato "HH:mm" un String
     * @param date la fecha en formato Date a ser convertida
     * @return String de la fecha en formato "HH:mm"
     */
    /*public static String dateToStringHoras(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm");
        StringBuilder fecha = new StringBuilder(dateformat.format(date));
        return fecha.toString();
    }*/

    /**
     * getAnio.
     * Funcion que recibe una fecha en formato "yyyy MM dd HH:mm:ss"
     * y retorna el año en entero
     * @param fech (en formato "yyyy MM dd HH:mm:ss"  )
     * @return el año
     */
    /*public static int getAnio(String fech) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formatoNormal);
        DateFormat myDateFormat2 = new SimpleDateFormat("yyyy");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }*/
    
    /**
      * getMes.
     * Funcion que recibe una fecha en formato "yyyy MM dd HH:mm:ss"
     * y retorna el mes en entero
     * @param fech (en formato "yyyy MM dd HH:mm:ss"  )
     * @return el mes
     */
     /*public static int getMes(String fech) {
        Date fe = new Date();
        fe = new Date();
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formatoNormal);
        DateFormat myDateFormat2 = new SimpleDateFormat("MM");
        try {
            fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             log("Problema al cambiar el formato de la fecha: " + fech + "\n"
                     + e.toString(), L.FATAL_ERROR);
        }
        return anio;
    }*/
    /**
      * getDia.
     * Funcion que recibe una fecha en formato "yyyy MM dd HH:mm:ss"
     * y retorna el dia en entero
     * @param fech (en formato "yyyy MM dd HH:mm:ss"  )
     * @return el dia
     */
    /*public static int getDia(String fech) {
        Date fe = new Date();
        fe = new Date();
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formatoNormal);
        DateFormat myDateFormat2 = new SimpleDateFormat("dd");
        try {
            fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             log("Problema al cambiar el formato de la fecha: " + fech + "\n"
                     + e.toString(), L.FATAL_ERROR);
        }
        return anio;
    }*/
    
    
    /**
     * getHora.
     * Funcion que recibe una fecha en formato "yyyy MM dd HH:mm:ss"
     * y retorna la hora en entero
     * @param fech (en formato "yyyy MM dd HH:mm:ss")
     * @return la hora
     */
    /*public static int getHora(String fech) {
        Date fe = new Date();
        fe = new Date();
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formatoNormal);
        DateFormat myDateFormat2 = new SimpleDateFormat("HH");
        try {
            fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             log("Problema al cambiar el formato de la fecha: " + fech + "\n"
                     + e.toString(), L.FATAL_ERROR);
        }
        return anio;
    }*/
    
        /**
     * getMinuto.
     * Funcion que recibe una fecha en formato "yyyy MM dd HH:mm:ss"
     * y retorna el minuto en entero
     * @param fech (en formato "yyyy MM dd HH:mm:ss")
     * @return el minuto
     */
   /* public static int getMinuto(String fech) {
        int anio = 0;
        DateFormat myDateFormat = new SimpleDateFormat(formatoNormal);
        DateFormat myDateFormat2 = new SimpleDateFormat("mm");
        try {
            Date fe = myDateFormat.parse(fech);
            StringBuilder aux = new StringBuilder(myDateFormat2.format(fe));
            anio = Integer.parseInt(aux.toString());
        } catch (ParseException e) {
             logger.error("Problema al cambiar el formato de la fecha: " + fech);
        }
        return anio;
    }*/
    
        

    /**
     * momentoString.
     * Funcion que retorna la fecha al momento con hora minutos y segundos.
     * @return la fecha en String.
     */
    /*public static String momentoString() {
        return dateToString(new Date());
    }*/

}
