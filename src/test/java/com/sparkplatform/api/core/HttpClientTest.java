//
//  Copyright (c) 2013 Financial Business Systems, Inc. All rights reserved.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

package com.sparkplatform.api.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class HttpClientTest {
	
	@Test
	public void testGet() throws IOException{
		HttpClient c = new DefaultHttpClient();
		HttpHost h = new HttpHost("www.sparkplatform.com");
		
		HttpRequest r = new HttpGet("/");
		HttpResponse rs = c.execute(h,r);
		assertEquals(200, rs.getStatusLine().getStatusCode());
		
		assertTrue(readString(rs.getEntity().getContent()).contains("Spark"));
		
	}
	
	@Test
	public void testSSL() throws Exception {
		
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null,new TrustManager[]{new ConnectionApacheHttps.FullTrustManager()},null);
		HttpClient c = new DefaultHttpClient();
		//SSLSocketFactory sf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		SSLSocketFactory sf = SSLSocketFactory.getSocketFactory();
		@SuppressWarnings("deprecation")
		Scheme https = new Scheme("https", sf, 443);
		c.getConnectionManager().getSchemeRegistry().register(https);
		HttpHost h = new HttpHost("api.flexmls.com", 443, "https");
		
		HttpRequest r = new HttpGet("/v1/");
		HttpResponse rs = c.execute(h,r);
		
		assertEquals(404, rs.getStatusLine().getStatusCode());
	}
	
	private String readString(InputStream is) throws IOException{
		StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
        		new InputStreamReader(is));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
	}
	


}
