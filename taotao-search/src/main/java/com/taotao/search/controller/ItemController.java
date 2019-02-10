package com.taotao.search.controller;

        import com.taotao.search.service.ItemService;
        import com.taotao.utils.TaotaoResult;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author chengpunan
 * @Description //TODO 索引库维护
 * @Date 12:45 2019/2/10 0010
 * @Param
 * @return
 **/

@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService itemService;
    /**
     * @Author chengpunan
     * @Description //TODO 导入商品到索引库
     * @Date 12:59 2019/2/10 0010
     * @Param
     * @return
     **/

    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItems() throws Exception {
        TaotaoResult result = itemService.importAllItems();
        return result;
    }
}
