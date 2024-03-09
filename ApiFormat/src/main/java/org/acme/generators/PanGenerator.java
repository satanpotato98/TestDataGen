package org.acme.generators;

import com.mifmif.common.regex.Generex;

public class PanGenerator {
	
public static String panGenerator() {
		
		Generex generex=new Generex("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		return generex.random();
	}

}
