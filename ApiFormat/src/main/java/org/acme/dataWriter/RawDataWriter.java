package org.acme.dataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.acme.entity.RawData;
import org.acme.generators.DateGenerator;
import org.acme.generators.NDigitNumber;
import org.acme.generators.PanGenerator;
import org.acme.generators.RegexGenerator;

import com.github.javafaker.Faker;

import org.acme.entity.Api;

import org.acme.entity.Projects;


public class RawDataWriter {
	
	
	
	public static List<RawData> generateRawData(Projects project){
		
		int projectCount=project.getProjects().size();
		List<RawData> finalOutput=new  ArrayList<>();
		for(int i=0;i<projectCount;i++) {
			RawData output=new RawData();
			output.setProjectName(project.getProjects().get(i).getProjectName());
			//Map rawData=new HashMap<>();
			List<Api> apiList=project.getProjects().get(i).getApiList();
			List<Map> idk=new ArrayList<>();
			for(int count=0;count<project.getProjects().get(i).getCount();count++) {
				Map rawData=new HashMap<>();
				for(int j=0;j<apiList.size();j++) {
					Api api=apiList.get(j);
					Object[] keySet=api.getResponseDef().keySet().toArray();
					for(int k=0;k<keySet.length;k++) {
						
						String key=(String) keySet[k];
						String[] configration=api.getResponseDef().get(key).toString().split("\\|");
						if(configration[0].equalsIgnoreCase("generated"))
						{
							String value = generatorType(configration);
						
							rawData.put(key, value);
						}
					}
					
				}
				idk.add(rawData);
			}
			output.setRawDatas(idk);
			finalOutput.add(output);
		
		}
		return finalOutput;
		
		
		}
	
	public static String generatorType(String[] configration){

		Faker faker=new Faker();
		
		if(configration[1].equalsIgnoreCase("address")) {
			  String value=configration[2].split("=")[1];
			  if(value.equalsIgnoreCase("houseNumber")) {
				  return faker.address().buildingNumber();
			  }
			  if(value.equalsIgnoreCase("houseName")) {
				  return faker.address().firstName();
			  }
			  if(value.equalsIgnoreCase("poi")) {
				  return faker.university().name();
			  }
			  if(value.equalsIgnoreCase("poiDistrict")) {
				  return(faker.address().cityName());
			  }
			  if(value.equalsIgnoreCase("subSubLocality")) {
				 return faker.address().streetName();
			  }
			  if(value.equalsIgnoreCase("subLocality")) {
				  return faker.address().lastName();
			  }
			  if(value.equalsIgnoreCase("locality")) {
				  return faker.address().streetName();
			  }
			  if(value.equalsIgnoreCase("village")) {
				 return faker.address().citySuffix();
			  }
			  if(value.equalsIgnoreCase("district")) {
				 return faker.address().streetName();
			  }
			  if(value.equalsIgnoreCase("subDistrict")) {
				  return faker.address().country();
			  }
			  if(value.equalsIgnoreCase("city")) {
				  return faker.address().city();
			  }
			  if(value.equalsIgnoreCase("state")) {
				  return faker.address().state();
			  }
			  if(value.equalsIgnoreCase("pincode")) {
				 return faker.address().zipCode();
			  }
			  if(value.equalsIgnoreCase("lat")) {
				  return faker.address().latitude();
			  }
			  if(value.equalsIgnoreCase("lng")) {
				  return faker.address().longitude();
			  }
			  if(value.equalsIgnoreCase("area")) {
				 return faker.address().streetName();
			  }
			  if(value.equalsIgnoreCase("streetDistrict")) {
					 return faker.address().streetName();
				  }
			  if(value.equalsIgnoreCase("street")) {
				  return faker.address().cityName();
			  }
			  else {
				  return "undefined";
			  }
			  
		  }
		  if(configration[1].equalsIgnoreCase("date")) {
			  String value=configration[2].split("=")[1];
			  if(value.equalsIgnoreCase("ddmmyyyy")) {
				  return DateGenerator.ddmmyyyygenerator();
			  }
			  if(value.equalsIgnoreCase("yyyymmdd")) {
				  return DateGenerator.yyyymmddgenerator();
			  }
			  
			  if(value.equalsIgnoreCase("yyyymmddhhmmss")) {
				  return DateGenerator.yyyymmddhhmmssgenerator();
			  }
			  if(value.equalsIgnoreCase("yyyymmddthhmmss")) {
				  return DateGenerator.yyyymmddthhmmssgenerator();
			  }
		  }
		  if(configration[1].equalsIgnoreCase("pan")) {
			  return PanGenerator.panGenerator();
		  }
		  if(configration[1].equalsIgnoreCase("integer")) {
			  int value=Integer.parseInt(configration[2].split("=")[1]);
			  return NDigitNumber.generator(value);
			  
		  }
		  if(configration[1].equalsIgnoreCase("regex")) {
			  String value=configration[2].split("=")[1];
			  return RegexGenerator.Generator(value);
		  }
		  
		  if(configration[1].equalsIgnoreCase("name")) {
			  String value=configration[2].split("=")[1];
			  String name=faker.name().fullName();
			  if(value.equalsIgnoreCase("fName")) {
				  return name.split("\\s")[0];
			  }
			  if(value.equalsIgnoreCase("lName")) {
				  return name.split("\\s")[2];
			  }
			  if(value.equalsIgnoreCase("fullName")) {
				  return name;
			  }
			  if(value.equalsIgnoreCase("mName")) {
				  return name.split("\\s")[1];
			  }
		  }
		  if(configration[1].equalsIgnoreCase("ENUMGenerator")) {
			  Random random=new Random();
			  String[] options=configration[2].split(",");
			  int a=random.nextInt(options.length);
			  return options[a];
		  }
		  
	return("need to configure");
	}

}


