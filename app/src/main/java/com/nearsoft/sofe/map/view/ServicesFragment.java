package com.nearsoft.sofe.map.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nearsoft.sofe.map.model.Servicio;
import com.nearsoft.sofe.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServicesFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment implements ServicesAdapter.ServicesListener {

    private ServicesFragmentListener mListener;
    private RecyclerView mServicesRecyclerView;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance() {
        ServicesFragment fragment = new ServicesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        mServicesRecyclerView = view.findViewById(R.id.servicesList);
        setupServicesList();
        return view;
    }

    private void setupServicesList() {
        List<Servicio> services = getMedicalServices();
        ServicesAdapter adapter = new ServicesAdapter(services, this);
        mServicesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mServicesRecyclerView.setAdapter(adapter);
    }


    private List<Servicio> getMedicalServices() {
        List<Servicio> services = new ArrayList<>();
        services.add(new Servicio("Añadir Contacto ", R.drawable.ic_anadircontacto));
        services.add(new Servicio("Ayuda ", R.drawable.ic_ayuda));
        services.add(new Servicio("Numeros de Emergencia", R.drawable.ic_numerosllamada));

        return services;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ServicesFragmentListener) {
            mListener = (ServicesFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MapFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onServiceSelected(Servicio servicio) {
        if (mListener != null) {
            mListener.onServiceSelected(servicio);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ServicesFragmentListener {
        void onServiceSelected(Servicio service);
    }
}
