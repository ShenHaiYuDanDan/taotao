package com.taotao.controller;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ContentCategoryService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 *
 * 功能描述:内容分类管理
 *
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/14 14:56
 */

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    @RequestMapping("/list")
    @ResponseBody
//    加入的id不是parentId 此处使用@requestParam 此处保证parentId是有值
    public List<EasyUITreeNode>getContentCategoryList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId,String name ){
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long id){
        TaotaoResult result = contentCategoryService.deleteContentCategory(id);
        return  result;
    }
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
        return result;
    }
}
