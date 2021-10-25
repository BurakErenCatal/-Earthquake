package com.example.depremler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DepremDataAdapter extends RecyclerView.Adapter<DepremDataAdapter.ViewHolder> {
    private ArrayList<DepremModel> localDataset;
    private Context context;
    public DepremDataAdapter(ArrayList<DepremModel> dataSet,Context ctx) {
        this.localDataset = dataSet;
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deprem_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepremDataAdapter.ViewHolder holder, int position) {
        holder.SetView(position);
    }

    @Override
    public int getItemCount() {
        return localDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ml, tarih_saat, yer;

        public ViewHolder(View itemView) {
            super(itemView);
            ml = itemView.findViewById(R.id.tv_ml);
            tarih_saat = itemView.findViewById(R.id.tv_tarih_saat);
            yer = itemView.findViewById(R.id.tv_yer);
        }

        public void SetView(int pos) {
            DepremModel d = localDataset.get(pos);
            double ML = d.getmL();
            int c = 0;
            if(ML<=2) {
                c = context.getResources().getColor(R.color.Secure);
            }else if(ML<=3){
                c = context.getResources().getColor(R.color.Caution);
            }else if(ML<=4){
                c = context.getResources().getColor(R.color.Warning);
            }else if(ML>4){
                c = context.getResources().getColor(R.color.Danger);
            }

            if(c!=0){
                ml.setTextColor(c);
            }
            ml.setText(String.valueOf(ML));
            String t_s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(d.getTarih_saat());
            tarih_saat.setText(t_s);
            yer.setText(d.getYer());
        }
    }
}
