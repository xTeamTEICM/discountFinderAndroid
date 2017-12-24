package eu.jnksoftware.discountfinderandroid.ui.customer.shops;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountPost;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddDiscount extends AppCompatActivity {
    private IuserService apiInterface;
    private String auth;
    private int shopId;
    private double startPrice;
    private double endPrice;
    private String desc;
    private int categoryId;
    private String image;
    private Button myDiscount;
    private Button choosePhoto;
    private Bitmap discountPhoto;
    private ImageView discountImageView;
    private EditText startingPrice;
    private EditText finalPrice;
    private EditText description;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_discount);

        apiInterface = ApiUtils.getUserService();
        auth = getIntent().getStringExtra("auth");
        shopId = getIntent().getIntExtra("shopId",-1);

        myDiscount = findViewById(R.id.addMyDiscountButton);
        myDiscount.setOnClickListener(myDiscountClick);
        choosePhoto = findViewById(R.id.choosePhotoButton);
        choosePhoto.setOnClickListener(choosePhotoClick);
        startingPrice = findViewById(R.id.etStartingPrice);
        finalPrice = findViewById(R.id.etFinalPrice);
        description = findViewById(R.id.etDescription);
        discountImageView = findViewById(R.id.newDiscountImage);
    }

    private View.OnClickListener myDiscountClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DiscountPost discountPost=new DiscountPost();
            image=imageToString();
            discountPost.setImageBase(image);
            discountPost.setImageTitle("TestPhoto1");
            discountPost.setShopId(shopId);
            discountPost.setCurrentPrice(Double.parseDouble(finalPrice.getText().toString().trim()));
            discountPost.setOriginalPrice(Double.parseDouble(startingPrice.getText().toString().trim()));
            discountPost.setDescription(description.getText().toString());
            discountPost.setCategory(2);
            addDiscount(discountPost);
        }
    };

    private View.OnClickListener choosePhotoClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent showGallery = new Intent();
            showGallery.setType("image/*");
            showGallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(showGallery,"Select discount picture"),PICK_IMAGE_REQUEST);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            Uri path=data.getData();
            try {
                discountPhoto= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                discountImageView.setImageBitmap(discountPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addDiscount(DiscountPost discountPost){
        Call<DiscountGet> call = apiInterface.addDiscount(discountPost,auth);
        call.enqueue(new Callback<DiscountGet>() {
            @Override
            public void onResponse(Call<DiscountGet> call, Response<DiscountGet> response) {
                if(response.message().equals("OK")) finish();
            }

            @Override
            public void onFailure(Call<DiscountGet> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    public String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        discountPhoto.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] imgByte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }


}
