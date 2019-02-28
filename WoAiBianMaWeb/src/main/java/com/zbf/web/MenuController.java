package com.zbf.web;

import com.zbf.common.ResponseResult;
import com.zbf.core.CommonUtils;
import com.zbf.core.page.Page;
import com.zbf.core.utils.UID;
import com.zbf.entity.Menu;
import com.zbf.service.MenuService;
import com.zbf.utils.MenuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 作者：LCG
 * 创建时间：2018/11/23 15:56
 * 描述：
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/treeMenu")
    public ResponseResult getTreeMenu(){

        List<Menu> list= menuService.getMenuTree ();

        ResponseResult responseResult=ResponseResult.getResponseResult ();

        responseResult.setResult ( list );

        return responseResult;
    }


    /**
     * 角色列表中的 权限列表回显
     * @return
     */
    @RequestMapping("/getTreeMenu2")
    public ResponseResult getTreeMenu2(HttpServletRequest request){

        List<Menu> list= menuService.getMenuTree();

        List<Long> ids=new ArrayList<Long> (  );
        //获取已经绑定给角色的菜单的IDS
        Map<String, Object> paramsJsonMap = CommonUtils.getParamsJsonMap ( request );
        List<Menu> list2= menuService.getMenuTreeByRole ( paramsJsonMap.get ( "roleId" ).toString () );
        ids=MenuUtils.getCheckedNode ( list2,ids );

        ResponseResult responseResult=ResponseResult.getResponseResult ();
        if(ids.size ()>0){
            Long[] lonids=new Long[ids.size ()];
            responseResult.setMenuIds ( ids.toArray ( lonids ) );
        }
        responseResult.setResult ( list );

        return responseResult;
    }


    /**
     * 菜单列表
     * @param request
     * @return
     */
    @RequestMapping("/menuList")
    public ResponseResult pageMenu(HttpServletRequest request){
        Map<String,Object> map= CommonUtils.getParamsJsonMap ( request );
        Page<Menu> page=new Page<Menu> ();
        page.setParams ( map );
        page.setPageNo ( Integer.valueOf ( map.get ( "pageNo" ).toString () ) );
        page.setPageSize ( Integer.valueOf ( map.get ( "pageSize" ).toString () ) );
        menuService.menuList ( page );
        ResponseResult rr= ResponseResult.getResponseResult ();
        rr.setResult ( page );
        return rr;
    }

    @RequestMapping("/addMenu")
    public ResponseResult addMenu(HttpServletRequest request){

        //parentLeval 父菜单的等级
        //parentId 父菜单的ID
        //currMenuName 添加的菜单的名称
        Map<String,Object> map=CommonUtils.getParameterMap ( request );
        ResponseResult responseResult=ResponseResult.getResponseResult ();

/*      if(map.get ( "parentLeveal" )!=null&&map.get ( "parentId" )!=null&&map.get ( "currMenuName" )!=null){
            Menu menu=new Menu();
            menu.setId ( 4444L );
            menu.setLeval ( Integer.valueOf ( map.get ( "parentLeval" ).toString ())+1 );
        }*/

        //创建实体类对象，用于存储新添加的信息
        Menu menu = new Menu();

        menu.setId(UID.next());    //自动生成一个id
        menu.setLeval(Integer.parseInt(map.get("parentLeval").toString())+1);  //子菜单等级
        menu.setMenuName(map.get("zicaidanName").toString());    //子菜单的名称
        menu.setUrl(map.get("zicaidanURL").toString());          //子菜单的地址
        menu.setParentMenuId(Integer.parseInt(map.get("parentId").toString()));   //父级菜单的id

        menuService.addCaidanlan(menu);  //调用Servie层的添加菜单栏方法，此时的对象有了一条新添加的数据

        return responseResult;
    }


    //修改
    @RequestMapping("/updateMenu")
    public ResponseResult updataMenu(HttpServletRequest request){

        return null;
    }



}
