# pkceclienttest

Application test to authenticate with OAuth2 in NexaasID

&nbsp;

This application uses the following external libraries:

* [Retrofit](https://square.github.io/retrofit/) (REST API);
* [LoganSquare](https://github.com/bluelinelabs/LoganSquare) (Mapping JSON);
* [AppAuth](https://github.com/openid/AppAuth-Android) (OAuth2);


&nbsp;

The app has only a simple button to ask for NexaasID authentication:

<img src="https://github.com/myfreecomm/nexaas-id-pkce-android-example/blob/master/main_screen.jpg" width="250" title="Main screen">

&nbsp;

By clicking on the button, the app calls a NexaasID webpage where the user will sign in. It is done like this:

```java
// Method called by clicking on button
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
```
&nbsp;

After user sign in, he will be redirect to the app again, with the AuthorizationResponse:
```java
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
```
&nbsp;

If the response is ok, then application will create a OAuthToken and request User credentials:
```java
private void requestToken(String authCode, String codeVerifier) {
    OauthToken oauthToken = new OauthToken();

    oauthToken.clientId = CLIENT_ID;
    oauthToken.clientSecret = CLIENT_SECRET;
    oauthToken.codeVerifier = codeVerifier;
    oauthToken.code = authCode;
    oauthToken.redirectUri = REDIRECT_URI;

    OauthManager.getUserCredentials(oauthToken, oauthTokenListener());
}
```
&nbsp;

if the response is successful then OAuthManager will return an `NexaasIDUser` object like this:
```java
public class NexaasIdUser {
    public String accessToken;
    public String tokenType;
    public String refreshToken;
    public String scope;
    public long expiresIn;

    public NexaasIdUser (){}
}
```

With this object the application can make requests for their features. :)

&nbsp;

&nbsp;

Obs.: `CLIENT_ID`, `CLIENT_SECRET` and `REDIRECT_URL` would be created when you create your application in NexaasID applications section.
