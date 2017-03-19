package edu.oregonstate.cs361.battleship.test;

import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import spark.utils.IOUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class MainTest {
    @Test
    public void testGetModel() {
        TestResponse res = request("GET", "/model");
        assertEquals(200, res.status);

        String info = res.body;
        Gson gson = new Gson();
        assertEquals(info, res.body);

    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4000" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        }
        catch (IOException e) {
            return null;
        }
    }
    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }

}