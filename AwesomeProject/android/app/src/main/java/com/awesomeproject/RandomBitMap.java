package com.awesomeproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class RandomBitMap {
    public static Bitmap createRandomBitmap(int width, int height) {
        // Create a new bitmap with the specified width and height
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // Create a new canvas for the bitmap
        Canvas canvas = new Canvas(bitmap);

        // Create a new random number generator
        Random random = new Random();

        // Create a new paint object
        Paint paint = new Paint();

        // Loop through each pixel in the bitmap
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Generate a random color
                int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

                // Set the paint color
                paint.setColor(color);

                // Draw a rectangle at the current pixel position
                canvas.drawRect(x, y, x + 1, y + 1, paint);
            }
        }

        return bitmap;
    }
}
