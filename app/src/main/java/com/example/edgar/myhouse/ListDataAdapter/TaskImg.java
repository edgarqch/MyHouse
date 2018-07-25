package com.example.edgar.myhouse.ListDataAdapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.Result;

/**
 * Created by edgar on 25-07-18.
 */

public class TaskImg extends AsyncTask<String, String, Bitmap> {

    private OnLoadImage IMG;
    private ImageView imageContainer;
    public void setLoadImage(ImageView container, OnLoadImage img){
        IMG = img;
        imageContainer = container;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            InputStream stream = url.openConnection().getInputStream();
            Bitmap imageBitmap = BitmapFactory.decodeStream(stream);
            return imageBitmap;
            //img.setImageBitmap(imageBitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Bitmap result) {
        IMG.setLoadImage(imageContainer, result);
    }
}
