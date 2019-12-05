package com.example.searchphotoapp.presenter;

public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
