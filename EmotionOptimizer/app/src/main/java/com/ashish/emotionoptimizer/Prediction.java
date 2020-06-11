package com.ashish.emotionoptimizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Prediction extends AppCompatActivity {
    EditText ageAc,noOfChildAc,eduAc,totalMembersAc,savingsAc,medicalExpAc;
    EditText educationExpAc,socialExpAc,incomeAc,proportionOfSickAc,childSickAc,investmentAc;
    RadioButton maleAc,femaleAc,marriedAc,unMarriedAc,alYesAc,alNoAc,toYesAc,toNoAc;
    Button Submit;
    String gender,maritalStatus,alcohol,tobacco;
    String PredictionValue="nothing";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        initialize();
        Submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitData();
                    }
                }
        );

    }

    private void submitData() {
        if(!checkForEmpty()){
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_LONG).show();
        }
        else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API api= retrofit.create(API.class);

            Call<List<PatientHistory>> call = api.getDepression(gender,
                                                                ageAc.getText().toString(),
                                                                maritalStatus,
                                                                noOfChildAc.getText().toString(),
                                                                eduAc.getText().toString(),
                                                                totalMembersAc.getText().toString(),
                                                                savingsAc.getText().toString(),
                                                                alcohol,
                                                                tobacco,
                                                                medicalExpAc.getText().toString(),
                                                                educationExpAc.getText().toString(),
                                                                socialExpAc.getText().toString(),
                                                                "20",
                                                                incomeAc.getText().toString(),
                                                                proportionOfSickAc.getText().toString(),
                                                                childSickAc.getText().toString(),
                                                                investmentAc.getText().toString());

            call.enqueue(
                    new Callback<List<PatientHistory>>() {
                        @Override
                        public void onResponse(Call<List<PatientHistory>> call, Response<List<PatientHistory>> response) {
                            List<PatientHistory> x = response.body();

                            for(PatientHistory p:x){
                                PredictionValue = p.getPredictedDepression();
                            }
                            Log.d("PredictionValue",PredictionValue);
                            Intent i=new Intent(Prediction.this,Result.class);
                            i.putExtra("prediction",PredictionValue);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<List<PatientHistory>> call, Throwable t) {
                            Toast.makeText(Prediction.this,"No internet connection",Toast.LENGTH_LONG).show();

                        }
                    }
            );
        }
    }


    private boolean checkForEmpty() {
        if(ageAc.getText().toString().trim().equals("")) return false;
        if(incomeAc.getText().toString().trim().equals("")) return false;
        if(medicalExpAc.getText().toString().trim().equals("")) return false;
        if(socialExpAc.getText().toString().trim().equals("")) return false;
        if(educationExpAc.getText().toString().trim().equals("")) return false;
        if(eduAc.getText().toString().trim().equals("")) return false;
        if(noOfChildAc.getText().toString().trim().equals("")) return false;
        if(totalMembersAc.getText().toString().trim().equals("")) return false;
        if(savingsAc.getText().toString().trim().equals("")) return false;
        if(proportionOfSickAc.getText().toString().trim().equals("")) return false;
        if(childSickAc.getText().toString().trim().equals("")) return false;
        if(investmentAc.getText().toString().trim().equals("")) return false;
        if(!maleAc.isChecked() && !femaleAc.isChecked()) return false;
        if(!marriedAc.isChecked() && !unMarriedAc.isChecked()) return false;
        if(!alYesAc.isChecked() && !alNoAc.isChecked()) return false;
        if(!toYesAc.isChecked() && !toNoAc.isChecked()) return false;

        if(maleAc.isChecked()) gender = "0";
        else if(femaleAc.isChecked()) gender = "1";
        else ;

        if(marriedAc.isChecked()) maritalStatus = "1";
        else if(unMarriedAc.isChecked()) maritalStatus = "0";
        else ;

        if(alYesAc.isChecked()) alcohol = "1";
        else if(alNoAc.isChecked()) alcohol = "0";
        else ;

        if(toYesAc.isChecked()) tobacco = "1";
        else if(toNoAc.isChecked()) tobacco = "0";
        else ;
        return true;

    }

    private void initialize() {
        ageAc = findViewById(R.id.age);
        noOfChildAc = findViewById(R.id.noofchild);
        eduAc = findViewById(R.id.educatioyear);
        totalMembersAc = findViewById(R.id.familymembers);
        savingsAc = findViewById(R.id.savings);
        medicalExpAc = findViewById(R.id.medicalexp);
        educationExpAc = findViewById(R.id.educationexp);
        socialExpAc = findViewById(R.id.socialexp);
        incomeAc = findViewById(R.id.income);
        proportionOfSickAc = findViewById(R.id.peoplesick);
        childSickAc = findViewById(R.id.childsick);
        investmentAc = findViewById(R.id.investment);
        maleAc = findViewById(R.id.male);
        femaleAc = findViewById(R.id.female);
        marriedAc = findViewById(R.id.married);
        unMarriedAc = findViewById(R.id.unmarried);
        alYesAc = findViewById(R.id.alyes);
        alNoAc = findViewById(R.id.alno);
        toYesAc = findViewById(R.id.Toyes);
        toNoAc = findViewById(R.id.Tono);
        Submit = findViewById(R.id.submit);
    }
}
