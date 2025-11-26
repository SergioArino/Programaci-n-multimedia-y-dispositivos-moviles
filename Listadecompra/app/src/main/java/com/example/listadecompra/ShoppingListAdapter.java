package com.example.listadecompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class ShoppingListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> items;

    // Constructor: recibe el contexto y la lista de artículos
    public ShoppingListAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    // Devuelve el número de elementos en la lista
    @Override
    public int getCount() {
        return items.size();
    }

    // Devuelve el objeto en una posición concreta
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    // Devuelve el ID del elemento (aquí usamos la posición como ID)
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.itemImage);
        TextView name = convertView.findViewById(R.id.itemName);
        TextView quantity = convertView.findViewById(R.id.itemQuantity);
        Button delete = convertView.findViewById(R.id.btnDelete);

        final Item item = items.get(position);

        // Rellenamos los controles con los datos del objeto
        img.setImageResource(item.getImagenResId());
        name.setText(item.getNombre());
        quantity.setText("Cantidad: " + item.getCantidad());

        // Acción del botón "Eliminar": borra el elemento de la lista
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);   // Elimina el objeto
                notifyDataSetChanged();   // Refresca la lista en pantalla
            }
        });

        return convertView;
    }
}