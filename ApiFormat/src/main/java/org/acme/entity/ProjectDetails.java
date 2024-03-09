package org.acme.entity;

import java.util.ArrayList;
import java.util.List;

import org.acme.entity.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProjectDetails {

	String projectName;
	List<Api> apiList=new ArrayList<>();
	int count;
	
}