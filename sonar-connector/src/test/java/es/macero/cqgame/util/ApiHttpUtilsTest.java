package es.macero.cqgame.util;

import es.macero.cqgame.util.ApiHttpUtils;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ApiHttpUtilsTest {

    @Test
    public void testHeaders() {
        final HttpHeaders headers = ApiHttpUtils.getHeaders("user", "password");
        assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON), headers.getAccept());
        assertEquals("Basic dXNlcjpwYXNzd29yZA==", headers.get("Authorization").get(0));
    }
}