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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.log4j.Logger;

/**
 * SSL Connection wrapper for the Apache HTTPClient library.
 */
public class ConnectionApacheHttps extends ConnectionApacheHttp {
	private static final int SSL_PORT = 443;
	private static Logger logger = Logger.getLogger(ConnectionApacheHttps.class);

	public ConnectionApacheHttps(Configuration config) {
		super(config);
		resetChild();
	}

	@Override
	protected final void resetChild() {
		try {
			SSLSocketFactory sf = SSLSocketFactory.getSocketFactory();
			@SuppressWarnings("deprecation")
			Scheme https = new Scheme("https", sf, SSL_PORT); // Android constructor
			getClient().getConnectionManager().getSchemeRegistry().register(https);
			setHost(new HttpHost(getConfig().getEndpoint(), SSL_PORT, "https"));
		} catch (Exception e) {
			logger.error("Failed to setup SSL authentication for the client (https disabled).", e);
		}
	}
	
	/**
	 * Basic trust manager that accepts everyone.  This should be scoped to the appropriate certificates.
	 *
	 */
	public static class FullTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
	
}
