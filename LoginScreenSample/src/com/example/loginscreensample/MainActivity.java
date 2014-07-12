package com.example.loginscreensample;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends Activity {

	private EditText username = null;
	private EditText password= null;
	private Button login;
	private TextView attempts;
	private TextView signup;
	ArrayList<ParseObject> results;
	int counter = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "VNNwXhwJnqPeR7mZRxTLSggeMCkaiT7oWXlZDgRU", "lZX2WWmo3qW4pEEinCtwB5CUrZ3Sqb6tVCFAO0Ge");
		username= (EditText)findViewById(R.id.editText1);
		password= (EditText)findViewById(R.id.editText2);
		signup=(Button)findViewById(R.id.textView6);
		attempts = (TextView)findViewById(R.id.textView5);
		attempts.setText(Integer.toString(counter));
		login= (Button)findViewById(R.id.button1);
	}
	
	public void login (View view){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
		query.selectKeys(Arrays.asList("userName", "password"));
		
		try {
			results = (ArrayList<ParseObject>)query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(username.getText().toString().length()== 0  && password.getText().toString().length( )== 0){
			Toast.makeText(getApplicationContext(), "Enter the credentials",Toast.LENGTH_SHORT).show();
		}
		ParseObject userCredentials=results.get(0);
		else if (userCredentials.getString("userName").equals(username.getText().toString()) && userCredentials.getString("password").equals(password.getText().toString())){
			
			
			Intent launchActivity1= new Intent(MainActivity.this,SignupActivity.class);
	         startActivity(launchActivity1);
		
		  
		    	       }	
		
		    	      else {
		    	    		// if credentials are wrong counter decrements
		    	    	    Toast.makeText(getApplicationContext(), "Wrong Credentials",
		    			    	      Toast.LENGTH_SHORT).show();
		    			    	      attempts.setBackgroundColor(Color.RED);	
		    			    	      counter--;
		    			    	      attempts.setText(Integer.toString(counter));
		    			    	      if(counter==0){
		    			    	          login.setEnabled(false);
		    	      }
		}
	}
	
	public void signup(View view){
	
		 Intent launchActivity1= new Intent(MainActivity.this,SignupActivity.class);
         startActivity(launchActivity1);
	}
	
}
