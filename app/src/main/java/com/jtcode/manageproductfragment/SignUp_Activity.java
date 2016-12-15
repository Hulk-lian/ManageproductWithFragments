package com.jtcode.manageproductfragment;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.jtcode.manageproductfragment.interfaces.SignupPresenter;
import com.jtcode.manageproductfragment.presenter.SignupPresenterImpl;

public class SignUp_Activity extends AppCompatActivity implements SignupPresenter.View {

    RelativeLayout layout;
    RadioGroup mRg;
    EditText mEtEmpresa;
    Spinner mSpnProvincia;
    Spinner mSpnLocalidad;
    TextInputLayout mTilMail;
    TextInputLayout mTilUsername;
    TextInputLayout mTilPassword;

    private AdapterView.OnItemSelectedListener mSpinnerListener;

    private SignupPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mRg = (RadioGroup)findViewById(R.id.rg_generos);
        mEtEmpresa = (EditText)findViewById(R.id.editText);
        mSpnProvincia = (Spinner)findViewById(R.id.spn_provincia);
        mSpnLocalidad = (Spinner)findViewById(R.id.spn_localidad);
        mTilMail = (TextInputLayout)findViewById(R.id.til_email);
        mTilUsername = (TextInputLayout)findViewById(R.id.til_username);
        mTilPassword = (TextInputLayout)findViewById(R.id.til_password2);
        layout=(RelativeLayout)findViewById(R.id.activity_sign_up);

        presenter= new SignupPresenterImpl(this);

        //muesta el nombre de la empresa si se checkea el radio button de empresa
        //tipo de cliente
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_Empresa)
                    mEtEmpresa.setVisibility(View.VISIBLE);
                else
                    mEtEmpresa.setVisibility(View.GONE);
            }
        });

        loadSpinnerProvincia();
    }

    private void loadSpinnerProvincia(){


        mSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner sp = (Spinner) view.getParent();
                switch (sp.getId()){
                    case R.id.spn_provincia:
                        cargarLocalidad(mSpnProvincia.getSelectedItemPosition());
                        break;
                    case R.id.spn_localidad:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        //Inicializar provincias
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, R.layout.support_simple_spinner_dropdown_item);
        mSpnProvincia.setOnItemSelectedListener(mSpinnerListener);
        mSpnProvincia.setAdapter(adapter);
        }

    private void cargarLocalidad(int pos){
        TypedArray aLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] localidades = aLocalidades.getTextArray(pos);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(SignUp_Activity.this, android.R.layout.simple_spinner_dropdown_item, localidades);
        mSpnLocalidad.setAdapter(adapter);
    }


    public void registrarse(View v){
        //recoger los datos de la vista y llama al metodo del presentador
       presenter.validateCredentials(mTilUsername.getEditText().getText().toString(),mTilPassword.getEditText().getText().toString(),mTilMail.getEditText().getText().toString());

       /* switch (validate()){
            case 0:
                Toast.makeText(this, "Registro OK", Toast.LENGTH_LONG).show();
                break;
            case 1:
                mTilUsername.setError("User not valid");
                break;
            case 2:
                mTilPassword.setError("Password not valid");
                break;
            case 3:
                mTilMail.setError("Mail not valid");
                break;
        }*/
    }

    //metodo que muestra la localidad
    public void verLocalidad(){
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.double_message,
                mSpnProvincia.getSelectedItem(),
                mSpnLocalidad.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
    }

        /**
         * Metodo que muesta un mensaje de error segun el error que ha ocurrido en la validacion de los datos
         * @param nameResource El string del mensaje de error a mostar
         * utiliza {@link android.content.res.Resources#getIdentifier(String,String,String)} para obtener el id en la clase R
         * @param idview el id de la vista donde se mostrará el error
         * */
        public void setMessageError(String nameResource, int idview) {

            String messageError = getResources().getString(getResources().getIdentifier(nameResource, "string", getPackageName()));

            switch (idview){
                case R.id.til_user: //Incorrect user case
                    //mTilUser.setError(error);
                    Snackbar.make(layout,messageError, Snackbar.LENGTH_SHORT).show();
                    break;
                case R.id.til_password: //Incorrect Password Case
                    //mTilPass.setError(messageError);
                    Snackbar.make(layout,messageError, Snackbar.LENGTH_SHORT).show();
                    break;
                case 0: //Correct Login
                    Snackbar.make(layout,messageError, Snackbar.LENGTH_SHORT).show();
                    break;
            }

    }

    @Override
    public void startActivity() {
        Intent intent= new Intent(SignUp_Activity.this,ListProductFragment.class);
        startActivity(intent);
    }
}
