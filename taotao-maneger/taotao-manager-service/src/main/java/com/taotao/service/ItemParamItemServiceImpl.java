package com.taotao.service;

//import com.taotao.mapper.TbItemParamItemMapper;
//import com.taotao.pojo.TbItemParamItem;
//import com.taotao.pojo.TbItemParamItemExample;
//import com.taotao.pojo.TbItemParamItemExample.Criteria;
//import com.taotao.utils.JsonUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;

/**
 *
 * 功能描述:展示商品规格参数
 *
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/6 19:37
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;


    public String getItemParamByItemId(Long itemId) {
//根据商品id查询规格参数
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
//        执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
//        if没有值 返回空的字符串
        if (list == null || list.size() == 0) {
            return "";
        }

//        取规格参数信息
        TbItemParamItem item = list.get(0);

        String paramData = item.getParamData();
//        生成一个html
//        把规格参数json数据转化为java对象

        List<Map> jsonToList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb=new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:jsonToList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
