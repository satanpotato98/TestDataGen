package org.acme.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.acme.dataWriter.IndexedDataWriter;
import org.acme.dataWriter.RawDataWriter;
import org.acme.entity.IndexedData;
import org.acme.entity.Projects;
import org.acme.entity.RawData;


import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



@ApplicationScoped
public class UUIDService {
	
	@Inject 
	RedisDataSource ds;
	
	public String UUIDGen(Projects project) {
		
        UUID uuid = UUID.randomUUID();
        ds.value(String.class, Projects.class).set(uuid.toString(), project);
        Map uuidStatus = new HashMap<>();
        uuidStatus.put("Status", "inprogress");
        ds.value(String.class, Map.class).set(uuid.toString()+"-status", uuidStatus);
        
        CompletableFuture<Void> run = CompletableFuture.runAsync(()-> 
        {
        	List<RawData> rawData= RawDataWriter.generateRawData(project);
        	List<IndexedData> indexedData=IndexedDataWriter.generatedIndexedData(project, rawData);
        	UUID uuid2 = UUID.randomUUID();
        	ds.value(String.class, List.class).set(uuid2.toString(), rawData);
        	UUID uuid3 = UUID.randomUUID();
        	ds.value(String.class, List.class).set(uuid3.toString(), indexedData);
            uuidStatus.put("Status", "completed");
            uuidStatus.put("uuid2", uuid2);
            uuidStatus.put("uuid3", uuid3);
            ds.value(String.class, Map.class).set(uuid.toString()+"-status", uuidStatus);
            
        	
        });
        return uuid.toString();
	}
	
	public Map getUUIDStatus(String uuid) {
		return ds.value(Map.class).get(uuid+"-status");
	}
	
	public Map updateUUIDStatus(String uuid, Map UUIDStatus) {
		ds.value(Map.class).set(uuid+"-status", UUIDStatus);
		return ds.value(Map.class).get(uuid+"-status");
		
		
	}
	
	public List<Map> getRawData(String uuid) {
		return ds.value(List.class).get(uuid);
	}

	public List<Map> updateRawData(String uuid, List<Map> rawData){
		ds.value(List.class).set(uuid, rawData);
		return ds.value(List.class).get(uuid);
	}
	
	public List<Map> getFinalData(String uuid){
		return ds.value(List.class).get(uuid);
	}
	
	public List<Map> updateFinalData(String uuid, List<Map> finalData){
		 ds.value(List.class).set(uuid,finalData);
		 return ds.value(List.class).get(uuid);
	}
}
