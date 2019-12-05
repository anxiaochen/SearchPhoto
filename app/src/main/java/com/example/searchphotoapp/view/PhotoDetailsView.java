package com.example.searchphotoapp.view;

import com.example.searchphotoapp.model.PhotoModelBean;

public interface PhotoDetailsView {
    void showPhotoDetails(PhotoModelBean photoModelBean);
    void showProgress();
    void hideProgress();
}
