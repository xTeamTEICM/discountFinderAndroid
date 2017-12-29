package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
public class MenuCustomer extends Fragment {

    private GeoLocation geoLocation;
    private User tempuser;
    private IuserService iuserService;
    private String auth;
    private Button about;
    private Button settings;
    private Button myShops;
    private Button filtersBtn;
    private Button myDiscount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_menu_customer,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iuserService= ApiUtils.getUserService();
        tempuser= ManageSharePrefs.readUser( null);
        geoLocation = new GeoLocation(getContext());

        about = view.findViewById(R.id.aboutBtn);
//        about.setOnClickListener(aboutClick);
        settings = view.findViewById(R.id.settingsBtn);
//        settings.setOnClickListener(settingsClick);

        myShops =  view.findViewById(R.id.showShopsButton);
        myShops.setOnClickListener(showShopsButtonClick);
        filtersBtn =  view.findViewById(R.id.filtersBtn);
//        filtersBtn.setOnClickListener(filtersButtonClick);
        myDiscount = view.findViewById(R.id.showDiscountsBtn);
        myDiscount.setOnClickListener(discountClick);


    }

    private final View.OnClickListener showShopsButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(MenuCustomer.this, SellerShops.class);
//            Gson user = new Gson();
//            intent.putExtra("auth", auth);
//            intent.putExtra("lat",geoLocation.getLatitude());
//            intent.putExtra("lon",geoLocation.getLongitude());
//            intent.putExtra("User", user.toJson(tempuser));
//
//            startActivity(intent);
        }
    };

    private final View.OnClickListener discountClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            //TODO REMOVE
        }
    };

}