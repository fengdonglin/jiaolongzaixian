/**
 * Author:   冯东林
 * Date:     2019/2/21 14:42
 */
package com.zbf.web;

import com.zbf.common.ResponseResult;
import com.zbf.core.CommonUtils;
import com.zbf.core.page.Page;
import com.zbf.core.utils.UID;
import com.zbf.service.TiKuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("tiku")
@RestController
public class TiKuGuanLiController {

    @Autowired    //将service层注入
    private TiKuService tiKuService;


    //添加题库的方法
    @RequestMapping("toaddTiKuInfo")
    public ResponseResult toaddTiKuInfo(HttpServletRequest request){

        ResponseResult responseResult=ResponseResult.getResponseResult();  //单例模式
        //获取数据
        Map<String, Object> map = CommonUtils.getParamsJsonMap(request);
        try{
            //传入数据
            map.put("id", UID.getUUIDOrder());
            tiKuService.toaddTiKuInfo(map);
            responseResult.setSuccess("ok");
        }catch(Exception e){
            e.printStackTrace();  //在命令行打印异常信息在程序中出错的位置及原因
            responseResult.setError("error");
        }
        return responseResult;
    }


    //列表展示的方法
    @RequestMapping("getTikuList")
    public ResponseResult getTikuList(HttpServletRequest request){

        ResponseResult responseResult=ResponseResult.getResponseResult();
        //获取参数
        Map<String, Object> paramsJsonMap = CommonUtils.getParamsJsonMap(request);
        //分页类
        Page<Map<String, Object>> page = new Page<>();
        //设置查询参数
        page.setParams(paramsJsonMap);
        //设置分页数据
        Page.setPageInfo(page,paramsJsonMap);

        responseResult.setResult(page);

        tiKuService.getTikuList(page);

        return responseResult;
    }




}