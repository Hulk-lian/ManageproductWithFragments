package com.jtcode.manageproductfragment.presenter;

import android.content.Context;
import android.util.Patterns;

import com.jtcode.manageproductfragment.Model.Error;
import com.jtcode.manageproductfragment.R;
import com.jtcode.manageproductfragment.interfaces.IValidateAccount;
import com.jtcode.manageproductfragment.utils.ErrorMapUtils;

public class LoginPresenterImpl implements IValidateAccount.IPresenter{

    private  IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenterImpl(IValidateAccount.View view){
        this.view = view;
        this.context = (Context)view;
    }


    public void validateCredentialsLogin(String user, String password){

        //validateUser = IValidateAccount.IPresenter.validateUser(user);
       // validatePassword = IValidateAccount.IPresenter.validatePassword(Password);

        if(validateUser(user)==Error.OK/*validateUser == Error.OK*/) {
            if (validatePassword(password)==Error.OK/*validatePassword == Error.OK*/) {
                /*
                    Por logica los startActivity siempre en las activitys, si no queremos abrirlo
                    desde las activitys los hacemos con view.startActivity
                 */

                //se puede utilizar la llamada al metodo start activity con un intent como parametro
                //y no tener que implementar el metodo starActivity en la vista
                //porque llama al metodo super.starActivity.
                view.startActivity();
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
    public int validateEmail(String email){
        int result = 0;

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            result = Error.INVALID_MAIL;

        return 0;
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

}
