package com.jsw.manageproductrecycler.presenter;


import android.content.Context;
import android.util.Patterns;

import com.jsw.manageproductrecycler.Model.Error;
import com.jsw.manageproductrecycler.Prefs.AccountPrefsImpl;
import com.jsw.manageproductrecycler.R;
import com.jsw.manageproductrecycler.interfaces.IValidateAccount;
import com.jsw.manageproductrecycler.interfaces.SignupPresenter;
import com.jsw.manageproductrecycler.utils.ErrorMapUtils;

//                                  presenter del user        presenter del Ivalidateaccount
public class SignupPresenterImpl implements SignupPresenter.Presenter,SignupPresenter.IPresenter {

    private  IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private int validateEmail;
    private Context context;

    public SignupPresenterImpl(IValidateAccount.View view){
        this.view = view;
        this.context = (Context)view;
    }

    public void validateCredentials(String user,String pass,String email){

        validateUser =validateUser(user);
        validatePassword = validatePassword(pass);
        validateEmail=validateCredentialsEmail(email);

        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                if(validateEmail==Error.OK) {
                /*
                    Por logica los startActivity siempre en las activitys, si no queremos abrirlo
                    desde las activitys los hacemos con view.startActivity
                 */
                    savePreferences(user, pass, email);
                    //se puede utilizar la llamada al metodo start activity con un intent como parametro
                    //y no tener que implementar el metodo starActivity en la vista
                    //porque llama al metodo super.starActivity.
                    view.startActivity();
                }
                else{
                    String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateEmail));
                    view.setMessageError(nameIdMessage, R.id.til_email);
                }
            }
            else{
                String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.til_password);
            }
        }
        else{
            String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.til_user);
        }
    }

    private void savePreferences(String user,String pass,String email){
        AccountPrefsImpl accountPrefs= (AccountPrefsImpl) AccountPrefsImpl.getInstance(this.context);
        accountPrefs.putUser(user);
        accountPrefs.putPassword(pass);
        accountPrefs.putMail(email);
    }

    public  int validateUser(String User) {
        int idOut = 0;

        if(User.isEmpty()) {//If User is null
            idOut = Error.DATAEMPTY;
        }

        return idOut;
    }

    public int validatePassword(String Password) {
        int idOut = 0;

        if (Password.isEmpty())//If Password is null
            idOut = Error.DATAEMPTY;
        else if (!(Password.matches(".*" + p1 + ".*")))
            idOut = Error.PASSMINLENGTH;
        else if (!(Password.matches(".*" + p2 + ".*")))
            idOut = Error.PASSCASE;
        else if (((Password.matches(".*" + p3 + ".*"))))
            idOut = Error.PASSDIGIT;

        return idOut;
    }

    @Override
    public int validateCredentialsEmail(String email) {
        int result = 0;

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            result = Error.INVALID_MAIL;

        return 0;
    }
    /*
    private int validate(){
        String mail = mTilMail.getEditText().getText().toString();
        String username = mTilUsername.getEditText().getText().toString();
        String pass = mTilPassword.getEditText().getText().toString();

        Pattern name = Pattern.compile("[a-zA-Z0-9]{1,255}");
        Pattern password = Pattern.compile("[a-zA-Z0-9]{1,20}");

        int res = 0;

        if(Pattern.matches(name.toString(), username))
            if (Pattern.matches(password.toString(), pass))
                if (Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), mail))
                    res = 0;
                else
                    res = 3;
            else res = 2;
        else
            res = 1;

        return res;
    }*/

}
