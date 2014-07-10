package com.example.sociallibrary;

import java.util.ArrayList;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class BookListActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		ParseConnection.setupConncetion(this);
		
		ArrayList<ParseObject> booksList=new ArrayList<ParseObject>();
		ArrayList<String> bookList=new ArrayList<String>();
		
		//Querying for data related to Books
		ParseQuery<ParseObject> books = ParseQuery.getQuery("Books");
		try {
			booksList=(ArrayList<ParseObject>)books.find();
			if(booksList.size()<=0){
				Toast.makeText(this, "There are no Books currently",Toast.LENGTH_SHORT).show();
			}
			else{
				for(int i=0;i<booksList.size();i++){
					bookList.add(booksList.get(i).getString("BookName"));
					System.out.println("Book"+bookList.get(i));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		GridView booksGridView=(GridView)findViewById(R.id.BooksGridView);
		AutoCompleteTextView autoBookSearch=(AutoCompleteTextView)findViewById(R.id.SearchForBookView);
		Button createBook=(Button)findViewById(R.id.CreateBookView);
		TextView viewMyCurrentBooks=(TextView)findViewById(R.id.CurrentBookDetailView);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,bookList);
		
		autoBookSearch.setAdapter(adapter);
		booksGridView.setAdapter(new BookViewAdapter(this,booksList));
		
		booksGridView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			 redirectActivity(BookDetailActivity.class);
			}
		});
		
		autoBookSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				redirectActivity(BookDetailActivity.class);
			}
		});
		
		createBook.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				redirectActivity(CreateBookActivity.class);
			}
		});
		
		viewMyCurrentBooks.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				redirectActivity(UserCurrentBookActivity.class);
			}
		});
	}

	public void redirectActivity(Class classname){
		Intent bookDetailIntent=new Intent(BookListActivity.this,classname);
		startActivity(bookDetailIntent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
