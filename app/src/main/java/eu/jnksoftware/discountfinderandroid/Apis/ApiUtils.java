package eu.jnksoftware.discountfinderandroid.Apis;

import eu.jnksoftware.discountfinderandroid.services.IuserService;


/**
 * Created by nikos on 1/12/2017.
 */
public class ApiUtils {
    public static final String baseUrl="http://83.212.117.108:9000/api/";

    public static IuserService getUserService(){
        return RetrofitClient.getClient(baseUrl).create(IuserService.class);
    }

}
