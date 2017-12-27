package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.Apis.HttpCall;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.token.RegisterTokenRequest;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Activity {

    private EditText eMail;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    IuserService iuserService;
    HttpCall httpCall=new HttpCall();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.registerBtn);
        iuserService= ApiUtils.getUserService();
        register.setOnClickListener(registerBtnClick);
        eMail= findViewById(R.id.eMailField);
        firstName= findViewById(R.id.firstNameField);
        lastName= findViewById(R.id.lastNameField);
        password= findViewById(R.id.passwordField);
    }


    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View reg) {


                RegisterTokenRequest registerTokenRequest=new RegisterTokenRequest();
                registerTokenRequest.setEMail(eMail.getText().toString().trim());
                registerTokenRequest.setFirstName(firstName.getText().toString().trim());
                registerTokenRequest.setLastName(lastName.getText().toString().trim());
                registerTokenRequest.setPassword(password.getText().toString().trim());

                if(httpCall.doRegister(registerTokenRequest)!=true){
                    Toast.makeText(Register.this,"Success!!",Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(Register.this,Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Register.this,"ERROR!!",Toast.LENGTH_SHORT).show();

                }





        }
    };

   
}