package com.jsw.manageproductrecycler;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.interfaces.ILogin;
import com.jsw.manageproductrecycler.interfaces.IValidateAccount;

public class Login_Activity extends AppCompatActivity implements IValidateAccount.View {

    //region vbles
    private LoginPresenter mLoginMVP;
    private EditText mETPassword;
    private EditText mETUser;
    private TextInputLayout mTilPass, mTilUser;
    private TextView mForget;
    private Button mBtnLogin;
    private ViewGroup layout;
    private final String TAG ="manageProduct";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //region Instances
        mLoginMVP = new LoginPresenter(this); //Al presentador se le referencia la vista porque tiene conexion directa con ella.
        mETUser =  (EditText) findViewById(R.id.et_user);
        mETPassword =  (EditText) findViewById(R.id.et_passwd);
        mTilUser = (TextInputLayout)findViewById(R.id.til_user);
        mTilPass = (TextInputLayout)findViewById(R.id.til_password);
        mBtnLogin = (Button) findViewById(R.id.bt_login);
        mForget = (TextView)findViewById(R.id.tv_forgot);
        layout = (RelativeLayout)findViewById(R.id.activity_relative_layout_);
        Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf");
        mForget.setTypeface(font);
        //endregion

        //region Anonim Class
        mETUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO: Implement
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTilUser.setError(null); //Reiniciamos el error a null cuando se cambie el texto.
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mETPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTilPass.setError(null);  //Reiniciamos el error a null cuando se cambie el texto.
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginMVP.validateCredentialsLogin(mETUser.getText().toString(), mETPassword.getText().toString());
            }
        });

        //endregion

    }

    /**
     * Show to the user the errors when the user o the password doesn't complete mimimun requeriments.
      * @param nameResource String Error
     * @param View User/Password error.
     */
    public void setMessageError(String nameResource, int View) {

        String messageError = getResources().getString(getResources().getIdentifier(nameResource, "String", getPackageName()));

        switch (View){
            case R.id.til_user: //Incorrect user case
                //mTilUser.setError(error);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.til_password: //Incorrect Password Case
                //mTilPass.setError(messageError);
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            case 0: //Correct Login
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                logear();
                break;
        }
    }

    /**
     * Open ManageProduct activity to show products when the login is correct.
     */
    private void logear(){
        Intent intent = new Intent(this, ManageProduct_Activity.class);
        startActivity(intent);
        finish();

    }

    public void signUp(View v){
        Intent i = new Intent(this, SignUp_Activity.class);
        startActivity(i);
        finish();
    }


    public void startActivity(){
        Intent intent= new Intent(Login_Activity.this, ManageProduct_Activity.class);
        startActivity(intent);
    }
}