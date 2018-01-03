package eu.jnksoftware.discountfinderandroid.ui.general;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;

public class Settings extends Fragment {

    private Button backButton;
    private Switch isSellerSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backButton = view.findViewById(R.id.settingsBackButton);
        backButton.setOnClickListener(backButtonClick);
        isSellerSwitch = view.findViewById(R.id.switchSettingsIsSeller);
//        TODO add this line when readIsUser works
//        isSellerSwitch.setChecked(ManageSharePrefs.readIsUserSeller(false));
    }

    View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean b = isSellerSwitch.isChecked();
            ManageSharePrefs.writeUserIsSeller(b);
        }

    };



}
