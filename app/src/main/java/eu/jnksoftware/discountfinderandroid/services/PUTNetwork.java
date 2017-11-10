package eu.jnksoftware.discountfinderandroid.services;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jordankostelidis on 10/11/2017.
 */

public class PUTNetwork implements INetwork {
    private String url;
    private String result;
    private String userAgent;
    private HashMap<String, String> properties;
    private HashMap<String, String> headers;
    private int timeout;

    public PUTNetwork(String argUrl) throws Exception {
        this(argUrl, 10000);
    }

    private PUTNetwork(String argUrl, int argTimeout) throws Exception {

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        } catch (Exception ignored) {

        }

        if (!isValidUrl(argUrl)) throw new Exception("Invalid URL");

        if (!argUrl.startsWith("https:")) throw new Exception("URL must have SSL (HTTPS)");

        url = argUrl;
        userAgent = "Mozilla/5.0";
        properties = new HashMap<>();
        headers = new HashMap<>();
        timeout = argTimeout;
    }

    private boolean isValidUrl(String argUrl) {
        String REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(argUrl);
        return m.find();
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public boolean addProperty(String property, String value) {
        properties.put(property, value);
        return properties.containsKey(property);
    }

    @Override
    public boolean removeProperty(String property) {
        if (properties.containsKey(property)) {
            properties.remove(property);
            return !properties.containsKey(property);
        } else {
            return false;
        }
    }

    @Override
    public boolean updateProperty(String property, String value) {
        if (properties.containsKey(property)) {
            properties.put(property, value);
            return Objects.equals(properties.get(property), value);
        } else {
            return false;
        }
    }

    @Override
    public boolean addHeader(String header, String value) {
        headers.put(header, value);
        return headers.containsKey(header);
    }

    @Override
    public boolean removeHeader(String header) {
        if (headers.containsKey(header)) {
            headers.remove(header);
            return !headers.containsKey(header);
        } else {
            return false;
        }
    }

    @Override
    public boolean updateHeader(String header, String value) {
        if (headers.containsKey(header)) {
            headers.put(header, value);
            return !headers.get(header).equals(value);
        } else {
            return false;
        }
    }

    @Override
    public boolean call() throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

        connection.setReadTimeout(timeout);
        connection.setConnectTimeout(timeout);
        connection.setRequestMethod("PUT");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        connection.addRequestProperty("User-Agent", userAgent);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(dataString());

        writer.flush();
        writer.close();
        os.close();

        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            InputStream input = connection.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(input));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }

            result = total.toString();
        }

        return !result.isEmpty();
    }

    private String dataString() throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
