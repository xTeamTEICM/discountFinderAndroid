package eu.jnksoftware.discountfinderandroid.Apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dito on 12/5/2017.
 */

public class UpdatePostShop {

    @SerializedName("id")
    @Expose
    private final int id;

    @SerializedName("brandName")
    @Expose
    private final String brandName;

    @SerializedName("logPos")
    @Expose
    private final double logPos;

    @SerializedName("latPos")
    @Expose
    private final double latPos;

    public UpdatePostShop(int id, String brandName, double logPos, double latPos) {
        this.id = id;
        this.brandName = brandName;
        this.logPos = logPos;
        this.latPos = latPos;
    }
}
