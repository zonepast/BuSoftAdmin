package com.prudent.busoftadmin.ui.base;

/**
 * Created by Dharmik Patel on 19-May-17.
 */

public interface Presenter<T extends View> {
    void onAttach(T view);

    void onDetach();
}
