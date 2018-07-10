package imli.me.mycoupon.coupon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import imli.me.mycoupon.R;
import imli.me.mycoupon.data.Coupon;

public class CouponActivity extends AppCompatActivity {

    private ImageView ivCover;
    private TextView tvName;
    private TextView tvRemark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        ivCover = findViewById(R.id.iv_cover);
        tvName = findViewById(R.id.tv_name);
        tvRemark = findViewById(R.id.tv_remark);

        Coupon coupon = (Coupon) getIntent().getSerializableExtra("coupon");
        ivCover.setImageResource(coupon.image);
        tvName.setText(coupon.name);
        tvRemark.setText(coupon.remark);
        setTitle(coupon.name);

    }
}
