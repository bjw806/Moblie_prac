package univ.yonsei.finaltestproblem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Problem1 extends AppCompatActivity {
    ArrayList<listview> movieDataList;
    String[] titles = {
            "헬보이 – 골든아미", "백투더 퓨쳐", "여인의 향기", "포레스트 검프", "사랑의 블랙홀",
            "혹성탈출 – 진화의 시작", "아름다운 비행", "내 이름은 칸", "해리포터 – 죽음의 성물 2", "마더"
    };

    Integer[] images = {
            R.drawable.p1mov01, R.drawable.p1mov02, R.drawable.p1mov03, R.drawable.p1mov04, R.drawable.p1mov05,
            R.drawable.p1mov06, R.drawable.p1mov07, R.drawable.p1mov08, R.drawable.p1mov09, R.drawable.p1mov10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem1);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.p1ListView);
        final MyListViewAdapter myAdapter = new MyListViewAdapter(this,movieDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<listview>();
        int position = 0;
        while(position < 10) {
            movieDataList.add(new listview(images[position], titles[position]));
            position++;
        }
    }
}

class listview{
    private int poster;
    private String name;

    public listview(int poster, String name) {
        this.poster = poster;
        this.name = name;
    }

    public int getPoster(){
        return this.poster;
    }

    public String getName(){
        return this.name;
    }
}

class MyListViewAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<listview> sample;

    public MyListViewAdapter(Context context, ArrayList<listview> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public listview getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = mLayoutInflater.inflate(R.layout.p1listitem, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.image1);
        TextView movieName = (TextView)view.findViewById(R.id.text1);

        imageView.setImageResource(sample.get(position).getPoster());
        movieName.setText(sample.get(position).getName());

        return view;
    }
}

