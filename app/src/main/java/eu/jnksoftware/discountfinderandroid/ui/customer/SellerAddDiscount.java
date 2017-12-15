package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.Apis.RestClient;
import eu.jnksoftware.discountfinderandroid.Apis.ShopsApiInterface;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.DiscountPost;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddDiscount extends AppCompatActivity {
    ShopsApiInterface apiInterface;
    String auth;
    double startingPrice;
    double finalPrice;
    String description;
    int categoryId;
    Bitmap bitmap;
    String image;
    ImageView img;



    private static final int IMG_REQUEST=777;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_discount);
         img =findViewById(R.id.newDiscountImage);

        Button imagebtn=findViewById(R.id.choosePhotoButton);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();

            }
        });




        Button myDiscount = findViewById(R.id.addMyDiscountButton);
        myDiscount.setOnClickListener(myDiscountClick);


        apiInterface = RestClient.getClient().create(ShopsApiInterface.class);
        auth = getIntent().getStringExtra("auth");
    }

    private View.OnClickListener myDiscountClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDiscountValues();
            addDiscount();
        }
    };

    public void addDiscount(){

        DiscountPost discountPost = new DiscountPost(categoryId,startingPrice,finalPrice,description,image);
        Call<DiscountGet> call = apiInterface.addDiscount(discountPost,auth);
        call.enqueue(new Callback<DiscountGet>() {
            @Override
            public void onResponse(Call<DiscountGet> call, Response<DiscountGet> response) {
                Toast.makeText(SellerAddDiscount.this, response.message() + "\nDiscount Added", Toast.LENGTH_SHORT).show();
                if(response.message().equals("OK")) finish();
            }

            @Override
            public void onFailure(Call<DiscountGet> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }


    private void selectImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK&&data!=null)
        {
            Uri path=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);



    }
    public void getDiscountValues(){
        EditText startingPrice = findViewById(R.id.etStartingPrice);
        EditText finalPrice = findViewById(R.id.etFinalPrice);
        EditText description = findViewById(R.id.etDescription);
        ImageView img =findViewById(R.id.newDiscountImage);


        this.startingPrice = Double.parseDouble(startingPrice.getText().toString());
        this.finalPrice = Double.parseDouble(finalPrice.getText().toString());
        this.description = description.getText().toString();
        this.categoryId = 1;
        this.image=imageToString();


    }
}
