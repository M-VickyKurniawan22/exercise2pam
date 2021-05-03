package com.example.exercise2pam;
import androidx.appcompat.app.AppCompatActivity;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import com.example.exercise2pam.adapter.TemanAdapter;
        import com.example.exercise2pam.database.DBController;
        import com.example.exercise2pam.database.Teman;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.util.ArrayList;
        import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController dbController = new DBController(this);
    String id, nama, telpon;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvTeman);
        fabAdd = findViewById(R.id.fabAdd);
        readData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTemanActivity.class);
                startActivity(intent);
            }
        });
    }

    public void readData() {
        ArrayList<HashMap<String, String>> listTeman = dbController.getAllTeman();
        temanArrayList = new ArrayList<>();
        for (int i=0; i < listTeman.size(); i++) {
            Teman teman = new Teman();
            teman.setId(listTeman.get(i).get("id"));
            teman.setNama(listTeman.get(i).get("nama"));
            teman.setTelpon(listTeman.get(i).get("telpon"));

            temanArrayList.add(teman);
        }
    }
}