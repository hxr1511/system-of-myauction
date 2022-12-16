package com.gyk.myauction.entity;

import java.math.BigDecimal;

/**
 * 商品的拓展类
 */
public class AuctionCustom extends Auction{
    private BigDecimal auctionprice;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(BigDecimal auctionprice) {
        this.auctionprice = auctionprice;
    }
}
