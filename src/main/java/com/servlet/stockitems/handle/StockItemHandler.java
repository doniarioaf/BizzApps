package com.servlet.stockitems.handle;

import com.servlet.cancelpackinglist.service.CancelPackingListService;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockadjusment.service.StockAdjusmentService;
import com.servlet.stockitems.entity.ParamCalculateQty;
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

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;
    @Autowired
    private DraftPurchaseReceiveService draftPurchaseReceiveService;
    @Autowired
    private StockAdjusmentService stockAdjusmentService;
    @Autowired
    private PackingListService packingListService;
    @Autowired
    private CancelPackingListService cancelPackingListService;

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

    @Override
    public Long calculateQty(Long idcompany, Long idbranch, ParamCalculateQty param) {
//        Long qtyMasuk1 = draftPurchaseReceiveService.calculateQtyDpr(idcompany,idbranch, param.getParamCalculateQtyDPR());
//        Long qtyMasuk1 = purchaseReceiveService.calculateQtyPr(idcompany,idbranch, param.getParamCalculateQtyPR());
//        System.out.println("qtyMasuk1 "+qtyMasuk1);
        //Type udah hidup
//        Long qtyMasuk2 = stockAdjusmentService.calculateQtySA(idcompany,idbranch,"H", param.getParamCalculateQtySA());

//        Long qtyMasuk3 = cancelPackingListService.calculateQtyCPL(idcompany,idbranch, param.getParamCalculateQtyCPL());
//        System.out.println("qtyMasuk2 "+qtyMasuk2);
        Long qtyMasuk = calculateQtyUdangMasuk(idcompany, idbranch, param);

        Long qtyKeluar1 = packingListService.calculateQtyPL(idcompany,idbranch, param.getParamCalculateQtyPL());
//        System.out.println("qtyKeluar1 "+qtyKeluar1);
        //Type udah hidup
        Long qtyKeluar2 = stockAdjusmentService.calculateQtySA(idcompany,idbranch,"M", param.getParamCalculateQtySA());

        //Khusus untuk Qty yang di input minus, karena auto menjadi qty keluar
        Long qtyKeluar3 = stockAdjusmentService.calculateQtySA(idcompany,idbranch,"MINUS_QTY", param.getParamCalculateQtySA());
        //di convert menjadi +, agar gampang dihitung
        qtyKeluar3 = Math.abs(qtyKeluar3.longValue());

//        System.out.println("qtyKeluar2 "+qtyKeluar2);
//        Long qtyKeluar3 = cancelPackingListService.calculateQtyCPL(idcompany,idbranch, param.getParamCalculateQtyCPL());
//        if(param.getParamCalculateQtyCPL().getIdcategoryproduct() == 10 ){
//            System.out.println("param.getParamCalculateQtyPL() "+param.getParamCalculateQtyPL().toString());
//            System.out.println("qtyMasuk1 "+qtyMasuk1);
//            System.out.println("qtyMasuk2 "+qtyMasuk2);
//            System.out.println("qtyMasuk3 "+qtyMasuk3);
//            System.out.println("qtyKeluar1 "+qtyKeluar1);
//            System.out.println("qtyKeluar2 "+qtyKeluar2);
//            System.out.println("qtyKeluar2 "+qtyKeluar3);
//
//        }
        Long hasil = qtyMasuk - (qtyKeluar1.longValue() + qtyKeluar2.longValue() + qtyKeluar3.longValue());

//        System.out.println("hasil "+hasil);

        return hasil;
    }

    @Override
    public Long calculateQtyUdangMasuk(Long idcompany, Long idbranch, ParamCalculateQty param) {
        Long qtyMasuk1 = draftPurchaseReceiveService.calculateQtyDpr(idcompany,idbranch, param.getParamCalculateQtyDPR());
//        Long qtyMasuk1 = purchaseReceiveService.calculateQtyPr(idcompany,idbranch, param.getParamCalculateQtyPR());
//        System.out.println("qtyMasuk1 "+qtyMasuk1);
        //Type udah hidup
        Long qtyMasuk2 = stockAdjusmentService.calculateQtySA(idcompany,idbranch,"H", param.getParamCalculateQtySA());
//        System.out.println("qtyMasuk2 "+qtyMasuk2);
        Long qtyMasuk3 = cancelPackingListService.calculateQtyCPL(idcompany,idbranch, param.getParamCalculateQtyCPL());
//        System.out.println("qtyMasuk3 "+qtyMasuk3);
//        System.out.println("qtyKeluar2 "+qtyKeluar2);
//        Long qtyKeluar3 = cancelPackingListService.calculateQtyCPL(idcompany,idbranch, param.getParamCalculateQtyCPL());

        Long hasil = qtyMasuk1.longValue() + qtyMasuk2.longValue() + qtyMasuk3.longValue();


//        if(param.getParamCalculateQtyCPL().getIdcategoryproduct() == 10 || param.getParamCalculateQtyCPL().getIdcategoryproduct() == 5 || param.getParamCalculateQtyCPL().getIdcategoryproduct() == 1
//            || param.getParamCalculateQtyCPL().getIdcategoryproduct() == 13 || param.getParamCalculateQtyCPL().getIdcategoryproduct() == 11){
//            System.out.println("qtyMasuk1 "+qtyMasuk1);
//            System.out.println("qtyMasuk2 "+qtyMasuk2);
//            System.out.println("qtyMasuk3 "+qtyMasuk3);
//            System.out.println("hasil "+hasil);
//
//        }
        return hasil;
    }
}
