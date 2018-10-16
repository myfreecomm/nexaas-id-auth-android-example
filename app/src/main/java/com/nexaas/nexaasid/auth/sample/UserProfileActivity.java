package com.nexaas.nexaasid.auth.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.nexaas.nexaasid.auth.sample.manager.SessionManager;
import com.nexaas.nexaasid.auth.sample.models.UserProfile;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {

    private TextView nameText, emailText, uuidText, nicknameText, birthText, genderText, languageText,
            timezoneText, countryText, cityText, bioText;

    private CircularImageView picture;

    private UserProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setupToolbar();
        onActivityCreated();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.user_profile);
        }
    }

    private void onActivityCreated() {
        nameText = findViewById(R.id.user_name);
        emailText = findViewById(R.id.user_email);
        uuidText = findViewById(R.id.user_uuid);
        nicknameText = findViewById(R.id.user_nickname);
        birthText = findViewById(R.id.user_birth);
        genderText = findViewById(R.id.user_gender);
        languageText = findViewById(R.id.user_language);
        timezoneText = findViewById(R.id.user_timezone);
        countryText = findViewById(R.id.user_country);
        cityText = findViewById(R.id.user_city);
        bioText = findViewById(R.id.user_bio);

        picture = findViewById(R.id.user_picture);

        getProfile();
    }

    private void getProfile() {
        profile = SessionManager.getUserProfile(this);

        if (profile != null) {
            setProfile();
        }
    }

    private void setProfile() {
        if (profile.getPicture() != null)
            Picasso.get().load(profile.getPicture()).into(picture);

        nameText.setText(profile.getName());
        emailText.setText(profile.getEmail());
        uuidText.setText(profile.getUuid());
        nicknameText.setText(profile.getNickname());
        birthText.setText(profile.getBirth());
        genderText.setText(profile.getGender());
        languageText.setText(profile.getLanguage());
        timezoneText.setText(profile.getTimezone());
        countryText.setText(profile.getCountry());
        cityText.setText(profile.getCity());
        bioText.setText(profile.getBio());
    }
}