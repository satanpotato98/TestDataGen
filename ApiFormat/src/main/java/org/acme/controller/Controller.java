package org.acme.controller;

import java.util.List;
import java.util.Map;

import org.acme.entity.Projects;
import org.acme.service.UUIDService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class Controller {
	
	@Inject
	UUIDService ug;

    @POST @Path("/APIDEF")
    public String UUIDGen(Projects project) {
    	return ug.UUIDGen(project);	
    }
    
    @GET @Path("/APIDEF/{uuid}")
    public Map UUIDGet(@PathParam("uuid") String uuid) {
    	return ug.getUUIDStatus(uuid);
    }
    
    @PUT @Path("/APIDEF/{uuid}")
    public Map UUIDupdate(@PathParam("uuid") String uuid, Map UUIDStatus) {
    	return ug.updateUUIDStatus(uuid,UUIDStatus);
    }
    
    @GET @Path("/rawdata/{uuid}")
    public List<Map> UUIDGetRawData(@PathParam("uuid") String uuid) {
    	return ug.getRawData(uuid);
    }
    
    @PUT @Path("/rawdata/{uuid}")
    public List<Map> updateRawData(@PathParam("uuid") String uuid, List<Map> rawData){
    	return ug.updateRawData(uuid, rawData);
    }
    
    @GET @Path("/finaldata/{uuid}")
    public List<Map> getFinalData(@PathParam("uuid") String uuid){
    	return ug.getFinalData(uuid);
    }
    
    @PUT @Path("/finaldata/{uuid}")
    public List<Map> updateFinalData(@PathParam("uuid") String uuid, List<Map> finalData){
    	return ug.updateFinalData(uuid, finalData);
    }
    
    @POST @Path("/{project}/{api}")
    public void finalApi(@PathParam("project") String project, @PathParam("api") String api) {
    	
    }
}
