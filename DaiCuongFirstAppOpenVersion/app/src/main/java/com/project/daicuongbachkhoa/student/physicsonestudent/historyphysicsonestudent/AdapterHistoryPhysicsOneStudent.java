package com.project.daicuongbachkhoa.student.physicsonestudent.historyphysicsonestudent;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.daicuongbachkhoa.R;

public class AdapterHistoryPhysicsOneStudent extends RecyclerView.ViewHolder {

    public TextView
            txtNameStoragePhysicsOneStudent,
            txtDateStoragePhysicsOneStudent,
            txtPointStoragePhysicsOneStudent;
    public LinearLayout
            btnFrameStorage;

    public AdapterHistoryPhysicsOneStudent(@NonNull final View itemView) {
        super(itemView);
        txtNameStoragePhysicsOneStudent = itemView.findViewById(R.id.txtNameStorage);
        txtDateStoragePhysicsOneStudent = itemView.findViewById(R.id.txtDateStorage);
        txtPointStoragePhysicsOneStudent = itemView.findViewById(R.id.txtPointStorage);
        btnFrameStorage = itemView.findViewById(R.id.btnFrameStorage);
    }
}
