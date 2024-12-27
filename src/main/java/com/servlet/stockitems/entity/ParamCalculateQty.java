package com.servlet.stockitems.entity;

import com.servlet.packinglist.entity.ParamCalculateQtyPL;
import com.servlet.purchasereceive.entity.ParamCalculateQtyPR;
import com.servlet.stockadjusment.entity.ParamCalculateQtySA;

public class ParamCalculateQty {
    private ParamCalculateQtyPL paramCalculateQtyPL;
    private ParamCalculateQtyPR paramCalculateQtyPR;
    private ParamCalculateQtySA paramCalculateQtySA;

    public ParamCalculateQtyPL getParamCalculateQtyPL() {
        return paramCalculateQtyPL;
    }

    public void setParamCalculateQtyPL(ParamCalculateQtyPL paramCalculateQtyPL) {
        this.paramCalculateQtyPL = paramCalculateQtyPL;
    }

    public ParamCalculateQtyPR getParamCalculateQtyPR() {
        return paramCalculateQtyPR;
    }

    public void setParamCalculateQtyPR(ParamCalculateQtyPR paramCalculateQtyPR) {
        this.paramCalculateQtyPR = paramCalculateQtyPR;
    }

    public ParamCalculateQtySA getParamCalculateQtySA() {
        return paramCalculateQtySA;
    }

    public void setParamCalculateQtySA(ParamCalculateQtySA paramCalculateQtySA) {
        this.paramCalculateQtySA = paramCalculateQtySA;
    }
}
