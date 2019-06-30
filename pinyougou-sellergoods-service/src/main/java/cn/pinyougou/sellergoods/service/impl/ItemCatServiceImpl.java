package cn.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import cn.pinyougou.mapper.TbTypeTemplateMapper;
import cn.pinyougou.pojo.TbItemCatExample;
import cn.pinyougou.pojo.TbTypeTemplate;
import cn.pinyougou.pojo.TbTypeTemplateExample;
import cn.pinyougou.pojogroup.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.pinyougou.mapper.TbItemCatMapper;
import cn.pinyougou.pojo.TbItemCat;
import cn.pinyougou.sellergoods.service.ItemCatService;

import entity.PageResult;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(ItemCat itemCat) {
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setId(itemCat.getId());
        tbItemCat.setName(itemCat.getName());
        tbItemCat.setParentId(itemCat.getParentId());
        tbItemCat.setTypeId(itemCat.getTypeTemplate().getId());
        itemCatMapper.insert(tbItemCat);


    }


    /**
     * 修改
     */
    @Override
    public void update(TbItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public ItemCat findOne(Long id) {
        TbItemCat tbItemCat = itemCatMapper.selectByPrimaryKey(id);
        ItemCat itemCat = new ItemCat();
        itemCat.setId(tbItemCat.getId());
        itemCat.setName(tbItemCat.getName());
        itemCat.setParentId(tbItemCat.getParentId());
        itemCat.setTypeTemplate(tbTypeTemplateMapper.selectByPrimaryKey(tbItemCat.getTypeId()));
        return itemCat;
       // return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        if (itemCat != null) {
            if (itemCat.getName() != null && itemCat.getName().length() > 0) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }

        }

        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据上级id 查询列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample catExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = catExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return itemCatMapper.selectByExample(catExample);
    }


}
