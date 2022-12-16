package com.gyk.myauction.service;

import com.gyk.myauction.entity.Auctionuser;

public interface UserService {
    Auctionuser login(String username,String password);
}
