package com.example.proyectoserviciosocial22b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.List;

import classes.LocalFile;
import classes.Medidor;
import classes.Message;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("ALL")
public class DetectorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int indexFlapper;
    private Context context;

    public DetectorFragment() {
        // Required empty public constructor
    }
    public static DetectorFragment newInstance(String param1, String param2) {
        DetectorFragment fragment = new DetectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detector, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        ImageView btnNext = (ImageView) view.findViewById(R.id.btn_next);
        ImageView btnPrevius = (ImageView) view.findViewById(R.id.btn_previous);

        Message message = new Message(context);
        indexFlapper = 0;
        LocalFile localFile = new LocalFile(Environment.getExternalStorageDirectory(),"/");
        List<Medidor>medidores = localFile.loadMedidores();



        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(medidores != null)
                {
                    Intent intent = new Intent(context, CameraOpencvActivity.class);
                    intent.putExtra("Medidor",medidores.get(indexFlapper));
                    startActivity(intent);
                }else {
                    message.ShowNewMessage("No se a descargado ningun medidor");
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medidores != null)
                {
                    if(indexFlapper<medidores.size()) {
                        indexFlapper++;
                        message.ShowNewMessage("Medidor : "+medidores.get(indexFlapper).getNombre());
                    }
                }else{
                    message.ShowNewMessage("Descague un modelo");
                }
            }
        });
        btnPrevius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medidores != null)
                {
                    if(indexFlapper>0) {
                        indexFlapper--;
                        message.ShowNewMessage("Medidor : "+medidores.get(indexFlapper).getNombre());
                    }
                }else{
                    message.ShowNewMessage("Descargue un modelo");
                }
            }
        });
        return view;
    }
}