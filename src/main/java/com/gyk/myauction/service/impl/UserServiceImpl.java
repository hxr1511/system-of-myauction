package com.gyk.myauction.service.impl;

import com.gyk.myauction.entity.AuctionExample;
import com.gyk.myauction.entity.Auctionuser;
import com.gyk.myauction.entity.AuctionuserExample;
import com.gyk.myauction.mapper.AuctionuserMapper;
import com.gyk.myauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AuctionuserMapper auctionuserMapper;

    @Override
    public Auctionuser login(String username, String password) {
        AuctionuserExample auctionuserExample = new AuctionuserExample();
        AuctionuserExample.Criteria criteria = auctionuserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andUserpasswordEqualTo(password);

        //查询
        List<Auctionuser> auctionusers = auctionuserMapper.selectByExample(auctionuserExample);

        if (auctionusers!=null && auctionusers.size()>0){
            return auctionusers.get(0);
        }

        return null;
    }
}
