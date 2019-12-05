package com.example.searchphotoapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.searchphotoapp.Adapter.CacheImage;
import com.example.searchphotoapp.R;
import com.example.searchphotoapp.model.PhotoModelBean;
import com.example.searchphotoapp.presenter.PhotoPresenter;
import com.example.searchphotoapp.view.PhotoDetailsView;


public class PhotoDetailsActivity extends AppCompatActivity implements PhotoDetailsView {
    private static PhotoDetailsActivity photoDetailsInstance;
    private TextView titleView;
    private NetworkImageView photoView;
    private ImageLoader mImageLoader;
    private PhotoPresenter photoPresenter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoDetailsInstance = this;
        setContentView(R.layout.list_details);


        initView();
    }
    private void initView() {
        photoPresenter = new PhotoPresenter(this);
        mProgressBar = (ProgressBar) findViewById(R.id.photoProgressBar);
        titleView = (TextView) findViewById(R.id.photoTitle);
        photoView = (NetworkImageView) findViewById(R.id.photoImage);
        photoPresenter.showPhotoDetails(getIntent().getStringExtra("photoID"));

    }

    @Override
    public void showPhotoDetails(PhotoModelBean photoModelBean) {
        String titleData = photoModelBean.getTitle();
        String imgUrl = photoModelBean.getImage();

        titleView.setText(titleData);
        mImageLoader = CacheImage.getInstance(this.getApplicationContext()).getImageLoader();
        mImageLoader.get(imgUrl, ImageLoader.getImageListener(photoView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        photoView.setImageUrl(imgUrl, mImageLoader);

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
