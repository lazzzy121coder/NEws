package com.android.learning.newsapp;

import android.util.Log;

class News {
    /*
    mNewsTitle  = String variable to get the news Title
    mNewsDesc = String variable to get the 2-3 line description
    mNewsUrl = String variable for the host website URL
    mImageUrl = String varibable for the image link
     */
    private String mNewsTitle,mNewsDesc,mNewsUrl,mImageUrl;
    public News(String Title,String Desc,String Url,String imageUrl)
    {
        mNewsTitle = Title;
        mNewsDesc = Desc;
        mNewsUrl = Url;
        mImageUrl = imageUrl;
    }
    public String getNewsTitle()
    {
        Log.v("getNewsTitle(): ","Working");
        return mNewsTitle;
    }
    public String getmNewsDesc()
    {
        Log.v("getNewsDesc() : ","Working");
        return mNewsDesc;
    }
    public String getNewsUrl()
    {
        Log.v("getNewsUrl() : ","Working");
        return mNewsUrl;
    }
    public String getImageUrl()
    {
        return mImageUrl;
    }
}
