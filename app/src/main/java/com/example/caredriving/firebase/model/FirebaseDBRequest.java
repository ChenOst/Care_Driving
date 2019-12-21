package com.example.caredriving.firebase.model;

import com.example.caredriving.firebase.model.dataObject.RequestObj;

public class FirebaseDBRequest extends FirebaseBaseModel{
    private RequestObj request;

    public FirebaseDBRequest(String srcId, String dstId){
        this.request = new RequestObj(srcId, dstId);
    }

    public void sendRequest(){
        myref.child("Requests").child(request.getRequestId()).setValue(request);
    }
}
