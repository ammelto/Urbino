package io.seamoss.urbino.views.sign_in;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.seamoss.urbino.R;
import io.seamoss.urbino.base.BaseActivity;
import io.seamoss.urbino.views.home.HomeActivity;
import io.seamoss.urbino.views.nav.NavActivity;

public class SignInActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);

        prefs.edit().putInt("Number",6).apply();

        Log.d("DEBUG","onCreate value of number is" + prefs.getInt("Number", 0));

        Intent intent = new Intent(this, NavActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);

        finish();
    }

}
