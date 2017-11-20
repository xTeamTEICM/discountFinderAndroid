package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import eu.jnksoftware.discountfinderandroid.Apis.RegisterApi;
import eu.jnksoftware.discountfinderandroid.R;

public class Register extends Activity {

    private String errorType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.registerBtn);
        
        register.setOnClickListener(registerBtnClick);
    }

    public final String getFirstName() {
        final EditText firstName =  findViewById(R.id.firstNameField);
        return firstName.getText().toString();
    }

    public final String getLastName() {
        final EditText lastName =  findViewById(R.id.lastNameField);
        return lastName.getText().toString();
    }

    public final String getEmail() {
        final EditText email =  findViewById(R.id.eMailField);
        return email.getText().toString();
    }
    
    public final String getPassword() {
        final EditText password =  findViewById(R.id.passwordField);
        return password.getText().toString();
    }
    
    private void checkMailValidation(CharSequence target) {
        if (target == null || !Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            errorType = "invalid email";
        }
    }

    private void checkMissedInfos() {
        if (getFirstName().isEmpty() || getLastName().isEmpty() || getEmail().isEmpty() || getPassword().isEmpty()) {
            errorType = "blank fields";
        }
    }
    
    private void showErrorType() {
        if (errorType.equals("blank fields")) {
            new AlertDialog.Builder(Register.this).setTitle("Invalid informations").setMessage("Please fill all the informations required").setNeutralButton("Close", null).show();
        } else if (errorType.equals("invalid email")) {
            new AlertDialog.Builder(Register.this).setTitle("Invalid email").setMessage("Please enter a valid email").setNeutralButton("Close", null).show();
        }
        errorType = "";
    }
  
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View reg) {
            checkMailValidation(getEmail());
            checkMissedInfos();
            if (errorType.equals("")) {
                Map<String, String> params = new HashMap<>();
                params.put("firstName", getFirstName());
                params.put("lastName", getLastName());
                params.put("eMail", getEmail());
                params.put("password", getPassword());

                JSONObject parameters = new JSONObject(params);

                RegisterApi registerApi= new RegisterApi();
                registerApi.doRegister(Register.this,parameters);

                finish();
            } else showErrorType();
        }
    };
   
}