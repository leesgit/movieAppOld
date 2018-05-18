package com.lbc.practice.movieapp.view.second;

public interface SecondContract {

    interface SecondView {
        void setPage(int page);
        void setText(int page);
    }

    interface SecondPresent {
        void displayViewPage(int page);
    }
}
