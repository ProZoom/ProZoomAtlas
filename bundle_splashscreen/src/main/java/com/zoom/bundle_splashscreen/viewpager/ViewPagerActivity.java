package com.zoom.bundle_splashscreen.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zoom.bundle_splashscreen.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity implements OnPageChangeListener {

	private List<ImageView> imageViewList;
	private TextView tvDescription;
	private LinearLayout llPoints;
	private String[] imageDescriptions;
	private int previousSelectPosition = 0;
	private ViewPager mViewPager;
	private boolean isLoop = true;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		}
	};


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_viewpager);
		// 自动切换页面功能
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLoop) {
					SystemClock.sleep(2000);
					handler.sendEmptyMessage(0);
				}
			}
		}).start();

		initView();

	}

	public void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
    	tvDescription = (TextView) findViewById(R.id.tv_image_description);
    	llPoints = (LinearLayout) findViewById(R.id.ll_points);
    	
    	prepareData();
    	
    	ViewPagerAdapter adapter = new ViewPagerAdapter();
    	mViewPager.setAdapter(adapter);
    	mViewPager.setOnPageChangeListener(this);
    	
    	tvDescription.setText(imageDescriptions[previousSelectPosition]);
    	llPoints.getChildAt(previousSelectPosition).setEnabled(true);
    	
    	/**
    	 * 2147483647 / 2 = 1073741820 - 1 
    	 */
    	int n = Integer.MAX_VALUE / 2 % imageViewList.size();
    	int itemPosition = Integer.MAX_VALUE / 2 - n;
    	
    	mViewPager.setCurrentItem(itemPosition);
	}
	
	 private void prepareData() {
	    	imageViewList = new ArrayList<ImageView>();
	    	int[] imageResIDs = getImageResIDs();
	    	imageDescriptions = getImageDescription();
	    	
	    	ImageView iv;
	    	View view;
	    	for (int i = 0; i < imageResIDs.length; i++) {
				iv = new ImageView(this);
				iv.setBackgroundResource(imageResIDs[i]);
				imageViewList.add(iv);
				
				// 添加点view对象
				view = new View(this);
				view.setBackgroundDrawable(getResources().getDrawable(R.drawable.point_background));
				LayoutParams lp = new LayoutParams(5, 5);
				lp.leftMargin = 10;
				view.setLayoutParams(lp);
				view.setEnabled(false);
				llPoints.addView(view);
			}
	    }
	    
	    private int[] getImageResIDs() {
	    	return new int[]{
					android.R.color.black,
					android.R.color.holo_blue_bright,
					android.R.color.holo_red_dark,
					android.R.color.holo_blue_dark,
					android.R.color.darker_gray
			};
	    }
	    
	    private String[] getImageDescription() {
	    	return new String[]{
	    			"第一个引导页面",
	    			"第二个引导页面",
	    			"第三个引导页面",
	    			"第四个引导页面",
	    			"第五个引导页面"
	    	};
	    }
	    
	    class ViewPagerAdapter extends PagerAdapter {

			@Override
			public int getCount() {
				return Integer.MAX_VALUE;
			}

			/**
			 * 判断出去的view是否等于进来的view 如果为true直接复用
			 */
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			/**
			 * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
			 */
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView(imageViewList.get(position % imageViewList.size()));
			}

			/**
			 * 创建一个view
			 */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(imageViewList.get(position % imageViewList.size()));
				return imageViewList.get(position % imageViewList.size());
			}
	    }

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			// 改变图片的描述信息
			tvDescription.setText(imageDescriptions[position % imageViewList.size()]);
			// 切换选中的点
			llPoints.getChildAt(previousSelectPosition).setEnabled(false);	// 把前一个点置为normal状态
			llPoints.getChildAt(position % imageViewList.size()).setEnabled(true);		// 把当前选中的position对应的点置为enabled状态
			previousSelectPosition = position  % imageViewList.size();
		}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		isLoop = false;
	}

}
