package com.megasoftworks.gl.math;

import java.util.List;

public class Averages {
	public static double logAvg(List<Double> valores) {
		double valor = 0.0;
		for(double valorLista: valores) {
			double exp = valorLista / 10.0;
			double suma = Math.pow(10, exp);
			valor = valor + suma;
		}
		valor = valor * 1.0 / Double.valueOf(valores.size());
		double log10 = Math.log10(valor);
		return 10.0 * log10;
	}
	
	public static double logAvg(double[] valores) {
		double valor = 0.0;
		for(double valorLista: valores) {
			double exp = valorLista / 10.0;
			double suma = Math.pow(10, exp);
			valor = valor + suma;
		}
		valor = valor * 1.0 / Double.valueOf(valores.length);
		double log10 = Math.log10(valor);
		return 10.0 * log10;
	}
	/*
	public static void main(String args[]) {
		List<Double> valores = new ArrayList<>();
		valores.add(56.37);
		valores.add(54.84);
		valores.add(53.62);
		valores.add(51.76);
		valores.add(54.66);
		valores.add(57.58);
		valores.add(57.66);
		valores.add(64.23);
		valores.add(56.85);
		valores.add(58.21);
		System.out.println(logAvg(valores));
		
		List<Double> valores2 = new ArrayList<>();
		valores2.add(56.37);
		valores2.add(54.84);
		valores2.add(53.62);
		valores2.add(51.76);
		valores2.add(54.66);
		valores2.add(57.58);
		valores2.add(57.66);
		valores2.add(58.62);
		valores2.add(58.91);
		valores2.add(58.16);
		valores2.add(59.81);
		valores2.add(58.64);
		valores2.add(59.34);
		valores2.add(59.59);
		valores2.add(60.74);
		valores2.add(58.46);
		valores2.add(58.2);
		valores2.add(58.47);
		valores2.add(59.03);
		valores2.add(57.8);
		valores2.add(57.37);
		valores2.add(64.23);
		valores2.add(56.85);
		valores2.add(58.21);
		System.out.println(logAvg(valores2));
	}*/
	
}
