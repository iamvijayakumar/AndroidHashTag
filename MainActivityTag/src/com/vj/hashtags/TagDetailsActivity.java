package com.vj.hashtags;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.widget.TextView;

public class TagDetailsActivity extends Activity {
	
	TextView selectedTag;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        selectedTag = new TextView(this);
        //Get the content URI
        Uri uri = getIntent().getData();
        //strip off hashtag from the URI
        String tag=uri.toString().split("/")[3];
        
        selectedTag.setText("Selected hash tag :" +tag);
        
        setContentView(selectedTag);
	}

}
