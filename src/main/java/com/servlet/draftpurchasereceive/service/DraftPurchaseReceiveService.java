package com.servlet.draftpurchasereceive.service;

import com.servlet.draftpurchasereceive.entity.BodyDraftPurchaseReceive;
import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveList;
import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveTemplate;
import com.servlet.draftpurchasereceive.entity.ParamSearchDraftPurchaseReceive;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface DraftPurchaseReceiveService {
    List<DraftPurchaseReceiveList> getList(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param);
    DraftPurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body);
}
