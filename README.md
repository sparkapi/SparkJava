SparkJava API
=============

The `SparkAPI` object is designed as a standalone Java interface for use with the [Spark API](http://www.sparkplatform.com/docs/overview/api).  It implements Spark [authentication](http://www.sparkplatform.com/docs/authentication/authentication) via the Hybrid or OpenID methods.  API calls per HTTP method provide a high-level Spark API interface and return a JSON results array on success while handling errors like session expiration for the client.

## Requirements

* JDK 1.6+
* [Maven 3.0.3+](http://maven.apache.org/download.html)

## Configuration

Once you [register](http://www.sparkplatform.com/register/developers) as a Spark developer and receive your Spark Client Id and Client Secret, open the [sparkapi.properties](./src/main/resources/sparkapi.properties) file and set the `API_KEY` and `API_SECRET` properties.  You must also set the `USER_AGENT` with the name of your app or your API requests will not be accepted.

## Command Line Interface

The `pom.xml` file contains the maven configuration.  To compile, run the tests, and generate the jar, execute `mvn install`.  A `SparkJava-1.0.jar` file will be generated for use in your project. 

## API Examples

### Authentication

The `SparkAPI` object is designed to work with Android `WebView` and `WebViewClient` objects to initiate and process Spark authentication.

**Initiating an Authentication Request**:

* To initiate a Hybrid authentication request, call `WebView loadUrl()` with `getSparkHybridOpenIdURLString`.

* To initiate an OpenID authentication request, call `WebView loadUrl()` with `getSparkOpenIdURLString` or `getSparkOpenIdAttributeExchangeURLString`.

**Processing Authentication**:

`SparkAPI` provides methods for processing authentication and setting a `SparkSession` object upon success: 

* **isHybridAuthorized** and **hybridAuthenticate** implement the Spark [OpenID+OAuth 2 Hybrid Protocol](http://www.sparkplatform.com/docs/authentication/openid_oauth2_authentication).
* **openIdAuthenticate** implements the Spark OpenID [Simple Registration Extension](http://www.sparkplatform.com/docs/authentication/openid_authentication#sreg) or [OpenID Attribute Exchange Extension](http://www.sparkplatform.com/docs/authentication/openid_authentication#ax).

``` java
public static String isHybridAuthorized(String url);

public SparkSession hybridAuthenticate(String openIdSparkCode) throws SparkAPIClientException;

public SparkSession openIdAuthenticate(String url) throws SparkAPIClientException;
```

These authentication methods are typically placed in a `WebViewClient` object to respond to a URL request generated after the user provides their Spark credentials.  See [WebViewActivity.java](./src/com/sparkplatform/ui/WebViewActivity.java) for an example.


``` java
		public boolean shouldOverrideUrlLoading (WebView view, String url)
		{
		    String openIdSparkCode = null;
		    if(loginHybrid && (openIdSparkCode = SparkAPI.isHybridAuthorized(url)) != null)
		    {
				Log.d(TAG, "openIdSparkCode>" + openIdSparkCode);
				new OAuth2PostTask().execute(openIdSparkCode);	   				   
				return true;
		    }

		    return false;
		}
		
	private class OAuth2PostTask extends AsyncTask<String, Void, SparkSession> {
	     protected SparkSession doInBackground(String... openIdSparkCode) {
	    	 SparkSession session = null;
	    	 try
	    	 {
	    		 session = sparkClient.hybridAuthenticate(openIdSparkCode[0]);
	    	 }
	    	 catch(SparkAPIClientException e)
	    	 {
	    		 Log.e(TAG, "SparkApiClientException", e);
	    	 }
	    	 
	    	 return session;
	     }
	     
	     protected void onPostExecute(SparkSession sparkSession) {	    	 
	    	if(sparkSession != null)
	    	{
	    		processAuthentication(sparkSession, null);
	    		
	    		Intent intent = new Intent(getApplicationContext(), ViewListingsActivity.class);
	    		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		startActivity(intent);	  
	    	}
		 }
	 }
```

### Making API calls

Once an authenticated `SparkSession` is set, `SparkAPI` methods corresponding to the four HTTP methods can be called.  

On success, a Spark `Response` object is returned with convenience methods to access the JSON, return code, and results array.

On failure, a `SparkAPIClientException` is thrown.

Session renewal is handled automatically by the `SparkAPI` object when a session token expire [error code](http://www.sparkplatform.com/docs/supporting_documentation/error_codes) is returned by the API.

``` java
public Response get(String path, Map<ApiParameter, String> options) throws SparkAPIClientException;

public Response post(String path, String body, Map<ApiParameter, String> options) throws SparkAPIClientException;

public Response put(String path, String body, Map<ApiParameter, String> options) throws SparkAPIClientException;

public Response delete(String path, Map<ApiParameter, String> options) throws SparkAPIClientException;
```

Below is an example API call to the `/my/account` Spark API endpoint from the example app.  On response, the list view interface is updated.

``` java
	 private class MyAccountTask extends AsyncTask<Void, Void, Response> {
	     protected Response doInBackground(Void... v) {
				   
	    	 Response r = null;
	    	 try
	    	 {
	    		 r = SparkAPI.getInstance().get("/my/account",null);
	    	 }
	    	 catch(SparkAPIClientException e)
	    	 {
	    		 Log.e(TAG, "/my/account exception>", e);
	    	 }
	    	 
	    	 return r;
	     }
	     	     
	     protected void onPostExecute(Response r) {
	    	 JsonNode account = r.getFirstResult();
	    	 
	    	 if(account != null)
	    	 {
	    		 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	    		 ActivityHelper.addListLine(list, "Name", account.get("Name").getTextValue());
	    		 ActivityHelper.addListLine(list, "Office", account.get("Office").getTextValue());
	    		 ActivityHelper.addListLine(list, "Company", account.get("Company").getTextValue());
	    		 ActivityHelper.addArrayLine(account, "Addresses", "Address", list, "Address");
	    		 ActivityHelper.addListLine(list, "MLS", account.get("Mls").getTextValue());
	    		 ActivityHelper.addArrayLine(account, "Emails", "Address", list, "Email");
	    		 ActivityHelper.addArrayLine(account, "Phones", "Number", list, "Phone");
	    		 ActivityHelper.addArrayLine(account, "Websites", "Uri", list, "Website");

	    		 ListAdapter adapter = new SimpleAdapter(getApplicationContext(), 
	    				 list,
	    				 R.layout.two_line_list_item, 
	    				 new String[] {"line1", "line2"}, 
	    				 new int[] {android.R.id.text1, android.R.id.text2});
	    		 setListAdapter(adapter);
	    	 }
	     }
	 }
```

## Dependencies

* [Jackson JSON processor](http://jackson.codehaus.org/)
* [HTTPClient](http://hc.apache.org/httpcomponents-client-ga/index.html)
* [log4j](http://logging.apache.org/log4j/1.2/)
* [Mockito](https://code.google.com/p/mockito/)

