package com.wkdgusdn3.soundcontroller2;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.wkdgusdn3.service.SoundService;

public class MainActivity extends Activity {

    Button button_apply;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wkdgusdn3.soundcontroller2.R.layout.activity_main);

        setVariable();
        setView();
        setClickListener();

        startService();
    }

    void setVariable() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
    }

    void setView() {
        button_apply = (Button) findViewById(R.id.main_apply);
    }

    void setClickListener() {

        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
    }

    void startService() {
        Intent soundServiceIntent = new Intent(getApplicationContext(), SoundService.class);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(3);
        stopService(soundServiceIntent);

        startService(soundServiceIntent);
    }
}
