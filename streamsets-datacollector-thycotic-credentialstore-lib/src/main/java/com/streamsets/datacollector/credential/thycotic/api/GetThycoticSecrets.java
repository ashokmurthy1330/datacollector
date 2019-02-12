/*
 * Copyright 2018 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.streamsets.datacollector.credential.thycotic.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetThycoticSecrets {

  private static final Logger LOG = LoggerFactory.getLogger(GetThycoticSecrets.class);

  public String getSecretField(CloseableHttpClient httpclient, String token, String secretServerUrl,
      Integer secretId, String secretField, String group) {
    secretServerUrl = secretServerUrl + "/api/v1/secrets/" + secretId + "/fields/" + secretField;
    LOG.debug("Thycotic Secret Server URL: " + secretServerUrl);
    String returnedSecret;
    try {
      returnedSecret = getValue(httpclient, secretServerUrl, token);
      if (returnedSecret != null && !returnedSecret.isEmpty()) {
        returnedSecret = returnedSecret.replace("\"", "");
        return returnedSecret;
      }
    } catch (Exception e) {
      LOG.debug("Exception in retrieving the secret value" + e);
    }
    return null;
  }

  private static String getValue(CloseableHttpClient httpclient, String secretServerUrl,
      String token) throws Exception {

    HttpGet httpGet = new HttpGet(secretServerUrl);
    httpGet.setHeader("Authorization", "Bearer " + token);
    CloseableHttpResponse response2 = httpclient.execute(httpGet);
    try {
      String json = EntityUtils.toString(response2.getEntity(), "UTF-8");
      return json;
    } finally {
      response2.close();
    }
  }

}
