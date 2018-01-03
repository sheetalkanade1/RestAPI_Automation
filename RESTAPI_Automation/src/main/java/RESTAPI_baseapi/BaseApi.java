package RESTAPI_baseapi;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import RESTAPI_baseapi.ResponsePayload;
import RESTAPI_helper.Configutils;


public class BaseApi{
	
	protected String url;
	HttpClient httpClient;
	ResponsePayload payLoad;
	Properties prop;
	String accessTokenValue;
	
	
	public BaseApi(String fileName) {
		httpClient=HttpClientBuilder.create().build();
		Configutils configUtil=new Configutils();
		prop=configUtil.SetProperties(fileName);		
		payLoad=new ResponsePayload();
		this.url=prop.getProperty("Subj_url");
		accessTokenValue="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1OTFhZjIyMTVhMTlhMjM1NGNlMTZjYWMiLCJsYXN0bW9kZGF0ZXRpbWUiOiIyMDE3LTA4LTAyVDEyOjQ5OjM0LjM4M1oiLCJfX"
				+ "3YiOjAsImxhc3Rtb2R1c2VyaWQiOiI1OTFhZjIyMTVhMTlhMjM1NGNlMTZjYWMiLCJhdXRoX2RldGFpbHMiOnsicmVnaXN0ZXJlZGRhdGV0aW1lIjoiMjAxNy0wNS0xNlQxMjozNTo0NS4wMDBaIiwibGF"
				+ "zdF92ZXJpZmljYXRpb25fY29kZSI6ImM2bnFoMzNxIiwiZmlyc3RfbG9naW5fY29tcGxldGVkIjoiWSIsImlzX3VzZXJfdmVyaWZpZWQiOiJZIiwiaXNfc3VwZXIiOnRydWV9LCJlbXBsb3llcl9kZXRha"
				+ "WxzIjp7ImpvYl90aXRsZSI6IkVuZ2luZWVyaW5nIE1hbmFnZXIiLCJjb21wYW55X3NpemUiOiI1MDAwKyIsImNvdW50cnlfbmFtZSI6IlVuaXRlZCBTdGF0ZXMiLCJjb21wYW55X25hbWUiOiJHb29nbGU"
				+ "ifSwic291cmNlX2RldGFpbHMiOnsidXNlcm5hbWUiOiJrdW1hckB3aGl0ZWJveC1sZWFybmluZy5jb20iLCJwYXNzd29yZCI6IjE2YTIxNzRhYTAwOTkwNDdhYWVmZDYzODUzNTE2ZDdjIiwic291cmNlI"
				+ "joidGFsZW50c2NyZWVuIn0sInVzZXJfcHJvZmlsZSI6eyJlbWFpbCI6Imt1bWFyQHdoaXRlYm94LWxlYXJuaW5nLmNvbSIsIm5hbWUiOiJLdW1hciB2ZWx1cHVsYSIsInBob25lIjoiKzE5MjU0MjQ3ND"
				+ "ExIn0sInJvbGUiOiJlbXBsb3llZSIsImlhdCI6MTUwMzkwMjE4MywiZXhwIjoxNTA3NTAyMTgzfQ.Jto1XCHCLOju5St12SW9oKmBFa1JJ8ovkhyiX1wKrB8";
	}
	
	
	
	
	public ResponsePayload get(String resource) {
		HttpGet get=new HttpGet(url+resource);
		try {
			get.setHeader(prop.getProperty("Access_Key"),accessTokenValue);
			
			HttpResponse response=httpClient.execute(get);
			payLoad.setHeaders(response.getAllHeaders());
			payLoad.setPayload(IOUtils.toString(response.getEntity().getContent()));
			payLoad.setStatusCode(response.getStatusLine().getStatusCode());
			payLoad.setStatusMessage(response.getStatusLine().toString());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payLoad;
	}
	
	
	
	
	
	
	public ResponsePayload post(String resource,String requestPayLoad) {
		HttpPost post=new HttpPost(url+resource);
		
		try {
			
			post.setHeader(prop.getProperty("Access_Key"),accessTokenValue);
			post.setHeader(prop.getProperty("Header"),prop.getProperty("Header_Value"));
			
			HttpEntity entity=new StringEntity(requestPayLoad);
			post.setEntity(entity);

			HttpResponse response=httpClient.execute(post);
			payLoad.setHeaders(response.getAllHeaders());
			payLoad.setPayload(IOUtils.toString(response.getEntity().getContent()));
			payLoad.setStatusCode(response.getStatusLine().getStatusCode());
			payLoad.setStatusMessage(response.getStatusLine().toString());
				
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payLoad;
	}
	
	
	
	
	public ResponsePayload delete(String resource,String requestParam) {
		HttpDelete delete=new HttpDelete(url+resource+"/"+requestParam);
		delete.setHeader(prop.getProperty("Access_Key"),accessTokenValue);
		try {
			HttpResponse response=httpClient.execute(delete);
			payLoad.setHeaders(response.getAllHeaders());
			payLoad.setStatusCode(response.getStatusLine().getStatusCode());
			payLoad.setStatusMessage(response.getStatusLine().toString());
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payLoad;
	}
	
	
	
	
	
	public ResponsePayload put(String resource ,int requestParam,String requestPayLoad) {
		HttpPut put=new HttpPut(url+resource+"/"+requestParam);
		try {
			//set headers
			put.setHeader(prop.getProperty("Access_Key"),accessTokenValue);
			put.setHeader(prop.getProperty("Header"),prop.getProperty("Header_Value"));
			
			//give request Payload::
			
			HttpEntity entity=new StringEntity(requestPayLoad);
			put.setEntity(entity);
			
			//execute put
			
			HttpResponse response=httpClient.execute(put);
			payLoad.setHeaders(response.getAllHeaders());
			payLoad.setPayload(IOUtils.toString(response.getEntity().getContent()));
			payLoad.setStatusCode(response.getStatusLine().getStatusCode());
			payLoad.setStatusMessage(response.getStatusLine().toString());
			
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payLoad;
	}
	
	
	
	
	
	
	
	
	
	

}
