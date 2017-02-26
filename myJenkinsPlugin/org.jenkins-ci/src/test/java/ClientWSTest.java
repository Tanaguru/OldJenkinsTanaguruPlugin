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

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

//import org.apache.http.HttpResponse;
//
//import org.apache.http.client.ClientProtocolException;
//
//import org.apache.http.client.HttpClient;
//
//import org.apache.http.client.methods.HttpGet;
//
//import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author mkebri
 */
public class ClientWSTest {

//    public void doTest(String val1, String val2) {
//
//        String urlWebService = "http://localhost:8080/rest/service/post";
//
//        ClientConfig config = new DefaultClientConfig();
//        Client client = Client.create(config);
//        WebResource webResource = client.resource(UriBuilder.fromUri(urlWebService).build());
//        MultivaluedMap formData = new MultivaluedMapImpl();
//        formData.add("val1", "val1");
//        formData.add("val2", "val2");
//        
//        String auditModel ="{\"id\":\"12\",\"idCode\":\"ZF123\", \"url\":\"myurl\",\"type\":\"RestApiJms\",\"state\":\"Initialization\",\"scenario\":\"my scenario\"}";
//        
//        
//        
//        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, auditModel);
//        if (response.getStatus() >= 200 || response.getStatus() <= 299) {
//            System.out.println("Response Succes !! " + response.getStatus());
//        }
//        System.out.println("Response " + response.getEntity(String.class));
//    }

    public static void main(String[] args) throws  IOException {
//
//        String urlWebService = "http://localhost:8080/rest/service/test";
//
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet(urlWebService);
//
//        HttpResponse response = client.execute(request);
//
//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            System.out.println(line);
//
//        }
        
        
        
//        ClientWSTest clientWSTest = new ClientWSTest();
//        clientWSTest.doTest("Must", "Test rest post");

    }

}
