package com.example.demonew;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOInfo {
    private DatabaseReference databaseReference,reference;
    public DAOInfo()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Info.class.getSimpleName());
        reference=db.getReference(RegModal.class.getSimpleName());
    }

    public Task<Void> add(Info info)
    {
        return databaseReference.push().setValue(info);
    }
    public Task<Void> add(RegModal regModal)
    {
        return reference.push().setValue(regModal);
    }
}
