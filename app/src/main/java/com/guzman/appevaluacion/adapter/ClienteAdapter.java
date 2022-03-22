package com.guzman.appevaluacion.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.guzman.appevaluacion.R;
import com.guzman.appevaluacion.activities.EditarActivity;
import com.guzman.appevaluacion.components.Parametros;
import com.guzman.appevaluacion.models.ClienteModel;
import com.guzman.appevaluacion.rest.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.CustomViewHolder> implements Filterable {

    Context context;
    private List<ClienteModel> ClienteList;
    private List<ClienteModel> ClienteFilterList;


    public ClienteAdapter(Context context, List<ClienteModel> list ) {
        this.context = context;
        ClienteList = list;
        ClienteFilterList = list;



    }

    @Override
    public ClienteAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cliente,parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ClienteAdapter.CustomViewHolder holder, final int position) {
        final  ClienteModel clienteModel = ClienteFilterList.get(position);

        //holder.tvNombre.setText(imagenClass.getNombre());

        holder.tvNombre.setText(clienteModel.getNombre());
        holder.tvApellido.setText(clienteModel.getApellido());
        holder.tvDocumento.setText(clienteModel.getDocumentoIdentidad());
        holder.tvNacionalidad.setText(clienteModel.getNacionalidad());
        holder.tvEdad.setText( String.valueOf(clienteModel.getEdad()) );
        holder.tvCorreo.setText(clienteModel.getCorreo());
        //holder.tvNombre.setText(clienteModel.getNombre());



       holder.btnEditar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Parametros.clienteModel = clienteModel;
               Intent intent = new Intent(context, EditarActivity.class);
               context.startActivity(intent);
           }
       });

    }



    public void Clear() {
        if (ClienteFilterList.size() > 0) {
            ClienteFilterList.clear();
            notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        return ClienteFilterList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                ArrayList<ClienteModel> filteredList;
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = new ArrayList<>();
                    ClienteFilterList = ClienteList;
                } else {
                    filteredList = new ArrayList<>();
                    for (ClienteModel publicacion : ClienteList) {
                        if (true) {
                            filteredList.add(publicacion);
                        }
                    }
                    ClienteFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ClienteFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ClienteFilterList = (ArrayList<ClienteModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {


        TextView tvNombre,tvApellido,tvCorreo,tvEdad,tvNacionalidad,tvDocumento;
        Button btnEditar;

        public CustomViewHolder(View view) {
            super(view);


            tvNombre =  itemView.findViewById(R.id.tv_nombre_card);
            tvApellido =  itemView.findViewById(R.id.tv_apellido_card);
            tvCorreo =  itemView.findViewById(R.id.tv_correo_card);
            tvEdad =  itemView.findViewById(R.id.tv_edad_card);
            tvNacionalidad =  itemView.findViewById(R.id.tv_nacionalidad_card);
            tvDocumento =  itemView.findViewById(R.id.tv_documento_card);
            btnEditar = itemView.findViewById(R.id.bt_editar_card);


        }

    }


}
