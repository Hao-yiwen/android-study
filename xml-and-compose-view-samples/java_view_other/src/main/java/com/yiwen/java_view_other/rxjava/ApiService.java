package com.yiwen.java_view_other.rxjava;


import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/users")
    Observable<List<User>> getUsers();

    @GET("api/users/{id}")
    Observable<User> getUser(@Path("id") int id);
}
