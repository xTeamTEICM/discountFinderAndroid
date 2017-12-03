package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.LoginApi;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.UserTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends Activity {
    private EditText eMail;
    private EditText password;
    IuserService iuserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eMail=(EditText) findViewById(R.id.loginEMailField);
        password=(EditText)findViewById(R.id.loginPasswordField);
        iuserService= ApiUtils.getUserService();


        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(loginBtnClick);

        TextView registerView = findViewById(R.id.loginRegisterBtn);
        registerView.setOnClickListener(registerBtnClick);
    }

    private final View.OnClickListener loginBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View loginView) {
            UserTokenRequest userTokenRequest=new UserTokenRequest();
            userTokenRequest.setUsername(eMail.getText().toString().trim());
            userTokenRequest.setPassword(password.getText().toString().trim());
            doLogin(userTokenRequest);





        }
    };
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View registerView) {
            Login.this.startActivity(new Intent(Login.this, Register.class));
        }
    };
    public void doLogin(final UserTokenRequest userTokenRequest){
        Call<UserTokenResponse> call=iuserService.getTokenAcess(userTokenRequest);
        call.enqueue(new Callback<UserTokenResponse>() {
            @Override
            public void onResponse(Call<UserTokenResponse> call, Response<UserTokenResponse> response) {
                int statusCode=response.code();


                if(response.isSuccessful())
                {
                    UserTokenResponse userTokenResponse=response.body();

                    Log.d("MainActivity","onResponse:"+statusCode);
                    Toast.makeText(Login.this,""+response.message(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,MenuCustomer.class);
                    intent.putExtra("username",userTokenRequest.getUsername().toString());
                    intent.putExtra("password",userTokenRequest.getPassword().toString());
                    startActivity(intent);


                }
                else
                {
                    Toast.makeText(Login.this,""+response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserTokenResponse> call, Throwable t) {
                call.cancel();
                Log.d("MaincActivity","onFailure"+t.getMessage());
                Toast.makeText(Login.this,"Wrong!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

