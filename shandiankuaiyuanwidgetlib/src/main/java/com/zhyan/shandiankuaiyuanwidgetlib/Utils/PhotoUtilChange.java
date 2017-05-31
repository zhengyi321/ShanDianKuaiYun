package com.zhyan.shandiankuaiyuanwidgetlib.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.widget.Toast;
public class PhotoUtilChange{
	public  final File file1 = new File(Environment.getExternalStorageDirectory(), "temp1.jpg");// 拍照路径
	public  final File file2 = new File(Environment.getExternalStorageDirectory(), "temp2.jpg");// 剪裁路径
	public  final File file3 = new File(Environment.getExternalStorageDirectory(), "temp3.jpg");// 压缩路径
	public  final int NONE = 0;
	public   int PHOTOGRAPH = 1;/// 拍照
	public   int PHOTOALBUM = 2; // 相册
	public  final int CLIPPING = 3;// 剪裁
	private  AlertDialog dlg;
	protected  Uri uri=Uri.fromFile(file1);
	private  boolean flag;
	private  Uri outPutUri;
	/**
	 * @param activity
	 *           吊起相册或拍照
	 */
	
	public  void getPhoto(final Activity activity,final Fragment fragment) {
		
			 try {
				final CharSequence[] items = {  "相册", "拍照"  };
				 dlg = new AlertDialog.Builder(activity==null?fragment.getActivity():activity).setTitle("选择图片")
						 .setItems(items, new DialogInterface.OnClickListener() {
							 public void onClick(DialogInterface dialog, int item) {
								 if (item == 1) {
									/* takePhoto(activity,fragment);*/
								 } else {
									/* pickPhoto(activity,fragment);*/
								 }
								 dlg.dismiss();
							 }
						 }).create();
				 dlg.show();
			} catch (Exception e) {
			/*	Toast.makeText(activity, "您可能已拒绝拍摄权限，请到系统设置打开权限！",0).show();*/
				e.printStackTrace();
			}
	
	}
	
	

	
	/**
	 *  调起拍照
	 */
	public  void TakePhoto(Activity activity,Fragment fragment) {
		Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
		getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		if(fragment!=null){
			fragment.startActivityForResult(getImageByCamera, PHOTOGRAPH);
		}else if(activity!=null){
			activity.startActivityForResult(getImageByCamera, PHOTOGRAPH);
			
		}
	}
	/**
	 *调起相册
	 */
	public void PickPhoto(Activity activity,Fragment fragment) {
		Intent getImage = new Intent("android.intent.action.PICK");
		getImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		if(activity!=null){
			activity.startActivityForResult(getImage, PHOTOALBUM);
		}else if(fragment!=null){
			fragment.startActivityForResult(getImage, PHOTOALBUM);
		}
	}
	/**
	 *  相机
	 * @param activity
	 * @param fragment
	 * @param
	 * @param x
	 * @param y
	 */
	public  void onPhotoFromCamera(Activity activity,Fragment fragment,int x,int y) {
		startPhotoZoom(uri, activity,fragment,x,y);
	}
	/**
	 * 相册返回调用
	 */
	public  void onPhotoFromPick(Activity activity,Uri uri,Fragment fragment,int x,int y) {
        PhotoUtilChange photoUtilChange = new PhotoUtilChange();
        photoUtilChange.uri=uri;
		startPhotoZoom(uri, activity,fragment,x,y);
	}
	/**
	 * @param  ``100 100表示原图
	 * 返回剪裁的图片
	 */
	public  Bitmap getBitmap(Context context) {
		Bitmap bm=null; 
		if(flag){
			if (file2 != null && file2.exists())
			{
				file2.delete();
			}
			bm = getBitmapFromBigImagByUri(uri,context);

		}else{
			bm=getBitmapFromBigImagByUri(outPutUri,context);
		}
		return bm;
	}
	/**
	 *剪裁
	 * @param uri
	 * @param activity
	 * @param fragment
	 * @param x
	 * @param y
	 */
	private  void startPhotoZoom(Uri uri, Activity activity,Fragment fragment,int x,int y) {
		if (uri == null)
		{
			return;
		}
		try
		{
			outPutUri = Uri.fromFile(file2);
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, "image/*");
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", x);
			intent.putExtra("aspectY", y);
			intent.putExtra("outputX", 300);
			intent.putExtra("outputY", 300);
			intent.putExtra("return-data", false);
			intent.putExtra("output", outPutUri);
			intent.putExtra("scale", true);
			intent.putExtra("scaleUpIfNeeded", true);
			intent.putExtra("noFaceDetection", true);
			if(fragment!=null){
				fragment.startActivityForResult(intent, CLIPPING);
			}else if(activity!=null){
				activity.startActivityForResult(intent, CLIPPING);
			}
		}
		catch (ActivityNotFoundException e)
		{
			// 发生了异常
			flag = true;
		}
	}
	/*
	 *  通过Uri得到压缩以后的图片
	 */
	private static Bitmap getBitmapFromBigImagByUri(Uri uri,Context context)
	{
		Bitmap result = null;
		InputStream is1 = null;
		InputStream is2 = null;
		try
		{
			is1 = context.getContentResolver().openInputStream(uri);
			is2 = context.getContentResolver().openInputStream(uri);
			Options opts1 = new Options();
			opts1.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is1, null, opts1);
			int bmpWidth = opts1.outWidth;
			int bmpHeight = opts1.outHeight;
			int scale = Math.max(bmpWidth / 300, bmpHeight / 300);
			Options opts2 = new Options();
			// 缩放的比例
			opts2.inSampleSize = scale;
			// 内存不足时可被回收
			opts2.inPurgeable = true;
			// 设置为false,表示不仅Bitmap的属性，也要加载bitmap
			opts2.inJustDecodeBounds = false;
			result = BitmapFactory.decodeStream(is2, null, opts2);
		}
		catch (Exception ex)
		{
		}
		finally
		{
			if (is1 != null)
			{
				try
				{
					is1.close();
				}
				catch (IOException e1)
				{
				}
			}
			if (is2 != null)
			{
				try
				{
					is2.close();
				}
				catch (IOException e2)
				{
				}
			}
		}
		return result;
	}


	

}
