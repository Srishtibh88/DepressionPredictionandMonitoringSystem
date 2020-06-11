package com.ashish.emotionoptimizer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface API {

    String BASE_URL = "http://192.168.43.164:5000";

    @FormUrlEncoded
    @POST("prediction")
    Call<List<PatientHistory>> getDepression(@Field("gender") String gender,
                                             @Field("age") String age,
                                             @Field("maritalstatus") String marital,
                                             @Field("noofchild") String child,
                                             @Field("edu") String edu,
                                             @Field("totalmembers") String members,
                                             @Field("savings") String savings,
                                             @Field("alcohol") String alcohol,
                                             @Field("tobacco") String tobacco,
                                             @Field("medicalexp") String medicalexp,
                                             @Field("educationexp") String educationexp,
                                             @Field("socialexp") String socialexp,
                                             @Field("otherexp") String otherexp,
                                             @Field("income") String income,
                                             @Field("proportionofsick") String proportionofsick,
                                             @Field("childsick") String childsick,
                                             @Field("investment") String investment);


}
