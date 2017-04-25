package com.example.magada;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MagadaMain extends Activity implements OnClickListener {
	private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    Integer i=0,j=0;
    
    private ExpandableListAdapter listAdapter;
    private ExpandableListView mDrawerList;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    DatabaseHandler db=new DatabaseHandler(this);
    NoDefaultSpinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6;
    Button B1;
	String S1,S2,S3,S4,S5,S6;
	
	//public static
	
	HashMap<String, Integer> map1 = new HashMap<String, Integer>();
	HashMap<String, Integer> map2 = new HashMap<String, Integer>();
	HashMap<String, Integer> map3 = new HashMap<String, Integer>();
	HashMap<String, Integer> map4 = new HashMap<String, Integer>();
	HashMap<String, Integer> map5 = new HashMap<String, Integer>();
	HashMap<String, Integer> map6 = new HashMap<String, Integer>();
	
	Integer val1,val2,val3,val4,val5,val6;
	
	Integer PI;
	Double SDA,SDH;
	ArrayAdapter<CharSequence>	adapter1,adapter2,adapter3,adapter4,adapter5,adapter6;
	
    // private static String[] mPwdTitles={"View Passwords List","Manage Passwords","Settings","Sign Out"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magada_main);
    
        map1.put( "0-10", 1);
        map1.put( "11-20", 2);
        map1.put( "21-30", 3);
        map1.put( "31-40", 4);
        map1.put( "41-50", 3);
        map1.put( "51-60", 2);
        map1.put( "61-70", 1);
        map1.put( "71-80", 0);
        map1.put( ">81", 0);

        map2.put( "Fully covered", 0);
        map2.put( "75% covered", 1);
        map2.put( "50% covered", 2);
        map2.put( "25% covered", 3);
        map2.put( "<10% covered", 4);
        map2.put( "No vegetation", 3);
        
        map3.put( "Transparent", 0);
        map3.put( "Pale", 1);
        map3.put( "Grey", 2);
        map3.put( "Light Green", 3);
        map3.put( "Olive Green", 4);
        map3.put( "Dark Green", 3);
        map3.put( "Muddy", 2);
        map3.put( "Black", 1);

        map4.put( ">10", 4);
        map4.put( "8-10", 3);
        map4.put( "4-6", 2);
        map4.put( "2-4", 1);
        map4.put( "<2", 0);
        
        map5.put( "<1", 0);
        map5.put( "1-2", 1);
        map5.put( "2-3", 2);
        map5.put( "3-4", 3);
        map5.put( "4-5", 4);
        map5.put( ">5", 5);

        map6.put( "3-4", 0);
        map6.put( "4-5", 1);
        map6.put( "5-6", 2);
        map6.put( "6-7", 3);
        map6.put( "7-8", 4);
        map6.put( "8-9", 3);
        map6.put( "9-10", 2);
        map6.put( ">10", 0);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);
       // mPwdTitles= db5.getAllPwd1();
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
 
    
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        mDrawerList.setAdapter(listAdapter);
        
        // set up the drawer's list view with items and click listener
      
      //  mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,mPwdTitles));
        
        
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        mDrawerList.setOnGroupClickListener(new OnGroupClickListener() {

    		@Override
    		public boolean onGroupClick(ExpandableListView parent, View v,
    				int groupPosition, long id) {
    			switch(groupPosition)
    			{
    			case (0):
    			//Intent i1=new Intent(Passwordmain_Navdrawer.this,Pwd_List_Expandlist.class);
			    //startActivity(i1);
			    //finish();
    			break;
    			case (1):
        		//Intent i2=new Intent(Passwordmain_Navdrawer.this,Password_Search.class);
    			//startActivity(i2);
    			//finish();
    			break;
    			case(3):
    				logoutbutton();
    			break;
    			}
    			return false;
    		}
    	});
        mDrawerList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				if(groupPosition==2)
				{
				switch(childPosition)
    			{
    			case (0):
    			//Intent i2=new Intent(Passwordmain_Navdrawer.this,Pwd_Add.class);
			    //startActivity(i2);
			    //finish();
    			break;
    			case(1):
    			//Intent i3=new Intent(Passwordmain_Navdrawer.this,Pwd_Change1.class);
			    //startActivity(i3);
			    //finish();
    			break;
    			case (2):
        			//Intent i4=new Intent(Passwordmain_Navdrawer.this,Pwd_Delete1.class);
    			    //startActivity(i4);
    			    //finish();
        			break;
    			}
				}
				else if(groupPosition==3)
				{
					switch(childPosition)
	    			{
	    			case (0):
	    			//Intent i5=new Intent(Passwordmain_Navdrawer.this,Secques_Validate.class);
				    //startActivity(i5);
				    //finish();
	    			break;
	    			case(1):
	    			new AlertDialog.Builder(MagadaMain.this)
	    		    .setTitle("Delete Profile")
	    		    .setMessage("Are you sure?")
	    		    .setCancelable(false)
	    		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    		        @Override
	    				public void onClick(DialogInterface dialog, int which) { 
	    		        	/*String s=db.dbdeletedetails(PasswordSaver_Activity.S1);
	    		        	 if(s == "SUCCESS")
	    	 	        	{
	    	 	        		alertbox1();
	    	 	        	}
	    	 	        	else if(s == "FAILURE")
	    	 	        	{
	    	 	        		alertbox2();
	    	 	        	}*/
	    	 	        }
	    	 	     })
	    	 	     .setNegativeButton("NO",
	    			   new DialogInterface.OnClickListener() {
	    			   public void onClick(DialogInterface dialog, int id) {
	    			   }
	    			   })
	    	 	   .show();	

	    			break;
				}
				}
				return false;
			
			}
		});
        
        //For custom spinner
        spinner1 = (NoDefaultSpinner) findViewById(R.id.spinner1);
	    adapter1 = ArrayAdapter.createFromResource(this, R.array.par1, android.R.layout.simple_spinner_item);
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner1.setAdapter(adapter1);
	    
	    spinner2 = (NoDefaultSpinner) findViewById(R.id.spinner2);
	    adapter2 = ArrayAdapter.createFromResource(this, R.array.par2, android.R.layout.simple_spinner_item);
	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter2);
	    
	    spinner3 = (NoDefaultSpinner) findViewById(R.id.spinner3);
	    adapter3 = ArrayAdapter.createFromResource(this, R.array.par3, android.R.layout.simple_spinner_item);
	    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner3.setAdapter(adapter3);
	    
	    spinner4 = (NoDefaultSpinner) findViewById(R.id.spinner4);
	    adapter4 = ArrayAdapter.createFromResource(this, R.array.par4, android.R.layout.simple_spinner_item);
	    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner4.setAdapter(adapter4);
	    
	    spinner5 = (NoDefaultSpinner) findViewById(R.id.spinner5);
	   	adapter5 = ArrayAdapter.createFromResource(this, R.array.par5, android.R.layout.simple_spinner_item);
	    adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner5.setAdapter(adapter5);
	    
	    spinner6 = (NoDefaultSpinner) findViewById(R.id.spinner6);
	    adapter6 = ArrayAdapter.createFromResource(this, R.array.par6, android.R.layout.simple_spinner_item);
	    adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner6.setAdapter(adapter6);
	    
	    B1=(Button) findViewById(R.id.B1);
	    B1.setOnClickListener(this);
     
    }

   // @Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        /*switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }*/
        return super.onOptionsItemSelected(item);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ExpandableListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        	
        }
    }
   

    private void selectItem(int position) {
        // update the main content by replacing fragments
      /*  Fragment fragment = new PwdFragment();
        Bundle args = new Bundle();
        args.putInt(PwdFragment.ARG_PWD_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();*/

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPwdTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
   /* public static class PwdFragment extends Fragment {
        public static final String ARG_PWD_NUMBER = "pwd_number";

        public PwdFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.test, container, false);
            ((TextView) rootView.findViewById(R.id.textView1)).setText("Password Manager V1.2");
            int i = getArguments().getInt(ARG_PWD_NUMBER);
            Log.e("I", String.valueOf(i));
            switch (i)
            {
            case(0):
            	Intent i1=new Intent(Passwordmain_Navdrawer.this,Pwd_List_Expandlist.class);
			    startActivity(i1);
			    finish();
            break;
            case(1):
            	
            break;
            case(2):
            	
            break;
            case(3):
            	
            break;               
            }
         //   String planet = getResources().getStringArray(R.array.planets_array)[i];

         // int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
           //                 "drawable", getActivity().getPackageName());
           // ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
           // getActivity().setTitle(planet);
           // if(i==1)
            //{
           
            //}
            return rootView;
        }
    }*/

    public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK){
			  logoutbutton();	    	   
		}
		return super.onKeyDown(keyCode, event);
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        
        // Adding child data
        listDataHeader.add("Notifications");
        listDataHeader.add("Search");
        listDataHeader.add("Settings");
        listDataHeader.add("Sign Out");
        
 
        // Adding child data
       /* List<String> MP = new ArrayList<String>();
        MP.add("ADD");
        MP.add("Change");
        MP.add("Delete");
       
 
        List<String> STNG = new ArrayList<String>();
        STNG.add("Change User Password");
        STNG.add("Delete Profile");
 */
 
        listDataChild.put(listDataHeader.get(0), null); // Header, Child data
        listDataChild.put(listDataHeader.get(1), null);        
        listDataChild.put(listDataHeader.get(2), null);
     //   listDataChild.put(listDataHeader.get(3), STNG);
        listDataChild.put(listDataHeader.get(3), null);
    }
    public void logoutbutton()
	{
		new AlertDialog.Builder(this)
	    .setTitle("Sign out")
	    .setMessage("Are you sure?")
	    .setCancelable(false)
	    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
	        @Override
			public void onClick(DialogInterface dialog, int which) {
	        	//db3.SetSid(PasswordSaver_Activity.S1,0);
	        	Intent i=new Intent(MagadaMain.this,MainPage.class);
			    startActivity(i);
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
    public void alertbox1()
    {
    	new AlertDialog.Builder(this)
        .setTitle("")
        .setMessage("Profile deleted successfully.")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
    		public void onClick(DialogInterface dialog, int which) {
            	Intent i3=new Intent(MagadaMain.this,MainPage.class);
    			startActivity(i3);
    			finish(); 
            }
         })
         .show();

    }
    public void alertbox2()
    {	
    //Log.d("Inside Delete1","s");
    new AlertDialog.Builder(this)
        .setTitle("Error")
        .setMessage("Database error")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
    		public void onClick(DialogInterface dialog, int which) { 
                
            }
         })
         .show();
    }

	@Override
	public void onClick(View arg0) {
		
	 	if(spinner1.getSelectedItem()==null || spinner2.getSelectedItem()==null || spinner3.getSelectedItem()==null || spinner4.getSelectedItem()==null || spinner5.getSelectedItem()==null || spinner6.getSelectedItem()==null)
	 	{
	 		Toast.makeText(this,"All Input Fields are mandatory", Toast.LENGTH_SHORT).show();
	 	}
	 	else
	 	{
		 	S1=spinner1.getSelectedItem().toString();
		 	S2=spinner2.getSelectedItem().toString();
	        S3=spinner3.getSelectedItem().toString();
	        S4=spinner4.getSelectedItem().toString();
	        S5=spinner5.getSelectedItem().toString();
	        S6=spinner6.getSelectedItem().toString();
	        
         	System.out.println(S1+" "+S2+" "+S3+" "+" "+S4+" "+S5+" "+S6);
         	
         	System.out.println(map1.get(S1));
         	System.out.println(map2.get(S2));
         	System.out.println(map3.get(S3));
         	System.out.println(map4.get(S4));
         	System.out.println(map5.get(S5));
         	System.out.println(map6.get(S6));
         	
         	val1=map1.get(S1);
         	val2=map2.get(S2);
         	val3=map3.get(S3);
         	val4=map4.get(S4);
         	val5=map5.get(S5);
         	val6=map6.get(S6);
         	
         	PI=val1+val2+val3+val4+val5+val6;
         	SDA=(double)(PI*100);
         	SDH=(double)(SDA * 2.5);
         	
         	LayoutInflater factory = LayoutInflater.from(this);
         	final View PopuptextEntryView = factory.inflate(R.layout.magada_sdview,null);
         	AlertDialog.Builder Popup= new AlertDialog.Builder(this);
         	Popup.setTitle("Productivity Info :");
         	Popup.setView(PopuptextEntryView);
         	final TextView T1=(TextView)PopuptextEntryView.findViewById(R.id.tV2);
    	    final TextView T2=(TextView)PopuptextEntryView.findViewById(R.id.tV5);
    	    final TextView T3=(TextView)PopuptextEntryView.findViewById(R.id.tV7);
    	    
    	    T1.setText(PI.toString());
    	    T2.setText(SDA.toString());
    	    T3.setText(SDH.toString());
    	    Popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	        @Override
    			public void onClick(DialogInterface dialog, int which) { 
    	        	
    	        }
    	     });
    	    Popup.setNegativeButton("Send Request",
 				   new DialogInterface.OnClickListener() {
 				   public void onClick(DialogInterface dialog, int id) {
 					  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
 					   MagadaStats Ms = new MagadaStats(LoginPage.S1, val1, val2,val3, val4, val5, val6, PI, SDA, SDH, timeStamp);
 					   String temp=db.dbuserstatscreate(Ms);
 						if(temp.equals("SUCCESS"))
 	        			{
 							Showtoast("Request sent to admin successfully");
 	        			}
 	        			else
 	        			{
 	        				Showtoast("Error occured while sending request");
 	        			}
 				   }
				
 				});
    	    Popup.show();
    	    Popup.setCancelable(false);
    	    spinner1.setAdapter(adapter1);
    	    spinner2.setAdapter(adapter2);
    	    spinner3.setAdapter(adapter3);
    	    spinner4.setAdapter(adapter4);
    	    spinner5.setAdapter(adapter5);
    	    spinner6.setAdapter(adapter6);
    	    
    	    	    
	 	}   
		
	}
	private void Showtoast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
	}
}
