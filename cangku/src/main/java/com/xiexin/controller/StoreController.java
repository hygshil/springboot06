package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Store;
import com.xiexin.bean.StoreExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @RequestMapping("/selectAll")
    public Result selectAll(){
        List<Store> stores = storeService.selectByExample(null);
        Result result = new Result(stores);
        return result;
    }

    //带参数的分页查询
    @RequestMapping("/selectPageAll")
    public Result selectPageAll(Store store,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value = "limit",defaultValue = "10",required = true) Integer pageSize){
        System.out.println("store = " + store);
        StoreExample example = new StoreExample();
        StoreExample.Criteria criteria = example.createCriteria();
        //使用pagehelper分页
        PageHelper.startPage(page,pageSize);
        List<Store> stores = storeService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(stores);
        Result result = new Result(pageInfo);
        return result;
    }

    //修改
    @RequestMapping("/updateOne")
    public Result updateOne(@RequestBody Store store){
        int i = storeService.updateByPrimaryKeySelective(store);
        if(i==1){
            return new Result();
        }else{
            return new Result("40001","修改失败");
        }
    }

    //删除
    @RequestMapping("/del")
    public Result del(@RequestParam(value = "id",required = false) Integer id){
        int i = storeService.deleteByPrimaryKey(id);
        if(i==1){
            return new Result(0,"删除成功");
        }else{
            return new Result("40001","删除失败");
        }
    }

    //添加
    @RequestMapping("/insert")
    public Result insert(@RequestBody Store store){
        int i = storeService.insertSelective(store);
        if(i==1){
            return new Result();
        }else{
            return new Result("40001","添加失败");
        }
    }



}
