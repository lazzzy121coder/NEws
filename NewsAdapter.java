package com.android.learning.newsapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static android.R.attr.bitmap;

public class NewsAdapter extends ArrayAdapter {
    NewsAdapter(Context context,List<News> newsData)
    {
        super(context,0,newsData);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView == null)
        {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }
        TextView textView1,textView2,textView3;
        ImageView imageView;
        Log.v("NewsAdapter Class : ","Working");
        News news = (News)getItem(position);
        textView1 = (TextView)convertView.findViewById(R.id.newsTitle);
        textView1.setText(news.getNewsTitle());
        textView2 = (TextView)convertView.findViewById(R.id.newsDesc);
        if(news.getmNewsDesc().equalsIgnoreCase("null"))
        {
            textView2.setText("No discription available tap to read the full news");
        }
        else {
            textView2.setText(news.getmNewsDesc());
        }
        textView3 = (TextView)convertView.findViewById(R.id.date_time);
        textView3.setText("tap to read more");
        imageView = (ImageView)convertView.findViewById(R.id.imageView);
        imageView.setImageDrawable(LoadImageFromWebOperations(news.getImageUrl()));
        return convertView;
    }
    private static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
