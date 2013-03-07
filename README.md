SparkJava API
=============

The `SparkAPI` object is designed as a standalone Java interface for use with the [Spark API](http://www.sparkplatform.com/docs/overview/api).  It implements Spark [authentication](http://www.sparkplatform.com/docs/authentication/authentication) via the Hybrid or OpenID methods.  API calls per HTTP method provide a high-level Spark API interface and return a JSON results array on success while handling errors like session expiration for the client.

## Requirements

* JDK 1.6+
* [Maven 3.0.3+](http://maven.apache.org/download.html)

## Configuration

Once you [register](http://www.sparkplatform.com/register/developers) as a Spark developer and receive your Spark Client Id and Client Secret, open the [sparkapi.properties](./src/main/resources/sparkapi.properties) file and set the `API_KEY` and `API_SECRET` properties.  You must also set the `USER_AGENT` with the name of your app or your API requests will not be accepted.

## Command Line Interface

The `pom.xml` file contains the maven configuration.  To compile, run the tests, and generate the jar, execute `mvn install`.  A `SparkJava-1.0.jar` file will be generated in the `target/` folder for use in your project. 

## API Examples

### Authentication

**Processing Authentication**:

`SparkAPI` provides methods for processing authentication and setting a `SparkSession` object upon success: 

* **isHybridAuthorized** and **hybridAuthenticate** implement the Spark [OpenID+OAuth 2 Hybrid Protocol](http://www.sparkplatform.com/docs/authentication/openid_oauth2_authentication).
* **openIdAuthenticate** implements the Spark OpenID [Simple Registration Extension](http://www.sparkplatform.com/docs/authentication/openid_authentication#sreg) or [OpenID Attribute Exchange Extension](http://www.sparkplatform.com/docs/authentication/openid_authentication#ax).

``` java
public static String isHybridAuthorized(String url);

public SparkSession hybridAuthenticate(String openIdSparkCode) throws SparkAPIClientException;

public SparkSession openIdAuthenticate(String url) throws SparkAPIClientException;
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

## Dependencies

* [Jackson JSON processor](http://jackson.codehaus.org/)
* [HTTPClient](http://hc.apache.org/httpcomponents-client-ga/index.html)
* [log4j](http://logging.apache.org/log4j/1.2/)
* [Mockito](https://code.google.com/p/mockito/)

