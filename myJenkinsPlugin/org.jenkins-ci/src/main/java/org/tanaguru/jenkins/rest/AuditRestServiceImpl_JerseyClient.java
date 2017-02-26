/*
 * The MIT License
 *
 * Copyright 2017 tanaguru.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tanaguru.jenkins.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author tanaguru
 */
public class AuditRestServiceImpl_JerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/rest/";

    public AuditRestServiceImpl_JerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("service");

    }

    public String launchAuditPage(String level, String pageUrl) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (level != null) {
            resource = resource.queryParam("level", level);
        }
        if (pageUrl != null) {
            resource = resource.queryParam("pageUrl", pageUrl);
        }
        resource = resource.path("launchaudit2");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String auditPage(String level, String pageUrl) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (level != null) {
            resource = resource.queryParam("level", level);
        }
        if (pageUrl != null) {
            resource = resource.queryParam("pageUrl", pageUrl);
        }
        resource = resource.path("auditpage");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public  String greeting() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("test");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
}
