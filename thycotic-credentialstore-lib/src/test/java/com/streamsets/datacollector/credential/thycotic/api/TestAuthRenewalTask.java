package com.streamsets.datacollector.credential.thycotic.api;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestAuthRenewalTask {

  @Test
  public void testGetAccessToken() throws Exception {
    AuthRenewalTask auth = new AuthRenewalTask();
    auth = Mockito.spy(auth);
    Mockito.doReturn("token").when(auth).fetchAccessToken();

    Assert.assertEquals("token", auth.getAccessToken());
    Assert.assertEquals(System.currentTimeMillis(), auth.getExpireTime());
  }

  @Test
  public void testNullToken() throws Exception {
    AuthRenewalTask auth = new AuthRenewalTask();
    auth = Mockito.spy(auth);
    Mockito.doReturn(null).when(auth).fetchAccessToken();

    Assert.assertEquals(0, auth.getExpireTime());
    Assert.assertEquals(null, auth.getAccessToken());
  }

  @Test
  public void testNegativeValues() throws Exception {
    AuthRenewalTask auth = new AuthRenewalTask();
    auth = Mockito.spy(auth);
    Mockito.doReturn("token").when(auth).fetchAccessToken();
    Mockito.doReturn(-1L).when(auth).getExpireTime();

    Assert.assertEquals("token", auth.getAccessToken());
    Assert.assertEquals(-1, auth.getExpireTime());
  }
}
