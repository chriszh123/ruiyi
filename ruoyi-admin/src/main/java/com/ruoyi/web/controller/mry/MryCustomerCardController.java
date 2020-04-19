/**
 * Copyright (C) 2006-2019 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2020/1/17
 * Description: 客户消费卡管理
 */
package com.ruoyi.web.controller.mry;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.fac.constant.FacConstant;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.mry.exception.MryException;
import com.ruoyi.mry.model.*;
import com.ruoyi.mry.service.MryCustomerCardService;
import com.ruoyi.mry.service.MryServiceProService;
import com.ruoyi.mry.service.MryShopCardService;
import com.ruoyi.mry.service.MryShopService;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 客户消费卡管理
 * @author zhangguifeng
 * @create 2020-01-17 17:14
 **/
@Controller
@RequestMapping("/mry/customercard")
public class MryCustomerCardController extends BaseController {

    private String prefix = "mry/customercard";

    @Autowired
    private MryCustomerCardService customerCardService;
    @Autowired
    private MryShopService shopService;
    @Autowired
    private MryServiceProService serviceProService;
    @Autowired
    private MryShopCardService shopCardService;

    @RequiresPermissions("mry:customercard:view")
    @GetMapping()
    public String customercard() {
        return prefix + "/customercard";
    }

    @RequiresPermissions("mry:customercard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MryCustomerCard customerCard) {
        final Map<Long, MryCustomer> customers = this.customerCardService.listCustomers(customerCard);
        final MryShop shop = new MryShop();
        final List<MryShop> shops = this.shopService.selectShops(shop);
        final MryServicePro servicePro = new MryServicePro();
        final List<MryServicePro> servicePros = this.serviceProService.selectMryServicePros(servicePro);
        final MryShopCard shopCard = new MryShopCard();
        final List<MryShopCard> shopCards = this.shopCardService.selectShopCards(shopCard);

        startPage();
        List<MryCustomerCard> list = customerCardService.selectCustomerCards(customerCard, customers, shops, servicePros, shopCards);
        return getDataTable(list);
    }

    @RequiresPermissions("mry:customercard:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MryCustomerCard customerCard) {
        final Map<Long, MryCustomer> customers = this.customerCardService.listCustomers(customerCard);
        final MryShop shop = new MryShop();
        final List<MryShop> shops = this.shopService.selectShops(shop);
        final MryServicePro servicePro = new MryServicePro();
        final List<MryServicePro> servicePros = this.serviceProService.selectMryServicePros(servicePro);
        final MryShopCard shopCard = new MryShopCard();
        final List<MryShopCard> shopCards = this.shopCardService.selectShopCards(shopCard);

        List<MryCustomerCard> list = customerCardService.selectCustomerCards(customerCard, customers, shops, servicePros, shopCards);
        ExcelUtil<MryCustomerCard> util = new ExcelUtil<>(MryCustomerCard.class);
        return util.exportExcel(list, "客户办卡资料");
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("mry:customercard:add")
    @Log(title = "客户办卡资料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MryCustomerCard customerCard) {
        SysUser user = ShiroUtils.getSysUser();
        if (user != null) {
            customerCard.setOperatorId(user.getUserId());
            customerCard.setOperatorName(user.getUserName());
        } else {
            return AjaxResult.error(FacConstant.ERROR_MSG_LOGIN_USER_NULL);
        }
        return toAjax(customerCardService.insertMryCustomerCard(customerCard));
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        MryCustomerCard customerCard = customerCardService.selectCustomerCardById(id);
        mmap.put("customercard", customerCard);
        return prefix + "/edit";
    }

    @RequiresPermissions("mry:customercard:edit")
    @Log(title = "客户办卡资料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MryCustomerCard customerCard) {
        SysUser user = ShiroUtils.getSysUser();
        if (user != null) {
            customerCard.setOperatorId(user.getUserId());
            customerCard.setOperatorName(user.getUserName());
        } else {
            return AjaxResult.error(FacConstant.ERROR_MSG_LOGIN_USER_NULL);
        }
        try {
            return toAjax(customerCardService.updateMryCustomerCard(customerCard));
        } catch (MryException me) {
            return AjaxResult.error(me.getMessage());
        } catch (Exception ex) {
            return AjaxResult.error();
        }
    }

    @RequiresPermissions("mry:customercard:remove")
    @Log(title = "客户办卡资料", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            SysUser user = ShiroUtils.getSysUser();
            return toAjax(customerCardService.deleteMryCustomerCardByIds(ids, user));
        } catch (Exception ex) {
            return error(ex.getMessage());
        }
    }

    @PostMapping("/getCustomersByShopId")
    @ResponseBody
    public List<MryCustomer> getCustomersByShopId(MryCustomerCard customerCard) {
        List<MryCustomer> customers = this.customerCardService.getCustomersByShopId(customerCard);
        return customers;
    }

    @PostMapping("/getShopCardsByShopId")
    @ResponseBody
    public List<MryShopCard> getShopCardsByShopId(MryCustomerCard customerCard) {
        List<MryShopCard> shopCards = this.customerCardService.getShopCardsByShopId(customerCard);
        return shopCards;
    }

    @PostMapping("/getServiceProsByShopId")
    @ResponseBody
    public List<MryServicePro> getServiceProsByShopId(MryCustomerCard customerCard) {
        List<MryServicePro> servicePros = this.customerCardService.getServiceProsByShopId(customerCard);
        return servicePros;
    }
}
