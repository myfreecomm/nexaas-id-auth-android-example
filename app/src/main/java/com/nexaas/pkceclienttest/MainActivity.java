package com.nexaas.pkceclienttest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nexaas.pkceclienttest.interfaces.ResponseListener;
import com.nexaas.pkceclienttest.manager.OauthManager;
import com.nexaas.pkceclienttest.manager.ProfileManager;
import com.nexaas.pkceclienttest.manager.SessionManager;
import com.nexaas.pkceclienttest.models.NexaasIdUser;
import com.nexaas.pkceclienttest.models.OauthToken;
import com.nexaas.pkceclienttest.models.UserProfile;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

public class MainActivity extends AppCompatActivity {

    public static final String API_OAUTH = "oauth/authorize";
    public static final String API_TOKEN = "oauth/token";
    private static final String CLIENT_ID = "JDBY5VIH55F3ZGDG2Y3WWCWNAE";
    private static final String CLIENT_SECRET = "2D7ZAIXJUJHHDHSZUQBIHAZUOU";
    private static final String REDIRECT_URI = "app://com.nexaas.pw2oauthtest.browserswitch/callback";

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
        progressDialog.setTitle("Recuperando dados do usuário");
        progressDialog.show();
        ProfileManager.getProfile(nexaasAuthorization.accessToken, onGetProfileListener());
    }

    private void onActivityCreated() {
        authenticationButton = findViewById(R.id.athentication_button);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Autenticando usuário");
        progressDialog.setMessage("aguarde...");
    }

    public void onLoginButtonClick(View view) {
        AuthorizationService authService = new AuthorizationService(this);
        Intent intent = authService.getAuthorizationRequestIntent(getAuthRequest());
        startActivityForResult(intent, 100);
    }

    private AuthorizationRequest getAuthRequest() {
        AuthorizationRequest.Builder requestBuilder =
                new AuthorizationRequest.Builder(
                        configureService(),
                        CLIENT_ID,
                        ResponseTypeValues.CODE,
                        Uri.parse(REDIRECT_URI));

        requestBuilder.setScope("profile");

        return requestBuilder.build();
    }

    private AuthorizationServiceConfiguration configureService() {
        return new AuthorizationServiceConfiguration(
                Uri.parse(RetrofitInstance.API_BASE_URL + API_OAUTH),
                Uri.parse(RetrofitInstance.API_BASE_URL + API_TOKEN));
    }

    private void requestToken(String authCode, String codeVerifier) {
        progressDialog.show();

        OauthToken oauthToken = new OauthToken();

        oauthToken.clientId = CLIENT_ID;
        oauthToken.clientSecret = CLIENT_SECRET;
        oauthToken.codeVerifier = codeVerifier;
        oauthToken.code = authCode;
        oauthToken.redirectUri = REDIRECT_URI;

        OauthManager.getUserCredentials(oauthToken, oauthTokenListener());
    }

    private void setProfile(UserProfile profile) {
        SessionManager.storeUserProfile(this, profile);
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    private ResponseListener<NexaasIdUser> oauthTokenListener() {
        return new ResponseListener<NexaasIdUser>() {
            @Override
            public void onSuccess(NexaasIdUser item) {
                progressDialog.dismiss();

                authenticationButton.setVisibility(View.GONE);
                nexaasAuthorization = item;

                SessionManager.storeUserTokens(MainActivity.this, nexaasAuthorization);

                getProfile();
            }

            @Override
            public void onFailure(String error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        };
    }

    private ResponseListener<UserProfile> onGetProfileListener() {
        return new ResponseListener<UserProfile>() {
            @Override
            public void onSuccess(UserProfile item) {
                progressDialog.dismiss();
                setProfile(item);
            }

            @Override
            public void onFailure(String error) {
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            if (data != null) {
                AuthorizationResponse authResponse = AuthorizationResponse.fromIntent(data);

                if (resultCode != Activity.RESULT_CANCELED) {
                    if (authResponse != null && authResponse.authorizationCode != null) {
                        requestToken(authResponse.authorizationCode, authResponse.request.codeVerifier);

                    }
                } else Toast.makeText(this, "Autenticação cancelada", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
