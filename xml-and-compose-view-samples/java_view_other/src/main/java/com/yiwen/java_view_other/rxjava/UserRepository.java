package com.yiwen.java_view_other.rxjava;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class UserRepository {
    private ApiService apiService;

    public UserRepository() {
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public Observable<List<User>> getUsers() {
        return apiService.getUsers();
    }

    public Observable<User> getUser(int id) {
        return apiService.getUser(id);
    }
}
