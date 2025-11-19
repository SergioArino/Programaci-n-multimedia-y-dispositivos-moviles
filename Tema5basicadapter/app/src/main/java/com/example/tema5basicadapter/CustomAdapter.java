package com.example.tema5basicadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private int selectedPosition = -1;
    private List<ListItem> items; // imagens
    private Context context; // contexto da tela de redirecionamento

    public CustomAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent,  false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.ItemImage);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.itemTitle);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.itemContent);
        TextView radioButton = (TextView) convertView.findViewById(R.id.itemRadioButton);
        ListItem item = items.get(position);
        imageView.setImageResource(item.getImageResId());
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getContent());

        // Set radio button checked state
        radioButton.setSelected(position == selectedPosition);
        radioButton.setOnClickListener(v -> {
                selectedPosition = position;
                notifyDataSetChanged();

        });

        return convertView;
    }
}
