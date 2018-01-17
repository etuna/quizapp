package com.tunahantuna.etuna.quizapp3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {

    ImageView profilePicture;
    TextView usernameTV, NSTV, emailTV;
    User user;
    String username;
    String name, surname, email, profilePictureURL;
    TextView ProfileEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        username = user.username;
        name = user.name;
        surname = user.surname;
        email = user.email;
        profilePictureURL = "https://www.drupal.org/files/profile_default.jpg";

        profilePicture =(ImageView) findViewById(R.id.profilePicProfileActivity);
        usernameTV =(TextView) findViewById(R.id.usernameProfileAct);
        NSTV = (TextView) findViewById(R.id.nameSurnameProfileAct);
        emailTV = (TextView) findViewById(R.id.emailProfileAct);
        ProfileEditButton = (TextView) findViewById(R.id.EditProfileButton);
        ProfileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileEditActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        usernameTV.setText(username);
        NSTV.setText(name+" "+surname);
        emailTV.setText(email);











        new ImageLoaderTask(profilePictureURL, profilePicture).execute();




    }




    public class ImageLoaderTask extends AsyncTask<Void, Void, Bitmap> {

        String url;
        ImageView iv;

        public ImageLoaderTask(String url, ImageView iv) {
            this.url = url;
            this.iv = iv;
        }


        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream is = connection.getInputStream();
                Bitmap bm = BitmapFactory.decodeStream(is);

                return bm;
            } catch (Exception e) {

            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            iv.setImageBitmap(result);
        }
    }
}
