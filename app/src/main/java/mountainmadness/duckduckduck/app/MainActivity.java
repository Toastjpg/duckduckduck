package mountainmadness.duckduckduck.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
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
    private int primary;
    private int alt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultImg = getResources().obtainTypedArray(R.array.defaultimg);
        altImg = getResources().obtainTypedArray(R.array.altimg);

        rand = new Random();
        int randomIndex = rand.nextInt(defaultImg.length());


        primary = defaultImg.getResourceId(randomIndex, 0);
        alt = altImg.getResourceId(randomIndex, 0);
        image = findViewById(R.id.main_image);
        text = findViewById(R.id.title_text);
        setUpViews();
        setUpButton();
    }

    // Initialize data model object here
    // Grab a image and file and set it to the object
    private void setUpViews(){
        image.setImageResource(primary);
        text.setText("QUACK QUACK QUACK");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpButton(){
        image.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                // set alt image here
                text.setText("QUACK QUACK QUACK");
                image.setImageResource(alt);
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                // Set default img here
                text.setText("UNQUACK UNQUACK UNQUACK");
                image.setImageResource(primary);
            }
            return true;
        });

    }
}