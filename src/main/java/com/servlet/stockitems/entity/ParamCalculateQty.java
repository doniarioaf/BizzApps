package com.servlet.stockitems.entity;

import com.servlet.draftpurchasereceive.entity.ParamCalculateQtyDPR;
import com.servlet.packinglist.entity.ParamCalculateQtyPL;
import com.servlet.purchasereceive.entity.ParamCalculateQtyPR;
import com.servlet.stockadjusment.entity.ParamCalculateQtySA;

public class ParamCalculateQty {
    private ParamCalculateQtyPL paramCalculateQtyPL;
//    private ParamCalculateQtyPR paramCalculateQtyPR;
    private ParamCalculateQtyDPR paramCalculateQtyDPR;
    private ParamCalculateQtySA paramCalculateQtySA;

    public ParamCalculateQtyDPR getParamCalculateQtyDPR() {
        return paramCalculateQtyDPR;
    }

    public void setParamCalculateQtyDPR(ParamCalculateQtyDPR paramCalculateQtyDPR) {
        this.paramCalculateQtyDPR = paramCalculateQtyDPR;
    }

    public ParamCalculateQtyPL getParamCalculateQtyPL() {
        return paramCalculateQtyPL;
    }

    public void setParamCalculateQtyPL(ParamCalculateQtyPL paramCalculateQtyPL) {
        this.paramCalculateQtyPL = paramCalculateQtyPL;
    }

//    public ParamCalculateQtyPR getParamCalculateQtyPR() {
//        return paramCalculateQtyPR;
//    }
//
//    public void setParamCalculateQtyPR(ParamCalculateQtyPR paramCalculateQtyPR) {
//        this.paramCalculateQtyPR = paramCalculateQtyPR;
//    }

    public ParamCalculateQtySA getParamCalculateQtySA() {
        return paramCalculateQtySA;
    }

    public void setParamCalculateQtySA(ParamCalculateQtySA paramCalculateQtySA) {
        this.paramCalculateQtySA = paramCalculateQtySA;
    }
}
