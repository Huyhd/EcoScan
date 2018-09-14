package app.creatingminds.ecoscan.ui.iteminfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import app.creatingminds.ecoscan.R;

public class IteminfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteminfo);


        TextView tvTitle = (TextView)findViewById(R.id.textView1);
        TextView tvArtist = (TextView)findViewById(R.id.textView2);
        ImageView iv = (ImageView)findViewById(R.id.imageView1);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        tvTitle.setText(intent.getStringExtra("title"));
        tvArtist.setText(intent.getStringExtra("artist"));
        iv.setImageResource(intent.getIntExtra("img", 0));
    }
}
