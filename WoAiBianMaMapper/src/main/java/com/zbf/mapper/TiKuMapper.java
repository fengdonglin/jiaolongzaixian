/**
 * Copyright (C),2018-2019, XXX有限公司
 * FileName: TiKuMapper
 * Author:   冯东林
 * Date:     2019/2/21 14:49
 * Description:
 * History:
 */
package com.zbf.mapper;


import com.zbf.core.page.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TiKuMapper {

    //新增题库
    public int toaddTiKuInfo(Map<String,Object> map);


    //题库列表展示
    public List<Map<String,Object>> getTikuList(Page<Map<String,Object>> page);




}