package eecs.eecs1021.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

/**
 * The controller of the camera app.
 *
 * @author Franck van Breugel (Instructor of course EECS1022, Summer2017)
 */
public class Controller extends AppCompatActivity {
    /**
     * Path to the folder in which the photo is stored.
     */
    private static final String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/temp/";

    /**
     * Reference to the model of this app.
     */
    private Model image;

    /**
     * Initializes this controller.
     *
     * @param savedInstanceState not applicable.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_controller);

        // make xreflect button invisible
        Button xReflect = (Button) findViewById(R.id.reflectYBtn);
        xReflect.setVisibility(View.GONE);

        // create a folder to store the photo
        File file = new File(PATH);
        file.mkdirs();
    }

    /**
     * Start the camera and store the photo.
     *
     * @param view not applicable.
     */
    public void start(View view) {
        // create file to store photo
        String path = PATH + "sample.jpg";
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            // do nothing
        }

        // convert file to uri
        Uri uri = Uri.fromFile(file);

        // start camera
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        this.startActivityForResult(cameraIntent, 0);
    }

    /**
     * Reflect along the y-axis.
     *
     * @param view not applicable.
     */
    public void reflectY(View view) {
        // update the model
        this.image.reflectY();

        // set photo as image
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageBitmap(this.image.getBitmap());
    }

    public void reflectX(View view) {
        // update the model
        this.image.reflectX();

        // set photo as image
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageBitmap(this.image.getBitmap());
    }

    /**
     * Rotate image by 90deg.
     *
     * @param view not applicable
     */
    public void rotate(View view) {
        // update the model
        this.image.rotate();

        // set photo as image
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageBitmap(this.image.getBitmap());
    }

    /**
     * Make the start button invisible, make the x-reflect button visible and display
     * the photo.
     *
     * @param requestCode not applicable.
     * @param resultCode  not applicable.
     * @param data        not applicable.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // make start button invisible
        Button start = (Button) findViewById(R.id.startBtn);
        start.setVisibility(View.GONE);
        // make xreflect button visible
        Button xReflect = (Button) findViewById(R.id.reflectYBtn);
        xReflect.setVisibility(View.VISIBLE);
        // path to the photo
        String path = PATH + "sample.jpg";
        // decode the photo as a bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        // creat model
        this.image = new Model(bitmap);
        // set photo as image
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageBitmap(bitmap);
    }
}


