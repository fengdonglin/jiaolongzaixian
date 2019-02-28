/**
 * Author:   冯东林
 * Date:     2019/2/21 15:02
 */
package com.zbf.service;

import com.zbf.core.page.Page;
import com.zbf.mapper.TiKuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TiKuService {

    @Autowired
    private TiKuMapper tiKuMapper;

    //新增题库
    public int toaddTiKuInfo(Map<String,Object> map){
        //调用Mapper层新增题库的方法
        return tiKuMapper.toaddTiKuInfo(map);
    }


    //列表展示
    public void getTikuList(Page<Map<String,Object>> page){
        //调用Mapper层列表展示的方法
        List<Map<String, Object>> list = tiKuMapper.getTikuList(page);  //查询到题库列表所有数据

        //判断题库的状态
        list.forEach((item)->{   //item 题库表的所有数据
            if(item.get("tikuzhuangtai").toString().equals("1")){
                item.put("tikuzhuangtai","开放");
            }else{
                item.put("tikuzhuangtai","关闭");
            }
        });

        //将查询到的所有数据存放在page分页工具类中
        page.setResultList(list);
    }




}