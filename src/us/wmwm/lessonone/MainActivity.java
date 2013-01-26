package us.wmwm.lessonone;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {	
	
	private Future<?> longRunningAction;
	
	private ExecutorService service = Executors.newFixedThreadPool(2);
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private Runnable countToAThousand = new Runnable() {
		public void run() {
			
			for(int i = 1; i < 1000; i++) {
				Log.d(MainActivity.class.getSimpleName(), String.valueOf(i));
			}
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG,"onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_linear);
	}
	
	@Override
	protected void onPause() {
		Log.d(TAG,"onPause()");
		super.onPause();
		longRunningAction.cancel(true);
	}
	
	@Override
	protected void onResume() {
		Log.d(TAG,"onResume()");
		super.onResume();
		longRunningAction = service.submit(countToAThousand);
	}
	
	@Override
	protected void onDestroy() {
		Log.d(TAG,"onDestroy()");
		super.onDestroy();
		service.shutdown();
	}
	
	@Override
	protected void onStart() {
		Log.d(TAG,"onStart()");
		super.onStart();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
