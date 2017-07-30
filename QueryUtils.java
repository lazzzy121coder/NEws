package com.android.learning.newsapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    public static List<News> fetchNewsData(String Url)throws Exception
    {
        String mJsonResponse = "";
        if(Url == null) {
            Log.v("Issue in fetchingData()","1");
        }
        Log.v("fetchNewData() : ","Working");
        URL url = createUrl(Url);
        mJsonResponse = makeHttpRequest(url);
        List<News> news = getDataFromResponse(mJsonResponse);
        Log.v("fetchNewsData(): ","returning data");
          return news;
    }
    private static URL createUrl(String url)throws MalformedURLException
    {
        Log.v("createUrl() : ","Working");
        URL Url = new URL(url);
        return Url;
    }
    private static String makeHttpRequest(URL url)throws Exception
    {
        Log.v("makeHttpRequest() : ","working");
        String mJson = "";
        if(url == null)
        {
            Log.v("Error in makeRequest()","null");
        }
        HttpURLConnection urlConnection;
        InputStream inputStream;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

        if(urlConnection.getResponseCode()==200)
        {
            inputStream = urlConnection.getInputStream();
            mJson = getInputStream(inputStream);
        }
        else
        {
            return mJson;
        }
        if(inputStream!=null)
        {
            inputStream.close();
        }
        if(urlConnection!=null)
        {
            urlConnection.disconnect();
        }

         }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return mJson;
    }

    private static String getInputStream(InputStream inputStream)throws IOException
    {
        Log.v("getInputStream() : ","working");
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream!=null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
        }
        else
        {
            Log.v("Error in inputStream()","null");
        }
        return stringBuilder.toString();
    }
    private static List<News> getDataFromResponse(String jsonResponse)throws JSONException
    {
        Log.v("getDataFromResponse(): ","Working");
        if(jsonResponse==null)
        {
            Log.v("Error in jsonOut","null");
        }
        List<News> news = new ArrayList<>();
        JSONObject firstObject = new JSONObject(jsonResponse);
        JSONArray resultsArray = firstObject.getJSONArray("articles");
        for(int i = 0; i<resultsArray.length();i++)
        {
           JSONObject dataObject = resultsArray.getJSONObject(i);
            String webTitle = dataObject.getString("title");
            String webUrl = dataObject.getString("url");
            String webDesc = dataObject.getString("description");
            String imageUrl = dataObject.getString("urlToImage");
            news.add(new News(webTitle,webDesc,webUrl,imageUrl));
        }
          return news;
    }
//    private static Bitmap getImage(String imageUrl)
//    {
//        Bitmap bmp = null;
//        try {
//            URL urlToImage = new URL(imageUrl);
//            bmp = BitmapFactory.decodeStream(urlToImage.openConnection().getInputStream());
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return bmp;
//    }
}
