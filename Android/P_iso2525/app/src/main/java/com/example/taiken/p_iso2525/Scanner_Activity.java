package com.example.taiken.p_iso2525;

import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class Scanner_Activity extends  Activity {


    private SurfaceView mySurfaceView;//表面図
    private Camera myCamera;//カメラ

    //Activity初期値
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new SurfaceView(this);
        mySurfaceView.setOnClickListener(onClickListener);
        setContentView(mySurfaceView);
    }

    //Viewを再表示する
    protected void onResume() {
        super.onResume();
        SurfaceHolder holder = mySurfaceView.getHolder();
        holder.addCallback(callback);
    }
    //表面ホルダーコールバック
    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        //面作成
        public void surfaceCreated(SurfaceHolder holder) {
            // 生成されたとき
            myCamera = Camera.open();
            try {
                // プレビューをセットする
                myCamera.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        //表面変更
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // 変更されたとき
            Camera.Parameters parameters = myCamera.getParameters();
            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            Camera.Size previewSize = previewSizes.get(0);
            // width, heightを変更する
            parameters.setPreviewSize(width,height);
            //カメラ90回転
            myCamera.setDisplayOrientation(90);

            myCamera.setParameters(parameters);
            myCamera.startPreview();

        }
        @Override
        //アクティビティを破棄した時
        public void surfaceDestroyed(SurfaceHolder holder) {
            // 破棄されたとき
            myCamera.release();
            myCamera = null;
        }
    };


    //クリックした場合オートフォーカス
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // オートフォーカス
            if (myCamera != null) {
                myCamera.autoFocus(autoFocusCallback);


            }
        }
    };
    // 読み込んだQRコードをプレビューに変換
    private AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                // 現在のプレビューをデータに変換
                camera.setOneShotPreviewCallback(previewCallback);
            }
        }
    };

    private PreviewCallback previewCallback = new PreviewCallback() {

        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            // 読み込む範囲
            int previewWidth = camera.getParameters().getPreviewSize().width;
            int previewHeight = camera.getParameters().getPreviewSize().height;

            // プレビューデータから BinaryBitmap を生成
            /**
             * 第１. byte[]の YUVデータ
             * 第２. 画像データの width
             * 第３. 画像データの height
             * 第４. 開始 X 座標
             * 第５. 開始 Y 座標
             * 第６. 読み取る範囲 width
             * 第７. 読み取る範囲 height
             * 第８. データを反転するか否か (フロントカメラの場合は true)
             */
            PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource
                    (data, previewWidth, previewHeight, 0, 0, previewWidth, previewHeight, false);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));


            // バーコードを読み込む
            Reader reader = new MultiFormatReader();
            Result result = null;

            //ビープ音を読み込む
            MediaPlayer player = new MediaPlayer();

            //第一引数　ストリームタイプ　第二引数　音量
            ToneGenerator toneGenerator
                    = new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);
            try {
                result = reader.decode(bitmap);
                //読み込んだQRコードをStringに変換
                String text = result.getText();
                //textを画面に表示
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
                //標準的なビープ音
                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
