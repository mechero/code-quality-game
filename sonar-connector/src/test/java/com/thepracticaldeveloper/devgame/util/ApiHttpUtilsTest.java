package com.thepracticaldeveloper.devgame.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ApiHttpUtilsTest {

    public static final String TOKEN_VALUE = "tokenvalue";

    @Test
    public void testHeaders() {
        final HttpHeaders headers = ApiHttpUtils.getHeaders(TOKEN_VALUE);
        final String token64 = new String(Base64.encodeBase64((TOKEN_VALUE + ":").getBytes()));
        assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), headers.getAccept());
        assertEquals("Basic " + token64, headers.get("Authorization").get(0));
    }
}