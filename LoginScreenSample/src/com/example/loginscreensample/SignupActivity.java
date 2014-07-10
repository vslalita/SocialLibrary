package com.example.loginscreensample;

import com.parse.ParseObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity {

	private Button register;
	private EditText usernameregister = null;
	private EditText passwordregister = null;
	private EditText confirmpassword = null;
	private EditText firstname = null;
	private EditText lastname = null;
	private EditText email = null;
	private EditText contact = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		register = (Button) findViewById(R.id.buttonCreateAccount);
		usernameregister = (EditText) findViewById(R.id.editTextUserName);
		passwordregister = (EditText) findViewById(R.id.editTextPassword);
		confirmpassword = (EditText) findViewById(R.id.editTextConfirmPassword);
		firstname = (EditText) findViewById(R.id.editTextFirstName);
		lastname = (EditText) findViewById(R.id.editTextLastName);
		email = (EditText) findViewById(R.id.editTextEmail);
		contact = (EditText) findViewById(R.id.editTextcontactNumber);

	}

	public void signUp(View view) {

		ParseObject registrationpage = new ParseObject("registrationPage");

		if ((usernameregister.getText().toString().length() == 0)
				|| ( passwordregister.getText().toString().length() == 0)
				|| (confirmpassword.getText().toString().length() == 0)
				|| (firstname.getText().toString().length() == 0)
				|| (lastname.getText().toString().length() == 0)
				|| (email.getText().toString().length() == 0)
				|| (contact.getText().toString().length() == 0)) {
			Toast.makeText(getApplicationContext(),
					"Every detail is mandatory", Toast.LENGTH_SHORT).show();
			//register.setEnabled(false);
		}

		else if (!passwordregister.getText().toString().equals(confirmpassword
				.getText().toString())) {
			Toast.makeText(getApplicationContext(),
					"Password and Confirm Password aren't same",
					Toast.LENGTH_SHORT).show();
		} else {

			registrationpage.put("userName", usernameregister.getText()
					.toString());
			registrationpage.put("password", passwordregister.getText()
					.toString());
			registrationpage.put("confirmPassword", confirmpassword.getText()
					.toString());
			registrationpage.put("firstName", firstname.getText().toString());
			registrationpage.put("lastName", lastname.getText().toString());
			registrationpage.put("eMail", email.getText().toString());
			registrationpage.put("contactNumber", Integer.valueOf(contact.getText().toString()));
			registrationpage.saveInBackground();
			Toast.makeText(getApplicationContext(),
					"You are now registered",
					Toast.LENGTH_SHORT).show();
			
		}
	}
}
