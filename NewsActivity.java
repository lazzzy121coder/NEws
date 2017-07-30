package com.android.learning.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    NewsAdapter newsAdapter;
    ListView menu_list;
    String mJson= "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=5a60af5a72884375b64b905838348893";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        menu_list = (ListView) findViewById(R.id.main_list);
        List<News> newsList = new ArrayList<>();
        new downloadDataTask().execute(mJson);
        Log.v("Calling onCreate Method", "Working");
        newsAdapter = new NewsAdapter(this, newsList);
        menu_list.setAdapter(newsAdapter);
        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = (News)newsAdapter.getItem(position);
                Uri newsUri = Uri.parse(news.getNewsUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW,newsUri);
                startActivity(webIntent);
            }
        });
    }
              public class downloadDataTask extends AsyncTask<String,Void,List<News>>
           {
               protected List<News> doInBackground(String... url)
               {
                   if(url==null)
                   {
                       return null;
                   }
                   List<News> news = new ArrayList<>();
                   try {
                       news = QueryUtils.fetchNewsData(url[0]);
                   }
                   catch(Exception e)
                   {
                       e.printStackTrace();
                   }

                   return news;
               }
               protected void onPostExecute(List<News> newsData)
               {
                   newsAdapter.clear();
                   if(newsData!=null&&!newsData.isEmpty())
                   {
                       newsAdapter.addAll(newsData);
                   }
               }
           }
    }

