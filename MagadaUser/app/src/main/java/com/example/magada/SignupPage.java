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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class SignupPage extends Activity implements OnClickListener {
	EditText E1,E2,E3,E4,E5,E6;
	Button B1;
	String S1,S2,S3,S4,S5,S6,S7,S8,S9,S10;
	NoDefaultSpinner spinner1,spinner2,spinner3,spinner4;
	DatabaseHandler Db=new DatabaseHandler(this);
	MagadaUser magadaUser=new MagadaUser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magada_signup);
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
	    
	     B1 = (Button) findViewById(R.id.B1);
	     E1=(EditText) findViewById(R.id.eT1);
	     E2=(EditText) findViewById(R.id.eT2);
	     E3=(EditText) findViewById(R.id.eT3);
	     E4=(EditText) findViewById(R.id.eT4);
	     E5=(EditText) findViewById(R.id.eT5);
	     E6=(EditText) findViewById(R.id.eT6);
	     
	     TextView T1=(TextView) findViewById(R.id.textV1);
	     String string="<font color=#cc0029>*</font>";
	     T1.append(Html.fromHtml(string));
	     
	     TextView T2=(TextView) findViewById(R.id.textV2);
	     T2.append(Html.fromHtml(string));
	     
	     TextView T3=(TextView) findViewById(R.id.textV3);
	     T3.append(Html.fromHtml(string));
	     
	     TextView T4=(TextView) findViewById(R.id.textV4);
	     T4.append(Html.fromHtml(string));
	     
	     TextView T5=(TextView) findViewById(R.id.textV5);
	     T5.append(Html.fromHtml(string));
	     
	     TextView T6=(TextView) findViewById(R.id.textV6);
	     T6.append(Html.fromHtml(string));
	     
	     TextView T7=(TextView) findViewById(R.id.TextV7);
	     T7.append(Html.fromHtml(string));
	     
	     TextView T8=(TextView) findViewById(R.id.TextV8);
	     T8.append(Html.fromHtml(string));
	     
	     TextView T9=(TextView) findViewById(R.id.TextV9);
	     T9.append(Html.fromHtml(string));
	    
	     TextView T10=(TextView) findViewById(R.id.TextV10);
	     T10.append(Html.fromHtml(string));
	    
	     B1.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String temp;
		S1=E1.getText().toString();
        S2=E2.getText().toString();
        S3=E3.getText().toString();
        S4=E4.getText().toString();
        S5=E5.getText().toString();
        S6=E6.getText().toString();
        S7=spinner1.getSelectedItem().toString();
        S8=spinner2.getSelectedItem().toString();
        S9=spinner3.getSelectedItem().toString();
        S10=spinner4.getSelectedItem().toString();
        
        if(S1.matches("") || S2.matches("")||S3.matches("")||S4.matches("")||S5.matches("")||S6.matches("")||S7.matches("")||S8.matches("")||S9.matches("")||S10.matches(""))
        {
       	 Toast.makeText(this,"All Input Fields are mandatory", Toast.LENGTH_SHORT).show();
       	 return;
        }
        else if(!((S6.length()==10)&&(S6.matches("[0-9]+"))))
        {
        	System.out.println(S6.length());
        	System.out.println(S6.matches("[0-9]+"));
        	if(!(S6.matches("[0-9]+")))
        	Toast.makeText(this,"Mobile number cannot contain characters", Toast.LENGTH_SHORT).show();
        	else
        		Toast.makeText(this,"Mobile number has to be of 10 digits", Toast.LENGTH_SHORT).show();	
        	return;
        }
        else if(!((S5.matches("[0-9]+"))||(S5.matches("([0-9]*)\\.([0-9]*)"))))
        {
        	Toast.makeText(this,"Hectre holdings cannot contain characters", Toast.LENGTH_SHORT).show();
        	return;
        }
        else
        {
        	if(S2.equals(S3))
        	{
        		System.out.println(S1+' '+S2+' '+S3+' '+S4+' '+S5+' '+S6+' '+S7+' '+S8+' '+S9);
        		    magadaUser.set_id(S1);
        		    magadaUser.set_pwd(S2);
        		    magadaUser.set_name(S4);
        		    magadaUser.set_hechldg(Double.parseDouble(S5));
        		    magadaUser.set_mobile(S6);
        		    magadaUser.set_species(S7);
        		    magadaUser.set_dist(S8);
        		    magadaUser.set_taluk(S9);
        		    magadaUser.set_village(S10);
        		    	temp=Db.dbusercreate(magadaUser);
        		            		
        			if(temp.equals("SUCCESS"))
        			{
        				new AlertDialog.Builder(this)
        			    .setTitle("")
        			    .setMessage("Registration successfull")
        			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        			        @Override
        					public void onClick(DialogInterface dialog, int which) { 
        			        	Intent i=new Intent(SignupPage.this,MainPage.class);
        					    startActivity(i);
        					    finish();
        			        }
        			      
        			     })
        			     .setCancelable(false)
        			     .show();
        			
        			}
        			else
        			{
        				new AlertDialog.Builder(this)
        			    .setTitle("Error")
        			    .setMessage("User ID Already Present.Please use any other.")
        			    .setCancelable(false)
        			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        			        @Override
        					public void onClick(DialogInterface dialog, int which) {
        			        	
        			            
        			        }
        			     })
        			     .show();
        			}
        		   	
        	}
        	else
        	{
            	Toast.makeText(this,"Password entered doesnot match", Toast.LENGTH_SHORT).show();
            	return;
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
