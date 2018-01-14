package eu.jnksoftware.discountfinderandroid.ui.customer.shops;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountGet;
import eu.jnksoftware.discountfinderandroid.models.discounts.DiscountPost;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAddDiscount extends AppCompatActivity {
    private IuserService apiInterface;
    private String auth;
    private User user;
    private int shopId;
    private double startPrice;
    private double endPrice;
    private String desc;
    private int categoryId;
    private String image;
    private Button myDiscount;
    private Long   tsLong;
    private Button choosePhoto;
    private Bitmap discountPhoto;
    private ImageView discountImageView;
    private EditText startingPrice;
    private EditText finalPrice;
    private EditText description;
    private Spinner spinnerCat;
    private List<Category> categories = new ArrayList<>();
    private ArrayAdapter<String> spinContentAdapter;
    private List<String> catTemp = new ArrayList<>();
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_discount);

        apiInterface = ApiUtils.getUserService();
        user = ManageSharePrefs.readUser(null);
        auth = getIntent().getStringExtra("auth");

        shopId = getIntent().getIntExtra("shopId",-1);
        tsLong = System.currentTimeMillis()/1000;
        myDiscount = findViewById(R.id.addMyDiscountButton);
        myDiscount.setOnClickListener(myDiscountClick);
        choosePhoto = findViewById(R.id.choosePhotoButton);
        choosePhoto.setOnClickListener(choosePhotoClick);
        startingPrice = findViewById(R.id.etStartingPrice);
        finalPrice = findViewById(R.id.etFinalPrice);
        description = findViewById(R.id.etDescription);
        discountImageView = findViewById(R.id.newDiscountImage);
        spinnerCat = findViewById(R.id.categorySpin);
        spinContentAdapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1, catTemp);
        spinnerCat.getBackground().setAlpha(130);
        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);
        fetchCategories();
    }

    private View.OnClickListener myDiscountClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DiscountPost discountPost=new DiscountPost();
            image=imageToString();

            discountPost.setImageBase(image);
            discountPost.setImageTitle(tsLong.toString());
            discountPost.setShopId(shopId);
            discountPost.setCurrentPrice(Double.parseDouble(finalPrice.getText().toString().trim()));
            discountPost.setOriginalPrice(Double.parseDouble(startingPrice.getText().toString().trim()));
            discountPost.setDescription(description.getText().toString());
            discountPost.setCategory(Integer.parseInt(categories.get((int) spinnerCat.getSelectedItemId()).getId()));
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

    public void fetchCategories() {
        Call<List<Category>> call = apiInterface.fetchCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                List<String> temp = new ArrayList<>();
                for(int i=0;i<categories.size();i++){
                    temp.add(categories.get(i).getTitle());
                }
                catTemp.addAll(temp);
                spinContentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(SellerAddDiscount.this, "Connection error!", Toast.LENGTH_SHORT).show();
            }

        });
    }


}
