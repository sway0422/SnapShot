package com.example.snapshotdemo;

import java.util.Timer;

import android.content.Context;
import android.content.Intent;




import com.example.snapshotdemo.Snapshot;

public class TimerCamera {
	private int intime;
	Context context;

	public TimerCamera(Context context, int intime) {
		super();
		this.intime = intime;
	}
	public void timer(final int timer){
		 Thread t1 = new Thread(new Runnable(){

				
					  

				@Override
				public  void run() {
					// TODO Auto-generated method stub
					int j = timer *60;
					
					System.out.println("initial call run time = " + j);
					for(; j>=0;j--){
						System.out.println("call time left: " + j);
					 /*ShutterCallback shutter = new ShutterCallback(){

						 @Override
						 public void onShutter() {
							 // TODO Auto-generated method stub
				
						 }
	    	
					 };
						  Camera.takePicture(shutter,null,null);
                      */
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(j==0){
							
							//break; // todo stop
						
							Intent intent = new Intent();
							intent.setClass(context, MainActivity.class);
							context.startActivity(intent);
							break;
							
						}
						
					
					}
				}
	        	
	        });
	        t1.start();
	}
	
}