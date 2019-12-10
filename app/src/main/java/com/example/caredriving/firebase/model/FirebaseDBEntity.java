package com.example.caredriving.firebase.model;

import com.example.caredriving.firebase.model.dataObject.StudentObj;
import com.example.caredriving.firebase.model.dataObject.TeacherObj;
import com.example.caredriving.firebase.model.dataObject.UserObj;

import java.io.Serializable;
import java.util.HashMap;

public class FirebaseDBEntity implements Serializable {
    HashMap<String, String> user;
    String type;

    public UserObj getUserObj(){
        try {
            if (type.equals("teacher"))
                return new TeacherObj(user);
            if (type.equals("student"))
                return new StudentObj(user);
        } catch (Exception e){
            return null;
        }
        return null;
    }

    public void setType(String type){this.type = type;}
    public void setInfo(HashMap<String, String> user){this.user = user;}
    public String getType(){return type;}
    public HashMap<String, String> getInfo(){return user;}
}
