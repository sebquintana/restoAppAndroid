package com.alse.activity.main;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.alse.R;
import com.alse.adapter.ItemCartaAdapter;
import com.model.carta.Carta;
import com.model.carta.ItemCarta;

public class CartaActivity extends ActionBarActivity {

	private Carta carta;
	private ListView listView;
	private ArrayList<ItemCarta> arrayOfItemsCarta;
	private ItemCartaAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carta);
		carta = (Carta) getIntent().getExtras().getSerializable("carta");
		arrayOfItemsCarta = new ArrayList<ItemCarta>();
		adapter = new ItemCartaAdapter(this,
				android.R.layout.simple_list_item_1, arrayOfItemsCarta);
		listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
		arrayOfItemsCarta.addAll(carta.getCarta());
	}

	private void agregarItems() {
		int i = 1;
		for (ItemCarta item : carta.getCarta()) {
			arrayOfItemsCarta.add(item);
			System.out.println(i);
			i++;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carta, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
