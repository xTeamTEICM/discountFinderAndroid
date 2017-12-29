package eu.jnksoftware.discountfinderandroid.ui.general;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import eu.jnksoftware.discountfinderandroid.R;

public class Settings extends Fragment {

    private Button backButton;
    private CheckBox sellerCheckBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(backButtonClick);

        boolean isEnabled = getIntent().getBooleanExtra("isSellerEnabled",false);
        if(isEnabled){
            sellerCheckBox.setChecked(true);
        }
        else{
            sellerCheckBox.setChecked(false);
        }*/
    }
//
//    private final View.OnClickListener backButtonClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    };
}
