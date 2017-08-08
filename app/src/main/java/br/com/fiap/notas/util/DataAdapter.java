package br.com.fiap.notas.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.fiap.notas.R;
import br.com.fiap.notas.entity.Row;

/**
 * Created by logonrm on 15/05/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Row> rows;

    //Construtor que recebe uma lista de row(Dataset) que vem do Bluemix
    public DataAdapter(ArrayList<Row> rows){
        this.rows=rows;
    }

    //Cria uma nova view com base nom lauyout do card_row.xml
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    //Popula os dados das views do card dinamicamente , com os dados vindo do Cloudant
    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        holder.tvId.setText(rows.get(position).getDoc().getId());
        holder.tvTitulo.setText(rows.get(position).getDoc().getTitulo());
        holder.tvAssunto.setText(rows.get(position).getDoc().getAssunto());
        holder.tvConteudo.setText(rows.get(position).getDoc().getConteudo());

    }

    //Método que retorna o tamanho do DataSet que recebemos do Cloudant
    @Override
    public int getItemCount() {
        return rows.size();
    }

    //Classe que providencia acesso as views do nosso card
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitulo, tvId,tvAssunto,tvConteudo;


        public ViewHolder(View itemView) {
            super(itemView);
            tvId= (TextView) itemView.findViewById(R.id.tvId);
            tvTitulo= (TextView) itemView.findViewById(R.id.tvTitulo);
            tvAssunto=(TextView) itemView.findViewById(R.id.tvAssunto);
            tvConteudo=(TextView) itemView.findViewById(R.id.tvConteudo);
        }
    }
}
