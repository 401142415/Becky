package com.example.administrator.note;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/20.
 */

public class AddContent extends Activity implements View.OnClickListener{

    private  String val;
    private Button savebtn,deletebtn;
    private EditText ettext;
    private ImageView c_img;
    private VideoView v_video;
    private NotesDB notesDB;
    private SQLiteDatabase dbwiter;
    private File phoheFile,videoFile;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");
        savebtn = (Button)findViewById(R.id.save);
        deletebtn = (Button)findViewById(R.id.delete);
        ettext = (EditText)findViewById(R.id.ettext);
        c_img = (ImageView)findViewById(R.id.c_img);
        v_video = (VideoView)findViewById(R.id.v_video);
        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        notesDB = new NotesDB(this);
        dbwiter = notesDB.getWritableDatabase();
        initView();
    }

    public void initView(){
        if(val.equals("1")){
            c_img.setVisibility(View.GONE);
            v_video.setVisibility(View.GONE);
        }
        if(val.equals("2")){
            c_img.setVisibility(View.VISIBLE);
            v_video.setVisibility(View.GONE);
            //调整系统相机拍照
            Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //存储文件，绝对路径
//            phoheFile = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()
//                    +"/"+getTime()+".jpg");
            //File.separator 在windows是\,unix是/
            File mediaStorageDir = null;
//            phoheFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
//                  + File.separator + "IMG_" + timeStamp + ".jpg");
            phoheFile =  getOutputMediaFile(Integer.valueOf(val).intValue());
            fileUri = Uri.fromFile(phoheFile);
            iimg.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(iimg,1);
        }
        if(val.equals("3")){
            c_img.setVisibility(View.GONE);
            v_video.setVisibility(View.VISIBLE);
            Intent video = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File mediaStorageDir = null;
//            phoheFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
//                  + File.separator + "IMG_" + timeStamp + ".jpg");
            videoFile =  getOutputMediaFile(Integer.valueOf(val).intValue());
            fileUri = Uri.fromFile(videoFile);
            video.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(video,2);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                addDB();
                finish();
                break;
            case R.id.delete:
                finish();
                break;
        }
    }

    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT,ettext.getText().toString());
        cv.put(notesDB.TIME,getTime());
        //使用+""强制转换未string类型
        cv.put(notesDB.PATH,phoheFile+"");
        cv.put(notesDB.VIDEO,videoFile+"");

        dbwiter.insert(NotesDB.TABLE_NAME,null,cv);
    }

 //   获取设备当前时间
    public String  getTime(){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //代表传入一张图片
        if(requestCode == 1){
            //将图片路径传入
            Bitmap bitmap = BitmapFactory.decodeFile(phoheFile.getAbsolutePath());

            c_img.setImageBitmap(bitmap);
        }
        if(requestCode == 2){
            v_video.setVideoURI(Uri.fromFile(videoFile));
            v_video.start();
        }
    }
//    private static Uri getOutputMediaFileUri(int type)
//    {
//        return Uri.fromFile(getOutputMediaFile(type));
//    }
    private static File getOutputMediaFile(int type)
    {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = null;
        try
        {
            // This location works best if you want the created images to be
            // shared
            // between applications and persist after your app has been
            // uninstalled.
            mediaStorageDir = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "MyCameraApp");

            Log.d("getOutputMediaFile", "Successfully created mediaStorageDir: "
                    + mediaStorageDir);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("getOutputMediaFile", "Error in Creating mediaStorageDir: "
                    + mediaStorageDir);
        }

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                // 在SD卡上创建文件夹需要权限：
                // <uses-permission
                // android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
                Log.d("getOutputMediaFile",
                        "failed to create directory, check if you have the WRITE_EXTERNAL_STORAGE permission");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        if (type == 2)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        }
        else if (type == 3)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        }
        else
        {
            return null;
        }

        return mediaFile;
    }
}
