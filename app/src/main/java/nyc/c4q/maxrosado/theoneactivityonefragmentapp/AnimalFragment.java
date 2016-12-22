package nyc.c4q.maxrosado.theoneactivityonefragmentapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.maxrosado.theoneactivityonefragmentapp.POJO.Animal;
import nyc.c4q.maxrosado.theoneactivityonefragmentapp.POJO.PrimaryPOJO;
import nyc.c4q.maxrosado.theoneactivityonefragmentapp.Retrofit.ApiService;
import nyc.c4q.maxrosado.theoneactivityonefragmentapp.Retrofit.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maxrosado on 12/21/16.
 */

public class AnimalFragment extends Fragment {
    private static final String TAG = AnimalFragment.class.getSimpleName();
    ApiService apiService;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = RetrofitBuilder.makeService();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.animal_fragment, parent, false);
    }

    public void animalLayoutManager(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.animal_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        animalLayoutManager(view);
        Call<PrimaryPOJO> primaryPOJOCall = apiService.pojoGetter();
        primaryPOJOCall.enqueue(new Callback<PrimaryPOJO>() {
            @Override
            public void onResponse(Call<PrimaryPOJO> call, Response<PrimaryPOJO> response) {
                PrimaryPOJO primaryPOJO = response.body();
                List<Animal> animals = primaryPOJO.getAnimals();
                recyclerView.setAdapter(new AnimalAdapter(animals));
                Log.d(TAG, "Success! " + response);
            }

            @Override
            public void onFailure(Call<PrimaryPOJO> call, Throwable t) {
                Log.d(TAG, "Failed! " + t);
            }
        });

    }

    public class AnimalAdapter extends RecyclerView.Adapter{
        private List<Animal> animals;

        public AnimalAdapter(List<Animal> animals) {
            this.animals = animals;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.animal_item, parent, false);
            return new AnimalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Animal animal = animals.get(position);
            AnimalViewHolder animalViewHolder = (AnimalViewHolder) holder;
            animalViewHolder.bind(animal);
        }

        @Override
        public int getItemCount() {
            if(animals == null) return 0;
            return animals.size();
        }
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private Animal vhAnimal;



        public AnimalViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.animal_name);
//            textView.setOnClickListener(this);
        }

        public void bind(Animal animal){
            this.vhAnimal = animal;
            String animalName = animal.getName();
            if(animalName != null)
                textView.setText(animalName);
        }

        @Override
        public void onClick(View view) {
//            textView.setBackgroundColor();
        }
    }
}
