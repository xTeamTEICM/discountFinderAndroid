package eu.jnksoftware.discountfinderandroid;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;



import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutUs = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.icon)
                .setDescription("This is a demo version \n Thank you for downloading our App \n "+"Currnet Version :"+BuildConfig.VERSION_NAME)
                .addGitHub("xTeamTEICM")
                .addFacebook("x-Team")
                .addPlayStore("Discount Finder")
                .addItem(createCopyright())
                .create();
        setContentView(aboutUs);
    }

    private Element createCopyright() {
        Element copyright = new Element();
        final String copyrightString = "Copyright 2017 by x-Team";
        copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(AboutUs.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}
