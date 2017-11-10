package eu.jnksoftware.discountfinderandroid.services;

import java.io.IOException;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 28/10/2017
 * License: Apache License 2.0
 */
public class MockCategoryNetwork implements INetwork {
    @Override
    public String getResult() {
        return "{\"result\":{\"status\":\"OK\",\"message\":\"Our categories is here !\",\"categories\":[{\"id\":7,\"title\":\"Laptops\"},{\"id\":6,\"title\":\"Smartphones\"},{\"id\":2,\"title\":\"Μπλούζες\"},{\"id\":3,\"title\":\"Παντελόνια\"},{\"id\":1,\"title\":\"Παπούτσια\"},{\"id\":4,\"title\":\"Τηλεοράσεις\"},{\"id\":5,\"title\":\"Υπολογιστές\"}]}}";
    }

    @Override
    public String getURL() {
        return "https://mock.mock/";
    }

    @Override
    public void setURL(String url) {

    }

    @Override
    public boolean addProperty(String property, String value) {
        return true;
    }

    @Override
    public boolean removeProperty(String property) {
        return true;
    }

    @Override
    public boolean updateProperty(String property, String value) {
        return true;
    }

    @Override
    public boolean addHeader(String header, String value) {
        return false;
    }

    @Override
    public boolean removeHeader(String header) {
        return false;
    }

    @Override
    public boolean updateHeader(String header, String value) {
        return false;
    }

    @Override
    public boolean call() throws IOException {
        return true;
    }
}
