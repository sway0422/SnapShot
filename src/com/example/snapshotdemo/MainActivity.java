package com.example.snapshotdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button btn1;
	EditText et1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn1 = (Button)findViewById(R.id.button1);
		et1 = (EditText)findViewById(R.id.edittext1);
		btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent= new Intent(MainActivity.this, Snapshot.class); // TODO Auto-generated method stub
				String et2=et1.getText().toString();//get information from edittext
				intent.putExtra("et3", et2);//Intent for transfer
				startActivity(intent);//transfer data with para: time
			}
			
		}); //listener
		//Intent
		
	}


}
