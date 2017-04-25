package com.example.magadaadmin;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment
implements DatePickerDialog.OnDateSetListener {

@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current date as the default date in the picker
	final Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	listener = (TheListener) getActivity(); 
	// Create a new instance of DatePickerDialog and return it
	return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	public interface TheListener{
	    public void returnDate(String date);
	}
	TheListener listener;
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
	// Do something with the date chosen by the user
		Log.d("Date:", Integer.toString(day)+Integer.toString(month)+Integer.toString(year));
		String date=Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year);
		Log.d("Date:",date);
		if (listener != null) 
		{
		  listener.returnDate(date); 

		}
	}

}