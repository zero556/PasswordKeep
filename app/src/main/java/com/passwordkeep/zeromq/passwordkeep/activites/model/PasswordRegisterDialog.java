

package com.passwordkeep.zeromq.passwordkeep.activites.model;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.LoginActivity;


/**
 *
 * Create custom Dialog windows for your application
 * Custom dialogs rely on custom layouts wich allow you to
 * create and use your own look & feel.
 *
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 *
 * <a href="http://my.oschina.net/arthor" target="_blank" rel="nofollow">@author</a> antoine vianey
 *
 */
public class PasswordRegisterDialog extends Dialog {

    public PasswordRegisterDialog(Context context, int theme) {
        super(context, theme);
    }

    public PasswordRegisterDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder  {

        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private EditText pass1,pass2,pass3,pass4;
        private EditText passc1,passc2,passc3,passc4;
        private EditText userNmae;
        private DialogInterface.OnClickListener positiveButtonClickListener, negativeButtonClickListener;

        private Button positiveButton;
        private String inputPassword ;
        private String inputPasswordConfirm ;
        private String inputUserName;




        public String getInputPassword() {
            return inputPassword;
        }

        public String getInputPasswordConfirm() {
            return inputPasswordConfirm;
        }

        public String getInputUserName() {
            return inputUserName;
        }

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         * @param title
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog.
         * If a message is set, the contentView is not
         * added to the Dialog...
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public PasswordRegisterDialog create() {


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final PasswordRegisterDialog dialog = new PasswordRegisterDialog(context, R.style.Custom_Dialog);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.password_register, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // set the dialog title
            //((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {


                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }

            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            // set the content message
            if (message != null) {
              //  ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
               // ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                //((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }


            pass1 = (EditText) layout.findViewById(R.id.editPasswordText1);
            pass2 = (EditText) layout.findViewById(R.id.editPasswordText2);
            pass3 = (EditText) layout.findViewById(R.id.editPasswordText3);
            pass4 = (EditText) layout.findViewById(R.id.editPasswordText4);
            passc1 = (EditText) layout.findViewById(R.id.editPasswordConfirmText1);
            passc2 = (EditText) layout.findViewById(R.id.editPasswordConfirmText2);
            passc3 = (EditText) layout.findViewById(R.id.editPasswordConfirmText3);
            passc4 = (EditText) layout.findViewById(R.id.editPasswordConfirmText4);


            userNmae=(EditText) layout.findViewById(R.id.editUsername);
            positiveButton=(Button) layout.findViewById(R.id.positiveButton);

            pass1.addTextChangedListener(textWatcher);
            pass2.addTextChangedListener(textWatcher);
            pass3.addTextChangedListener(textWatcher);
            pass4.addTextChangedListener(textWatcher);

            passc1.addTextChangedListener(textWatcher);
            passc2.addTextChangedListener(textWatcher);
            passc3.addTextChangedListener(textWatcher);
            passc4.addTextChangedListener(textWatcher);

            dialog.setContentView(layout);
            return dialog;
        }



        private TextWatcher textWatcher = new TextWatcher() {

            // @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            // @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Log.w("Log", "----" + s);

            }

            // @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    if (pass1.isFocused()) {
                        pass1.clearFocus();
                        pass2.requestFocus();
                    } else if (pass2.isFocused()) {
                        pass2.clearFocus();
                        pass3.requestFocus();
                    } else if (pass3.isFocused()) {
                        pass3.clearFocus();
                        pass4.requestFocus();
                    } else if (pass4.isFocused()) {
                        pass4.clearFocus();
                        passc1.requestFocus();
                    }else if (passc1.isFocused()) {
                        passc1.clearFocus();
                        passc2.requestFocus();
                    }else if (passc2.isFocused()) {
                        passc2.clearFocus();
                        passc3.requestFocus();
                    }else if (passc3.isFocused()) {
                        passc3.clearFocus();
                        passc4.requestFocus();
                    }else if (passc4.isFocused()) {
                        passc4.clearFocus();
                        if (!TextUtils.isEmpty(userNmae.getText())) {
                            inputPassword = pass1.getText().toString() + pass2.getText() + pass3.getText() + pass4.getText();
                            inputPasswordConfirm = passc1.getText().toString() + passc2.getText() + passc3.getText() + passc4.getText();
                            inputUserName = userNmae.getText().toString();
                            if (inputPassword.length() < 4) {

                            } else {
                                if (inputPasswordConfirm.length() < 4) {

                                } else {
                                    if (!inputPassword.equals(inputPasswordConfirm)) {

                                        Toast.makeText(context, "wrong password",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(passc4.getWindowToken(), 0) ;
                                    }
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(context, "please input user name",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{

                    if(pass1.isFocused())
                    {
                        if(!TextUtils.isEmpty(pass1.getText()))
                        {
                            pass1.setText("");
                        }

                    }
                    else if (pass2.isFocused()) {
                        if(TextUtils.isEmpty(pass2.getText()))
                        {
                            pass2.clearFocus();
                            pass1.requestFocus();
                        }
                        else
                        {
                            pass2.setText("");
                        }

                    } else if (pass3.isFocused()) {
                        if(TextUtils.isEmpty(pass3.getText()))
                        {
                            pass3.clearFocus();
                            pass2.requestFocus();
                        }
                        else
                        {
                            pass3.setText("");
                        }

                    } else if (pass4.isFocused()) {
                        if(TextUtils.isEmpty(pass4.getText()))
                        {
                            pass4.clearFocus();
                            pass3.requestFocus();
                        }
                        else
                        {
                            pass4.setText("");
                        }

                    }
                    else if (passc1.isFocused()) {
                        if(TextUtils.isEmpty(passc1.getText()))
                        {
                            passc1.clearFocus();
                            pass4.requestFocus();
                        }
                        else
                        {
                            passc1.setText("");
                        }

                    }else if (passc2.isFocused()) {
                        if(TextUtils.isEmpty(passc2.getText()))
                        {
                            passc2.clearFocus();
                            passc1.requestFocus();
                        }
                        else
                        {
                            passc2.setText("");
                        }

                    }else if (passc3.isFocused()) {
                        if(TextUtils.isEmpty(passc3.getText()))
                        {
                            passc3.clearFocus();
                            passc2.requestFocus();
                        }
                        else
                        {
                            passc3.setText("");
                        }

                    }else if (passc4.isFocused()) {
                        if(TextUtils.isEmpty(passc4.getText()))
                        {
                            passc4.clearFocus();
                            passc3.requestFocus();
                        }
                        else
                        {
                            passc4.setText("");
                        }

                    }


                }


            }



        };


    }

}
