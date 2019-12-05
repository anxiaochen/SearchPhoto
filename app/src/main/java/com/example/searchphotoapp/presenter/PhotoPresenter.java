package com.example.searchphotoapp.presenter;

import com.example.searchphotoapp.model.PhotoModel;
import com.example.searchphotoapp.model.PhotoModelBean;
import com.example.searchphotoapp.view.PhotoDetailsView;

public class PhotoPresenter implements Presenter<PhotoDetailsView>, IPhotoPresenter {
    private PhotoDetailsView photoView;
    private PhotoModel mPhotoModel;

    public PhotoPresenter(PhotoDetailsView view) {
        attachView(view);
        mPhotoModel = new PhotoModel(this);
    }

    public void showPhotoDetails(String photoID) {
        photoView.showProgress();
        mPhotoModel.getImage(photoID);
    }


    @Override
    public void loadDataSuccess(PhotoModelBean photoModelBean) {
        photoView.showPhotoDetails(photoModelBean);
        photoView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        photoView.hideProgress();
    }

    @Override
    public void attachView(PhotoDetailsView view) {
        this.photoView = view;
    }

    @Override
    public void detachView() {
        this.photoView = null;
    }
}
