package nyc.c4q.maxrosado.theoneactivityonefragmentapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hostFragment(new AnimalFragment());

//        textView1 = (TextView) findViewById(R.id.sample_text);
//
//        ApiService service = RetrofitBuilder.makeService();
//        Call<PrimaryPOJO> callApi = service.pojoGetter();
//        callApi.enqueue(new Callback<PrimaryPOJO>() {
//            @Override
//            public void onResponse(Call<PrimaryPOJO> call, Response<PrimaryPOJO> response) {
//
//                PrimaryPOJO primaryPOJO = response.body();
//                List<Animal> animals = primaryPOJO.getAnimals();
//                textView1.setText(animals.get(1).getName());
//            }
//
//            @Override
//            public void onFailure(Call<PrimaryPOJO> call, Throwable t) {
//
//            }
//        });
    }

    public void hostFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout, fragment)
                .commit();
    }
}
