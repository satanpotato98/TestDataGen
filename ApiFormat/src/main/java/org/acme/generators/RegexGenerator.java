package org.acme.generators;

import com.mifmif.common.regex.Generex;

public class RegexGenerator {
	
public static String Generator(String value) {
		
		Generex generex=new Generex(value);
		return generex.random();
	}

}
