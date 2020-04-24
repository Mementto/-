package com.arcsoft.arcfacedemo.face;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaceRegisterViewModel extends ViewModel {

    private FaceRegisterModel model;
    private MutableLiveData<Integer> result;

    public FaceRegisterViewModel() {
        model = new FaceRegisterModel(this);
    }

    public void submitFace(String path, Long userId) {
        model.submitFace(path, userId);
    }

    public void submitResult(Integer result) {
        this.getResult().setValue(result);
    }

    public MutableLiveData<Integer> getResult() {
        if (result == null) {
            result = new MutableLiveData<>();
        }
        return result;
    }
}
