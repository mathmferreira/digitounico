package br.com.digitounico.utils;

import java.util.LinkedHashMap;

import br.com.digitounico.dto.DigitoUnicoDTO;

public class CacheUtils {

	private static LinkedHashMap<String, Integer> cacheDigitoUnico = new LinkedHashMap<>();
	
	private CacheUtils() {
		throw new IllegalAccessError("Classe utilitária");
	}
	
	public static void addToCache(DigitoUnicoDTO dto) {
		if (cacheDigitoUnico.size() == 10) {
			cacheDigitoUnico.remove(cacheDigitoUnico.keySet().stream().findFirst().orElse(null));
		}
		cacheDigitoUnico.put(generateKey(dto), dto.getDigitoUnicoGerado());
	}
	
	public static Integer search(DigitoUnicoDTO dto) {
		String key = generateKey(dto);
		return cacheDigitoUnico.containsKey(key) ? cacheDigitoUnico.get(key) : null;
	}
	
	private static String generateKey(DigitoUnicoDTO dto) {
		return dto.getNumero() + "*" + dto.getConcatenacao();
	}
	
}
