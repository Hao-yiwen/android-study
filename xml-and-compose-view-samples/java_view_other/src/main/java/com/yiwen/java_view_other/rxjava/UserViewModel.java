package com.yiwen.java_view_other.rxjava;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<List<User>> users;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<User> currentUser;

    public UserViewModel() {
        userRepository = new UserRepository();
        users = new MutableLiveData<>();
        currentUser = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        fetchUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    private void fetchUsers() {
        compositeDisposable.add(userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userlist -> {
                            users.setValue(userlist);
                            if(userlist.size() > 0) {
                                currentUser.setValue(userlist.get(0));
                            }
                        },
                        throwable -> Log.e("UserViewModel", "Error: " + throwable.getMessage())
                ));
    }
    
    public void updateUser() {
        compositeDisposable.add(userRepository.getUser(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> currentUser.setValue(user),
                        throwable -> Log.e("UserViewModel", "Error: " + throwable.getMessage())
                ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
