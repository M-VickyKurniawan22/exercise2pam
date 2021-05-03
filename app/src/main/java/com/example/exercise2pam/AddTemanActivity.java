package com.example.exercise2pam;
import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.example.exercise2pam.database.DBController;
        import com.google.android.material.textfield.TextInputEditText;

        import java.util.HashMap;

public class AddTemanActivity extends AppCompatActivity {

    private TextInputEditText tiNama, tiNoTelfon;
    private Button btnSave;
    String nama, telpon;
    DBController dbController = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teman);

        tiNama = findViewById(R.id.tiNama);
        tiNoTelfon = findViewById(R.id.tiNoTelepon);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = tiNama.getText().toString();
                telpon = tiNoTelfon.getText().toString();

                if (nama.isEmpty() || telpon.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Harap lengkapi data!", Toast.LENGTH_SHORT).show();
                } else {

                    HashMap<String, String> values = new HashMap<>();
                    values.put("nama", nama);
                    values.put("telpon", telpon);

                    dbController.insertData(values);
                    openMainActivity();
                }
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}