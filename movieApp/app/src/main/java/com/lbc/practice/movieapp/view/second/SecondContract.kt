package com.lbc.practice.movieapp.view.second;


interface SecondContract {

    interface SecondView {
        fun setPage(page: Int)
        fun setText(page: Int)
    }

    interface SecondPresent {
        fun displayViewPage(page: Int)
        fun takeView(secondView: SecondContract.SecondView)

    }
}