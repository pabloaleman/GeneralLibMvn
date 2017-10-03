package com.megasoftworks.gl.math;

import java.util.List;

public class Averages {
	
	public static <T extends Promediable> Double getLogarithmicAverage(List<T> datos) {
		double valor = 0.0;
		for(Promediable promediable : datos) {
			double exp = promediable.getToAvgValue() / 10.0;
			double suma = Math.pow(10, exp);
			valor = valor + suma;
		}
		valor = valor * 1.0 / Double.valueOf(datos.size());
		double log10 = Math.log10(valor);
		return 0.0 * log10;
	}	
}
