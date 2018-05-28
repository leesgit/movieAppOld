package com.lbc.practice.movieapp.view.second;

import javax.inject.Inject


class SecondPresenter : SecondContract.SecondPresent {


    @Inject
    constructor()

    var secondView: SecondContract.SecondView?=null

    override fun takeView(secondView: SecondContract.SecondView) {
        this.secondView = secondView
    }
    override fun displayViewPage(page: Int) {
        secondView!!.setPage(page)
        secondView!!.setText(page)
    }
}