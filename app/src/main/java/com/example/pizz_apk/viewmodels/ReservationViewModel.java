package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationViewModel extends ViewModel {
    MutableLiveData<List<Reservation>> reservationList= new MutableLiveData<>();
    MutableLiveData<Reservation> selectedReservation = new MutableLiveData<>();

    public LiveData<List<Reservation>> getReservationList() {
        if (reservationList.getValue() == null) {
            reservationList.setValue(new ArrayList<>());
        }
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList.postValue(reservationList);
    }



    //ajout de réservation à la liste
    public void addReservation(Reservation reservation) {
        if (reservationList.getValue() == null) {
            reservationList.setValue(new ArrayList<Reservation>());
        }
        reservationList.getValue().add(reservation);
        reservationList.postValue(reservationList.getValue());
    }
    //suppression de réservation de la liste
    public void removeReservation(Reservation reservation) {
        if (reservationList.getValue() == null) {
            reservationList.setValue(new ArrayList<Reservation>());
        }
        reservationList.getValue().remove(reservation);
        reservationList.postValue(reservationList.getValue());
    }



    //get et set de la réservation sélectionnée
    public LiveData<Reservation> getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(Reservation reservation) {
        selectedReservation.setValue(reservation);
    }

}
