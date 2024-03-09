package org.acme.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Api {
	
	String name;
	boolean reqIsJSON;
	boolean resIsJSON;
	List<Map> indexExpr;
	String searchExpr;
	Map responseDef=new HashMap<>();
	List<Map> joltTransform=new ArrayList<>();
	
}