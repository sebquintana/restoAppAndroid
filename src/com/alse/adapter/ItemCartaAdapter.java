package com.alse.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alse.R;
import com.model.carta.ItemCarta;

public class ItemCartaAdapter extends ArrayAdapter<ItemCarta> {

	public ItemCartaAdapter(Context context, int resource,
			List<ItemCarta> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemCarta itemCarta = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_carta, parent, false);
		}
		// Lookup view for data population
		TextView itemCartaNombre = (TextView) convertView
				.findViewById(R.id.itemCartaNombre);
		TextView itemCartaDescripcion = (TextView) convertView
				.findViewById(R.id.itemCartaDescripcion);
		TextView itemCartaPrecio = (TextView) convertView
				.findViewById(R.id.itemCartaPrecio);
		// Populate the data into the template view using the data object
		itemCartaNombre.setText(itemCarta.getNombre());
		itemCartaDescripcion.setText(itemCarta.getDescripcion());
		itemCartaPrecio.setText(itemCarta.getPrecio());
		// Return the completed view to render on screen
		return convertView;
	}
}
