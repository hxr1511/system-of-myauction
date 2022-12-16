package com.gyk.myauction.mapper;

import com.gyk.myauction.entity.Auction;
import com.gyk.myauction.entity.AuctionCustom;

import java.util.List;

public interface AuctionCustomMapper {
    Auction findAuctionAndRecordListById(int id);

    /**
     * 查询拍卖结束商品
     * @return
     */
    List<AuctionCustom> findAuctionEndTime();

    /**
     * 查询拍卖中商品
     */
    List<Auction> findAuctionNoEndTime();
}
