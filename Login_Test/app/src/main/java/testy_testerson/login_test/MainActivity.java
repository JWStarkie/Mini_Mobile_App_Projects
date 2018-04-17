package testy_testerson.login_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;
    private TextView tvEmail, tvUsername, tvFirst, tvLast;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new UserSession(this);
        userInfo = new UserInfo(this);
        logoutButton = (Button) findViewById(R.id.logOutButton);
        tvEmail = (TextView) findViewById(R.id.textViewEmail);
        tvUsername = (TextView) findViewById(R.id.textViewUsername);
        tvFirst = (TextView) findViewById(R.id.textViewFirstName);
        tvLast = (TextView) findViewById(R.id.textViewLast);

        if (!session.isUserLoggedin()) {
            startActivity(new Intent(this, Login.class));
            finish();
        }

        String username = userInfo.getKeyUsername();
        String email = userInfo.getKeyUsername();
        String firstN = userInfo.getKeyFirstName();
        String lastI = userInfo.getKeyLastInitial();

        tvUsername.setText(username);
        tvEmail.setText(email);
        tvFirst.setText(firstN);
        tvLast.setText(lastI);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                session.setLoggedin(false);
                userInfo.clearUserInfo();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });
    }
}
