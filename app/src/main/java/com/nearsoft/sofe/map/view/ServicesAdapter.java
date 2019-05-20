package com.nearsoft.sofe.map.view;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nearsoft.sofe.map.model.Servicio;
import com.nearsoft.sofe.R;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private List<Servicio> mServices;
    private ServicesListener mListener;

    public ServicesAdapter(List<Servicio> services, ServicesListener listener) {
        mServices = services;
        mListener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.holder_service, viewGroup, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {
        serviceViewHolder.bindView(mServices.get(i));
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {

        private FloatingActionButton mFAB;
        private TextView mTitleTextView;

        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            mFAB = itemView.findViewById(R.id.fab);
            mTitleTextView = itemView.findViewById(R.id.title);
        }

        void bindView(final Servicio service) {
            mFAB.setImageResource(service.getIcon());
            mTitleTextView.setText(service.getServiceName());
            itemView.setOnClickListener(v -> mListener.onServiceSelected(service));
        }
    }

    interface ServicesListener {
        void onServiceSelected(Servicio servicio);
    }
}
