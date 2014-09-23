package com.gtotek.kidquiz.activity;
 

import com.gtotek.kidquiz.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

public class SplashScreenActivity extends Activity {

	private Context mContext = this;

	private static final int DELAY = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splashscreen);

		new AsyncTask<Void, Void, Void>() {
			protected Void doInBackground(Void... voids) {
				for (int i = 0; i < 3; ++i) {
					try {
						Thread.sleep(DELAY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			};

			protected void onPostExecute(Void result) {
				finish();
				Intent intent = new Intent(mContext, StartPlayActivity.class);
				mContext.startActivity(intent);
			};
		}.execute();
	}

}
