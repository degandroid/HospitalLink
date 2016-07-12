package com.enjoyor.hospitallink.act;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.enjoyor.hospitallink.R;
import com.enjoyor.hospitallink.act.base.ToolBarActivity;
import com.enjoyor.hospitallink.fragment.Data_AppFragment;
import com.enjoyor.hospitallink.fragment.SpecialistFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuzhenzhen on 2016/7/7.
 */
public class ActivtyAppointment extends ToolBarActivity implements View.OnClickListener {
    @Bind(R.id.appointment_class_specialist)
    TextView appointment_class_specialist;
    @Bind(R.id.appointment_class_data)
    TextView appointment_class_data;
    @Bind(R.id.appointment_class_text)
    View appointment_class_text;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_class, false);
        ButterKnife.bind(this);
        setTitle("心血管内科");
        this.fragmentManager=getSupportFragmentManager();
        this.fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.appointment_class_text, SpecialistFragment.getSpecialistFragment());
        fragmentTransaction.commit();
        appointment_class_specialist.setOnClickListener(this);
        appointment_class_data.setOnClickListener(this);
        appointment_class_specialist.setTextColor(appointment_class_specialist.getResources().getColor(R.color.hl_color_blue));
        appointment_class_data.setTextColor(appointment_class_data.getResources().getColor(R.color.app_color_text));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointment_class_data:
                this.fragmentManager=getSupportFragmentManager();
                this.fragmentTransaction=fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.appointment_class_text, Data_AppFragment.getData_app());
                fragmentTransaction.commit();
                appointment_class_specialist.setTextColor(appointment_class_specialist.getResources().getColor(R.color.app_color_text));
                appointment_class_data.setTextColor(appointment_class_data.getResources().getColor(R.color.hl_color_blue));
            break;
            case R.id.appointment_class_specialist:

                this.fragmentManager=getSupportFragmentManager();
                this.fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.appointment_class_text, SpecialistFragment.getSpecialistFragment());
                fragmentTransaction.commit();

                appointment_class_specialist.setTextColor(appointment_class_specialist.getResources().getColor(R.color.hl_color_blue));
                appointment_class_data.setTextColor(appointment_class_data.getResources().getColor(R.color.app_color_text));
                break;

        }
    }
}