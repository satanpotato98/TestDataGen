package org.acme.entity;

import java.util.List;

import org.acme.entity.ProjectDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Projects {

	List<ProjectDetails> projects;
}
