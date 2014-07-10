package com.example.sociallibrary;

import java.util.ArrayList;

import com.parse.ParseObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookViewAdapter extends BaseAdapter {
	Context context;
	ArrayList<ParseObject> booksList;
	
	BookViewAdapter(Context context,ArrayList<ParseObject> booksList) {
		this.context=context;
		this.booksList=booksList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return booksList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return booksList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//ViewHolder holder = new ViewHolder();
		View bookGridCell = LayoutInflater.from(context).inflate(R.layout.book_grid_view, null);
		
		com.parse.ParseImageView bookImage = (com.parse.ParseImageView) bookGridCell.findViewById(R.id.BookImageGridView);
		TextView bookName=(TextView)bookGridCell.findViewById(R.id.BookNameGridView);
		
		bookImage.setPlaceholder(null);
		bookImage.setParseFile(booksList.get(position).getParseFile("BookImage"));
		bookName.setText(booksList.get(position).getString("BookName"));
		
		return bookGridCell;
	}

}
