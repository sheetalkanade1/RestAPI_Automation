package RESTAPI_baseapi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Requestdata {
	
	

	 public Object [][] postRequestPayLoad() {
		 List <Object> list=new ArrayList<Object>();
		 JSONParser parser = new JSONParser();
		 Object obj=new Object();
		 JSONArray array =null;
		try {
			obj = parser.parse(new FileReader("C:/Users/Training/Documents/EclipsewrkspcTest/Niraj_Innovapath/RESTAPI_Automation/resources/postRequestPayload.json"));
			array=new JSONArray(obj.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(int i=0;i<4;i++) {
			 JSONObject jsonObject=new JSONObject(array.get(i).toString());
			 list.add((Object)jsonObject);
		 }
		 
	    Object [][] objArray = new Object[list.size()][1];
		 for(int i=0;i< objArray.length;i++) {
			
			objArray[i][0]= new Object[i][0];
			objArray[i][0]=(Object)list.get(i).toString();
		 } 
		 
		 return objArray;
	 
	 }
	 
	 
	 
	 
	 
	 
	 public Object [][] putRequestPayLoad() {
		 List <Object> list=new ArrayList<Object>();
		 JSONParser parser = new JSONParser();
		 Object obj=new Object();
		 JSONArray array =null;
		try {
			obj = parser.parse(new FileReader("C:/Users/Training/Documents/EclipsewrkspcTest/Niraj_Innovapath/RESTAPI_Automation/resources/putRequestPayload.json"));
			array=new JSONArray(obj.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(int i=0;i<4;i++) {
			 JSONObject jsonObject=new JSONObject(array.get(i).toString());
			 list.add((Object)jsonObject);
		 }
		 
	    Object [][] objArray = new Object[list.size()][1];
		
		 for(int i=0;i< objArray.length;i++) {
			
			objArray[i][0]= new Object[i][0];
			objArray[i][0]=(Object)list.get(i).toString();
		 
		 } 
		 
		 return objArray;
	 
	 }
	 
	 
}