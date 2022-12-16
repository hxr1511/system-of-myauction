package com.gyk.myauction.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Auction {
    private Integer auctionid;

    private String auctionname;

    private BigDecimal auctionstartprice;

    private BigDecimal auctionupset;

    private Date auctionstarttime;

    private Date auctionendtime;

    private String auctionpic;

    private String auctionpictype;

    private String auctiondesc;

    //一对多
    private List<Auctionrecord> auctionrecordList;   // 商品的记录列表



    public Auction(Integer auctionid, String auctionname, BigDecimal auctionstartprice, BigDecimal auctionupset, Date auctionstarttime, Date auctionendtime, String auctionpic, String auctionpictype, String auctiondesc) {
        this.auctionid = auctionid;
        this.auctionname = auctionname;
        this.auctionstartprice = auctionstartprice;
        this.auctionupset = auctionupset;
        this.auctionstarttime = auctionstarttime;
        this.auctionendtime = auctionendtime;
        this.auctionpic = auctionpic;
        this.auctionpictype = auctionpictype;
        this.auctiondesc = auctiondesc;

    }
    public Auction(Integer auctionid, String auctionname, BigDecimal auctionstartprice, BigDecimal auctionupset, Date auctionstarttime, Date auctionendtime, String auctionpic, String auctionpictype, String auctiondesc, List<Auctionrecord> auctionrecordList) {
        this.auctionid = auctionid;
        this.auctionname = auctionname;
        this.auctionstartprice = auctionstartprice;
        this.auctionupset = auctionupset;
        this.auctionstarttime = auctionstarttime;
        this.auctionendtime = auctionendtime;
        this.auctionpic = auctionpic;
        this.auctionpictype = auctionpictype;
        this.auctiondesc = auctiondesc;
        this.auctionrecordList = auctionrecordList;
    }

    public List<Auctionrecord> getAuctionrecordList() {
        return auctionrecordList;
    }

    public void setAuctionrecordList(List<Auctionrecord> auctionrecordList) {
        this.auctionrecordList = auctionrecordList;
    }

    public Auction() {
        super();
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public String getAuctionname() {
        return auctionname;
    }

    public void setAuctionname(String auctionname) {
        this.auctionname = auctionname == null ? null : auctionname.trim();
    }

    public BigDecimal getAuctionstartprice() {
        return auctionstartprice;
    }

    public void setAuctionstartprice(BigDecimal auctionstartprice) {
        this.auctionstartprice = auctionstartprice;
    }

    public BigDecimal getAuctionupset() {
        return auctionupset;
    }

    public void setAuctionupset(BigDecimal auctionupset) {
        this.auctionupset = auctionupset;
    }

    public Date getAuctionstarttime() {
        return auctionstarttime;
    }

    public void setAuctionstarttime(Date auctionstarttime) {
        this.auctionstarttime = auctionstarttime;
    }

    public Date getAuctionendtime() {
        return auctionendtime;
    }

    public void setAuctionendtime(Date auctionendtime) {
        this.auctionendtime = auctionendtime;
    }

    public String getAuctionpic() {
        return auctionpic;
    }

    public void setAuctionpic(String auctionpic) {
        this.auctionpic = auctionpic == null ? null : auctionpic.trim();
    }

    public String getAuctionpictype() {
        return auctionpictype;
    }

    public void setAuctionpictype(String auctionpictype) {
        this.auctionpictype = auctionpictype == null ? null : auctionpictype.trim();
    }

    public String getAuctiondesc() {
        return auctiondesc;
    }

    public void setAuctiondesc(String auctiondesc) {
        this.auctiondesc = auctiondesc == null ? null : auctiondesc.trim();
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctionid=" + auctionid +
                ", auctionname='" + auctionname + '\'' +
                ", auctionstartprice=" + auctionstartprice +
                ", auctionupset=" + auctionupset +
                ", auctionstarttime=" + auctionstarttime +
                ", auctionendtime=" + auctionendtime +
                ", auctionpic='" + auctionpic + '\'' +
                ", auctionpictype='" + auctionpictype + '\'' +
                ", auctiondesc='" + auctiondesc + '\'' +
                ", auctionrecordList=" + auctionrecordList +
                '}';
    }
}