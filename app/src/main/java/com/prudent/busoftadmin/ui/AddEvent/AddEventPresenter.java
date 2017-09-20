package com.prudent.busoftadmin.ui.AddEvent;

import com.prudent.busoftadmin.ui.base.Presenter;

/**
 * Created by Dharmik Patel on 07-Jun-17.
 */

public class AddEventPresenter implements Presenter<AddEventView> {

    private AddEventView addEventView;

    @Override
    public void onAttach(AddEventView view) {
        addEventView = view;
    }

    @Override
    public void onDetach() {
        addEventView = null;
    }
}
