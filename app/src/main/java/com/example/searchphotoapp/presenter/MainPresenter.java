package com.example.searchphotoapp.presenter;

import com.example.searchphotoapp.model.MainModel;
import com.example.searchphotoapp.model.MainModelBean;
import com.example.searchphotoapp.view.MainView;

import java.util.List;

public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(MainView view) {
        attachView(view);
        mMainModel = new MainModel(this);
    }

    public void loadData() {
        mMainView.showProgress();
        mMainModel.loadData();
    }

    public void searchData(String searchText) {
        mMainView.showProgress();
        mMainModel.searchData(searchText);
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }

    @Override
    public void loadDataSuccess(List<MainModelBean> mainModelBeans) {
        mMainView.showPhotoList(mainModelBeans);
        mMainView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        mMainView.hideProgress();
    }
}