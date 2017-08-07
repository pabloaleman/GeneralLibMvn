package com.megasoftworks.gl.list;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	public static List<Integer> stringArrayToIntegerList(String[] lista) {
		List<Integer> retorno = new ArrayList<>();
		for(String n : lista) {
			retorno.add(Integer.parseInt(n));
		}
		return retorno;
	}
}
