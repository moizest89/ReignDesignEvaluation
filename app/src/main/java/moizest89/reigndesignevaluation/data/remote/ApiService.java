package moizest89.reigndesignevaluation.data.remote;

import moizest89.reigndesignevaluation.data.models.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by moizest89 on 8/14/17.
 */

public interface ApiService {



    @GET("search_by_date?query=android")
//    @GET("hits")
    Call<UserResponse> getArticlesList();



}
