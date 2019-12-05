package com.example.searchphotoapp.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.searchphotoapp.Adapter.PhotoListAdapter;
import com.example.searchphotoapp.R;
import com.example.searchphotoapp.model.MainModelBean;
import com.example.searchphotoapp.presenter.MainPresenter;
import com.example.searchphotoapp.view.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private ProgressBar mProgressBar;
    private ListView listView;
    private MainPresenter mMainPresenter;
    private static MainActivity mInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.list);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mMainPresenter = new MainPresenter(this);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent myIntent = new Intent(view.getContext(), PhotoDetailsActivity.class);
                            TextView getPhotoId = (TextView) view.findViewById(R.id.id);
                            myIntent.putExtra("photoID", getPhotoId.getText());
                            startActivityForResult(myIntent, 0);
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainPresenter.loadData();
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mMainPresenter.searchData(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

    @Override
     public void showPhotoList(List<MainModelBean> MainModels) {
         PhotoListAdapter adapter = new PhotoListAdapter(this, MainModels);
         listView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}