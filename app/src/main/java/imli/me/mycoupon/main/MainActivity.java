package imli.me.mycoupon.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import imli.me.mycoupon.R;
import imli.me.mycoupon.data.Coupon;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private CouponListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvList = findViewById(R.id.rv_list);
        initList();
    }

    private void initList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        rvList.setLayoutManager(layoutManager);
        adapter = new CouponListAdapter();
        rvList.setAdapter(adapter);

        adapter.addCoupons(createCoupons());
    }


    private List<Coupon> createCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Coupon coupon = new Coupon();
            coupon.image = R.mipmap.bg_main;
            coupon.name = "优惠券 " + i;
            coupon.remark = "这是第 " + i + "条优惠券";
            coupons.add(coupon);
        }
        return coupons;
    }


}
