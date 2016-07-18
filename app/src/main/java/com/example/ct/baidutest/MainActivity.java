package com.example.ct.baidutest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.ct.baidutest.bean.SearchPoi;
import com.example.ct.baidutest.tool.MyLog;
import com.example.ct.baidutest.tool.MyToast;

public class MainActivity extends AppCompatActivity {

    MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private BaiDuMapReceiver  receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBaiDuMap();
        setContentView(R.layout.activity_main);

        initView();
        setClickEvent();


        //设置是否显示 缩小和放大按钮
        mMapView.showZoomControls(true);

        // 设置地图缩放比例 5-19 默认是12
        MapStatusUpdate mapStatusUpdate =MapStatusUpdateFactory.zoomTo(19);
        mBaiduMap.setMapStatus(mapStatusUpdate);

        // 设置地图中心点。
        MapStatusUpdate mapStatusUpdatePoint =MapStatusUpdateFactory.newLatLng( new LatLng(39.963175, 116.400244));
        mBaiduMap.setMapStatus(mapStatusUpdatePoint);


        setMapType(mBaiduMap);

        //开启交通图
        mBaiduMap.setTrafficEnabled(false);
        //开启百度热力图
        mBaiduMap.setBaiduHeatMapEnabled(false);
        // 地图Logo不允许遮挡，可通过方法可以设置logo的位置
        mBaiduMap.setPadding(105, 10, 5, 10);

        setMark(mBaiduMap);//设置标记
        // 将底图标注设置为隐藏，方法如下：
        mBaiduMap.showMapPoi(true);//true  表示不隐藏。
        addGround();//添加地形图图层

        /*SearchPoi searchPoi = new SearchPoi(mBaiduMap);
        searchPoi.citySearch(1, "北京", "餐厅");*/

    }


    public void initBaiDuMap(){

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        IntentFilter intentFilter = new IntentFilter();
        receiver=new BaiDuMapReceiver();
        intentFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        intentFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        registerReceiver(receiver,intentFilter);
    }

    public class BaiDuMapReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MyLog.i("ct","get a Receiver..");
            switch(action){
                case SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR:
                    MyToast.showToast(MainActivity.this,"网络出错");
                    break;
                case SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR:
                    MyToast.showToast(MainActivity.this,"权限错误");
                    break;

                default:
                    MyLog.i("ct","get a Receiver..");
                    break;
            }

        }
    }


    private EditText text1;
    private EditText text2;
    private Button commit;
    public void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         text1 = (EditText) findViewById(R.id.key1);
         text2 = (EditText) findViewById(R.id.key2);
         commit = (Button) findViewById(R.id.commit);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
    }

    public void setClickEvent() {

        //poi 解锁
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String key1= text1.getText().toString().trim();
                String key2= text2.getText().toString().trim();

                SearchPoi searchPoi = new SearchPoi(mBaiduMap);
                searchPoi.citySearch(1, key1, key2);
            }
        });
    }

    //添加地形图图层
    public void addGround() {
        LatLng southWest = new LatLng(39.92235, 116.380338);
        LatLng northEast = new LatLng(39.947246, 116.414977);
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(southWest)
                .include(northEast)
                .build();

        BitmapDescriptor bdGround = new BitmapDescriptorFactory().fromResource(R.drawable.ground);
        OverlayOptions ooGround = new GroundOverlayOptions().positionFromBounds(bounds)
                .image(bdGround)
                .transparency(0.8f);

        mBaiduMap.addOverlay(ooGround);

    }

    //设置地图标记  标记点击事件   弹出infoWindow
    public void setMark(final BaiduMap mBaiduMap) {

        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.mark_ct);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        Marker marker = (Marker) mBaiduMap.addOverlay(option);
        //弹出窗覆盖物
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {//mark 点击事件弹出 覆盖物
                MyLog.i("ct", "onMarkerClick ...");
                Button button = new Button(getApplicationContext());
                button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                LatLng pt = new LatLng(39.86923, 116.397428);
                InfoWindow infwindow = new InfoWindow(button, pt, -47);
                mBaiduMap.showInfoWindow(infwindow);

                return false;
            }
        });
       /* //对已经添加的标注设置拖拽
       // 第一步，设置可拖拽：
        OverlayOptions options = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(bitmap)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽
    //将marker添加到地图上
       Marker marker = (Marker) (mBaiduMap.addOverlay(options));



      //  第二步，设置监听方法：
    //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
                MyLog.i("ct","拖拽中...");

            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                MyLog.i("ct","拖拽结束...");
                marker.remove();//删除标记
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
                MyLog.i("ct","开始拖拽...");
            }
        });*/

      /* // 以生长动画为例，具体实现方法如下：
        MarkerOptions ooD = new MarkerOptions().position(llD).icons(giflist)
                .zIndex(0).period(10);
        if (animationBox.isChecked()) {
            // 生长动画
            ooD.animateType(MarkerOptions.MarkerAnimateType.grow);
        }
        Marker  mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));*/


    }

    //地图类型
    public void setMapType(BaiduMap mBaiduMap) {
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

       /* //卫星地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
*/
        /*//空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);*/

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
