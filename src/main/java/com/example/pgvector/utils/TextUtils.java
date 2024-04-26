package com.example.pgvector.utils;

import java.text.Normalizer;

public class TextUtils {
	
	public static String unaccent(String text) {
		if (text == null) {
			return null;
		}
		String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
		return normalizedText.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}
