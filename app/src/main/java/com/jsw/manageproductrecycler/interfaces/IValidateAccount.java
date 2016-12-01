package com.jsw.manageproductrecycler.interfaces;

import android.content.Context;
import android.content.Intent;

import com.jsw.manageproductrecycler.Model.Error;
import com.jsw.manageproductrecycler.R;

import java.util.regex.Pattern;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateAccount {

    interface View{
         void setMessageError(String error, int errCode);
         void startActivity();
    }

     interface IPresenter{
        //ILogin.View mView;
        Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
        Pattern p2 = Pattern.compile("[A-Z]");
        Pattern p3 = Pattern.compile("[0-9]");


         //<editor-fold desc="Cosas raras de java 1.8.lepra">
         /*static int validateUser(String User) {
            int idOut = 0;

            if(User.isEmpty()) {//If User is null
                idOut = Error.DATAEMPTY;
            }

            return idOut;
        }

        static int validatePassword(String Password) {
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
        }*/
         //</editor-fold>
    }


}
