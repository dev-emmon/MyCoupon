package imli.me.mycoupon.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import imli.me.mycoupon.R;
import imli.me.mycoupon.data.Coupon;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.VH> {

    private Context context;
    private OnItemClickListener onItemClickListener;
    private List<Coupon> coupons = new ArrayList<>();

    public CouponListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final Coupon coupon = getCoupon(position);
//        holder.ivCover.setImageResource(coupon.image);
        Glide.with(context).load(coupon.cover).into(holder.ivCover);
        holder.tvName.setText(coupon.name);
        holder.tvRemark.setText(coupon.description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, coupon);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public Coupon getCoupon(int position) {
        return coupons.get(position);
    }

    public void addCoupons(List<Coupon> list) {
        this.coupons.addAll(list);
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener  {
        public void onItemClick(View view, Coupon coupon);
    }

    class VH extends RecyclerView.ViewHolder {

        private ImageView ivCover;
        private TextView tvName;
        private TextView tvRemark;

        public VH(View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvName = itemView.findViewById(R.id.tv_name);
            tvRemark = itemView.findViewById(R.id.tv_remark);

        }
    }

}
