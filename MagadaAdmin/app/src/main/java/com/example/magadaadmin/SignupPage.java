package com.example.magadaadmin;

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

public class SignupPage extends Activity implements OnClickListener {
	EditText E1,E2,E3,E4,E5;
	Button B1;
	String S1,S2,S3,S4,S5;
	
	DatabaseHandler Db=new DatabaseHandler(this);
	MagadAdmin magadaUser=new MagadAdmin();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magadadmin_signup);
	    
	     B1 = (Button) findViewById(R.id.B1);
	     E1=(EditText) findViewById(R.id.eT1);
	     E2=(EditText) findViewById(R.id.eT2);
	     E3=(EditText) findViewById(R.id.eT3);
	     E4=(EditText) findViewById(R.id.eT4);
	     
	     E5=(EditText) findViewById(R.id.eT6);
	     
	     TextView T1=(TextView) findViewById(R.id.textV1);
	     String string="<font color=#cc0029>*</font>";
	     T1.append(Html.fromHtml(string));
	     
	     TextView T2=(TextView) findViewById(R.id.textV2);
	     T2.append(Html.fromHtml(string));
	     
	     TextView T3=(TextView) findViewById(R.id.textV3);
	     T3.append(Html.fromHtml(string));
	     
	     TextView T4=(TextView) findViewById(R.id.textV4);
	     T4.append(Html.fromHtml(string));
	     
	     TextView T5=(TextView) findViewById(R.id.textV6);
	     T5.append(Html.fromHtml(string));
	     
	    
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
        
        if(S1.matches("") || S2.matches("")||S3.matches("")||S4.matches("")||S5.matches(""))
        {
       	 Toast.makeText(this,"All Input Fields are mandatory", Toast.LENGTH_SHORT).show();
       	 return;
        }
        else if(!((S5.length()==10)&&(S5.matches("[0-9]+"))))
        {
        	System.out.println(S5.length());
        	System.out.println(S5.matches("[0-9]+"));
        	if(!(S5.matches("[0-9]+")))
        	Toast.makeText(this,"Mobile number cannot contain characters", Toast.LENGTH_SHORT).show();
        	else
        		Toast.makeText(this,"Mobile number has to be of 10 digits", Toast.LENGTH_SHORT).show();	
        	return;
        }
        else
        {
        	if(S2.equals(S3))
        	{
        		System.out.println(S1+' '+S2+' '+S3+' '+S4+' '+S5);
        		    magadaUser.set_id(S1);
        		    magadaUser.set_pwd(S2);
        		    magadaUser.set_name(S4);
        		    
        		    magadaUser.set_mobile(S5);
        		    
        		    temp=Db.dbusercreate(magadaUser);
        		            		
        			if(temp.equals("SUCCESS"))
        			{
        				new AlertDialog.Builder(this)
        			    .setTitle("")
        			    .setMessage("Registration successfull")
        			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        			        @Override
        					public void onClick(DialogInterface dialog, int which) { 
        			        	Intent i=new Intent(SignupPage.this,MagadadminMain.class);
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
			   Intent i=new Intent(this,MagadadminMain.class);
		       startActivity(i);
		       finish();		    	   
		}

		return super.onKeyDown(keyCode, event);
		}

}
