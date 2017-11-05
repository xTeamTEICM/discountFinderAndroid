package eu.jnksoftware.discountfinderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FindDiscount extends AppCompatActivity {

    private ListView lvOffers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_discount);

        lvOffers = (ListView) findViewById(R.id.lvOffers);
        List<String> offersList = new ArrayList<>();
        //Static Data..... Will change
        offersList.add("Παπούτσι Nike Mercurial "+"50 ευρώ");
        offersList.add("Παπούτσι Adidas Stan Smith "+"75 ευρώ");
        offersList.add("Γυαλιά Ηλίου RayBan Wayfarer "+"110 ευρώ");
        offersList.add("Γυναικεία τσάντα Versace "+"63 ευρώ");
        offersList.add("Ανδρική κολώνια Paco Rabanne "+"89 ευρώ");
        //End of Data

        ArrayAdapter<String> offersArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,offersList);
        lvOffers.setAdapter(offersArrayAdapter);

    }
}
