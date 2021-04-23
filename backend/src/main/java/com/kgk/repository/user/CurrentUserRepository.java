package com.kgk.repository.user;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.kgk.model.user.User;

public interface CurrentUserRepository {

    User findCurrentUser(AwsProxyRequest awsRequest);

}
