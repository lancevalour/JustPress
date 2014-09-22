package app.just_press;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import app.just_press.R;


/**
 * 
 */


/**
 * @author Yicheng
 *
 */


public class StartingView extends Activity{
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	float downXValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.starting_view);

		 Handler handler = new Handler(); 
		    handler.postDelayed(new Runnable() { 
		         public void run() { 
		        	 Intent main_view_intent = new Intent("app.just_press.MAINACTIVITY");
						startActivity(main_view_intent);
		         } 
		    }, 700); 
		

		







		/*		if (firstLaunch){

		setContentView(R.layout.view_filpper);

		Thread startingApp = new Thread(){
			public void run() {
				// TODO Auto-generated method stub
				try {

					//sleep(1000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					editor.putBoolean("firstLaunch", false);
					editor.commit();
				final ViewFlipper flipper = (ViewFlipper) findViewById(R.id.view_flipper);
				flipper.setInAnimation(StartingView.this, R.anim.flip_in);
				flipper.setOutAnimation(StartingView.this, R.anim.flip_out);

				flipper.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						flipper.showNext();
						//setContentView(R.layout.main_activity);
						if (flipper.getDisplayedChild()== flipper.getChildCount()-1){	
							flipper.setClickable(false);
							new Thread(){
								public void run(){
									try {
										sleep(2000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									finally{
										Intent main_view_intent = new Intent("com.example.doordie.MAINACTIVITY");
										startActivity(main_view_intent);
									}

								}
							}.start();


							}

						}
						}

					}
				});
				//float downXValue;
				final Button enterButton = (Button) findViewById(R.id.enter_button);

				enterButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						flipper.setClickable(false);
						enterButton.setClickable(false);
						flipper.setEnabled(false);

						new Thread(){
							public void run(){
								try {
									sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								finally{
									Intent main_view_intent = new Intent("com.example.doordie.MAINACTIVITY");
									startActivity(main_view_intent);
								}

							}
						}.start();
					}
				});

				flipper.setOnTouchListener(new View.OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						// TODO Auto-generated method stub
						switch (arg1.getAction())
				        {
				            case MotionEvent.ACTION_DOWN:
				            {
				                // store the X value when the user's finger was pressed down
				                downXValue = arg1.getX();
				                break;
				            }

				            case MotionEvent.ACTION_UP:
				            {
				                // Get the X value when the user released his/her finger
				                float currentX = arg1.getX();            

				                // going backwards: pushing stuff to the right


				                if (downXValue < currentX)
				                {
				                    // Get a reference to the ViewFlipper
				                    // ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
				                     // Set the animation
				                	flipper.setInAnimation(StartingView.this, R.anim.flip_in);

				                      // Flip!
				                	if (flipper.getDisplayedChild()>= 1 && flipper.getDisplayedChild()<=flipper.getChildCount()-1){
				                		flipper.showPrevious();
				                	}
				                }


				                // going forwards: pushing stuff to the left
				                if (downXValue > currentX)
				                {
				                    // Get a reference to the ViewFlipper
				                    ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
				                     // Set the animation
				                	flipper.setInAnimation(StartingView.this, R.anim.flip_out);
				                      // Flip!

				                	if (flipper.getDisplayedChild()>= 0 && flipper.getDisplayedChild()<flipper.getChildCount()-1){
				                		flipper.showNext();
				                	}
				                }
				                break;
				            }
				        }

				        // if you return false, these actions will not be recorded
				        return true;
					}
				});


			}
		 */
		/*};

		startingApp.start();*/
		/*}*/
		/*	else{*/

		/*	}*/
	}









	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();



	}









	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();



		//setContentView(R.layout.main_activity);

	}

	protected void onResume(){
		super.onResume();
		//setContentView(R.layout.main_activity);
		/*editor.putBoolean("firstLaunch", false);
		editor.commit();*/


	}

	protected void onRestart(){
		super.onRestart();


	}



	protected void onStop(){
		super.onStop();

		/*editor.putBoolean("firstLaunch", true);
		editor.commit();*/
	}


	protected void onDestroy(){
		super.onDestroy();

	}





}
