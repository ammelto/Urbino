package io.seamoss.urbino.views.onboarding.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Status;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.seamoss.urbino.R;
import io.seamoss.urbino.Urbino;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.base.BaseFragment;
import io.seamoss.urbino.views.home.HomeActivity;
import timber.log.Timber;

/**
 * Created by Alexander Melton on 2/17/2017.
 */

public class SigninActivity extends BaseActivity implements SigninView {

    @Inject
    SigninPresenter signinPresenter;

    @BindView(R.id.authentication_google_sign_in)
    SignInButton signInButton;

    private int RC_SIGN_IN = 9001;

    private GoogleApiClient googleApiClient;

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Urbino.instance().getAppGraph().inject(this);
        signinPresenter.attachView(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Signing in");
        progressDialog.setMessage("This will only take a moment.");
        progressDialog.setCancelable(false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this::onFailApiClientFail)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this::onSignInClick);

        OptionalPendingResult<GoogleSignInResult> pendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(pendingResult.isDone()){
            progressDialog.show();
            GoogleSignInResult result = pendingResult.get();
            if(result.isSuccess()) signinPresenter.onSignInSuccess(result.getSignInAccount());
            else onGoogleSigninFail(result.getStatus());
        }

    }

    public void onSignInClick(View v){
        Timber.d("Signing in");
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Timber.d("Activity Result");
        if(requestCode == RC_SIGN_IN){
            progressDialog.show();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()) signinPresenter.onSignInSuccess(result.getSignInAccount());
            else onGoogleSigninFail(result.getStatus());
        }
    }

    public void onFailApiClientFail(ConnectionResult connectionResult){
        Timber.d("Failed" + connectionResult.getErrorMessage());
    }

    public void onGoogleSigninFail(Status status){
        Timber.e("Failed google sign in" + status.getStatusMessage());
    }

    @Override
    public void launchHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        progressDialog.dismiss();
        startActivity(intent);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_signin;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signinPresenter.detachView();
    }
}
