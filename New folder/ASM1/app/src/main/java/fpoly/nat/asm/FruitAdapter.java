package fpoly.nat.asm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {

    List<FruitModel> carModelList;

    Context context;

    public FruitAdapter(Context context, List<FruitModel> carModelList) {
        this.context = context;
        this.carModelList = carModelList;
    }

    @Override
    public int getCount() {
        return carModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return carModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_car, viewGroup, false);

        TextView tvID = (TextView) rowView.findViewById(R.id.tvId);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgAvatatr);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);

        TextView tvNamSX = (TextView) rowView.findViewById(R.id.tvNamSX);

        TextView tvHang = (TextView) rowView.findViewById(R.id.tvHang);

        TextView tvGia = (TextView) rowView.findViewById(R.id.tvGia);

//        String imageUrl = mList.get(position).getThumbnailUrl();
//        Picasso.get().load(imageUrl).into(imgAvatar);
////        imgAvatar.setImageResource(imageId[position]);
        tvName.setText(String.valueOf(carModelList.get(position).getTen()));

        tvNamSX.setText(String.valueOf(carModelList.get(position).getNamSX()));

        tvHang.setText(String.valueOf(carModelList.get(position).getHang()));

        tvGia.setText(String.valueOf(carModelList.get(position).getGia()));

        return rowView;
    }
}