package com.nexaas.nexaasid.auth.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nexaas.nexaasid.auth.api.OAuthTokenRequest;
import com.nexaas.nexaasid.auth.api.ProfileRequest;
import com.nexaas.nexaasid.auth.auth.AuthConfig;
import com.nexaas.nexaasid.auth.callback.ResponseCallback;
import com.nexaas.nexaasid.auth.helper.Environment;
import com.nexaas.nexaasid.auth.model.OAuthTokenResponse;
import com.nexaas.nexaasid.auth.model.PersonalInfo;
import com.nexaas.nexaasid.auth.sample.manager.SessionManager;
import com.nexaas.nexaasid.auth.sample.models.NexaasIdUser;
import com.nexaas.nexaasid.auth.sample.models.UserProfile;

public class MainActivity extends AppCompatActivity {

    // These three consts must be taken in your application created in NexaasID dashboard
    private static final String CLIENT_ID = "[client id]";
    private static final String CLIENT_SECRET = "[client secret]";
    private static final String REDIRECT_URI_SCHEME = "app://";
    private static final String REDIRECT_URI = "[redirect uri (with no scheme prefix)]";

    private Button authenticationButton;
    private ProgressDialog progressDialog;
    private NexaasIdUser nexaasAuthorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onActivityCreated();
        checkSession();
    }

    private void checkSession() {
        nexaasAuthorization = SessionManager.getNexaasAccessAuthorization(this);

        if (nexaasAuthorization != null) {
            authenticationButton.setVisibility(View.GONE);

            UserProfile prof = SessionManager.getUserProfile(this);

            if (prof != null)
                setProfile(prof);
            else {
                getProfile();
            }
        }
    }

    private void getProfile() {
        progressDialog.setTitle(getString(R.string.recovering_user_data));
        progressDialog.show();

        ProfileRequest.getPersonalInfo(
                nexaasAuthorization.getAccessToken(),
                Environment.SANDBOX,
                onGetProfileListener());
    }

    private void onActivityCreated() {
        authenticationButton = findViewById(R.id.athentication_button);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.loading_auth));
        progressDialog.setMessage(getString(R.string.waiting));
    }

    public void onLoginButtonClick(View view) {
        Intent authIntent = AuthConfig.authorize(this, CLIENT_ID, REDIRECT_URI_SCHEME, REDIRECT_URI, Environment.SANDBOX);
        startActivityForResult(authIntent, 100);
    }

    private void setProfile(UserProfile profile) {
        SessionManager.storeUserProfile(this, profile);
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    private ResponseCallback<OAuthTokenResponse> oauthTokenListener() {
        return new ResponseCallback<OAuthTokenResponse>() {
            @Override
            public void onSuccess(OAuthTokenResponse tokenResponse) {
                progressDialog.dismiss();

                authenticationButton.setVisibility(View.GONE);

                nexaasAuthorization = new NexaasIdUser(
                        tokenResponse.getAccessToken(),
                        tokenResponse.getTokenType(),
                        tokenResponse.getRefreshToken(),
                        tokenResponse.getScope(),
                        tokenResponse.getExpiresIn(),
                        tokenResponse.getApiToken());

                SessionManager.storeUserTokens(MainActivity.this, nexaasAuthorization);

                getProfile();
            }

            @Override
            public void onFailure(@NonNull String error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        };
    }

    private ResponseCallback<PersonalInfo> onGetProfileListener() {
        return new ResponseCallback<PersonalInfo>() {
            @Override
            public void onSuccess(PersonalInfo personalInfo) {
                progressDialog.dismiss();
                setProfile(new UserProfile(
                    personalInfo.getId(),
                    personalInfo.getFullName(),
                    personalInfo.getNickname(),
                    personalInfo.getEmail(),
                    personalInfo.getBirth(),
                    personalInfo.getGender(),
                    personalInfo.getLanguage(),
                    personalInfo.getPicture(),
                    personalInfo.getTimezone(),
                    personalInfo.getCountry(),
                    personalInfo.getCity(),
                    personalInfo.getBio()
                ));
            }

            @Override
            public void onFailure(@NonNull String error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            if (data != null) {
                if (resultCode != Activity.RESULT_CANCELED) {

                    String authCode = AuthConfig.getAuthorizationCode(data);
                    String codeVerifier = AuthConfig.getCodeVerifier(data);
                    if (authCode != null && codeVerifier != null) {
                        com.nexaas.nexaasid.auth.model.OAuthTokenRequest token =
                                AuthConfig.requestToken(
                                        CLIENT_ID,
                                        CLIENT_SECRET,
                                        REDIRECT_URI_SCHEME + REDIRECT_URI,
                                        authCode,
                                        codeVerifier);

                        OAuthTokenRequest.getOAuthTokenResponse(token, Environment.SANDBOX,
                                oauthTokenListener());
                    }
                } else
                    Toast.makeText(this, R.string.auth_canceled, Toast.LENGTH_SHORT).show();
            }
        }
    }
}