package com.example.anewstart;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseServices {
    private static FirebaseServices instance;
    private FirebaseAuth auth;


    public FirebaseServices(){
        auth=FirebaseAuth.getInstance();
        LIT=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
    }

    public static FirebaseServices getInstance(){
        if (instance == null) {
            instance=new FirebaseServices();
        }
        return instance;
    }


    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getLIT() {
        return LIT;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    private FirebaseFirestore LIT;
    private FirebaseStorage storage;







}
