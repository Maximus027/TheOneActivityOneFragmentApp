package nyc.c4q.maxrosado.theoneactivityonefragmentapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maxrosado on 12/21/16.
 */

public class RetrofitBuilder {
    private static final String primaryURL = "http://jsjrobotics.nyc/";
    private static Retrofit retrofit;
    private static Retrofit.Builder builder;


    public static ApiService makeService() {
        if (retrofit == null) {
            builder = new Retrofit.Builder()
                    .baseUrl(primaryURL)
                    .addConverterFactory(GsonConverterFactory.create());
        }
        retrofit = builder.build();
        return retrofit.create(ApiService.class);
    }
}
