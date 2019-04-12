package com.cao.nang.duan.fitnessapi;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.base.Base;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class StepCounter_activity extends Base {

    public static final String TAG = "BasicHistoryApi";
    private static final int REQUEST_OAUTH_REQUEST_CODE = 1;
    private static final int REQUEST_OAUTH_REQUEST_CODEA = 0x1001;
    private TextView tvSobuoc;
    private TextView tinhkm;
    private TextView tinhcalo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_step_counter);
        tinhkm = (TextView) findViewById(R.id.tinhkm);
        tinhcalo = (TextView) findViewById(R.id.tinhcalo);
        tvSobuoc = (TextView) findViewById(R.id.tvSobuoc);
        fit();

   //readHistoryData();



    }
    //đọc ghi dữ liệu mới
    private void insertAndReadData() {
        insertData()
                .continueWithTask(
                        new Continuation<Void, Task<DataReadResponse>>() {
                            @Override
                            public Task<DataReadResponse> then(@NonNull Task<Void> task) throws Exception {
                                return readHistoryData();
                            }
                        });
    }

    /** tạo {@link DataSet} và thêm vào google fit Api. */
    private Task<Void> insertData() {
        //tạo và chèn dữ liệu mới
        DataSet dataSet = insertFitnessData();

        //  gọi History API và chèn dữ liệu.
        Log.i(TAG, "Inserting the dataset in the History API.");
        return Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .insertData(dataSet)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Log.i(TAG, "thêm thành công!");
                                } else {
                                    Log.e(TAG, "thêm thất bại.", task.getException());
                                }
                            }
                        });
    }

    /**
     * thành công thì sẽ in ra dữ liệu
     */
    private Task<DataReadResponse> readHistoryData() {
        DataReadRequest readRequest = queryFitnessData();
        //đọc dữ liệu
        return Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readData(readRequest)
                .addOnSuccessListener(
                        new OnSuccessListener<DataReadResponse>() {
                            @Override
                            public void onSuccess(DataReadResponse dataReadResponse) {

                                printData(dataReadResponse);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "There was a problem reading the data,có vấn đề đọc.", e);
                            }
                        });
    }

    /**
     * tạo và trả về {@link DataSet} của đếm bước cho History API.
     */
    private DataSet insertFitnessData() {
        Log.i(TAG, "Creating a new data insert request.");

///tạo thời gian bắt đầu và kết thúc
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        long startTime = cal.getTimeInMillis();


        DataSource dataSource =
                new DataSource.Builder()
                        .setAppPackageName(this)
                        .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
                        .setStreamName(TAG + " - step count")
                        .setType(DataSource.TYPE_RAW)
                        .build();

        // Create a data set
        int stepCountDelta = 1;
        DataSet dataSet = DataSet.create(dataSource);
        //chỉ time start and end.
        DataPoint dataPoint =
                dataSet.createDataPoint().setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS);
        dataPoint.getValue(Field.FIELD_STEPS).setInt(stepCountDelta);
        dataSet.add(dataPoint);


        return dataSet;
    }

    /** trả về một {@link DataReadRequest} cho tất cả bước đếm trong ngày. */
    public  DataReadRequest queryFitnessData() {

        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        long startTime = cal.getTimeInMillis();

        java.text.DateFormat dateFormat = getDateInstance();
        Log.i(TAG, "Range Start: " + dateFormat.format(startTime));
        Log.i(TAG, "Range End: " + dateFormat.format(endTime));

        DataReadRequest readRequest =
                new DataReadRequest.Builder()

                        .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)

                        .bucketByTime(20, TimeUnit.HOURS)
                        .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                        .build();
        // trả về dữ liệu.

        return readRequest;
    }


     // nhận kết quả dữ liệu history trả về
    public void printData(DataReadResponse dataReadResult) {

        if (dataReadResult.getBuckets().size() > 0) {
            Log.i(
                    TAG, "Number of returned buckets of DataSets is: " + dataReadResult.getBuckets().size());
            for (Bucket bucket : dataReadResult.getBuckets()) {
                List<DataSet> dataSets = bucket.getDataSets();
                for (DataSet dataSet : dataSets) {
                    dumpDataSet(dataSet);
                }
            }
        } else if (dataReadResult.getDataSets().size() > 0) {
            Log.i(TAG, "Number of returned DataSets is: " + dataReadResult.getDataSets().size());
            for (DataSet dataSet : dataReadResult.getDataSets()) {
                dumpDataSet(dataSet);
            }
        }

    }

   // đọc dữ liệu history
    private  void dumpDataSet(DataSet dataSet) {
        Log.i(TAG, "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = getTimeInstance();

        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.i(TAG, "Data point:");
            Log.i(TAG, "\tType: " + dp.getDataType().getName());
            Log.i(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            Log.i(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for (Field field : dp.getDataType().getFields()) {
                Log.i(TAG, "\tField: " + field.getName() + " Value: " + dp.getValue(field));
                Value value=dp.getValue(field);
            tvSobuoc.setText(String.valueOf(value));
            float sobuoc=Float.parseFloat(tvSobuoc.getText().toString());
            float km= (float) (0.002*sobuoc);
          tinhkm.setText(String.valueOf(km+" Km"));
          int tinhkalo= (int) (km/1.6*100);
         tinhcalo.setText(String.valueOf(tinhkalo+" Calo"));

            }
        }
    }
    // [END parse_dataset]

    /**
     * xóa một {@link DataSet} dữ liệu history api.
     * trong 1 day.
     */
    private void deleteData() {
        Log.i(TAG, "Deleting today's step count data.");


        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        long startTime = cal.getTimeInMillis();
//tạo thời gian
        DataDeleteRequest request =
                new DataDeleteRequest.Builder()
                        .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
                        .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                        .build();

        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .deleteData(request)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Successfully deleted today's step count data.");
                                } else {
                                    Log.e(TAG, "Failed to delete today's step count data.", task.getException());
                                }
                            }
                        });
    }
    public void fit() {
        FitnessOptions fitnessOptions =
                FitnessOptions.builder()
                        .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                        .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                        .build();
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this,
                    REQUEST_OAUTH_REQUEST_CODEA,
                    GoogleSignIn.getLastSignedInAccount(this),
                    fitnessOptions);
        } else {
            subscribe();
        }
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == REQUEST_OAUTH_REQUEST_CODE) {
                    subscribe();
                }
            }
        }

    public void subscribe() {

        //đọc bước đi theo ngày
        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Successfully subscribed!");
                                } else {
                                    Log.w(TAG, "There was a problem subscribing.", task.getException());
                                }
                            }
                        });
        readData();
    }
    private void readData() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                long total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                                Log.i(TAG, "Total steps: " + total);
                                tvSobuoc.setText(String.valueOf(total));
                                float sobuoc= Float.parseFloat(tvSobuoc.getText().toString());
                                float km= (float) (0.0004*sobuoc);
                                DecimalFormat df = new DecimalFormat("0.00");

                                tinhkm.setText(String.valueOf( df.format(km)+" Km"));
                                int tinhkalo= (int) (km/1.6*100);
                                tinhcalo.setText(String.valueOf(tinhkalo+" Calo"));


                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "There was a problem getting the step count.", e);
                            }
                        });
    }




    public void tuannay(View view) {
        classintent(StepsCounterWeek_activity.class);

    }

    public void lammoingay(View view) {
        fit();
    }
}

