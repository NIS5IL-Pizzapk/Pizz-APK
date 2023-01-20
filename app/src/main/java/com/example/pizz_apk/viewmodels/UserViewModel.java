package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    int selectedUserId;

    public int getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
}
