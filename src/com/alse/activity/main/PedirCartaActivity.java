package com.alse.activity.main;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alse.R;
import com.http.cliente.HttpClientTest;
import com.model.carta.Carta;

public class PedirCartaActivity extends ActionBarActivity {

	private static final String DEBUG_TAG = "HttpExample";
	private EditText urlText;
	private TextView textView;
	private Carta carta;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedir_carta);

		urlText = (EditText) findViewById(R.id.myUrl);
		textView = (TextView) findViewById(R.id.myText);
		
		intent = new Intent(this, CartaActivity.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pedir_carta, menu);
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

	public void myClickHandler(View view) {
		String stringUrl = urlText.getText().toString();
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			new DownloadWebpageTask().execute(stringUrl);
		} else {
			textView.setText("No network connection available.");
		}
	}

	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			try {
				return downloadUrl(urls[0]);
			} catch (IOException e) {
				return "Unable to retrieve web page. URL may be invalid. Pone bien la url papa";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			textView.setText("test: " + result);
			intent.putExtra("carta", carta);
			startActivity(intent);
		}

	}
	private String downloadUrl(String myurl) throws IOException {
		HttpClientTest clientTest = new HttpClientTest();
		clientTest.setServerURL(myurl);
		carta =clientTest.pedirCarta();
		return carta.getCarta().get(0).getDescripcion();
	}
}
