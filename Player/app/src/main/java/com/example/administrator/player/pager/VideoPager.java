package com.example.administrator.player.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.player.R;
import com.example.administrator.player.base.BasePager;
import com.example.administrator.player.domain.MediaItem;
import com.example.administrator.player.utils.TimeUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/30.
 */

public class VideoPager extends BasePager{
    private ListView listView;
    private TextView tv_nomedio;
    private ProgressBar pd_loading;

    private ArrayList<MediaItem> mediaItems;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mediaItems != null && mediaItems.size()> 0){
                //将数据放入适配器
                listView.setAdapter();
            }else {
                //提示无视频

            }
            //将progressbar隐藏
        }
    };
    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.video_pager,null);
        listView = (ListView) view.findViewById(R.id.listview);
        tv_nomedio = (TextView) view.findViewById(R.id.tv_nomedia);
        pd_loading = (ProgressBar) view.findViewById(R.id.pd_loading);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //加载数据，本地视频
        getDateFromLocal();
    }
    /**
     * 从本地获取视频数据
     * 1、遍历sd卡，根据后缀名获取数据，效率较低
     * 2、从内容提供者中获取
     * 3、6.0以上系统，需要添加动态权限
     * @return
     */
    private void getDateFromLocal() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mediaItems = new ArrayList<>();
                //内容解析者
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                //获取的地址，字段
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//文件名
                        MediaStore.Video.Media.DURATION,//视频时长
                        MediaStore.Video.Media.SIZE,//大小
                        MediaStore.Video.Media.DATA,//视频的绝对地址
                        MediaStore.Video.Media.ARTIST,//歌曲演唱者，视频未必有
                };
                //返回一个光标
                Cursor cursor =resolver.query(uri,objs,null,null,null);
                if (cursor != null){
                    while(cursor.moveToNext()){

                        MediaItem mediaItem = new MediaItem();
                        //先加入后放数据亦可将数据写入
                        mediaItems.add(mediaItem);

                        String name = cursor.getString(0);
                        mediaItem.setName(name);

                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);

                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);

                        String date = cursor.getString(3);
                        mediaItem.setDate(date);

                        String artist = cursor.getString(4);
                        mediaItem.setArtist(artist);

                    }
                    //用完释放
                    cursor.close();
                }

                //handler发消息表示已取完数据
                handler.sendEmptyMessage(10);

            }
        }.start();
    }

    class VideoPagerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mediaItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoder viewHoder;

            if (convertView == null){
                convertView = View.inflate(context,R.layout.item_video_pager,null);
                viewHoder = new ViewHoder();
                viewHoder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHoder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHoder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHoder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);

                convertView.setTag(viewHoder);
            }else{
                viewHoder = (ViewHoder) convertView.getTag();
            }
            //根据position获取列表中对应位置数据
            MediaItem mediaitem = mediaItems.get(position);
            viewHoder.tv_name.setText(mediaitem.getName());
            //将Byte转为MB
            viewHoder.tv_size.setText(Formatter.formatFileSize(context,mediaitem.getSize()));
            viewHoder.tv_time.setText(TimeUtil.getDateFromMillisecond(mediaitem.getDate()));

            return convertView;
        }
    }

    static class ViewHoder{
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_time;
        TextView tv_size;
    }

}
