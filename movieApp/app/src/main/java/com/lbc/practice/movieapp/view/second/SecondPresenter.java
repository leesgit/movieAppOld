package com.lbc.practice.movieapp.view.second;

public class SecondPresenter implements SecondContract.SecondPresent {

    SecondContract.SecondView secondView;

    public SecondPresenter(SecondContract.SecondView secondView) {
        this.secondView = secondView;
    }

    @Override
    public void displayViewPage(int page) {
        secondView.setPage(page);
        secondView.setText(page);
    }
}
