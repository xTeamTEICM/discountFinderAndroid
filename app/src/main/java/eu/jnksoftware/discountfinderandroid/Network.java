package eu.jnksoftware.discountfinderandroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 20/10/2017
 * License: Apache License 2.0
 */
public class Network {
    private String url;
    private String result;
    private String userAgent;
    private HashMap<String, String> properties;
    private int timeout;

    public Network(String argUrl) throws Exception {
        this(argUrl, 10000);
    }

    public Network(String argUrl, int argTimeout) throws Exception {
        if (!isValidUrl(argUrl)) throw new Exception("Invalid URL");

        if (!argUrl.startsWith("https:")) throw new Exception("URL must have SSL (HTTPS)");

        url = argUrl;
        userAgent = "Mozilla/5.0";
        properties = new HashMap<>();
        timeout = argTimeout;
    }

    private boolean isValidUrl(String argUrl) {
        String REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(argUrl);
        return m.find();
    }

    public String getResult() {
        return result;
    }

    public boolean addProperty(String property, String value) {
        properties.put(property, value);
        return properties.containsKey(property);
    }

    public boolean removeProperty(String property) {
        if (properties.containsKey(property)) {
            properties.remove(property);
            return !properties.containsKey(property);
        } else {
            return false;
        }
    }

    public boolean updateProperty(String property, String value) {
        if (properties.containsKey(property)) {
            properties.put(property, value);
            return Objects.equals(properties.get(property), value);
        } else {
            return false;
        }
    }

    public boolean call() throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

        connection.setReadTimeout(timeout);
        connection.setConnectTimeout(timeout);
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        connection.addRequestProperty("User-Agent", userAgent);

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(dataString());

        writer.flush();
        writer.close();
        os.close();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
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
