package exercise.fa.calcultax.util;

import java.text.Normalizer;

public class StringNormalizer {
	public static String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
}
