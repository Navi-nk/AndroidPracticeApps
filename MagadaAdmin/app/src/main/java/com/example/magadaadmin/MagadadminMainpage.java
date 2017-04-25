package com.example.magadaadmin;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MagadadminMainpage extends Activity implements OnClickListener,DatePickerFragment.TheListener {

	Button B1;
	Button B2;
	Button B3;
	int f=0;
	NoDefaultSpinner spinner1,spinner2,spinner3,spinner4;
	Spinner dateSpinner,dateSpinner1;
	DatabaseHandler Db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magadaadminmainpage);
		super.onCreate(savedInstanceState);
		dateSpinner = (Spinner)findViewById(R.id.spinnerdate);
		dateSpinner1 = (Spinner)findViewById(R.id.spinnerdate1);
		
		spinner1 = (NoDefaultSpinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence>	adapter1 = ArrayAdapter.createFromResource(this, R.array.fish_type, android.R.layout.simple_spinner_item);
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter1);
	    
	    spinner2 = (NoDefaultSpinner) findViewById(R.id.spinner2);
	    ArrayAdapter<CharSequence>	adapter2 = ArrayAdapter.createFromResource(this, R.array.district, android.R.layout.simple_spinner_item);
	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter2);
	    
	    spinner3 = (NoDefaultSpinner) findViewById(R.id.spinner3);
	    ArrayAdapter<CharSequence>	adapter3 = ArrayAdapter.createFromResource(this, R.array.taluk, android.R.layout.simple_spinner_item);
	    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner3.setAdapter(adapter3);
	    
	    spinner4 = (NoDefaultSpinner) findViewById(R.id.spinner4);
	    ArrayAdapter<CharSequence>	adapter4 = ArrayAdapter.createFromResource(this, R.array.village, android.R.layout.simple_spinner_item);
	    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner4.setAdapter(adapter4);
	    
		View.OnTouchListener Spinner_OnTouch = new View.OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	            if (event.getAction() == MotionEvent.ACTION_UP) {
	                showDatePickerDialog(v);
	            }
	            return true;
	        }
	    };
		View.OnTouchListener Spinner_OnTouch1 = new View.OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	            if (event.getAction() == MotionEvent.ACTION_UP) {
	                showDatePickerDialogflag(v);
	            }
	            return true;
	        }
	    };
	    dateSpinner.setOnTouchListener(Spinner_OnTouch);
	    dateSpinner1.setOnTouchListener(Spinner_OnTouch1);
	}
		@Override
		public void returnDate(String date) {
			// TODO Auto-generated method stub
		
				 	 List<String> spinnerArray =  new ArrayList<String>();
				 	 spinnerArray.add(date);
				 	 

				 	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerArray);

				 	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				 	
				 	 if(f==0)
				 	 dateSpinner.setAdapter(adapter);	  
				 	 else
				 		dateSpinner1.setAdapter(adapter);
				 	   

				     
				 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.magadaadmin, menu);
		return true;
	}
	@Override
	public void onClick(View V) {
		/*switch (V.getId())
		{
		case(R.id.about):
		  Intent i2=new Intent(MagadadminMain.this,About.class);
	      startActivity(i2);
	      finish();
			break;
		case (R.id.signin):
			Intent i=new Intent(MagadadminMain.this,LoginPage.class);
		    startActivity(i);
		    finish();
			break;
		case (R.id.signup):
			//I1=db1.dbUserCheck();
		//if(I1 == I2)
		//{
			Intent i1=new Intent(MagadadminMain.this,SignupPage.class);
	        startActivity(i1);
	        finish();
		//}
	/*	else
		{
			new AlertDialog.Builder(this)
		    .setTitle("Error")
		    .setMessage("The device is already registered by a user.Please Login with correct credentials")
		    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        @Override
				public void onClick(DialogInterface dialog, int which) { 
		        	Intent i2=new Intent(MainPage.this,PasswordSaver_Activity.class);
				    startActivity(i2);
				    finish();
		        }
		     })
		     .show();
			
		}--    
			break;
		}*/
	}
	public void showDatePickerDialog(View v) {
		f=0;
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");

	}
	public void showDatePickerDialogflag(View v) {
		f=1;
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK){
			new AlertDialog.Builder(this)
		    .setTitle("Exit")
		    .setMessage("Are you sure?")
		    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
		        @Override
				public void onClick(DialogInterface dialog, int which) { 
		        	finish();
		        }
		     })
		     .setNegativeButton("NO",
        		   new DialogInterface.OnClickListener() {
        		   public void onClick(DialogInterface dialog, int id) {
        		   }
        		   })
		     .show();
		       		    	   
		}

		return super.onKeyDown(keyCode, event);
		}

}
