package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePreference extends AppCompatActivity {

    UserTokenResponse userTokenResponse;
    IuserService iuserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_preference);
        final Gson user = new Gson();
        userTokenResponse = user.fromJson(getIntent().getStringExtra("User"), UserTokenResponse.class);
        iuserService= ApiUtils.getUserService();


        Button deleteButton=findViewById(R.id.dltBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String auth;
                EditText id=findViewById(R.id.idTxt);
                auth="Bearer "+userTokenResponse.getAccessToken();
                int idpref;
                String s1;
                s1=id.getText().toString();
                idpref=Integer.parseInt(s1);
                deletePref(idpref,auth);


            }
        });
    }
    public void deletePref(final int id, String auth)
    {
        Call<Void>delete=iuserService.deleteDiscountPreference(id,auth);
        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(DeletePreference.this,"You Preference with "+id+" remove Succesfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
