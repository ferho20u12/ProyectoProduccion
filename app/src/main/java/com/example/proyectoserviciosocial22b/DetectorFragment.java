package com.example.proyectoserviciosocial22b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
//propias
import classes.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetectorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private ImageCarousel carousel;
    private List<CarouselItem> list;
    private List<Integer>cantCirculos;
    private Message message;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detector, container, false);
        carousel = (ImageCarousel)view.findViewById(R.id.carousel);
        Button button = (Button) view.findViewById(R.id.button);

        //------------------------------------------------------
        message = new Message(context);
        carousel.registerLifecycle(getLifecycle());
        list = new ArrayList<>();
        cantCirculos = new ArrayList<>();
        //------------------------------------------------------
        CargarSlider();
        //------------------------------------------------------
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, CameraOpencvActivity.class);
                intent.putExtra("cantCirculos", cantCirculos.get(carousel.getCurrentPosition()));
                startActivity(intent);
            }
        });
        return view;
    }
    private void CargarSlider(){
        //Lista donde se guardaran las cantidades de circulos que tiene cada tipo de medidor
        cantCirculos.add(5);//Medidor 1 -Westinghouse
        cantCirculos.add(4);
        cantCirculos.add(3);
        //------------------------ Aqui se carga el slider con las imagenes
        list.add(
                new CarouselItem(
                        R.drawable.westinghouse_d4s,
                        "Westinghouse"
                )
        );
        list.add(
                new CarouselItem(
                        "",
                        "prueba 4 circulos"
                )
        );
        list.add(
                new CarouselItem(
                        "",
                        "prueba 3 circulos"
                )
        );
        carousel.setData(list);

    }
}