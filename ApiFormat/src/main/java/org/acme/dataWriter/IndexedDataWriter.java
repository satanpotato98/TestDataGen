package org.acme.dataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.entity.Api;
import org.acme.entity.Projects;
import org.acme.entity.RawData;

import com.bazaarvoice.jolt.Chainr;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.acme.entity.ApiOutputData;
import org.acme.entity.Data;
import org.acme.entity.IndexedData;

public class IndexedDataWriter {
	
public static List<IndexedData> generatedIndexedData(Projects project, List<RawData> rawDataList){
		
		int projectCount=project.getProjects().size();
		List<IndexedData> indexedDataList=new ArrayList<>();
		//start iterating in project
		for(int i=0;i<projectCount;i++) {
			IndexedData indexedData=new IndexedData();
			indexedData.setProjectName(project.getProjects().get(i).getProjectName());
			List<ApiOutputData> apiOutputDataList= new ArrayList<>();
			//start iterating in apiList
			List<Map> rawDatas = new ArrayList<>();
			List<Api> apiList=project.getProjects().get(i).getApiList();
			for(int j=0;j<apiList.size();j++) {
				Api api=apiList.get(j);
				Object[] keySet=api.getResponseDef().keySet().toArray();				//start iterating in its keys
				Data data=new Data();
				Map fixed=new HashMap<>();
				Map generic=new HashMap<>();
				for(int k=0;k<keySet.length;k++) {
					String key=(String) keySet[k];
					String[] configration=api.getResponseDef().get(key).toString().split("\\|");
					if(configration[0].equalsIgnoreCase("fixed")) {
						
						fixed.put(key, configration[2].split("=")[1]);
						
					}
					if(configration[0].equalsIgnoreCase("generic"))
					{
						
						generic.put(key,"to be configured");
						
					}
				}
				
				Map responseDef=apiList.get(i).getResponseDef();
				
				Map<String,Object> rawGeneratedIndexed=new HashMap<>();
				
				List<Map> indexExpr = api.getIndexExpr();
				String projectName=project.getProjects().get(i).getProjectName();
				for(int k=0;k<rawDataList.size();k++) {
					if(rawDataList.get(k).getProjectName().equalsIgnoreCase(projectName)) {
						for(int l=0;l<rawDataList.get(k).getRawDatas().size();l++)
						//rawDatas = rawDataList.get(k).getRawDatas();
						rawDatas.add(rawDataList.get(k).getRawDatas().get(l));
						break;
					}
				}
				
		
				 keySet=api.getResponseDef().keySet().toArray();
				List<String> keyGenerated=new ArrayList<>();
				for(int k=0;k<keySet.length;k++) {
					String key=(String) keySet[k];
					
					if(api.getResponseDef().get(key).toString().startsWith("generated"))
					{
						keyGenerated.add(key);
					}
					
					
				}
				
				  if(rawDatas != null){ 
					  for(int k=0;k<rawDatas.size();k++) {
						  Map rawData = rawDatas.get(k); 
						  
						  Chainr chainr = Chainr.fromSpec( indexExpr );
						  Object transformedOutput = chainr.transform( rawData );
						  ObjectMapper objectMapper =  new ObjectMapper(); 
						  Map<String, Object> map = objectMapper.convertValue(transformedOutput, new TypeReference<Map<String,Object>>() {}); 
						  
				  
						  String indexValue = (String) map.get("indexvalue");
						  
						  Map<String,Object> newMap=new HashMap<>();
						  for(int l=0;l<keyGenerated.size();l++) {
							  newMap.put(keyGenerated.get(l),rawData.get(keyGenerated.get(l)));
						  }
						 
				  
						  rawGeneratedIndexed.put(indexValue, newMap);
				  }
				  
				  }
				
				
				data.setFixed(fixed);
				data.setRawGeneratedGeneric(generic);
				data.setRawGeneratedIndexed(rawGeneratedIndexed);
				ApiOutputData apiOutputData=new ApiOutputData();
				apiOutputData.setApiName(api.getName());
				apiOutputData.setData(data);
				apiOutputDataList.add(apiOutputData);
			}
			indexedData.setApiData(apiOutputDataList);
			indexedDataList.add(indexedData);
		}
		return indexedDataList;
	}

}
