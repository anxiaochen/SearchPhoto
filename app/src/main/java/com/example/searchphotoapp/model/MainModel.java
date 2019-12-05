package com.example.searchphotoapp.model;

import com.example.searchphotoapp.presenter.IMainPresenter;
import com.example.searchphotoapp.presenter.IPhotoPresenter;
import com.loopj.android.http.*;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainModel {
    IMainPresenter mIMainPresenter;
    private List<MainModelBean> resultsList = new ArrayList<MainModelBean>();
    private String api_key = "4861e2fbc98afb4c9d4ab3f0858a57a3";

    public MainModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData() {
        resultsList.clear();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key="+api_key+"&tags=cat&format=json&nojsoncallback=1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    JSONObject data = response.getJSONObject("photos");
                    JSONArray results = data.getJSONArray("photo");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);

                        //Get photo basic info
                        getPhotoInfo(item.getString("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mIMainPresenter.loadDataFailure();
            }
        });
    }

    public void searchData(String searchText) {

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key="+api_key+"&tags="+searchText+"&format=json&nojsoncallback=1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    resultsList.clear();
                    JSONObject data = response.getJSONObject("photos");
                    JSONArray results = data.getJSONArray("photo");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject item = results.getJSONObject(i);

                        //Get photo basic info
                        getPhotoInfo(item.getString("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mIMainPresenter.loadDataFailure();
            }
        });
    }

    public void getPhotoInfo(String photoId) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://www.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key="+api_key+"&photo_id="+photoId+"&format=json&nojsoncallback=1", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject item = response.getJSONObject("photo");
                    JSONObject itemTitle = item.getJSONObject("title");
                    JSONObject itemDescription = item.getJSONObject("description");

                    MainModelBean mainModelBean = new MainModelBean();
                    mainModelBean.setID(item.getString("id"));
                    mainModelBean.setTitle(itemTitle.getString("_content"));
                    mainModelBean.setDescription(itemDescription.getString("_content"));
                    mainModelBean.setViews(item.getInt("views"));

                    resultsList.add(mainModelBean);

                    mIMainPresenter.loadDataSuccess(resultsList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mIMainPresenter.loadDataFailure();
            }
        });
    }


}







