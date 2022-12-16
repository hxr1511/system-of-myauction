package com.gyk.myauction.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gyk.myauction.entity.Auction;
import com.gyk.myauction.entity.AuctionCustom;
import com.gyk.myauction.entity.Auctionrecord;
import com.gyk.myauction.entity.Auctionuser;
import com.gyk.myauction.service.AuctionService;
import com.gyk.myauction.utils.AuctionPriceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Controller
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    public static final Integer PAGESIZE=5;

    /**
     * RequestParam  设置默认值为1
     * 查询所有
     * @param auction
     * @param pageNum
     * @return
     */
    @RequestMapping("/findAuction")
    public ModelAndView findAuction(@ModelAttribute("queryAuction") Auction auction,
                                    @RequestParam(defaultValue = "1" ,
                                            required = false ,value = "pageNum") Integer pageNum)
                                    {

        ModelAndView mv = new ModelAndView();


        //设置开始页面  以及页面数据
        PageHelper.startPage(pageNum,PAGESIZE);

        //查询全部拍卖品
        List<Auction> auctionList = auctionService.findAuction(auction);  //会在查询时的sql加上分页
        System.out.println(auction);
        PageInfo<Auction> pageInfo = new PageInfo<>(auctionList);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("auctionList",auctionList);  //前五条数据
        mv.setViewName("index");
//        mv.addObject("queryAuction",auction);
        return mv;
    }


    /**
     * 跳转到添加页面
     */
    @RequestMapping("/toaddAuction")
    public String toaddAuction(){
        return "addAuction";
    }

    /**
     * 新增商品
     * @param auction
     * @param pic
     * @return
     */
    @RequestMapping("/addAuction")
    public String addAcution(Auction auction, MultipartFile pic) {

        //把图片保存到本地自定义文件中
        if (pic.getSize() > 0) {//判断是否有文件  即判断大小
            String path = "D:/gyk/Project_class/secl/img";
            File picFile = new File(path, pic.getOriginalFilename());//创建文件对象
            try {
                pic.transferTo(picFile);  //把图片存入picFile
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //保存图片名字与类型
            auction.setAuctionpic(pic.getOriginalFilename());
            auction.setAuctionpictype(pic.getContentType());

        }
        //添加商品
        auctionService.addAuction(auction);

        //重定向到首页
        return "redirect:/findAuction";
    }

    @RequestMapping("/del/{auctionid}")
    public String delete(@PathVariable int auctionid){
        ModelAndView mv = new ModelAndView();
        System.out.println("要删除的商品id是："+auctionid);

        auctionService.deleteByPrimaryKey(auctionid);

        //重定向到首页
        return "redirect:/findAuction";
    }

    /**
     * 用户操作：显示竞拍详情
     * @param auctionid
     * @return
     */
    @RequestMapping("/toDetail/{auctionid}")
    public ModelAndView toDail(@PathVariable int auctionid){
        ModelAndView mv = new ModelAndView();
        System.out.println(auctionid);
        Auction auctionDetail = auctionService.findAuctionAndRecordListById(auctionid);

        mv.addObject("auctionDetail",auctionDetail);
        mv.setViewName("auctionDetail");

        return mv;
    }

    /**
     * 用户操作：保存用户竞拍价格
     * @param record
     * @param session
     * @return
     * @throws AuctionPriceException
     */
    @RequestMapping("/saveAuctionRecord")
    public String saveAuctionRecord(Auctionrecord record, HttpSession session)throws AuctionPriceException {
        //  获取到出价值和session id

        //获得用户id
        Auctionuser loginuser = (Auctionuser) session.getAttribute("loginUser");

        record.setUserid(loginuser.getUserid());
        record.setAuctiontime(new Date());

        auctionService.addRecord(record);

        //重定向
        return "redirect:/toDetail/"+record.getAuctionid();
    }

    @RequestMapping("/toAuctionResult")
    public ModelAndView toAuctionResult(){
        //拍卖结束商品
        List<AuctionCustom> auctionEndTimeList = auctionService.findAuctionEndTime();
        //拍卖中商品
        List<Auction> auctionNoEndTimeList = auctionService.findAuctionNoEndTime();
        System.out.println(auctionNoEndTimeList);

        ModelAndView mv = new ModelAndView();

        mv.addObject("endtimeList",auctionEndTimeList);
        mv.addObject("noendtimeList",auctionNoEndTimeList);

        mv.setViewName("auctionResult");
        return mv;



    }
}

