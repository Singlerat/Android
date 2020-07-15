package com.example.chapter7.image;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.chapter7.R;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity
{
	private static final int CODE_FOR_READ_PERMISSION = 251;

	public static void launch(Activity activity)
	{
		Intent intent = new Intent(activity, ImageActivity.class);

		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		getSupportActionBar().hide();

		// permission
		int state = ContextCompat.checkSelfPermission(
				getApplication(), Manifest.permission.READ_EXTERNAL_STORAGE);
		boolean hasPermission = state == PackageManager.PERMISSION_GRANTED;
		if (hasPermission) {
			// already has permission
			initViewPager(true);
		} else {
			// request permission
			ActivityCompat.requestPermissions(
					this,
					new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
					CODE_FOR_READ_PERMISSION);
		}

	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(requestCode == CODE_FOR_READ_PERMISSION)
		{
			boolean allowed = grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED;

			initViewPager(allowed);
		}
	}

	private void initViewPager(boolean hasPermission)
	{
		List<View> imageViewList = new ArrayList<>();
		imageViewList.add(getImageView(R.drawable.ic_launcher_background));
		imageViewList.add(getImageView("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1408798572,3755768198&fm=26&gp=0.jpg"));
		imageViewList.add(getImageView("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594793664707&di=bdc9bc5f1c2160c6f0d05e93227255a8&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20171110%2F8abf7b616c92466f95b8e554cf11658a.jpeg"));


		ViewPager viewPager = findViewById(R.id.viewPager);
		MyPagerAdapter adapter = new MyPagerAdapter();
		adapter.setImages(imageViewList);
		viewPager.setAdapter(adapter);
	}

	private View getImageView(int id)
	{
		ImageView imageView = createImageView();
		Glide.with(this)
				.load(id)
				.error(R.drawable.error)
				.into(imageView);

		return imageView;
	}

	private View getImageView(String path)
	{
		ImageView imageView = createImageView();
		Glide.with(this)
				.load(path)
				.apply(new RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
				.error(R.drawable.error)
				.into(imageView);

		return imageView;
	}

	private ImageView createImageView()
	{
		return (ImageView)getLayoutInflater().inflate(R.layout.layout_image_view, null);
	}

	private void makeToast(String text)
	{
		Toast.makeText(this, text, Toast.LENGTH_SHORT);
	}

}
