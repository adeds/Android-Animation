package com.noes.adeds.androanim;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.ViewFlipper;

import com.noes.adeds.androanim.databinding.ActivityRegisterBinding;


public class Register extends AppCompatActivity implements View.OnClickListener, RegisterPresent {
    ActivityRegisterBinding binding2;
    int vHot, vSalt, vSweet;
    private ViewFlipper simpleViewSwitcher;
    private EditText inputName, inputEmail, inputPassword, inputPhone, inputRepassword, inputAddress;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutPhone, inputLayoutRepassword, inputLayoutAddress;
    private SeekBar sbHot, sbSweet, sbSalty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding2 = DataBindingUtil.setContentView(this, R.layout.activity_register);
        initUI();


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonNext) {
            if (!validateEmail()) {
                return;
            }
            if (!validatePhone()) {
                return;
            }
            simpleViewSwitcher.showNext();
        } else if (id == R.id.buttonNext1) {
            if (!validateName()) {
                return;
            }

            if (!validatePassword()) {
                return;
            }
            if (!validateRepassword()) {
                return;
            }
            if (!validateAddress()) {
                return;
            }

            simpleViewSwitcher.showNext();
        } else if (id == R.id.buttonNext2) {
            Intent intent = new Intent(Register.this, MainActivity.class);
            intent.putExtra("word",result());
            startActivity(intent);
//            Toast.makeText(this, result(), Toast.LENGTH_SHORT).show();

        }
    }
public String result () {
        String word;
                word =
                        inputEmail.getText()+"\n"+
                        inputName.getText()+"\n"+
                        inputPassword.getText()+"\n"+
                        inputRepassword.getText()+"\n"+
                        inputPhone.getText()+"\n"+
                        inputAddress.getText()+"\n"+
                                "level hot = "+vHot+
                                "\nlevel salt = "+vSalt+
                                "\nlevel sweet = "+vSweet
                                ;
        return word;
    }
    @Override
    public void initUI() {
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutRepassword = (TextInputLayout) findViewById(R.id.input_layout_repassword);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        inputLayoutAddress = (TextInputLayout) findViewById(R.id.input_layout_address);
        sbHot = (SeekBar) findViewById(R.id.seekBarHot);
        sbSalty = (SeekBar) findViewById(R.id.seekBarSalty);
        sbSweet = (SeekBar) findViewById(R.id.seekBarSweet);

        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputRepassword = (EditText) findViewById(R.id.input_repassword);
        inputAddress = (EditText) findViewById(R.id.input_address);


        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputRepassword.addTextChangedListener(new MyTextWatcher(inputRepassword));
        inputAddress.addTextChangedListener(new MyTextWatcher(inputAddress));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));


        simpleViewSwitcher = (ViewFlipper) findViewById(R.id.simpleViewSwitcher);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);


        simpleViewSwitcher.setInAnimation(in);
        simpleViewSwitcher.setOutAnimation(out);
        binding2.buttonNext.setOnClickListener(this);
        binding2.buttonNext1.setOnClickListener(this);
        binding2.buttonNext2.setOnClickListener(this);
        sbHot.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress = i;
                changeThumb(i, sbHot, "hot");

              vHot = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });//1
        sbSalty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress = i;
                changeThumb(i, sbSalty, "salty");
                vSalt = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });//2
        sbSweet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress = i;
                changeThumb(i, sbSweet, "sweet");
               vSweet = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });//4

    }


    public void onBackPressed() {

        if (simpleViewSwitcher.getDisplayedChild() == 2) {
            simpleViewSwitcher.setDisplayedChild(1);
        } else if (simpleViewSwitcher.getDisplayedChild() == 1) {
            simpleViewSwitcher.setDisplayedChild(0);
        } else if (simpleViewSwitcher.getDisplayedChild() == 0) {
            finish();
        }
    }

    private void changeThumb(int prog, SeekBar seek, String imej) {

        int id;
        try {

            if (prog <= 5) {

                seek.setThumb(getResources().getDrawable(R.drawable.base));
            } else if (prog <= 10) {
                id = getResources().getIdentifier(imej + "_one", "drawable", Register.this.getPackageName());
                Drawable draw = Register.this.getResources().getDrawable(id);
                seek.setThumb(draw);
            } else if (prog <= 15) {
                id = getResources().getIdentifier(imej + "_two", "drawable", Register.this.getPackageName());
                Drawable draw = Register.this.getResources().getDrawable(id);
                seek.setThumb(draw);
            } else {
                id = getResources().getIdentifier(imej + "_three", "drawable", Register.this.getPackageName());
                Drawable draw = Register.this.getResources().getDrawable(id);
                seek.setThumb(draw);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private boolean validateName() {


        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);

            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {


        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRepassword() {
        String pass = inputRepassword.getText().toString().trim();

        if (pass.isEmpty() || !inputPassword.getText().toString().trim().equals(pass)) {

            inputLayoutRepassword.setError(getString(R.string.err_msg_repassword));
            requestFocus(inputRepassword);
            return false;
        } else {
            inputLayoutRepassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();

        if (!isValidPhone(phone) || phone.isEmpty()) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));

            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {
        if (inputAddress.getText().toString().trim().isEmpty()) {
            inputLayoutAddress.setError(getString(R.string.err_msg_address));
            requestFocus(inputAddress);
            return false;
        } else {
            inputLayoutAddress.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_repassword:
                    validateRepassword();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
                case R.id.input_address:
                    validateAddress();
                    break;
            }
        }
    }

}


