package com.kukuhsain.simple.boilerplate.presentation.base;

/**
 * Created by kukuh on 27/01/17.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
