package com.example.marco.hw;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String color = "color";
    public static final int BUTTON_REQUEST = 1;
    private int current_color = -1;
    private int current_button = -1;

    private MediaPlayer backgroundPlayer;
    private boolean give_music = true;
    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sounds = new Uri[1];
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ringd);
        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent background = new Intent(getApplicationContext(),SecondActivity.class);
                background.putExtra(color,current_color);
                startActivityForResult(background, BUTTON_REQUEST);
                current_button = 0;
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent background = new Intent(getApplicationContext(),SecondActivity.class);
                background.putExtra(color,current_color);
                startActivityForResult(background, BUTTON_REQUEST);
                current_button = 1;
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent background = new Intent(getApplicationContext(),SecondActivity.class);
                background.putExtra(color,current_color);
                startActivityForResult(background, BUTTON_REQUEST);
                current_button = 2;
            }
        });

      FloatingActionButton music = (FloatingActionButton) findViewById(R.id.floatingActionButton);
      music.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(give_music){
                  onPause();
                  give_music = false;
              }
              else{
                  backgroundPlayer.start();
                  give_music = true;
              }
          }
      });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            if(requestCode == BUTTON_REQUEST)
            {
                Button b1 = (Button) findViewById(R.id.button1);
                Button b2 = (Button) findViewById(R.id.button2);
                Button b3 = (Button) findViewById(R.id.button3);
                View view = this.getWindow().getDecorView();
                current_color = data.getIntExtra(color,0);

                switch (current_button){
                    case 0: view.setBackgroundColor(current_color);break;
                    case 1: b1.setTextColor(current_color); b2.setTextColor(current_color); b3.setTextColor(current_color);break;
                    case 2: b1.setBackgroundColor(current_color);b2.setBackgroundColor(current_color);b3.setBackgroundColor(current_color);break;
                }
            }
        }
        else if(resultCode == RESULT_CANCELED){
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//Send the background player to the paused state
        backgroundPlayer.pause();
        buttonPlayer.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
//Create and prepare MediaPlayer with R.raw.mario as the data stream
//source
        backgroundPlayer = MediaPlayer.create(this, R.raw.mario);
//Define a procedure that will be executed when the MediaPlayer goes to
//the prepared state
        backgroundPlayer.setOnPreparedListener(new
                                                       MediaPlayer.OnPreparedListener() {
                                                           @Override
                                                           public void onPrepared(MediaPlayer mp) {
//Set the looping parameter to true
                                                               mp.setLooping(true);
//Start the playback.
//By placing the start method in the onPrepared event
//we are always certain that the audio stream is prepared.
                                                               mp.start();
                                                           }
                                                       });
    }
    @Override
    protected void onStop(){
        super.onStop();
//Release the background player when we don’t need it.
        backgroundPlayer.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
