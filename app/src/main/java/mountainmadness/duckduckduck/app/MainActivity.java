package mountainmadness.duckduckduck.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import mountainmadness.duckduckduck.R;

public class MainActivity extends AppCompatActivity {
    private TypedArray defaultImg;
    private TypedArray altImg;
    private ImageView image;
    private TextView text;
    private Random rand;
    private int randomIndex;
    private int primary;
    private int alt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultImg = getResources().obtainTypedArray(R.array.defaultimg);
        altImg = getResources().obtainTypedArray(R.array.altimg);

        rand = new Random();
        randomIndex = rand.nextInt(defaultImg.length());
        setImages(randomIndex);

        image = findViewById(R.id.main_image);
        text = findViewById(R.id.title_text);
        image.setImageResource(primary);
        text.setText("Tap the duck to duck the duck!");
        setUpImage();
        setUpRefreshButton();
    }

    private void setImages(int index){
        primary = defaultImg.getResourceId(index, 0);
        alt = altImg.getResourceId(index, 0);
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
            text.setText("Tap the duck to duck the duck!");
            image.setImageResource(primary);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpImage(){
        image.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                // set alt image here
                text.setText("Duck!");
                image.setImageResource(alt);
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                // Set default img here
                text.setText("Not Ducked!");
                image.setImageResource(primary);
            }
            return true;
        });

    }
}