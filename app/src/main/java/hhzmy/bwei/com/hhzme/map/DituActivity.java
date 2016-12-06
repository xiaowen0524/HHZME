package hhzmy.bwei.com.hhzme.map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import hhzmy.bwei.com.hhzme.R;

public class DituActivity extends Activity implements View.OnClickListener, BaiduMap.OnMapClickListener, BaiduMap.OnMarkerClickListener {
    private MapView mapView = null;
    private BaiduMap myBaiduMap = null;
    // 修改默认View相关
    private View defaultBaiduMapScaleButton, defaultBaiduMapLogo, defaultBaiduMapScaleUnit;
    // 基本地图类型,实时交通，及覆盖物相关
    private ImageView mapRoad;
    private ImageView mapType;
    private String[] types = {"普通地图", "卫星地图", "热力地图(已关闭)"};
    private float current;// 放大或缩小的比例系数
    private ImageView expandMap;// 放大地图控件
    private ImageView narrowMap;// 缩小地图
    private ImageView addMarks;// 添加覆盖物控件
    private BitmapDescriptor myMarks;
    private List<MarkInfo> markInfoList;
    private LinearLayout markLayout;
    // 定位相关
    private LocationClient myLocationClient;// 专门用于监听位置的客户端对象
    private MyLocationListener myListener;// 定位监听器对象
    private double latitude, longtitude;// 经纬度
    private BitmapDescriptor myBitmapLocation;// 定位的自定义图标
    private boolean isFirstIn = true;// 设置一个标记，查看是否是第一次
    private String locationTextString;// 定义的位置的信息
    private TextView locationText;// 显示定位信息的TextView控件
    private MyOrientationListener myOrientationListener;
    private float myCurrentX;
    private ImageView selectLocationMode;
    private ImageView myLocation;
    private String[] LocationModeString = {"罗盘模式", "普通模式", "跟随模式", "3D俯视模式(已关闭)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_ditu);
        mapView = (MapView) findViewById(R.id.map_view_test);
        initMapView();
        changeDefaultBaiduMapView();
        initMapLocation();
    }

    /**
     * @author Mikyou 初始化定位功能
     */
    private void initMapLocation() {
        myLocationClient = new LocationClient(this);// 创建一个定位客户端对象
        myListener = new MyLocationListener();// 创建一个定位事件监听对象
        myLocationClient.registerLocationListener(myListener);// 并给该定位客户端对象注册监听事件
        // 对LocaitonClient进行一些必要的设置
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");// 设置坐标的类型
        option.setIsNeedAddress(true);// 返回当前的位置信息，如果不设置为true，默认就为false，就无法获得位置的信息
        option.setOpenGps(true);// 打开GPS
        option.setScanSpan(1000);// 表示1s中进行一次定位请求
        myLocationClient.setLocOption(option);
        useLocationOrientationListener();// 调用方向传感器
    }

    /**
     * @author Mikyou 定位结合方向传感器，从而可以实时监测到X轴坐标的变化，从而就可以检测到
     * 定位图标方向变化，只需要将这个动态变化的X轴的坐标更新myCurrentX值， 最后在MyLocationData
     * data.driection(myCurrentX);
     */
    private void useLocationOrientationListener() {
        myOrientationListener = new MyOrientationListener(DituActivity.this);
        myOrientationListener.setMyOrientationListener(new MyOrientationListener.onOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {// 监听方向的改变，方向改变时，需要得到地图上方向图标的位置
                myCurrentX = x;
                System.out.println("方向:x---->" + x);
            }
        });
    }

    /**
     * @author zhongqihong 获取位置信息的客户端对象的监听器类MyLocationListener
     */
    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // 得到一个MyLocationData对象，需要将BDLocation对象转换成MyLocationData对象
            MyLocationData data = new MyLocationData.Builder().accuracy(location.getRadius())// 精度半径
                    .direction(myCurrentX)// 方向
                    .latitude(location.getLatitude())// 经度
                    .longitude(location.getLongitude())// 纬度
                    .build();
            myBaiduMap.setMyLocationData(data);
            // 配置自定义的定位图标,需要在紧接着setMyLocationData后面设置
            // 调用自定义定位图标
            changeLocationIcon();
            latitude = location.getLatitude();// 得到当前的经度
            longtitude = location.getLongitude();// 得到当前的纬度
            // toast("经度："+latitude+" 纬度:"+longtitude);
            if (isFirstIn) {// 表示用户第一次打开，就定位到用户当前位置，即此时只要将地图的中心点设置为用户此时的位置即可
                getMyLatestLocation(latitude, longtitude);// 获得最新定位的位置,并且地图的中心点设置为我的位置
                isFirstIn = false;// 表示第一次才会去定位到中心点
                locationTextString = "" + location.getAddrStr();// 这里得到地址必须需要在设置LocationOption的时候需要设置isNeedAddress为true;
                toast(locationTextString);
                locationText.setText(locationTextString);
            }
        }

    }

    /**
     * @author zhongqihong 获得最新定位的位置,并且地图的中心点设置为我的位置
     */
    private void getMyLatestLocation(double lat, double lng) {
        LatLng latLng = new LatLng(lat, lng);// 创建一个经纬度对象，需要传入当前的经度和纬度两个整型值参数
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);// 创建一个地图最新更新的状态对象，需要传入一个最新经纬度对象
        myBaiduMap.animateMapStatus(msu);// 表示使用动画的效果传入，通过传入一个地图更新状态对象，然后利用百度地图对象来展现和还原那个地图更新状态，即此时的地图显示就为你现在的位置
    }

    /**
     * @author zhongqihong 自定义定位图标
     */
    private void changeLocationIcon() {
        myBitmapLocation = BitmapDescriptorFactory.fromResource(R.mipmap.logisticsmap_customer_logo);// 引入自己的图标
        if (isFirstIn) {// 表示第一次定位显示普通模式
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, myBitmapLocation);
            myBaiduMap.setMyLocationConfigeration(config);
        }
    }

    /**
     * @author zhongqihong 初始化地图的View
     */
    private void initMapView() {
        registerAllIds();
        registerAllEvents();
    }

    private void registerAllIds() {
        mapRoad = (ImageView) findViewById(R.id.road_condition);
        mapType = (ImageView) findViewById(R.id.map_type);
        expandMap = (ImageView) findViewById(R.id.add_scale);
        narrowMap = (ImageView) findViewById(R.id.low_scale);
        addMarks = (ImageView) findViewById(R.id.map_marker);
        markLayout = (LinearLayout) findViewById(R.id.mark_layout);
        locationText = (TextView) findViewById(R.id.mylocation_text);
        selectLocationMode = (ImageView) findViewById(R.id.map_location);
        myLocation = (ImageView) findViewById(R.id.my_location);

    }

    private void registerAllEvents() {
        mapRoad.setOnClickListener(this);
        mapType.setOnClickListener(this);
        expandMap.setOnClickListener(this);
        narrowMap.setOnClickListener(this);
        addMarks.setOnClickListener(this);
        selectLocationMode.setOnClickListener(this);
        myLocation.setOnClickListener(this);
    }

    /**
     * @author zhongqihong 除去百度地图上的默认控件
     */
    private void changeDefaultBaiduMapView() {
        changeInitialzeScaleView();// 改变默认百度地图初始加载的地图比例
        // 设置隐藏缩放和扩大的百度地图的默认的比例按钮
        for (int i = 0; i < mapView.getChildCount(); i++) {// 遍历百度地图中的所有子View,找到这个扩大和缩放的按钮控件View，然后设置隐藏View即可
            View child = mapView.getChildAt(i);
            if (child instanceof ZoomControls) {
                defaultBaiduMapScaleButton = child;// 该defaultBaiduMapScaleButton子View是指百度地图默认产生的放大和缩小的按钮，得到这个View
                break;
            }
        }
        defaultBaiduMapScaleButton.setVisibility(View.GONE);// 然后将该View的Visiblity设为不存在和不可见，即隐藏
        defaultBaiduMapLogo = mapView.getChildAt(1);// 该View是指百度地图中默认的百度地图的Logo,得到这个View
        defaultBaiduMapLogo.setPadding(300, -10, 100, 100);// 设置该默认Logo
        // View的位置，因为这个该View的位置会影响下面的刻度尺单位View显示的位置
        mapView.removeViewAt(1);// 最后移除默认百度地图的logo View
        defaultBaiduMapScaleUnit = mapView.getChildAt(2);// 得到百度地图的默认单位刻度的View
        defaultBaiduMapScaleUnit.setPadding(100, 0, 115, 200);// 最后设置调整百度地图的默认单位刻度View的位置
    }

    /**
     * @author zhongqihong 改变默认初始化的地图的比例
     */
    private void changeInitialzeScaleView() {
        myBaiduMap = mapView.getMap();// 改变百度地图的放大比例,让首次加载地图就开始扩大到500米的距离,获得百度地图对象
        MapStatusUpdate factory = MapStatusUpdateFactory.zoomTo(15.0f);
        myBaiduMap.animateMapStatus(factory);
    }

    /**
     * @author zhongqihong 管理地图的生命周期
     */
    protected void onDestroy() {
        super.onDestroy();
        // 在Activity执行onDestory时执行mapView(地图)生命周期管理
        mapView.onDestroy();
    }

    @Override
    protected void onStart() {// 当Activity调用onStart方法，开启定位以及开启方向传感器，即将定位的服务、方向传感器和Activity生命周期绑定在一起
        myBaiduMap.setMyLocationEnabled(true);// 开启允许定位
        if (!myLocationClient.isStarted()) {
            myLocationClient.start();// 开启定位
        }
        // 开启方向传感器
        myOrientationListener.start();
        super.onStart();
    }

    @Override
    protected void onStop() {// 当Activity调用onStop方法，关闭定位以及关闭方向传感器
        myBaiduMap.setMyLocationEnabled(false);
        myLocationClient.stop();// 关闭定位
        myOrientationListener.stop();// 关闭方向传感器
        super.onStop();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // 在Activity执行onResume是执行MapView(地图)生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mapView.onPause();
    }

    // 点击事件相关
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.road_condition:// 是否打开实时交通
                switchRoadCondition();
                break;
            case R.id.map_type:// 选择地图的类型
                selectMapType();
                break;
            case R.id.add_scale:// 放大地图比例
                expandMapScale();
                break;
            case R.id.low_scale:// 缩小地图比例
                narrowMapScale();
                break;
            case R.id.map_marker:// 添加覆盖物
                addMapMarks();
                break;
            case R.id.my_location:// 定位功能，需要用到LocationClient进行定位
                // BDLocationListener
                getMyLatestLocation(latitude, longtitude);
                break;
            case R.id.map_location:// 选择定位模式
                selectLocation();
                break;
            default:
                break;
        }
    }

    /**
     * @author mikyou 选择定位的模式
     */
    private void selectLocation() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setIcon(R.mipmap.track_collect_running).setTitle("请选择定位的模式")
                .setItems(LocationModeString, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mode = LocationModeString[which];
                        if (mode.equals("罗盘模式")) {
                            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true,
                                    myBitmapLocation);
                            myBaiduMap.setMyLocationConfigeration(config);
                        } else if (mode.equals("跟随模式")) {
                            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true,
                                    myBitmapLocation);
                            myBaiduMap.setMyLocationConfigeration(config);
                        } else if (mode.equals("普通模式")) {
                            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true,
                                    myBitmapLocation);
                            myBaiduMap.setMyLocationConfigeration(config);
                        } else if (mode.equals("3D俯视模式(已关闭)") || mode.equals("3D俯视模式(已打开)")) {
                            if (mode.equals("3D俯视模式(已打开)")) {
                                UiSettings mUiSettings = myBaiduMap.getUiSettings();
                                mUiSettings.setCompassEnabled(true);
                                LocationModeString[which] = "3D俯视模式(已关闭)";
                                toast("3D模式已关闭");
                            } else {
                                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true,
                                        myBitmapLocation);
                                myBaiduMap.setMyLocationConfigeration(config);
                                MyLocationConfiguration config2 = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true,
                                        myBitmapLocation);
                                myBaiduMap.setMyLocationConfigeration(config2);
                                LocationModeString[which] = "3D俯视模式(已打开)";
                                toast("3D模式已打开");
                            }
                        }
                    }
                }).show();
    }

    /**
     * @author Mikyou 添加覆盖物
     */
    private void addMapMarks() {
        initMarksData();
        myBaiduMap.clear();// 先清除一下图层
        LatLng latLng = null;
        Marker marker = null;
        OverlayOptions options;
        myMarks = BitmapDescriptorFactory.fromResource(R.mipmap.mark);// 引入自定义的覆盖物图标，将其转化成一个BitmapDescriptor对象
        // 遍历MarkInfo的List一个MarkInfo就是一个Mark
        for (int i = 0; i < markInfoList.size(); i++) {
            // 经纬度对象
            latLng = new LatLng(markInfoList.get(i).getLatitude(), markInfoList.get(i).getLongitude());// 需要创建一个经纬对象，通过该对象就可以定位到处于地图上的某个具体点
            // 图标
            options = new MarkerOptions().position(latLng).icon(myMarks).zIndex(6);
            marker = (Marker) myBaiduMap.addOverlay(options);// 将覆盖物添加到地图上
            Bundle bundle = new Bundle();// 创建一个Bundle对象将每个mark具体信息传过去，当点击该覆盖物图标的时候就会显示该覆盖物的详细信息
            bundle.putSerializable("mark", markInfoList.get(i));
            marker.setExtraInfo(bundle);
        }
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);// 通过这个经纬度对象，地图就可以定位到该点
        myBaiduMap.animateMapStatus(msu);
    }

    /**
     * @author mikyou 初始化覆盖物信息数据
     */
    private void initMarksData() {
        markInfoList = new ArrayList<MarkInfo>();
        markInfoList.add(new MarkInfo(32.079254, 118.787623, R.mipmap.pic1, "英伦贵族小旅馆", "距离209米", 1888));
        markInfoList.add(new MarkInfo(32.064355, 118.787624, R.mipmap.pic2, "沙井国际高级会所", "距离459米", 388));
        markInfoList.add(new MarkInfo(28.7487420000, 115.8748860000, R.mipmap.pic4, "华东交通大学南区", "距离5米", 888));
        markInfoList.add(new MarkInfo(28.7534890000, 115.8767960000, R.mipmap.pic3, "华东交通大学北区", "距离10米", 188));
        myBaiduMap.setOnMarkerClickListener(this);
        myBaiduMap.setOnMapClickListener(this);
    }

    /**
     * @author zhongqihong 放大地图的比例
     */
    private void narrowMapScale() {
        current -= 0.5f;
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f + current);
        myBaiduMap.animateMapStatus(msu);
    }

    /**
     * @author zhongqihong 缩小地图的比例
     */
    private void expandMapScale() {
        current += 0.5f;
        MapStatusUpdate msu2 = MapStatusUpdateFactory.zoomTo(15.0f + current);
        myBaiduMap.animateMapStatus(msu2);
    }

    /**
     * @author mikyou 选择地图的类型
     */
    private void selectMapType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DituActivity.this);
        builder.setIcon(R.mipmap.icon).setTitle("请选择地图的类型").setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String select = types[which];
                if (select.equals("普通地图")) {
                    myBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                } else if (select.equals("卫星地图")) {
                    myBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                } else if (select.equals("热力地图(已关闭)") || select.equals("热力地图(已打开)")) {
                    if (myBaiduMap.isBaiduHeatMapEnabled()) {
                        myBaiduMap.setBaiduHeatMapEnabled(false);
                        Toast.makeText(DituActivity.this, "热力地图已关闭", Toast.LENGTH_SHORT).show();
                        types[which] = "热力地图(已关闭)";
                    } else {
                        myBaiduMap.setBaiduHeatMapEnabled(true);
                        Toast.makeText(DituActivity.this, "热力地图已打开", Toast.LENGTH_SHORT).show();
                        types[which] = "热力地图(已打开)";
                    }
                }
            }
        }).show();
    }

    /**
     * @author mikyou 是否打开实时交通
     */
    private void switchRoadCondition() {
        if (myBaiduMap.isTrafficEnabled()) {// 如果是开着的状态，当点击后，就会出关闭状态
            myBaiduMap.setTrafficEnabled(false);
            mapRoad.setImageResource(R.mipmap.main_icon_roadcondition_off);
        } else {// 如果是的关闭的状态，当点击后，就会处于开启的状态
            myBaiduMap.setTrafficEnabled(true);
            mapRoad.setImageResource(R.mipmap.main_icon_roadcondition_on);
        }
    }

    /**
     * @author mikyou 覆盖物的点击事件
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        Bundle bundle = marker.getExtraInfo();
        MarkInfo MyMarker = (MarkInfo) bundle.getSerializable("mark");
        ImageView iv = (ImageView) markLayout.findViewById(R.id.mark_image);
        TextView distanceTv = (TextView) markLayout.findViewById(R.id.distance);
        TextView nameTv = (TextView) markLayout.findViewById(R.id.name);
        TextView zanNumsTv = (TextView) markLayout.findViewById(R.id.zan_nums);
        iv.setImageResource(MyMarker.getImageId());
        distanceTv.setText(MyMarker.getDistance() + "");
        nameTv.setText(MyMarker.getName());
        zanNumsTv.setText(MyMarker.getZanNum() + "");
        // 初始化一个InfoWindow
        initInfoWindow(MyMarker, marker);
        markLayout.setVisibility(View.VISIBLE);
        return true;
    }

    /**
     * @author mikyou 初始化出一个InfoWindow
     */
    private void initInfoWindow(MarkInfo MyMarker, Marker marker) {
        // TODO Auto-generated method stub
        InfoWindow infoWindow;
        // InfoWindow中显示的View内容样式，显示一个TextView
        TextView infoWindowTv = new TextView(DituActivity.this);
        infoWindowTv.setBackgroundResource(R.mipmap.ic_track_explore_zoomout_dis);
        infoWindowTv.setPadding(30, 20, 30, 50);
        infoWindowTv.setText(MyMarker.getName());
        infoWindowTv.setTextColor(Color.parseColor("#FFFFFF"));

        final LatLng latLng = marker.getPosition();
        Point p = myBaiduMap.getProjection().toScreenLocation(latLng);// 将地图上的经纬度转换成屏幕中实际的点
        p.y -= 47;// 设置屏幕中点的Y轴坐标的偏移量
        LatLng ll = myBaiduMap.getProjection().fromScreenLocation(p);// 把修改后的屏幕的点有转换成地图上的经纬度对象
        /**
         * @author mikyou 实例化一个InfoWindow的对象 public InfoWindow(View view,LatLng
         *         position, int yOffset)通过传入的 view 构造一个 InfoWindow,
         *         此时只是利用该view生成一个Bitmap绘制在地图中，监听事件由开发者实现。 参数: view - InfoWindow
         *         展示的 view position - InfoWindow 显示的地理位置 yOffset - InfoWindow Y
         *         轴偏移量
         */
        infoWindow = new InfoWindow(infoWindowTv, ll, 10);
        myBaiduMap.showInfoWindow(infoWindow);// 显示InfoWindow
    }

    /**
     * @author zhongqihong 给整个地图添加的点击事件
     */
    @Override
    public void onMapClick(LatLng arg0) {// 表示点击地图其他的地方使得覆盖物的详情介绍的布局隐藏，但是点击已显示的覆盖物详情布局上，则不会消失，因为在详情布局上添加了Clickable=true
        // 由于事件的传播机制，因为点击事件首先会在覆盖物布局的父布局(map)中,由于map是可以点击的，map则会把点击事件给消费掉，如果加上Clickable=true表示点击事件由详情布局自己处理，不由map来消费
        markLayout.setVisibility(View.GONE);
        myBaiduMap.hideInfoWindow();// 隐藏InfoWindow
    }

    @Override
    public boolean onMapPoiClick(MapPoi arg0) {
        return false;
    }

    public void toast(String str) {
        Toast.makeText(DituActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}