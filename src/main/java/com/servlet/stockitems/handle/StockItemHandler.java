package com.servlet.stockitems.handle;

import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockitems.entity.StockItems;
import com.servlet.stockitems.entity.StockItemsPK;
import com.servlet.stockitems.repo.StockItemsRepo;
import com.servlet.stockitems.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockItemHandler implements StockItemService {
    @Autowired
    private StockItemsRepo repo;

    @Override
    public ReturnData tambah(Long idcompany, Long idbranch,Long idproduct,Long idcategoryproduct,String type, Long qty) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        try{
            StockItemsPK pk = new StockItemsPK();
            pk.setIdcompany(idcompany);
            pk.setIdbranch(idbranch);
            pk.setIdproduct(idproduct);
            pk.setIdcategoryproduct(idcategoryproduct);
            pk.setType(type);
            Optional<StockItems> check = repo.findById(pk);
            if(check.isPresent()){
                StockItems table = check.get();
                table.setQty(table.getQty() + qty);
                repo.saveAndFlush(table);
            }else{
                StockItems table = new StockItems();
                table.setStockItemsPK(pk);
                table.setQty(qty);
                repo.saveAndFlush(table);
            }
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        ReturnData data = new ReturnData();
        data.setId(0);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData kurang(Long idcompany, Long idbranch,Long idproduct,Long idcategoryproduct,String type, Long qty) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        try{
            StockItemsPK pk = new StockItemsPK();
            pk.setIdcompany(idcompany);
            pk.setIdbranch(idbranch);
            pk.setIdproduct(idproduct);
            pk.setIdcategoryproduct(idcategoryproduct);
            pk.setType(type);
            Optional<StockItems> check = repo.findById(pk);
            if(check.isPresent()){
                StockItems table = check.get();
                long qtyTemp = table.getQty() - qty.longValue();
                if(qtyTemp >= 0){
                    table.setQty(qtyTemp);
                    repo.saveAndFlush(table);
                }else{
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STOCK_NOT_ENOUGH,"Stock tidak cukup");
                    validations.add(msg);
                }

            }else{
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STOCK_NOT_EXIST,"Stock tidak ada");
                validations.add(msg);
            }
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        ReturnData data = new ReturnData();
        data.setId(0);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }
}
