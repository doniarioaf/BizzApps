package com.servlet.vendor.handler;

import com.servlet.area.service.AreaService;
import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.entity.*;
import com.servlet.vendor.mapper.*;
import com.servlet.vendor.repo.VendorCategoryProductNotIncludeRepo;
import com.servlet.vendor.repo.VendorRepo;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class VendorHandler implements VendorService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private VendorRepo repo;

    @Autowired
    private VendorCategoryProductNotIncludeRepo vendorCategoryProductNotIncludeRepo;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "Vendor";

    /**
     * sengaja, semua cuma query by idcompany
     */

    @Override
    public List<ListVendorData> getListAll(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListVendor().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListVendor(), queryParameters);
    }

    @Override
    public VendorData getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryVendorDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<VendorData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryVendorDetail(), queryParameters);
        if(list != null && list.size() > 0){
            VendorData ven = list.get(0);
            ven.setItems(getListItems(id));
            return ven;
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyVendor body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        if(!body.getIsparent() && !body.getType().equals("BROKER")) {
            ListVendorData ven = checkVendorIsParent(idcompany,idbranch, body.getIdvendorparent());
            if(ven == null){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_PARENT, "Vendor Bukan Parent");
                validations.add(msg);
            }
        }
        if(validations.size() == 0) {
            try {
                Vendor vendor = new Vendor();
                vendor.setIdcompany(idcompany);
                vendor.setIdbranch(idbranch);
                vendor.setNama(body.getNama());
                vendor.setAlias(body.getAlias());
                vendor.setType(body.getType());
                vendor.setBank(body.getBank());
                vendor.setAccountnobank(body.getAccountnobank());
                vendor.setAccountnamebank(body.getAccountnamebank());
                vendor.setPricebox(body.getPricebox());
                vendor.setPriceongkos(body.getPriceongkos());
                vendor.setPacking(body.getPacking());
                vendor.setKurir(body.getKurir());
                vendor.setKomisi(body.getKomisi());
                vendor.setProfit(body.getProfit());
                vendor.setValue1(body.getValue1());
                vendor.setIsparent(body.getIsparent());
                if (body.getIsparent()) {
                    vendor.setIdvendorparent(null);
                } else {
                    vendor.setIdvendorparent(body.getIdvendorparent());
                }
                if(body.getType().equals("UDANG")){
                    vendor.setIdvendorbroker(body.getIdvendorbroker());
                }
                vendor.setIdarea(body.getIdarea());
                vendor.setAddress1(body.getAddress1());
                vendor.setAddress2(body.getAddress2());
                vendor.setNpwp(body.getNpwp());
                vendor.setPhone(body.getPhone());
                vendor.setLimittransaction(body.getLimittransaction().equals("Y")?true:false);
                vendor.setIsdelete(false);
                vendor.setCreateddate(ts);
                vendor.setCreatedby(iduser);
                idsave = repo.saveAndFlush(vendor).getId();
                HashMap<Object, Object> mapsItems = setItems(body.getIdcategoryproduct(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if (validationsItems.size() == 0) {
                    String data = vendor.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = " + data + " | Items = " + dataItems;
                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "ADD", namaMenu, mixData, "", "", ts);
                } else {
//                repo.deleteById(idsave);
//                vendorCategoryProductNotIncludeRepo.deleteAllByIdVendor(idsave);

                    validations.add(validationsItems.get(0));
                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyVendor body) {
        List<ValidationDataMessage> validations = new ArrayList<>();

        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        if(!body.getIsparent() && !body.getType().equals("BROKER")) {
            ListVendorData ven = checkVendorIsParent(idcompany,idbranch, body.getIdvendorparent());
            if(ven == null){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_PARENT, "Vendor Bukan Parent");
                validations.add(msg);
            }
        }
        if(validations.size() == 0) {
            try {
                Vendor vendor = repo.getById(id);
                vendor.setNama(body.getNama());
                vendor.setAlias(body.getAlias());
                vendor.setType(body.getType());
                vendor.setBank(body.getBank());
                vendor.setAccountnobank(body.getAccountnobank());
                vendor.setAccountnamebank(body.getAccountnamebank());
                vendor.setPricebox(body.getPricebox());
                vendor.setPriceongkos(body.getPriceongkos());
                vendor.setPacking(body.getPacking());
                vendor.setKurir(body.getKurir());
                vendor.setKomisi(body.getKomisi());
                vendor.setProfit(body.getProfit());
                vendor.setValue1(body.getValue1());
                vendor.setIsparent(body.getIsparent());
                if (body.getIsparent()) {
                    vendor.setIdvendorparent(null);
                } else {
                    vendor.setIdvendorparent(body.getIdvendorparent());
                }
                if(body.getType().equals("UDANG")){
                    vendor.setIdvendorbroker(body.getIdvendorbroker());
                }else{
                    vendor.setIdvendorbroker(null);
                }
                vendor.setIdarea(body.getIdarea());
                vendor.setAddress1(body.getAddress1());
                vendor.setAddress2(body.getAddress2());
                vendor.setNpwp(body.getNpwp());
                vendor.setPhone(body.getPhone());
                vendor.setLimittransaction(body.getLimittransaction().equals("Y")?true:false);
                vendor.setModifieddate(ts);
                vendor.setModifiedby(iduser);
                idsave = repo.saveAndFlush(vendor).getId();

                List<VendorCategoryProductNotIncludeData> listItems = getListItems(idsave);
                List<String> ls = new ArrayList<>();
                if (ls != null && ls.size() > 0) {
                    for (VendorCategoryProductNotIncludeData val : listItems) {
                        ls.add(val.getIdcategoryproduct().toString());
                    }
                }
                String dataBefore = vendor.toString();
                String dataItemsBefore = ls.toString();
                String mixDataBefore = "header = " + dataBefore + " | Items = " + dataItemsBefore;

                vendorCategoryProductNotIncludeRepo.deleteAllByIdVendor(id);
                HashMap<Object, Object> mapsItems = setItems(body.getIdcategoryproduct(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if (validationsItems.size() == 0) {
                    String data = vendor.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = " + data + " | Items = " + dataItems;
                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", mixData, mixDataBefore, ts);
                } else {
                    validations.add(validationsItems.get(0));
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData delete(Long id, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Vendor vendor = repo.getById(id);
            vendor.setIsdelete(true);
            vendor.setDeletedate(ts);
            vendor.setDeleteby(iduser);
            idsave = repo.saveAndFlush(vendor).getId();
        }catch (Exception e){
            // TODO: handle exception
            e.printStackTrace();
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public VendorTemplate getTemplate(Long idcompany, Long idbranch) {
        VendorTemplate template = new VendorTemplate();
        ParamTemplate cpParam = new ParamTemplate();
        cpParam.setForcategory("VENDOR");
        template.setCategoryProductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,cpParam));
        ParamVendor pv = new ParamVendor();
        pv.setOnlyParent("Y");
        template.setVendorParentOpt(getListDropdown(idcompany,idbranch,pv));

        ParamVendor pvbroker = new ParamVendor();
        pvbroker.setVendorTypes("'BROKER'");
        template.setVendorBrokerOpt(getListDropdown(idcompany,idbranch,pvbroker));

        template.setAreaOpt(areaService.getList(idcompany,idbranch));
        return template;
    }

    @Override
    public List<VendorDataForTemplate> getListDropdown(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListForDropdownList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListForDropdownList(), queryParameters);
    }

    @Override
    public List<VendorDataForTemplate> getListDropdown(Long idcompany, Long idbranch, ParamVendor param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListForDropdownList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        if(param.getListIdVendor() != null && !param.getListIdVendor().equals("")){
            sqlBuilder.append(" and data.id in ("+param.getListIdVendor()+") ");
        }
        if(param.getVendorTypes() != null && !param.getVendorTypes().equals("")){
            sqlBuilder.append(" and data.type in ("+param.getVendorTypes()+") ");
        }
        if(param.getOnlyParent() != null && !param.getOnlyParent().equals("")){
            sqlBuilder.append(" and data.isparent = true ");
        }

        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListForDropdownList(), queryParameters);
    }

    @Override
    public String queryIdVendorCategoryProductNotInclud(Long idcompany, Long idbranch,Long idvendor) {
        String query = "select idcategoryproduct from vendor_categoryproduct_not_include as vc ";
        query += " where vc.idvendor = "+idvendor;
        return query;
    }

    @Override
    public ListVendorData checkVendorIsParent(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListVendor().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false and data.isparent = true ");
        final Object[] queryParameters = new Object[] {idvendor,idcompany};
        List<ListVendorData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListVendor(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Long getIdParent(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryGetIdParent().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idvendor,idcompany};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryGetIdParent(), queryParameters);
        if(list != null && list.size() > 0){
            //Jika ada id parent maka ambil idparent
            return list.get(0) != null && list.get(0)  != 0?list.get(0):idvendor;
        }
        return null;
    }

    @Override
    public List<Long> getListSubIdParent(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryGetId().schema());
        sqlBuilder.append(" where data.idvendorparent = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idvendor,idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryGetId(), queryParameters);
    }

    @Override
    public List<Long> getListSubIdParentByListIdParent(Long idcompany, Long idbranch, String listidvendorparents) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryGetId().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        sqlBuilder.append(" and data.idvendorparent in ("+listidvendorparents+") ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryGetId(), queryParameters);
    }

    @Override
    public List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCheckIdCategoryProduct().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and items.idcategoryproduct = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch,idcategoryProduct};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCheckIdCategoryProduct(), queryParameters);
    }

    @Override
    public Boolean isLimitTransaksi(Long id) {
        Vendor ven = repo.getById(id);
        if(ven != null){
            return ven.getLimittransaction();
        }
        return false;
    }


    private HashMap<Object,Object> setItems(Long[] items, Long idvendor){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        String dataItems = "";
        List<String> listitems = new ArrayList<>();
        try {
            if (items.length > 0) {
                for(Long idcategoryproduct:items ){
                    VendorCategoryProductNotIncludePK pk = new VendorCategoryProductNotIncludePK();
                    pk.setIdcategoryproduct(idcategoryproduct);
                    pk.setIdvendor(idvendor);
                    VendorCategoryProductNotInclude ven = new VendorCategoryProductNotInclude();
                    ven.setVendorCategoryProductNotIncludePK(pk);
                    vendorCategoryProductNotIncludeRepo.saveAndFlush(ven);
                    listitems.add(idcategoryproduct.toString());
                }
                dataItems = listitems.toString();
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }

    private List<VendorCategoryProductNotIncludeData> getListItems(Long idvendor){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryVendorCategoryProductNotInclude().schema());
        sqlBuilder.append(" where data.idvendor = ? ");
        final Object[] queryParameters = new Object[] {idvendor};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryVendorCategoryProductNotInclude(), queryParameters);
    }
}
