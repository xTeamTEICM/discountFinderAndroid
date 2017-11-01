package eu.jnksoftware.discountfinderandroid.ui.general;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.BuildConfig;
import eu.jnksoftware.discountfinderandroid.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutUs = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("Αυτο ειναι η Demo εκδοση !\n Ευχαριστουμε που την κατεβασατε ! \n " + "Τρεχουσα Εκδοση : " + BuildConfig.VERSION_NAME)
                .addGitHub("xTeamTEICM")
                .addFacebook("xTeamTEICM")
                .addPlayStore(BuildConfig.APPLICATION_ID)
                .addEmail("xteamteicm@gmail.com")
                .addGroup("Third Party Libraries")
                .addItem(usedLibraries())
                .addItem(createCopyright())
                .create();
        setContentView(aboutUs);
    }

    private Element usedLibraries() {
        Element library = new Element();
        final String libraryString = "com.github.medyo:android-about-page:1.1.1";
        library.setIcon(R.drawable.github);
        library.setTitle("Medyo About Page");
        library.setGravity(Gravity.START);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AboutUs.this, libraryString, Toast.LENGTH_SHORT).show();
            }
        });
        return library;
    }

    private Element createCopyright() {
        Element copyright = new Element();
        final String copyrightString = "Copyright 2017 by x-Team";
        copyright.setIcon(R.drawable.copyright);
        copyright.setTitle("Copyright Alright Reserved");
        copyright.setGravity(Gravity.START);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(AboutUs.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}
