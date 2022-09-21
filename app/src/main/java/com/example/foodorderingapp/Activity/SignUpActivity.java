package com.example.foodorderingapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.Data.UserDao;
import com.example.foodorderingapp.Data.UserRepository;
import com.example.foodorderingapp.Model.User;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Utilites.ApplicationClass;
import com.example.foodorderingapp.Utilites.DishDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SignUpActivity extends AppCompatActivity {
    private final int PICK_IMAGE_CODE = 78;
    public User user;
    String Filepath;
    ImageView profile;
    private TextView loginPage, tv_signUp;
    private EditText et_email, et_name, et_password;
    private ImageButton btn_upload;
    private Bitmap imgToStore;
    private Uri imageUri;

    public static void loadImageFromStorage(String path, ImageView view) {
        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            view.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginPage = findViewById(R.id.loginPage);
        et_email = findViewById(R.id.et_Email);
        et_name = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        tv_signUp = findViewById(R.id.tv_signUp);
        btn_upload = findViewById(R.id.img_upload);
        profile = findViewById(R.id.profile_img);


        UserRepository userRepository = new UserRepository(this.getApplication());
        loginPage.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_CODE);
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = et_email.getText().toString();
                String Name = et_name.getText().toString();
                String Password = et_password.getText().toString();

                Thread thread = new Thread(() -> {
                    user = new User(Email, Name, Password, Filepath);
                    UserDao userDao = DishDatabase.instance.userDao();
                    userDao.addUser(user);
                    user = userDao.getUser(Email, Password);
                    ApplicationClass.currentUser = user;
                    Intent intent = new Intent(SignUpActivity.this, HomePage.class);
                    startActivity(intent);
                });
                thread.start();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_CODE && resultCode == Activity.RESULT_OK) {
            if (data == null && data.getData() == null)
                return;
            imageUri = data.getData();
            try {
                imgToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Filepath = saveToInternalStorage(imgToStore);
                loadImageFromStorage(Filepath, profile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

}