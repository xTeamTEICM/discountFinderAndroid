package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.RegisterApi;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Activity {

    private EditText eMail;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    IuserService iuserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.registerBtn);
        iuserService= ApiUtils.getUserService();
        register.setOnClickListener(registerBtnClick);
        eMail=(EditText)findViewById(R.id.eMailField);
        firstName=(EditText)findViewById(R.id.firstNameField);
        lastName=(EditText)findViewById(R.id.lastNameField);
        password=(EditText)findViewById(R.id.passwordField);
    }

  
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View reg) {


                RegisterTokenRequest registerTokenRequest=new RegisterTokenRequest();
                registerTokenRequest.setEMail(eMail.getText().toString().trim());
                registerTokenRequest.setFirstName(firstName.getText().toString().trim());
                registerTokenRequest.setLastName(lastName.getText().toString().trim());
                registerTokenRequest.setPassword(password.getText().toString().trim());
                doRegister(registerTokenRequest);





        }
    };
    public void doRegister(final RegisterTokenRequest registerTokenRequest){
        Call<UserTokenResponse> call=iuserService.register(registerTokenRequest);
        call.enqueue(new Callback<UserTokenResponse>() {
            @Override
            public void onResponse(Call<UserTokenResponse> call, Response<UserTokenResponse> response) {
                int statusCode=response.code();


                if(response.isSuccessful())
                {
                    UserTokenResponse userTokenResponse=response.body();

                    Log.d("Register","onResponse:"+statusCode);
                    Toast.makeText(Register.this,""+response.message(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,Login.class);
                    startActivity(intent);


                }
                else
                {
                    Toast.makeText(Register.this,""+response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserTokenResponse> call, Throwable t) {
                call.cancel();
                Log.d("MaincActivity","onFailure"+t.getMessage());
                Toast.makeText(Register.this,"Wrong!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
   
}