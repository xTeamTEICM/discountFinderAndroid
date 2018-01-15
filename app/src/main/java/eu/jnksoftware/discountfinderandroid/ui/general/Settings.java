package eu.jnksoftware.discountfinderandroid.ui.general;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;

public class Settings extends AppCompatActivity {

    private Switch isSellerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);
        super.onCreate(savedInstanceState);

        Button backButton = findViewById(R.id.settingsBackButton);
        backButton.setOnClickListener(backButtonClick);
        isSellerSwitch = findViewById(R.id.switchSettingsIsSeller);
        isSellerSwitch.setChecked(ManageSharePrefs.readUserIsSeller(false));
    }

    View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ManageSharePrefs.writeUserIsSeller(isSellerSwitch.isChecked());
            Toast.makeText(getBaseContext(), "You have to restart the application for changes to be applied", Toast.LENGTH_LONG).show();
            finish();
        }

    };

}
