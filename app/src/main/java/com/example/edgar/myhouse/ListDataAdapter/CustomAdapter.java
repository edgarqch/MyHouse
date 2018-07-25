package com.example.edgar.myhouse.ListDataAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgar.myhouse.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by edgar on 23-07-18.
 */

public class CustomAdapter extends BaseAdapter implements OnLoadImage{

    private Context CONTEXT;
    private ArrayList<ItemList> LIST;

    public CustomAdapter(Context contex, ArrayList<ItemList> list){
        this.CONTEXT = contex;
        this.LIST = list;
    }

    @Override
    public int getCount() {
        return this.LIST.size();
    }

    @Override
    public Object getItem(int i) {
        return this.LIST.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater infater = (LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infater.inflate(R.layout.item_layout, null);
        }
        TextView state = (TextView)view.findViewById(R.id.state);
        TextView ubication = (TextView)view.findViewById(R.id.ubication);
        TextView characteristics = (TextView)view.findViewById(R.id.characteristics);
        TextView price = (TextView)view.findViewById(R.id.price);

        state.setText(this.LIST.get(i).getState());
        ubication.setText(this.LIST.get(i).getUbication());
        characteristics.setText(this.LIST.get(i).getCharacteristics());
        price.setText(this.LIST.get(i).getPrice());

        ImageView img = (ImageView)view.findViewById(R.id.facade_layoud);

        TaskImg hilo = new TaskImg();
        hilo.setLoadImage(img, this);
        hilo.execute(this.LIST.get(i).getFacade());

        return view;
    }

    @Override
    public void setLoadImage(ImageView container, Bitmap img) {
        container.setImageBitmap(img);
    }
}
