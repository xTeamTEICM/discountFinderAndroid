package eu.jnksoftware.discountfinderandroid.ui.seller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import eu.jnksoftware.discountfinderandroid.R;

public class AddOffersActivity extends AppCompatActivity {

    private static final int TAKE_PIC=100;
    private static final int LOAD_PIC=200;
    private Button takePhotoBtn;
    private Button loadPhotoBtn;
    private ImageView sellersOfferImageView;
    private EditText descriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offers);

        takePhotoBtn =(Button)findViewById(R.id.takePhotoButton);
        loadPhotoBtn =(Button)findViewById(R.id.loadPhotoButton);
        sellersOfferImageView =(ImageView)findViewById(R.id.sellersOfferView);
        descriptionText= (EditText)findViewById(R.id.decriptionText);


        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        loadPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPicture();
            }
        });

    }


    private void takePicture() {
        Intent camera=new Intent();
        camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,TAKE_PIC);
    }


    private void loadPicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_PIC);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==TAKE_PIC && resultCode == RESULT_OK){
            Bundle b=data.getExtras();
            Bitmap img=(Bitmap)b.get("data");
            sellersOfferImageView.setImageBitmap(img);
        }

        if (requestCode==LOAD_PIC && resultCode == RESULT_OK){

            Picasso.with(AddOffersActivity.this).load(data.getData()).noPlaceholder().centerCrop().fit().into(sellersOfferImageView);
        }


    }
}


