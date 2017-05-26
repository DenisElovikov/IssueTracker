package com.issueTracker.services;

import com.issueTracker.models.bindingModels.LoggedUserModel;
import com.issueTracker.models.bindingModels.RegisterUserModel;

public interface UserService {

    void create(RegisterUserModel registerUserModel);

    RegisterUserModel findByUsername(String username);

    LoggedUserModel findByUsernameAndPassword(String username, String password);
}
