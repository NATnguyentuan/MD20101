package fpoly.nat.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIServicess {

    String DOMAIN = "http://10.82.1.44:3000/"; // localhost

    @GET("/api/get-list-fruit")
    Call<List<CarModel>> getCars();


    @POST("/api/add_fruit")
    Call<List<CarModel>> addCar(@Body CarModel xe);

    @PUT("/api/update-fruit-by-id/{id}")
    Call<List<CarModel>> updateCar(@Body CarModel xe);

    @DELETE("/api/deleteFruitsById/{id}")
    Call<List<CarModel>> deleteCar(String id);
}