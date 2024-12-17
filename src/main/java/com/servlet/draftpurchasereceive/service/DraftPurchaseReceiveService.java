package com.servlet.draftpurchasereceive.service;

import com.servlet.draftpurchasereceive.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface DraftPurchaseReceiveService {
    List<DraftPurchaseReceiveList> getList(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param);
    DraftPurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body);
    SearchDataTemplateByVendor getTemplateByIdVendor(Long idcompany, Long idbranch, Long idvendor);
}
