package com.example.ijkpayertest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.ijkpayertest.ijkplayer.media.IRenderView;
import com.example.ijkpayertest.ijkplayer.media.IjkVideoView;
import com.example.ijkpayertest.ijkplayer.media.PlayerManager;

import tv.danmaku.ijk.media.player.IMediaPlayer;


/**
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 1.播放会一直进行 后台也是
     */

    private IjkVideoView mVideoView;
    private PlayerManager player;

    private String url5 = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";


    private String liveUrl="rtmp://58.200.131.2:1935/livetv/cctv1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        initVideo();

//        /** 普通播放 start **/
//        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
//        mVideoView.setVideoURI(Uri.parse(url5));
//        mVideoView.start();
        /** 普通播放 end **/

        //initVideo();
    }

    //使用滑动控制的话解开注释
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (player.gestureDetector.onTouchEvent(event))
//            return true;
//        return super.onTouchEvent(event);
//    }

    /**
     * 可左半屏滑动控制亮度  右半屏控制音量  双击切换比例  （无提示）
     */
    private void initVideo() {
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.live(true);
        player.setScaleType(PlayerManager.SCALETYPE_WRAPCONTENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(new PlayerManager.PlayerStateListener() {
            @Override
            public void onComplete() {
                Log.e("   player  status    :", "complete");
            }

            @Override
            public void onError() {
                Log.e("   player  status    :", "error");
            }

            @Override
            public void onLoading() {
                Log.e("   player  status    :", "loading");
            }

            @Override
            public void onPlay() {
                Log.e("   player  status    :", "play");
            }
        });
        player.play(liveUrl);
        IjkVideoView videoView = player.getVideoView();
        videoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                switch (i) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        break;
                }
                return false;

            }
        });
    }


    private void initLive(){

    }
}