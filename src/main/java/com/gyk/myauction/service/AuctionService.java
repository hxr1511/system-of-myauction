package com.gyk.myauction.service;

import com.gyk.myauction.entity.Auction;
import com.gyk.myauction.entity.AuctionCustom;
import com.gyk.myauction.entity.Auctionrecord;
import com.gyk.myauction.utils.AuctionPriceException;

import java.util.List;
public interface AuctionService {
    List<Auction> findAuction(Auction auction);

    void addAuction(Auction auction);



    Auction findAuctionAndRecordListById(int id);

    void addRecord(Auctionrecord auctionrecord) throws AuctionPriceException;

    int deleteByPrimaryKey(Integer auctionid);


    List<AuctionCustom> findAuctionEndTime();

    List<Auction> findAuctionNoEndTime();
}
