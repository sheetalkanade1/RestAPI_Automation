package RESTAPI_test;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RESTAPI_baseapi.BaseApi;
import RESTAPI_baseapi.Requestdata;
import RESTAPI_baseapi.ResponsePayload;
import RESTAPI_helper.Configutils;

public class TalentScreenApi {
	
	BaseApi api;
	ResponsePayload payload;
	Properties resource;
	private  List<Integer> id ;
	private Iterator<Integer> iterator;

	@BeforeClass
	public void  beforeClass() {
		
		api=new BaseApi("confi.properties");
		payload=new ResponsePayload();
		id= new ArrayList<Integer>();
		resource=Configutils.SetProperties("confi.properties");
	
	}
	@Test(priority=0)
	public void getRequest() {
		payload=api.get(resource.getProperty("resource"));
		assertEquals(payload.getStatusCode(),200);		
	}
	
	
	@Test(priority=2,dataProvider="PostRequest")
	public void postRequest(String requestPayLoad) throws InterruptedException {
		api.post(resource.getProperty("resource"),requestPayLoad);
		assertEquals(payload.getStatusCode(),201);
		JSONObject jsonObject=new JSONObject(payload.getPayload());
		id.add((Integer) jsonObject.get("id"));
		iterator=id.iterator();
	}
	
	@DataProvider(name="PostRequest")
	public Object[][] postRequestPayLoad(){
		Object [][] obj=new Requestdata().postRequestPayLoad();
		return obj;
	}
	
	
	
	
	@Test(priority =3,dataProvider="PutRequest")
	public void putRequest(String requestPayLoad ) {
		payload=api.put(resource.getProperty("resource"),iterator.next(),requestPayLoad);
			assertEquals(payload.getStatusCode(),200);
			JSONObject jsonObject=new JSONObject(payload.getPayload());
		}
	@Test(priority =4)
	public void deleteRequest() {
		iterator=id.iterator();
		while(iterator.hasNext()) {
			payload=api.delete(resource.getProperty("resource"),iterator.next().toString());		
			assertEquals(payload.getStatusCode(),204);
		}
	}
	

	
	@DataProvider(name="PutRequest")
	public  Object[][] putRequestPayLoad(){
		Object [][] obj=new Requestdata().putRequestPayLoad();
		return obj;
	}
	
}


	
	
	