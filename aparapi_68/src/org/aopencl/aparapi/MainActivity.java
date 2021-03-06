/*

Copyright 2013 Mahadevan GSS
Copyright 2013 Rahul Garg

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/

package org.aopencl.aparapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

public class MainActivity extends Activity {
	static{
		System.loadLibrary("aparapi");
	}
	public String getClInfo(){
		JarHelper.currentResources=getResources();		
		return Main.squares(32);
	}
	
	public void setResultText(String s){
		TextView textv = (TextView)findViewById(R.id.resultView);
                textv.setMovementMethod(ScrollingMovementMethod.getInstance());
		Log.i("CLInfo","CLInfo:"+s);
		textv.setText(s);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//    	Thread t = new Thread(new MyRunner(this));
//    	t.start();
        new UpdateResultTask(R.id.resultView).execute(); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class UpdateResultTask extends AsyncTask<Void, Void, Void> {
    	int viewId;
    	String result;
    	
    	public UpdateResultTask(int viewId){
    		this.viewId=viewId;
    	}
    	@Override
    	protected Void doInBackground(Void... params) {
    		result=getClInfo();
    		return null;
    	}
    	protected void onPostExecute(Void res) {
    		TextView textv = (TextView)findViewById(R.id.resultView);
    		textv.setText(result);
    	}          
    } 
}
