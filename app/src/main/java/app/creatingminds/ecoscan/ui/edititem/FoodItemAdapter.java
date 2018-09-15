package app.creatingminds.ecoscan.ui.edititem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.creatingminds.ecoscan.R;

/**
 * Created by Kimsoomin on 2017-10-29.
 */

// TODO: Improve performance, add ViewHolder pattern
public class FoodItemAdapter extends BaseAdapter {

        // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
        private List<FoodItem> listViewItemListnew = new ArrayList<>();

        // ListViewAdapter의 생성자
        public FoodItemAdapter() {

        }

        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {
            return listViewItemListnew.size() ;
        }

        // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_newitems, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            TextView NameView = convertView.findViewById(R.id.Name);
            TextView InfoVIew = convertView.findViewById(R.id.Infomation);

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            FoodItem listViewItemnew = listViewItemListnew.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            NameView.setText(listViewItemnew.getTitle());
            InfoVIew.setText(listViewItemnew.getTime());

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
            return listViewItemListnew.get(position) ;
        }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(String title, String time) {
            FoodItem newItem = new FoodItem();

            newItem.setTitle(title);
            newItem.setTime(time);

            addItem(newItem);
        }

    public void addItem(FoodItem foodItem) {
        listViewItemListnew.add(foodItem);
        notifyDataSetChanged();
    }

    public void setFoodList(List<FoodItem> foodList) {
        listViewItemListnew = foodList;
        notifyDataSetChanged();
        }
    }
