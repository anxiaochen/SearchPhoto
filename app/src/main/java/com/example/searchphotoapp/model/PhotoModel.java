package com.example.searchphotoapp.model;

import com.example.searchphotoapp.presenter.IPhotoPresenter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PhotoModel {
    IPhotoPresenter IphotoPresenter;
    private PhotoModelBean photoResult = new PhotoModelBean();
    private List<PhotoModelBean> resultsList = new ArrayList<PhotoModelBean>();
    private String api_key = "4861e2fbc98afb4c9d4ab3f0858a57a3";

    public PhotoModel(IPhotoPresenter iPhotoPresenter) {
        this.IphotoPresenter = iPhotoPresenter;
    }


    public void getImage(String photoId){

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://www.flickr.com/services/rest/?method=flickr.photos.getContext&api_key="+api_key+"&photo_id="+photoId+"&format=json&nojsoncallback=1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    JSONObject item = response.getJSONObject("prevphoto");
                    photoResult.setImage(item.getString("thumb"));
                    photoResult.setTitle(item.getString("title"));

                    IphotoPresenter.loadDataSuccess(photoResult);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                IphotoPresenter.loadDataFailure();
            }
        });
    }

}
