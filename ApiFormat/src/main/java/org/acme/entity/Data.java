package org.acme.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Data {
	
	Map fixed;
	Map rawGeneratedIndexed;
	Map rawGeneratedGeneric;

}
