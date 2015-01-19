package com.vj.hashtags;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.vj.hashtags.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	//public static variable for demo purpose
	//we'll use this ArrayList in other activity
	public static ArrayList<String> tagList=new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private ListView tagListView;
	private EditText tagEditText;
	private Button addButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initialize variables
		tagListView = (ListView)findViewById(R.id.tagListView);
		tagEditText = (EditText)findViewById(R.id.tagEditText);
		addButton = (Button)findViewById(R.id.addButton);

		//Extend an ArrayAdapter so that we can make 
		//our TextView HashTag compatible 
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
				tagList){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView hashView;
				if (convertView == null) {
					hashView = new TextView(MainActivity.this);
				} else 
					hashView = (TextView) convertView;
				
				//Get the message from list and set as text
				String message = tagList.get(position);
				hashView.setText(message);
				
				//Pattern to find if there's a hash tag in the message
				//i.e. any word starting with a # and containing letter or numbers or _
				Pattern tagMatcher = Pattern.compile("[#]+[A-Za-z0-9-_]+\\b");
				
				//Scheme for Linkify, when a word matched tagMatcher pattern,
				//that word is appended to this URL and used as content URI
		        String newActivityURL = "content://com.vj.hashtags.tagdetailsactivity/";
		        
		        //Attach Linkify to TextView
		        Linkify.addLinks(hashView, tagMatcher, newActivityURL);
				
				return hashView;
			}
		};
		
		//assign custom adapter to messages listview
		tagListView.setAdapter(adapter);

		//Add text from EditText to our messages list on button click
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String message = tagEditText.getText().toString();
				if(message.trim()!="")
				{
					tagList.add(0,"#"+message);
					adapter.notifyDataSetChanged();
					tagEditText.setText("");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
