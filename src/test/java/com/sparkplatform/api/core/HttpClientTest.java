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

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
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
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                builder.build());
        HttpClient c = HttpClients.custom().setSSLSocketFactory(
                sslsf).build();

        HttpGet httpGet = new HttpGet("https://sparkapi.com/v1");
		HttpResponse rs = c.execute(httpGet);

		assertEquals(404, rs.getStatusLine().getStatusCode());
		String s = readString(rs.getEntity().getContent());
		assertEquals(s, "{\"D\":{\"Success\":false,\"Code\":404,\"Message\":\"Not Found\"}}");
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
