package com.lbc.practice.movieapp.view.second;


class SecondPresenter(var secondView: SecondContract.SecondView) : SecondContract.SecondPresent {

    override fun displayViewPage(page: Int) {
        secondView.setPage(page)
        secondView.setText(page)
    }
}