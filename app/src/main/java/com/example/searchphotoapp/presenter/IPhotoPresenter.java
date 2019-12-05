package com.example.searchphotoapp.presenter;


import com.example.searchphotoapp.model.PhotoModelBean;


public interface IPhotoPresenter {
    void loadDataSuccess(PhotoModelBean photoModelBean);
    void loadDataFailure();
}
