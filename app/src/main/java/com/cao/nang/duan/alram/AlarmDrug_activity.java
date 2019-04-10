package com.cao.nang.duan.alram;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.cao.nang.duan.R;

public class AlarmDrug_activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {
    SeekBar seekBarinterval,seekBarvol;
    Spinner spinner ;
    int gap,vol,maxVal;
    private Spinner hours, min;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_alarm_drug);
        seekBarinterval=(SeekBar)findViewById(R.id.interval);
        seekBarinterval.setOnSeekBarChangeListener(this);
        seekBarvol=(SeekBar)findViewById(R.id.vol);
        seekBarvol.setOnSeekBarChangeListener(this);
        spinner= (Spinner) findViewById(R.id.count);
        spinner.setOnItemSelectedListener(this);

        hours = (Spinner) findViewById(R.id.hours);
        min = (Spinner) findViewById(R.id.minutes);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.no_of_count, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterHours = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours.setAdapter(adapterHours);
        ArrayAdapter<CharSequence> adapterMin = ArrayAdapter.createFromResource(this,
                R.array.min, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        min.setAdapter(adapterMin);


        audioManager =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        maxVal = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        if(seekBar.equals(seekBarinterval))
            gap=progress;
        else
            vol=progress;
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(seekBar.equals(seekBarinterval))
            Toast.makeText(getApplicationContext(),"thời gian báo "+gap+" phút", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Volume : "+(int)((vol*maxVal)/40), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setAlarm(View view)
    {
        int hourss = Integer.parseInt(hours.getSelectedItem().toString());

        int mins = Integer.parseInt(min.getSelectedItem().toString());
        Switch vibrate = (Switch)findViewById(R.id.vibrate);
        boolean v = vibrate.isChecked();
        EditText editText3 = (EditText)findViewById(R.id.label);
        String label = editText3.getText().toString();
        int count = Integer.parseInt(spinner.getSelectedItem().toString());

        int volume = (vol*maxVal)/40;
        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume,1);

        for(int i=1;i<=count;i++) {

            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, label+"\nbáo lần : "+i)
                    .putExtra(AlarmClock.EXTRA_HOUR, hourss)
                    .putExtra(AlarmClock.EXTRA_MINUTES, mins)
                    .putExtra(AlarmClock.EXTRA_VIBRATE,v)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            mins=mins+gap;
            if(mins>=60)
            {
                hourss++;
                mins=mins-60;
            }
        }
        finish();
    }

}
