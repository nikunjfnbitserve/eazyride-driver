package com.eziride.driverRide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText mail,full_name,ph_number,e_contact,password,city,address,state;
    CheckBox privacy;
    Button register;

    String smail,sfull_name,sph_number,se_contact,spassword,scity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.reg_register_btn);

        mail=findViewById(R.id.reg_mail_et);
        full_name=findViewById(R.id.reg_name_et);
        ph_number=findViewById(R.id.reg_ph_et);
        e_contact=findViewById(R.id.reg_emergencycontact_et);
        password=findViewById(R.id.reg_pass_et);
        address=findViewById(R.id.reg_address_et);
        state=findViewById(R.id.reg_state_et);
        city=findViewById(R.id.reg_city_et);



        register.setEnabled(false);

        privacy=findViewById(R.id.reg_privacy_chk);

        privacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    register.setEnabled(true);
                }
                else{
                    register.setEnabled(false);
                }
            }
        });





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    getRegisterData();

                }
                catch (Exception ex)
                {

                }

            }
        });

    }
    public void getRegisterData()
    {



        smail=mail.getText().toString();
        sfull_name=full_name.getText().toString();
        sph_number=ph_number.getText().toString();
        se_contact=e_contact.getText().toString();
        spassword=password.getText().toString();
        scity=city.getText().toString();
        startActivity(new Intent(getApplicationContext(),Step2Register.class));

    }
}
