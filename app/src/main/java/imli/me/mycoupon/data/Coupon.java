package imli.me.mycoupon.data;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Coupon implements Serializable {

    public String cover;

    public String name;

    public String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coupon)) return false;
        Coupon coupon = (Coupon) o;
        return cover.equals(coupon.cover) &&
                name.equals(coupon.name) &&
                description.equals(coupon.description);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {

        return Objects.hash(cover, name, description);
    }
}
