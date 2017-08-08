package com.zoom.bundle_splashscreen.Zaker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zoom.bundle_splashscreen.R;


public class ZakerActivity extends Activity implements OnClickListener{
	
	private Button btnBelow,btnAbove;
	private TextView tvHint;
	private int lastDownY = 0;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_zaker);

		initView();

		initEvent();
	}


	public void initView() {
		btnBelow = (Button)this.findViewById(R.id.btn_Below);
		btnAbove = (Button)this.findViewById(R.id.btn_above);
		tvHint   = (TextView)this.findViewById(R.id.tv_hint);
		
		Animation ani = new AlphaAnimation(0f,1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE);
		ani.setRepeatCount(Animation.INFINITE);
		tvHint.startAnimation(ani);
		
	}

	public void initEvent() {
		btnBelow.setOnClickListener(this);
		btnAbove.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.btn_Below) {
			Toast.makeText(ZakerActivity.this, "这是下面一层按钮", Toast.LENGTH_LONG).show();

		} else if (i == R.id.btn_above) {
			Toast.makeText(ZakerActivity.this, "这是上面一层按钮", Toast.LENGTH_LONG).show();

		} else {
		}
		
	}

}
