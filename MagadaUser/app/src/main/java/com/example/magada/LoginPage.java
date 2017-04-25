package com.example.magada;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Activity implements OnClickListener {
	
	EditText E1,E2;
	Button B1;
	static String S1;
	String S2,val;
	DatabaseHandler Db = new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magada_signin);
		E1 = (EditText) findViewById(R.id.eT1);
		 E2 = (EditText) findViewById(R.id.eT2);
		 TextView T1=(TextView) findViewById(R.id.tV1);
	     String string="<font color=#cc0029>*</font>";
	     T1.append(Html.fromHtml(string));
	     TextView T2=(TextView) findViewById(R.id.tV2);
	     T2.append(Html.fromHtml(string));
	     B1 = (Button) findViewById(R.id.B1);
	     B1.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		S1=E1.getText().toString();
     	S2=E2.getText().toString();
     	if(S1.equals("")||S2.equals(""))
     	{
     		Toast.makeText(this,"\tInput Fields cannot be empty", Toast.LENGTH_SHORT).show();
     	}
     	else
     	{
     		val=Db.dbpasswordvalidate(S1,S2);
	     	if(val == "Success")
	     	{  
	     		Intent i=new Intent(this,MagadaMain.class);
			    startActivity(i);
			    finish();
	     	}
	     	else if(val == "Failure")
	     	{
	     		new AlertDialog.Builder(this)
			    .setTitle("Error")
			    .setMessage("\tInvalid User ID or Password! Please retry.")
			    .setCancelable(false)
			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        @Override
					public void onClick(DialogInterface dialog, int which) { 
			        }
			     })
			     .show();
	     	}
	     	else
	     	{
	     		new AlertDialog.Builder(this)
			    .setTitle("Error")
			    .setMessage("\t\t\tUser Id doesn't exist.")
			    .setCancelable(false)
			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        @Override
					public void onClick(DialogInterface dialog, int which) { 
			        	
			        }
			     })
			     .show();
	     	}
     		
     	}
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK){
			   Intent i=new Intent(this,MainPage.class);
		       startActivity(i);
		       finish();		    	   
		}

		return super.onKeyDown(keyCode, event);
		}
}
