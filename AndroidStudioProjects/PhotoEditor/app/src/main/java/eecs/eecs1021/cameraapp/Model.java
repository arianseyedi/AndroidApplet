package eecs.eecs1021.cameraapp;

import android.graphics.Bitmap;

/**
 * The model of the camera app.
 *
 * @author Arian Seyedi
 */
public class Model {

    private Bitmap bitmap;

    /**
     * Initializes this model with the given bitmap.
     *
     * @param bitmap bitmap of this model.
     */
    public Model(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Returns the bitmap of this model.
     *
     * @return bitmap of this model.
     */
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    /**
     * Reflects the bitmap of this model along the y-axis.
     */
    public void reflectY() {
        final int WIDTH = this.bitmap.getWidth(), HEIGHT = this.bitmap.getHeight();

        Bitmap reflection = Bitmap.createBitmap(WIDTH, HEIGHT, this.bitmap.getConfig());

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int color = this.bitmap.getPixel(x, y);
                reflection.setPixel(WIDTH - 1 - x, y, color);
            }
        }

        this.bitmap = reflection;
    }

    /**
     * Reflects the bitmap of this model along the x-axis.
     */
    public void reflectX() {
        final int WIDTH = this.bitmap.getWidth(), HEIGHT = this.bitmap.getHeight();

        Bitmap reflection = Bitmap.createBitmap(WIDTH, HEIGHT, this.bitmap.getConfig());

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int color = this.bitmap.getPixel(x, y);
                reflection.setPixel(x, HEIGHT - 1 - y, color);
            }
        }

        this.bitmap = reflection;
    }

    /**
     * Rotate class rotates a photo by 90 degrees clockwise.
     */
    public void rotate() {
        final int WIDTH = this.bitmap.getWidth(), HEIGHT = this.bitmap.getHeight();

        // new bitmap with the same hight and width.
        Bitmap rotation = Bitmap.createBitmap(WIDTH, HEIGHT, this.bitmap.getConfig());

        double originX = Math.floor(WIDTH / 2), originY = Math.floor(HEIGHT / 2);
        int oX = (int) originX, oY = (int) originY;
        int yVal, xVal;

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int color = this.bitmap.getPixel(x, y);
                xVal = -y + oY + oX;
                yVal = x - oX + oY;
                if (xVal < WIDTH && xVal >= 0 && yVal >= 0 && yVal < HEIGHT)
                    rotation.setPixel(xVal, yVal, color);
            }
        }

        this.bitmap = rotation;
    }


}


