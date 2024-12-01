package com.servlet.purchasereceive.handler;

import com.servlet.pricelist.service.PriceService;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.BodyPurchaseReceive;
import com.servlet.purchasereceive.entity.PurchaseReceive;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import com.servlet.purchasereceive.entity.PurchaseReceiveTemplate;
import com.servlet.purchasereceive.mapper.QueryDataList;
import com.servlet.purchasereceive.repo.PurchaseReceiveRepo;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseReceiveHandler implements PurchaseReceiveService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PurchaseReceiveRepo purchaseReceiveRepo;

    @Autowired
    private VendorService vendorService;
    @Autowired
    private PriceService priceService;

    @Autowired
    private ProductService productService;

    @Override
    public List<PurchaseReceiveDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(from != null){
            Date dt = new Date(from);
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
        }
        if(to != null){
            Date dt = new Date(to);
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);

    }

    @Override
    public PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch,Long pricedate) {
        PurchaseReceiveTemplate data = new PurchaseReceiveTemplate();
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch));
        data.setPriceItems(priceService.getDataPriceByDate(idcompany,idbranch,pricedate));
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            PurchaseReceive table = new PurchaseReceive();
            table.setIdcompany(idcompany);
            table.setIdbranch(idbranch);
            table.setIdvendor(body.getIdvendor());
            table.setTransactiondate(new Date(body.getTransactiondate()));
            table.setKoli(body.getKoli());
            table.setNotes(body.getNotes());
            table.setBank(body.getBank());
            table.setAccountnobank(body.getAccountnobank());
            table.setAccountnamebank(body.getAccountnamebank());
            table.setCreateddate(ts);
            table.setCreatedby(iduser);

        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }
}
