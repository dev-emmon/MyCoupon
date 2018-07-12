package imli.me.mycoupon.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;

import imli.me.mycoupon.App;
import imli.me.mycoupon.R;
import imli.me.mycoupon.coupon.CouponActivity;
import imli.me.mycoupon.data.Coupon;
import imli.me.mycoupon.data.Resulte;
import imli.me.mycoupon.main.CouponListAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private CouponListAdapter adapter;
    private Handler handler;
    private SmartRefreshLayout refreshLayout;
    private int curPage = 1;
    private String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        App app = (App) getApplication();
        searchText = getIntent().getStringExtra("searchText");
        setTitle(searchText);
        refreshLayout = findViewById(R.id.sr_layout);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Resulte.CouponResult result = (Resulte.CouponResult) msg.obj;

                int page = msg.arg1;
                if (page == 1) {
                    refreshLayout.finishRefresh(2000);
                    adapter.refreshCoupons(result.data);
                } else {
                    refreshLayout.finishLoadMore(2000);
                    adapter.addCoupons(result.data);
                }
            }
        };

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refersh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });


        refersh();

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
                Intent intent = new Intent(SearchActivity.this, CouponActivity.class);
                intent.putExtra("coupon", coupon);
                startActivity(intent);
            }
        });
    }

    private void loadMore() {
        getData(++curPage);
    }

    private void refersh() {
        getData(1);
    }

    /**
     * 获取数据
     */
    private void getData(final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://39.106.207.131/api/product" + "?page=" + page + "&search=" + searchText)
                            .build();
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    Log.d("getData", data);

                    Resulte.CouponResult result = new Gson().fromJson(data, Resulte.CouponResult.class);

                    Message msg = new Message();
                    msg.obj = result;
                    msg.arg1 = page;

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
