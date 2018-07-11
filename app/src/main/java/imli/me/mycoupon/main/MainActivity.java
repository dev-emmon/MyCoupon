package imli.me.mycoupon.main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import imli.me.mycoupon.App;
import imli.me.mycoupon.R;
import imli.me.mycoupon.coupon.CouponActivity;
import imli.me.mycoupon.data.Coupon;
import imli.me.mycoupon.data.Resulte;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvName;
    private RecyclerView rvList;
    private CouponListAdapter adapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App app = (App) getApplication();
        tvName = findViewById(R.id.tv_name);
        tvName.setText(app.getStudent().name);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Resulte.CouponResult result = (Resulte.CouponResult) msg.obj;

                adapter.addCoupons(result.data);

            }
        };


        getData();

        rvList = findViewById(R.id.rv_list);
        initList();
    }

    private void initList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        rvList.setLayoutManager(layoutManager);
        adapter = new CouponListAdapter(this);
        rvList.setAdapter(adapter);

//        adapter.addCoupons(createCoupons());
        adapter.setOnItemClickListener(new CouponListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Coupon coupon) {
                Intent intent = new Intent(MainActivity.this, CouponActivity.class);
                intent.putExtra("coupon", coupon);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取数据
     */
    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://39.106.207.131/api/product")
                            .build();
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    Log.d("getData", data);

                    Resulte.CouponResult result = new Gson().fromJson(data, Resulte.CouponResult.class);

                    Message msg = new Message();
                    msg.obj = result;

                    handler.sendMessage(msg);

                } catch (IOException e) {
                    Log.e("getData", e.toString());
                }
            }
        }).start();
    }


//    private List<Coupon> createCoupons() {
//        List<Coupon> coupons = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            Coupon coupon = new Coupon();
//            coupon.image = R.mipmap.bg_main;
//            coupon.name = "优惠券 " + i;
//            coupon.remark = "这是第 " + i + "条优惠券";
//            coupons.add(coupon);
//        }
//        return coupons;
//    }


}
