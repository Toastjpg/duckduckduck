package mountainmadness.duckduckduck.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import mountainmadness.duckduckduck.R;

public class MainActivity extends AppCompatActivity {
    public static final String DEFAULT_TITLE_TEXT = "Tap the duck to duck the duck!";
    private TypedArray defaultImg;
    private TypedArray altImg;
    private ImageView image;
    private TextView text;
    private Random rand;
    private Handler handler;
    private MediaPlayer mpduck;
    private MediaPlayer mpalt;
    private boolean clickable = true;
    private int randomIndex;
    private int primary;
    private int alt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultImg = getResources().obtainTypedArray(R.array.defaultimg);
        altImg = getResources().obtainTypedArray(R.array.altimg);
        mpduck = MediaPlayer.create(this, R.raw.quack_sound);
        mpalt = MediaPlayer.create(this, R.raw.cow_sound);

        handler = new Handler();

        rand = new Random();
        randomIndex = rand.nextInt(defaultImg.length());
        setImages(randomIndex);

        image = findViewById(R.id.main_image);
        text = findViewById(R.id.title_text);
        image.setImageResource(primary);
        text.setText(DEFAULT_TITLE_TEXT);
        setUpImage();
        setUpRefreshButton();
    }

    private void setUpRefreshButton() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            int temp = rand.nextInt(defaultImg.length());
            while(temp == randomIndex){
                temp = rand.nextInt(defaultImg.length());
            }
            randomIndex = temp;

            setImages(randomIndex);
            text.setText(DEFAULT_TITLE_TEXT);
            image.setImageResource(primary);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpImage(){
        image.setOnClickListener(view -> {
            if (clickable){
                // set alt image and play sound clip here
                clickable = false;
                image.setImageResource(alt);
                int random = rand.nextInt(10);

                if (random <= 2) {
                    text.setText("Duck??");
                    playSound(false);
                }
                else{
                    text.setText("Duck!!");
                    playSound(true);
                }

                handler.postDelayed(() -> {
                    // Do something after 5s = 5000ms
                    // Set default img here
                    text.setText(DEFAULT_TITLE_TEXT);
                    image.setImageResource(primary);
                    clickable = true;
                }, 1500);
            }

        });
    }

    private void playSound(boolean isDuck) {
        if ((isDuck)) {
            mpduck.start();
        } else {
            mpalt.start();
        }
    }

    private void setImages(int index){
        primary = defaultImg.getResourceId(index, 0);
        alt = altImg.getResourceId(index, 0);
    }
}