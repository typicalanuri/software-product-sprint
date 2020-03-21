// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.gson.Gson;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
ArrayList<String> userComments = new ArrayList<String>();
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
   // ArrayList<String> str = new ArrayList<String>();
    //str = this.getArraylist();
    response.setContentType("text/html;");
   // response.getWriter().println(this.convertToJsonUsingGson(str));
    //response.getRandomQuoteUsingArrowFunctions('/data');
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("comment");
    PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) {
    String comment = (String) entity.getProperty("text");
    response.getWriter().println(comment);
   }
  }
    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "text-input", "");
    userComments.add(text);
    response.setContentType("text/html;");
    response.getWriter().println(this.convertToJsonUsingGson(userComments));
    
    Entity commententity = new Entity("comment");
    commententity.setProperty("text", text);
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commententity);
  }

 private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
 private String convertToJsonUsingGson(ArrayList<String> str) {
    Gson gson = new Gson();
    String json = gson.toJson(str);
    return json;
  }
ArrayList<String> getArraylist()
{
  ArrayList<String> str = new ArrayList<String>();
       str.add("Hello world");
       str.add("Its 2020, yay!");
       str.add("Google SPS!");
       return str;
}
}
