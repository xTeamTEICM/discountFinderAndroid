package eu.jnksoftware.discountfinderandroid.services;

import java.io.IOException;

/**
 * Owner: JNK Software
 * Developer: Jordan Kostelidis
 * Date: 28/10/2017
 * License: Apache License 2.0
 */
interface INetwork {
    String getResult();

    boolean addProperty(String property, String value);

    boolean removeProperty(String property);

    boolean updateProperty(String property, String value);

    boolean call() throws IOException;
}
