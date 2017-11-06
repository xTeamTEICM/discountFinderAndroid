package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.R;

public class FindDiscount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_discount);

        TextView offersFound = (TextView) findViewById(R.id.tvFoundOffers);
        ListView lvOffers = (ListView) findViewById(R.id.lvOffers);
        List<String> offersList = new ArrayList<>();
        //Static Data..... Will change
        offersList.add("Παπούτσι Nike Mercurial "+"50 ευρώ");
        offersList.add("Παπούτσι Adidas Stan Smith "+"75 ευρώ");
        offersList.add("Γυαλιά Ηλίου RayBan Wayfarer "+"110 ευρώ");
        offersList.add("Γυναικεία τσάντα Versace "+"63 ευρώ");
        offersList.add("Ανδρική κολώνια Paco Rabanne "+"89 ευρώ");
        offersList.add("Ανδρική κολώνια Paco Rabanne "+"89 ευρώ");
        offersList.add("Ανδρική κολώνια Paco Rabanne "+"89 ευρώ");
        offersList.add("Ανδρική κολώνια Paco Rabanne "+"89 ευρώ");
        offersList.add("Ανδρική κολώνια Paco Rabanne "+"89 ευρώ");
        //End of Data

        String offers = "Βρέθηκαν " + offersList.size() + " προσφορές";
        offersFound.setText(offers);

        ArrayAdapter<String> offersArrayAdapter = new ArrayAdapter<>(this, R.layout.list_text_color, offersList);
        lvOffers.setAdapter(offersArrayAdapter);

    }
}
