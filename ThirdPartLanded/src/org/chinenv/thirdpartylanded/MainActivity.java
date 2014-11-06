package org.chinenv.thirdpartylanded;

import org.chinenv.thirdpartylanded.bean.AppConstants;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class MainActivity extends ActionBarActivity {
	private static final String TAG ="MainActivity";
	BaseUiListener listener;
	LinearLayout   login;
	
	private static final String SCOPE = "get_user_info,get_simple_userinfo,get_user_profile,get_app_friends,upload_photo,"
			+ "add_share,add_topic,list_album,upload_pic,add_album,set_user_face,get_vip_info,get_vip_rich_info"
			+ ",get_intimate_friends_weibo,match_nick_tips_weibo";
	
	private Tencent mTencent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTencent = Tencent.createInstance(AppConstants.APP_ID, this.getApplicationContext());
		listener = new BaseUiListener();
		
		login = (LinearLayout)findViewById(R.id.login_btn);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login();
			}
		});
		
		
	}
	
	public void login(){
		if (!mTencent.isSessionValid()){
			mTencent.login(this, SCOPE, listener);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mTencent.onActivityResult(requestCode, resultCode, data);
	}
	
	private class BaseUiListener implements IUiListener{

		@Override
		public void onCancel() {
			
		}

		@Override
		public void onComplete(Object response) {
			Log.i(TAG, "--->response: "+response);
			JSONObject object = (JSONObject) response;
			Log.i(TAG, "--->JSONObject: "+object);
			try {
				Log.i(TAG, "--------->ret: 					"+object.getInt("ret"));
				Log.i(TAG, "--------->pay_token: 			"+object.getString("pay_token"));
				Log.i(TAG, "--------->pf: 					"+object.getString("pf"));
				Log.i(TAG, "--------->query_authority_cost: "+object.getString("query_authority_cost"));
				Log.i(TAG, "--------->authority_cost: 		"+object.getString("authority_cost"));
				Log.i(TAG, "--------->openid: 				"+object.getString("openid"));
				Log.i(TAG, "--------->expires_in: 			"+object.getString("expires_in"));
				Log.i(TAG, "--------->pfkey: 				"+object.getString("pfkey"));
				Log.i(TAG, "--------->msg: 					"+object.getString("msg"));
				Log.i(TAG, "--------->access_token: 		"+object.getString("access_token"));
				Log.i(TAG, "--------->login_cost: 			"+object.getString("login_cost"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}

		@Override
		public void onError(UiError e) {
			// TODO Auto-generated method stub
			
		}};
}
