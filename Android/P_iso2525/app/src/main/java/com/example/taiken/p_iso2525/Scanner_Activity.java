package com.example.taiken.p_iso2525;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class Scanner_Activity extends  Activity implements BarcodePostTask.BarcodePostCallback,OnClickListener{

    private SurfaceView mySurfaceView;//表面図
    private Camera myCamera;//カメラ
    private Scanner_Activity parentActivity;
    private static URL url = null;
    private static String serverIP = "";
    private final String scanPass = "/Web/Scan";
    private String barcodeData = "";
    private static String itemData = "商品名が表示されます";
    // Activity初期値
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_);
        mySurfaceView = (SurfaceView) findViewById(R.id.camera);
        mySurfaceView.setOnClickListener(onClickListener);
        Button btn = (Button)findViewById(R.id.button);
        TextView text = (TextView) findViewById(R.id.currentIP);
        text.setText("接続先：" + serverIP);
        btn.setOnClickListener(this);

        parentActivity = this;
        // 接続先のURLを指定

    }

    //Viewを再表示する
    protected void onResume() {
        super.onResume();
        SurfaceHolder holder = mySurfaceView.getHolder();
        holder.addCallback(callback);
        TextView text = (TextView) findViewById(R.id.itemTextView);
        text.setText(itemData);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        try {
            serverIP = data.getStringExtra("ipAddress");
            TextView text = (TextView) findViewById(R.id.currentIP);
            text.setText("接続先：" + serverIP);

            //自分の端末
            url = new URL("http://" + serverIP + scanPass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }


    }

    //画面サイズを設定
    private void setCamera(Camera camera){
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
        Camera.Size previewSize = previewSizes.get(0);


        Display display = parentActivity.getWindowManager().getDefaultDisplay();
        Point displayPoint = new Point();
        display.getSize(displayPoint);

        for(Camera.Size size : previewSizes){
            if(size.width > displayPoint.x && size.height > displayPoint.y){
                previewSize = size;
                break;
            }
        }

        // width, heightを変更する
        parameters.setPreviewSize(previewSize.width, previewSize.height);
        camera.setParameters(parameters);
    }

    //表面ホルダーコールバック
    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        //面作成
        public void surfaceCreated(SurfaceHolder holder) {
            // 生成されたとき
            myCamera = Camera.open();
            setCamera(myCamera);

            try {
                // プレビューをセットする
                myCamera.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //表面変更
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Configuration config = getResources().getConfiguration();
            if(config.orientation == Configuration.ORIENTATION_PORTRAIT){
                setCamera(myCamera);
                //カメラ90回転
                myCamera.setDisplayOrientation(90);
                myCamera.startPreview();
            }else{

                new configActivity();
                setCamera(myCamera);
                //ディスプレイ回転
                Display display = getWindowManager().getDefaultDisplay();
                switch (display.getRotation()){
                    case Surface.ROTATION_90:
                        myCamera.setDisplayOrientation(0);
                        break;
                    case Surface.ROTATION_270:
                        myCamera.setDisplayOrientation(180);
                }

                myCamera.startPreview();

            }
        }

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

            //第一引数　ストリームタイプ　第二引数　音量
            ToneGenerator toneGenerator
                    = new ToneGenerator(AudioManager.STREAM_SYSTEM, ToneGenerator.MAX_VOLUME);
            //ビープ音を読み込む
            MediaPlayer player = new MediaPlayer();


            try {
                result = reader.decode(bitmap);
                //QRのデータをテキストに変更
                barcodeData = result.getText();
                //標準的なビープ音
                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
            } catch (Exception e) {
                //Android デバイスを振動させる
                Vibrator v = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                v.vibrate(300);
                Toast.makeText(getApplicationContext(), "読み取り失敗", Toast.LENGTH_SHORT).show();

            }



            //serverのデータを表示
            new BarcodePostTask(url, barcodeData, parentActivity).execute();

        }
    };


    @Override
    public void Post(String returnData) {
        itemData = returnData;
        TextView text = (TextView) findViewById(R.id.itemTextView);
        text.setText(itemData);
    }

    @Override
    public void onClick(View view) {
        Intent intetnt = new Intent();
        intetnt.putExtra("ipAddress",serverIP);
        intetnt.setClassName("com.example.taiken.p_iso2525","com.example.taiken.p_iso2525.configActivity");
        startActivityForResult(intetnt,0);
    }
}
