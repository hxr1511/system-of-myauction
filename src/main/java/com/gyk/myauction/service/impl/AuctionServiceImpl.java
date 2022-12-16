package com.gyk.myauction.service.impl;

import com.gyk.myauction.entity.Auction;
import com.gyk.myauction.entity.AuctionCustom;
import com.gyk.myauction.entity.AuctionExample;
import com.gyk.myauction.entity.Auctionrecord;
import com.gyk.myauction.mapper.AuctionCustomMapper;
import com.gyk.myauction.mapper.AuctionMapper;
import com.gyk.myauction.mapper.AuctionrecordMapper;
import com.gyk.myauction.service.AuctionService;
import com.gyk.myauction.utils.AuctionPriceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionMapper auctionMapper;

    @Autowired
    AuctionCustomMapper auctionCustomMapper;

    @Autowired
    AuctionrecordMapper auctionrecordMapper;

    @Override
    public List<Auction> findAuction(Auction auction) {

//        return auctionMapper.selectByExample(null);  //查询全部拍卖品
        AuctionExample auctionExample = new AuctionExample();
        AuctionExample.Criteria criteria = auctionExample.createCriteria();

        if(auction!=null){
            //对名称模糊查询
            if (auction.getAuctionname()!=null&& auction.getAuctionname()!=""){
                criteria.andAuctionnameLike("%"+auction.getAuctionname()+"%");
            }
            //对描述进行模糊查询
            if (auction.getAuctiondesc()!=null&& auction.getAuctiondesc()!=""){
                criteria.andAuctiondescLike("%"+auction.getAuctiondesc()+"%");
            }
            //起始时间 大于
            if (auction.getAuctionstarttime()!=null){
                criteria.andAuctionstarttimeGreaterThan(auction.getAuctionstarttime());
            }
            //结束时间 小于
            if (auction.getAuctionendtime()!=null){
                criteria.andAuctionendtimeLessThan(auction.getAuctionendtime());
            }
            //起拍价  大于
            if (auction.getAuctionstartprice()!=null){
                criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());
            }
        }

        //按起拍时间降序排序
        auctionExample.setOrderByClause(" Auctionstarttime desc ");
        List<Auction> auctionList = auctionMapper.selectByExample(auctionExample);
        return auctionList;
    }

    @Override
    public void addAuction(Auction auction) {
        auctionMapper.insert(auction);
    }

    @Override
    public Auction findAuctionAndRecordListById(int id) {
       return auctionCustomMapper.findAuctionAndRecordListById(id);
    }

    @Override
    public void addRecord(Auctionrecord auctionrecord)  throws AuctionPriceException{
        Auction auction = findAuctionAndRecordListById(auctionrecord.getAuctionid());

        //逻辑判断
        //判断拍卖时间
        if (!auction.getAuctionendtime().after(new Date())){
            throw new AuctionPriceException("拍卖时间已结束！");
        }
        else {
            //判断价格
            if (auction.getAuctionrecordList()!=null && auction.getAuctionrecordList().size()>0){//有人竞拍了
                //竞拍价格最高价
                Auctionrecord maxRecord = auction.getAuctionrecordList().get(0);
                //a.compareTo(b)  返回-1 a<b 返回0 相等  返回1 a>b
                if (!(auctionrecord.getAuctionprice().compareTo(maxRecord.getAuctionprice())==1)){
                    throw new AuctionPriceException("价格要高于所有价格的最高价！");
                }
            }else {
                //首次竞拍价格  价格与起拍价比较
                if (!(auctionrecord.getAuctionprice().compareTo(auction.getAuctionstartprice())==1)){
                    throw new AuctionPriceException("价格要高于商品的起始价");
                }
            }
        }

         auctionrecordMapper.insert(auctionrecord);
    }

    @Override
    public int deleteByPrimaryKey(Integer auctionid) {
        return auctionMapper.deleteByPrimaryKey(auctionid);
    }

    @Override
    public List<AuctionCustom> findAuctionEndTime() {
        return auctionCustomMapper.findAuctionEndTime();
    }

    @Override
    public List<Auction> findAuctionNoEndTime() {
        return auctionCustomMapper.findAuctionNoEndTime();
    }


}
