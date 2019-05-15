package Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.naomi.nasatoday.R;

import Fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    private EditText Email, Password;
    private FirebaseAuth auth;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() !=null){
            HomeFragment frag = new HomeFragment();
            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.list, frag);
            transaction.commit();
        }
        setContentView(R.layout.activity_home);
        Email = (EditText) findViewById(R.id.txtEmail);
        Password = (EditText) findViewById(R.id.txtPwd);
        Login = (Button) findViewById(R.id.btnLogin);
        TextView register = (TextView)findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                Intent intent = new Intent(HomeActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                final String password = Password.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) { if (password.length() < 6) {
                            Password.setError(getString(R.string.invalid_password));
                        } else {
                            Toast.makeText(HomeActivity.this, getString(R.string.login_failed), Toast.LENGTH_LONG).show();
                        }
                        } else {
                            HomeFragment frag = new HomeFragment();
                            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.list, frag);
                            transaction.commit();
                        }
                    }
                });
            }
        });
    }
}
