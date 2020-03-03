package com.eziride.driverRide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Step2Register extends AppCompatActivity {

    //permission access
    //private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;


    EditText brand_name,veh_number,model,year,coloret;
    String sbrand_name,sveh_number,smodel,syear,scolor;
    Button submit,photo,bikephoto,dlfront,dlback,motoinsurance,certreg;
    TextView phototv,bikephototv,dlfronttv,dlbacktv,motoinsurancetv,certregtv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2_register);

        brand_name=findViewById(R.id.step2_brand_et);
        veh_number=findViewById(R.id.step2_vnumber_et);
        model=findViewById(R.id.step2_model_et);
        year=findViewById(R.id.step2_year_et);
        coloret=findViewById(R.id.step2_color_et);


        //getbutton
        bikephoto=findViewById(R.id.step2_bikephoto_btn);
        submit=findViewById(R.id.step2_sub_btn);
        photo=findViewById(R.id.step2_pass_btn);
        dlfront=findViewById(R.id.step2_dlfront_btn);
        dlback=findViewById(R.id.step2_dlback_btn);
        motoinsurance=findViewById(R.id.step2_motorInsurance_btn);
        certreg=findViewById(R.id.step2_certoreg_btn);
        //getText
        bikephototv=findViewById(R.id.step2_bikephoto_tv);
        phototv=findViewById(R.id.step2_bikephoto_tv);
        dlfronttv=findViewById(R.id.step2_dlfront_tv);
        dlbacktv=findViewById(R.id.step2_dlback_tv);
        motoinsurancetv=findViewById(R.id.step2_motorInsurance_tv);
        certregtv=findViewById(R.id.step2_certoreg_tv);


        bikephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent,1);


            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent,2);




            }
        });

        dlfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent,3);



            }
        });
        dlback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent,4);




            }
        });
        motoinsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent,5);





            }
        });
        certreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("*/*");
                startActivityForResult(intent,6);




            }
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    startActivity(new Intent(getApplicationContext(),Skip.class));
                    //geVehicleData();
                }
                catch (Exception ex)
                {

                }


            }
        });





    }

    private void checkPermission(String permission, int requestCode) {

        if(ContextCompat.checkSelfPermission(Step2Register.this,permission)== PackageManager.PERMISSION_DENIED)
        {
            //req the permission
            ActivityCompat.requestPermissions(Step2Register.this,new String[]{ permission },requestCode);
        }
        else{
            Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if(requestCode== STORAGE_PERMISSION_CODE)
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(this, "Permission Denied ", Toast.LENGTH_SHORT).show();
                }

            }
       }

    public void getVehicleData(){

        sbrand_name=brand_name.getText().toString();
        sveh_number=veh_number.getText().toString();
        smodel=model.getText().toString();
        syear=year.getText().toString();
        scolor=coloret.getText().toString();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode)
        {
            case 1:

                if(resultCode==RESULT_OK)
                {
                    String path=data.getData().getPath();
                    bikephototv.setText(path);

                }

            case 2:

                if(resultCode==RESULT_OK)
                {
                    String path=data.getData().getPath();
                    phototv.setText(path);

                }
            case 3:

                if(resultCode==RESULT_OK)
                {
                    String path=data.getData().getPath();
                    dlfronttv.setText(path);

                }

            case 4:

                if(resultCode==RESULT_OK)
                {
                    String path=data.getData().getPath();
                    dlbacktv.setText(path);

                }

            case 5:

                if(resultCode==RESULT_OK)
                {
                    String path=data.getData().getPath();
                    motoinsurancetv.setText(path);

                }




            case 6:

                if(resultCode==RESULT_OK)
                {
                    String path=data.getData().getPath();
                    certregtv.setText(path);

                }









        }

    }
}
