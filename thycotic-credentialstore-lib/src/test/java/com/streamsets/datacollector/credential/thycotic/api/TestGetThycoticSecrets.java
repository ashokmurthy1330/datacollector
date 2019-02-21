package com.streamsets.datacollector.credential.thycotic.api;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestGetThycoticSecrets {

  @Test
  public void testGetSecretField() throws Exception {
    GetThycoticSecrets secrets = new GetThycoticSecrets();
    secrets = Mockito.spy(secrets);

    CloseableHttpClient closeableHttpClient = Mockito.mock(CloseableHttpClient.class);

    Mockito.doReturn("secret").when(secrets).getValue(closeableHttpClient,
        "u/api/v1/secrets/1/fields/p", "t");

    Assert.assertEquals("secret",
        secrets.getSecretField(closeableHttpClient, "t", "u", 1, "p", "g"));
  }

  @Test
  public void testNullSecretField() throws Exception {
    GetThycoticSecrets secrets = new GetThycoticSecrets();
    secrets = Mockito.spy(secrets);

    CloseableHttpClient closeableHttpClient = Mockito.mock(CloseableHttpClient.class);

    Mockito.doReturn(null).when(secrets).getValue(closeableHttpClient,
        "u/api/v1/secrets/1/fields/p", "t");

    Assert.assertEquals(null,
        secrets.getSecretField(closeableHttpClient, "t", "u", 1, "p", "g"));
  }

}
