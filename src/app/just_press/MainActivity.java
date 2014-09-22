/**
 * 
 */
package app.just_press;






import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import app.just_press.R;

/**
 * @author Yicheng
 *
 */
public class MainActivity extends Activity{

	EditText word_inputText1, word_inputText2, min_number_inputText, max_number_inputText;

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;

	Button push_button, setting_button, returnButton, feed_back_button, clear_button;

	ViewFlipper view_flipper;

	TextView display_textView;
	LinearLayout tab_display_layout, setting_display_layout, setting_textView_layout;
	LinearLayout number_setting_layout, word_setting_layout, spinner_layout;
	RelativeLayout main_display_layout;
	TabHost setting_tabHost;
	TabWidget setting_tabWidget;
	int background_color;
	int text_color = Color.WHITE;

	String word1;
	String word2;
	String min_number;
	String max_number;
	String showType;
	/*
	CheckBox red_checkBox;
	CheckBox blue_checkBox;
	CheckBox green_checkBox;*/

	String randomNum;

	Spinner type_selection_spinner;

	CheckedTextView red_checkTextView, blue_checkTextView, green_checkTextView;


	float downXValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);


		sharedPref =  this.getPreferences(Context.MODE_PRIVATE);
		background_color = sharedPref.getInt("color", Color.rgb(200, 12, 51));

		word1 = sharedPref.getString("word1", "YES");
		word2 = sharedPref.getString("word2", "NO");
		min_number = sharedPref.getString("minNumber", "1");
		max_number = sharedPref.getString("maxNumber", "100");
		
		

		showType = sharedPref.getString("showType", "word");




		main_display_layout = (RelativeLayout) findViewById(R.id.main_display_relative_layout);
		main_display_layout.setBackgroundColor(background_color);

		setting_textView_layout = (LinearLayout) findViewById(R.id.setting_textView_layout);

		setting_textView_layout.setBackgroundColor(background_color);

		push_button = (Button) findViewById(R.id.push_button);

		if (background_color == Color.rgb(200, 12, 51)){
			push_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selector_red));
			setTextColorSelector(R.drawable.button_text_color_red);


		}
		if (background_color == Color.rgb(55, 93, 129)){
			push_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selector_blue));
			setTextColorSelector(R.drawable.button_text_color_blue);

		}
		if (background_color == Color.rgb(6, 88, 48)){
			push_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selector_green));
			setTextColorSelector(R.drawable.button_text_color_green);

		}

		//tab_display_layout = (LinearLayout) findViewById(R.id.tab_view_layout);
		setting_display_layout = (LinearLayout) findViewById(R.id.setting_base_layout);

		//setting_display_layout.setVisibility(View.INVISIBLE);

		word_inputText1 = (EditText) findViewById(R.id.word1_input);
		word_inputText2 = (EditText) findViewById(R.id.word2_input);
		min_number_inputText = (EditText) findViewById(R.id.number1_input);
		max_number_inputText = (EditText) findViewById(R.id.number2_input);
		
		word_inputText1.setText(word1);
		word_inputText2.setText(word2);
		min_number_inputText.setText(min_number);
		max_number_inputText.setText(max_number);

		min_number_inputText.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				min_number_inputText.setCursorVisible(true);
				min_number_inputText.setFocusableInTouchMode(true);


				return false;
			}
		});







		max_number_inputText.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				max_number_inputText.setCursorVisible(true);
				max_number_inputText.setFocusableInTouchMode(true);

				min_number = min_number_inputText.getText().toString();
				max_number = max_number_inputText.getText().toString();





				return false;
			}
		});

		min_number_inputText.setText(min_number);
		max_number_inputText.setText(max_number);
		word_inputText1.setText(word1);
		word_inputText2.setText(word2);

		word_setting_layout = (LinearLayout) findViewById(R.id.word_editText_layout);
		word_setting_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				word_inputText1.setCursorVisible(false);
				word_inputText2.setCursorVisible(false);
				word_inputText1.setFocusableInTouchMode(false);
				word_inputText2.setFocusableInTouchMode(false);

				hideKeyboard();
				return false;
			}
		});

		number_setting_layout = (LinearLayout) findViewById(R.id.number_editText_layout);

		number_setting_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				min_number_inputText.setCursorVisible(false);
				max_number_inputText.setCursorVisible(false);
				min_number_inputText.setFocusableInTouchMode(false);
				max_number_inputText.setFocusableInTouchMode(false);

				hideKeyboard();
				return false;
			}
		});


		spinner_layout = (LinearLayout) findViewById(R.id.spinner_layout);
		spinner_layout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				word_inputText1.setCursorVisible(false);
				word_inputText2.setCursorVisible(false);
				hideKeyboard();
				return false;
			}
		});


		//main_display_layout.setVisibility(View.INVISIBLE);
		//setting_tabHost = (TabHost) findViewById(R.id.tab_host);


		//setting_tabWidget = (TabWidget) findViewById(R.id.tabWidget);


		/*setting_tabWidget.addView(findViewById(R.id.tab1));
		setting_tabWidget.addView(findViewById(R.id.tab2));
		setting_tabWidget.addView(findViewById(R.id.tab3));*/


		//tab_display_layout.setVisibility(View.INVISIBLE);

		main_display_layout.setVisibility(View.VISIBLE);
		//setting_display_layout.setVisibility(View.INVISIBLE);
		//setContentView(R.id.main_display_linear_layout);



		//push_button.setBackgroundColor(background_color);
		display_textView = (TextView) findViewById(R.id.display_textView);

		if (showType.equals("word")){
			double randomNum = Math.random();
			if (randomNum < 0.5){

				display_textView.setText(word1);

			}
			else{
				display_textView.setText(word2);
			}
		}
		else if (showType.equals("number")){
			randomNum = generateRandomNum();

			display_textView.setText(randomNum);
		}







		push_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation fadeIn = new AlphaAnimation(0, 1);
				fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
				fadeIn.setDuration(1000);
				display_textView.setAnimation(fadeIn);
				display_textView.setVisibility(View.VISIBLE);


				showType = sharedPref.getString("showType", "word");

				if (showType.equals("word")){
					double randomNum = Math.random();
					if (randomNum < 0.5){
						
						display_textView.setText(word1);

					}
					else{
						display_textView.setText(word2);
					}
				}
				else if (showType.equals("number")){
					randomNum = generateRandomNum();

					display_textView.setText(randomNum);
				}




			}
		});




		type_selection_spinner = (Spinner) findViewById(R.id.type_selection_spinner);




		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_arrays, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		type_selection_spinner.setAdapter(adapter);


		type_selection_spinner.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				return false;
			}
		});

		type_selection_spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				word_inputText1.setCursorVisible(false);
				word_inputText2.setCursorVisible(false);
				min_number_inputText.setCursorVisible(false);
				max_number_inputText.setCursorVisible(false);
				hideKeyboard();

				if (parent.getItemAtPosition(position).toString().equals("Word")){
					word_setting_layout.setVisibility(View.VISIBLE);
					number_setting_layout.setVisibility(View.INVISIBLE);



				}

				if (parent.getItemAtPosition(position).toString().equals("Number")){
					word_setting_layout.setVisibility(View.INVISIBLE);
					number_setting_layout.setVisibility(View.VISIBLE);


				}


			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});








		setting_button = (Button) findViewById(R.id.setting_button);

		//setting_button.setBackgroundColor(background_color);


		setting_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent setting_tab_intent = new Intent("com.example.doordie.SETTINGVIEW");
				 * 
				startActivity(setting_tab_intent);*/

				word_inputText1.setCursorVisible(false);
				word_inputText2.setCursorVisible(false);
				min_number_inputText.setCursorVisible(false);
				max_number_inputText.setCursorVisible(false);

				word_inputText1.setFocusableInTouchMode(false);
				word_inputText2.setFocusableInTouchMode(false);
				min_number_inputText.setFocusableInTouchMode(false);
				max_number_inputText.setFocusableInTouchMode(false);

				//setContentView(R.id.setting_display_linear_layout);
				main_display_layout.setVisibility(View.INVISIBLE);
				setting_display_layout.setVisibility(View.VISIBLE);

				setting_display_layout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.buttom_up));
				showType = sharedPref.getString("showType", "word");

				if (showType.equals("word")){
					word_setting_layout.setVisibility(View.VISIBLE);
					number_setting_layout.setVisibility(View.INVISIBLE);
					type_selection_spinner.setSelection(0);
				}
				else if (showType.equals("number")){
					word_setting_layout.setVisibility(View.INVISIBLE);
					number_setting_layout.setVisibility(View.VISIBLE);
					type_selection_spinner.setSelection(1);
				}

				//setting_tabWidget.setVisibility(View.VISIBLE);


			}
		});


		word_inputText1.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				word_inputText1.setFocusableInTouchMode(true);
				word_inputText1.setCursorVisible(true);


				return false;

			}
		});

		word_inputText2.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				word_inputText2.setCursorVisible(true);
				word_inputText2.setFocusableInTouchMode(true);
				return false;

			}
		});

		returnButton = (Button) findViewById(R.id.return_button);


		//returnButton.setBackgroundColor(background_color);

		returnButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				min_number = min_number_inputText.getText().toString();
				max_number = max_number_inputText.getText().toString();

				if (Integer.parseInt(max_number) < Integer.parseInt(min_number)){
					Toast.makeText(MainActivity.this, "Max number has to be greater or eqaul to min number", Toast.LENGTH_LONG).show();;
				}
				else{




					editor = sharedPref.edit();


					if (number_setting_layout.isShown()){

						min_number = min_number_inputText.getText().toString();
						max_number = max_number_inputText.getText().toString();

						editor.putString("minNumber", min_number);
						editor.putString("maxNumber", max_number);
						editor.putString("showType", "number");
						editor.commit();



						randomNum = generateRandomNum();

						display_textView.setText(randomNum);


					}
					else if (word_setting_layout.isShown()){







						//returnButton.setBackgroundColor(background_color);
						//push_button.setBackgroundColor(background_color);
						//setting_button.setBackgroundColor(background_color);




						word1 = word_inputText1.getText().toString();
						word2 = word_inputText2.getText().toString();
						editor.putString("showType", "word");
						editor.putString("word1", word1);
						editor.putString("word2", word2);
						editor.commit();


						double randomNum = Math.random();
						if (randomNum < 0.5){

							display_textView.setText(word1);

						}
						else{
							display_textView.setText(word2);
						}
					}


					// TODO Auto-generated method stub

					if (red_checkTextView.isChecked()){
						background_color = Color.rgb(200, 12, 51);
						push_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selector_red));
						setTextColorSelector(R.drawable.button_text_color_red);

					}

					if (blue_checkTextView.isChecked()){
						background_color = Color.rgb(55, 93, 129);
						push_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selector_blue));
						setTextColorSelector(R.drawable.button_text_color_blue);
					}
					if (green_checkTextView.isChecked()){
						background_color = Color.rgb(6, 88, 48);
						push_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selector_green));
						setTextColorSelector(R.drawable.button_text_color_green);
					}


					editor.putInt("color", background_color);
					editor.commit();
					main_display_layout.setBackgroundColor(background_color);

					setting_textView_layout.setBackgroundColor(background_color);




					hideKeyboard();

					main_display_layout.setVisibility(View.VISIBLE);

					main_display_layout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.top_down));
					setting_display_layout.setVisibility(View.INVISIBLE);

				}

			}
		});



		final ViewFlipper flipper = (ViewFlipper) findViewById(R.id.setting_view_flipper);
		flipper.setInAnimation(MainActivity.this, R.anim.flip_in);
		flipper.setOutAnimation(MainActivity.this, R.anim.flip_out);



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
					if (65 < currentX - downXValue)
					{
						// Get a reference to the ViewFlipper
						// ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);
						// Set the animation
						flipper.setOutAnimation(MainActivity.this, R.anim.right_out);
						flipper.setInAnimation(MainActivity.this, R.anim.left_in);

						// Flip!
						if (flipper.getDisplayedChild()>= 1 && flipper.getDisplayedChild()<=flipper.getChildCount()-1){
							flipper.showPrevious();
						}
					}


					// going forwards: pushing stuff to the left
					if (downXValue - currentX> 65)
					{
						// Get a reference to the ViewFlipper
						/* ViewFlipper vf = (ViewFlipper) findViewById(R.id.details);*/
						// Set the animation
						flipper.setInAnimation(MainActivity.this, R.anim.right_in);
						flipper.setOutAnimation(MainActivity.this, R.anim.left_out);

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

		red_checkTextView = (CheckedTextView) findViewById(R.id.red_checkTextView);
		blue_checkTextView = (CheckedTextView) findViewById(R.id.blue_checkTextView);
		green_checkTextView = (CheckedTextView) findViewById(R.id.green_checkTextView);


		if (background_color == Color.rgb(200, 12, 51)){

			red_checkTextView.setChecked(true);
			blue_checkTextView.setChecked(false);
			green_checkTextView.setChecked(false);
		}

		if (background_color == Color.rgb(55, 93, 129)){
			red_checkTextView.setChecked(false);
			blue_checkTextView.setChecked(true);
			green_checkTextView.setChecked(false);
		}

		if (background_color == Color.rgb(6, 88, 48)){
			red_checkTextView.setChecked(false);
			blue_checkTextView.setChecked(false);
			green_checkTextView.setChecked(true);
		}



		red_checkTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (blue_checkTextView.isChecked() || green_checkTextView.isChecked()){
					red_checkTextView.setChecked(true);
					blue_checkTextView.setChecked(false);
					green_checkTextView.setChecked(false);
				}


			}
		});

		blue_checkTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (red_checkTextView.isChecked() || green_checkTextView.isChecked()){
					blue_checkTextView.setChecked(true);
					red_checkTextView.setChecked(false);
					green_checkTextView.setChecked(false);
				}
			}
		});



		green_checkTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (blue_checkTextView.isChecked() || red_checkTextView.isChecked()){
					green_checkTextView.setChecked(true);
					blue_checkTextView.setChecked(false);
					red_checkTextView.setChecked(false);
				}
			}
		});



		feed_back_button = (Button) findViewById(R.id.feedback_button);

		feed_back_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);

				dialogBuilder.setTitle("Feedback");
				dialogBuilder.setMessage("Write something about this app?");
				dialogBuilder.setCancelable(true);
				dialogBuilder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)  {
						// TODO Auto-generated method stub
						sendFeedbackViaEmail();
					}
				});

				dialogBuilder.setNegativeButton("No,thanks", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();

					}
				});

				AlertDialog dialog = dialogBuilder.create();
				dialog.show();




			}
		});


		clear_button = (Button) findViewById(R.id.clear_button);

		clear_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation fadeOut = new AlphaAnimation(1, 0);
				fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
				//fadeOut.setStartOffset(1000);
				fadeOut.setDuration(400);
				if (!display_textView.getText().equals("")){
					display_textView.setAnimation(fadeOut);
				}
			
				
				display_textView.setVisibility(View.GONE);
				
			}
		});














	}


	private void hideKeyboard(){
		if(getCurrentFocus()!=null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		moveTaskToBack(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			//preventing default implementation previous to 
			//android.os.Build.VERSION_CODES.ECLAIR
			moveTaskToBack(true);
			return false;
		}     
		return super.onKeyDown(keyCode, event);    
	}


	private void setTextColorSelector(int id){
		XmlResourceParser xrp = getResources().getXml(id);
		try {  
			ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);  
			push_button.setTextColor(csl);  
		} catch (Exception e) {

		} 
	}


	private void sendFeedbackViaEmail(){
		String to = "zhangyicheng1234@hotmail.com";

		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});



		//need this to prompts email client only
		email.setType("message/rfc822");

		startActivity(Intent.createChooser(email, "Choose an Email client :"));


	}

	private String generateRandomNum(){

		int minNum = Integer.parseInt(min_number);
		int maxNum = Integer.parseInt(max_number);

		return String.valueOf((int)(Math.floor(Math.random() * (maxNum - minNum + 1)) + minNum));



	}


}
