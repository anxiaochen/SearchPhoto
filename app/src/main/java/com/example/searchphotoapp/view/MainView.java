package com.example.searchphotoapp.view;

import com.example.searchphotoapp.model.MainModelBean;

import java.util.List;

public interface MainView {
    void showPhotoList(List<MainModelBean> MainModels);
    void showProgress();
    void hideProgress();
}