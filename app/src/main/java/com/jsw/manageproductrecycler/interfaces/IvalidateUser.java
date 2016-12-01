package com.jsw.manageproductrecycler.interfaces;

import android.util.Patterns;

import com.jsw.manageproductrecycler.Model.Error;



public interface IvalidateUser extends IValidateAccount {

    interface Presenter {
    int validateCredentialsEmail(String email);

        //<editor-fold desc="Description">
       /* static int validateEmail(String email){
            int result = 0;

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = Error.INVALID_MAIL;

            return 0;
        }*/
        //</editor-fold>
    }
}
