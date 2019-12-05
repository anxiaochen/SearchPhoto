package com.example.searchphotoapp.presenter;

import com.example.searchphotoapp.model.MainModelBean;

import java.util.List;

public interface IMainPresenter {
    void loadDataSuccess(List<MainModelBean> mainModelBean);
    void loadDataFailure();
}