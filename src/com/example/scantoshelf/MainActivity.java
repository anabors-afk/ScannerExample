package com.example.scantoshelf;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity implements OnClickListener{

	private Button scanBtn;
	private TextView formatTxt, contentTxt;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }
    
    public void onClick(View v){
    	if(v.getId()==R.id.scan_button){
    		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
    		scanIntegrator.initiateScan();
    	}
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	if (scanningResult != null) {
    		String scanContent = scanningResult.getContents();
    		String scanFormat = scanningResult.getFormatName();
    		formatTxt.setText("FORMAT: " + scanFormat);
    		contentTxt.setText("CONTENT: " + scanContent);
    	}
    	else{
    	    Toast toast = Toast.makeText(getApplicationContext(), 
    	        "No scan data received!", Toast.LENGTH_SHORT);
    	    toast.show();
    	}
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//    }

}
