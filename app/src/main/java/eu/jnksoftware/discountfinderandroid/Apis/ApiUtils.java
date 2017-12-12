package eu.jnksoftware.discountfinderandroid.Apis;

import eu.jnksoftware.discountfinderandroid.services.IuserService;


public class ApiUtils {
    public static final String baseUrl="http://83.212.117.108:9000/api/";
    public static final String mockBaseUrl="http://83.212.117.108:9010/api/";

    public static IuserService getUserService(){
        return RetrofitClient.getClient(baseUrl).create(IuserService.class);
    }
    public static IuserService getMockUserService(){
        return RetrofitClient.getClient(mockBaseUrl).create(IuserService.class);
    }

}
