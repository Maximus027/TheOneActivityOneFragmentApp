package nyc.c4q.maxrosado.theoneactivityonefragmentapp.Retrofit;

import nyc.c4q.maxrosado.theoneactivityonefragmentapp.POJO.PrimaryPOJO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by maxrosado on 12/21/16.
 */

public interface ApiService {
    @GET("cgi-bin/12_21_2016_exam.pl")
    Call<PrimaryPOJO> pojoGetter();
}
