package app.creatingminds.ecoscan.ui.main;

/**
 * Created by Kimsoomin on 2017-10-28.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.creatingminds.ecoscan.R;
import app.creatingminds.ecoscan.data.model.Food;
import app.creatingminds.ecoscan.utils.FormatUtils;

// TODO: Improve performance, add ViewHolder pattern
public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<FoodInfoItem> listViewItemList = new ArrayList<FoodInfoItem>();

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_items, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = convertView.findViewById(R.id.imageView1);
        TextView titleTextView = convertView.findViewById(R.id.textView1);
        TextView descTextView = convertView.findViewById(R.id.textView2);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        FoodInfoItem foodInfoItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Drawable icon = foodInfoItem.getIcon();
        if (icon != null)
            iconImageView.setImageDrawable(icon);
        titleTextView.setText(foodInfoItem.getTitle());
        descTextView.setText(String.format("Expiration date : %s", foodInfoItem.getDesc()));

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable icon, String title, String desc) {
        FoodInfoItem item = new FoodInfoItem(icon, title, desc);
        addItem(item);
    }

    public void addItem(FoodInfoItem infoItem) {
        listViewItemList.add(infoItem);
        notifyDataSetChanged();
    }

    public void setFood(List<Food> foodList) {
        listViewItemList = new ArrayList<>();
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            listViewItemList.add(new FoodInfoItem(null, food.getName(), FormatUtils.formatDate(food.getExpireTimestamp())));
        }

        notifyDataSetChanged();
    }
}