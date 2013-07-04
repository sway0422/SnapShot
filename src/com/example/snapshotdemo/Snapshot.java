package com.example.snapshotdemo;



import com.example.snapshotdemo.TimerCamera;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Snapshot extends Activity {
	CameraAdmin cameraAdmin;
	boolean isClicked = false;
    int et6;
	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.snapshot);
	cameraAdmin = new CameraAdmin(this);
	setContentView(cameraAdmin); 
	String et5 = getIntent().getStringExtra("et3");
	et6= Integer.parseInt(et5);	
	//step1: receive int time from intent ->MainActivity
	TimerCamera timercamera1= new TimerCamera(et6);
	//step1: TimerCamera new
	timercamera1.timer(et6);
	Button btn1 =(Button)findViewById(R.id.button1);
	//cameraAdmin = new CameraAdmin(Snapshot.this);
	btn1.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//cameraAdmin
		}
		
	});
}

public void startCamera(){
	//tbd 
	cameraAdmin = new CameraAdmin(this);
}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
public class CameraAdmin extends SurfaceView implements SurfaceHolder.Callback{
	private SurfaceHolder holder;
	private SurfaceView surfaceView;
	private Camera camera;
	private Camera.Parameters parameters;
	private boolean preview = false;
//	int defaultCameraId;
	    
	private ShutterCallback shutter = new ShutterCallback(){
	@Override
	    public void onShutter() {
		// TODO Auto-generated method stub
				
		}
	    	
	};
	private PictureCallback raw = new PictureCallback(){
    @Override
		public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub			
		}
	    	
	};
	private PictureCallback jpeg = new PictureCallback(){
	@Override
		public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
				
		}	    	
    };

	@SuppressWarnings("deprecation")
    public CameraAdmin(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
//			surfaceView = this;
//			this.context = context;
			holder = getHolder();
			holder.addCallback(this);
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	        }

		@Override
		public void surfaceChanged(final SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			try{
			parameters = camera.getParameters();
			parameters.setPictureFormat(PixelFormat.JPEG);
	        camera.setParameters(parameters);
	        camera.startPreview();
	        System.out.println("check surface changed");
			}catch(Exception e){
			e.printStackTrace();	
			}
			
			  camera.takePicture(shutter, null, null);
			
		}

		@SuppressLint("NewApi")
		@Override
		public void surfaceCreated(final SurfaceHolder holder) {
			// TODO Auto-generated method stub
			int numberOfCameras = Camera.getNumberOfCameras();
			System.out.println("check number: " + numberOfCameras);
			CameraInfo cameraInfo = new CameraInfo();
			for(int i = 0; i< numberOfCameras; i ++){
				Camera.getCameraInfo(i, cameraInfo);
				if(cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK){
					int defaultCameraId = i;
					System.out.println("debug " + defaultCameraId);
					try{
					if(camera == null){
						
					camera = Camera.open(defaultCameraId);
					System.out.println("back camera: "+ camera);
					}	
					}catch(Exception e){
						e.printStackTrace();
					}
							
				}
				else if(cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT){
	
				    int defaultCameraId = i;
					System.out.println("debug " + defaultCameraId);
					try{
					if(camera == null){
					camera = Camera.open(defaultCameraId);
					System.out.println("front camera: " + camera);
					}	
					}catch(Exception e){
						e.printStackTrace();
					}

				}
				
			try{
				camera.setPreviewDisplay(holder);
				System.out.println("check preview");
			}catch(Exception e){
				e.printStackTrace();
				if(camera != null)
				{
				camera.release();
				camera = null;
				}
				System.out.println("check preview fails");
			}
		}

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
	             camera.stopPreview();
	             camera.release();
	             camera = null;
	             
			System.out.println("check camera destoryed");
		} 
	    
	       
	    public void takePicture() { 
	        // Log.e(TAG, "==takePicture=="); 
	    	
	    	System.out.println("check take1: " + camera);
	        if (camera != null) { 
	        	camera.takePicture(shutter, null, null);
	        	System.out.println("check take2");
	        } 
	    } 
	    
	    public void voerTack(){
	    	camera.startPreview();
	    }
	    public void releaseCamera(){
	    	if(camera != null){
	    		camera.release();
	    		camera = null;
	    		System.out.println("CameraAdmin: release camera.");
	    	}
	    }
	} 
	public void action(){
		if(!isClicked){
			cameraAdmin.takePicture();
			isClicked = true;
//			finish();
		}else{
			cameraAdmin.voerTack();
			isClicked = false;
//			finish();
		}
	}
}


    
    
    

//SurfaceHolder or activity
//receive para from main Intent
//analysis data cast to integer
//create camera 
/*
 * create
 * change 
 * destroy
 * change F
 * timer
 * */
