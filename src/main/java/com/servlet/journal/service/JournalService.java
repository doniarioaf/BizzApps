package com.servlet.journal.service;

import com.servlet.journal.entity.BodyMigrasi;
import com.servlet.journal.entity.PostingJournalParam;
import com.servlet.journal.entity.SaldoJournal;
import com.servlet.journal.entity.SaldoJournalParam;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

import java.util.List;

public interface JournalService {
    List<ValidationDataMessage> postingJournal(PostingJournalParam param);
    ReturnData migrationOrIntegrity(Long idcompany, Long idbranch, Long iduser, BodyMigrasi payload);

    List<ValidationDataMessage> updateJournalDetail(PostingJournalParam param);
    List<ValidationDataMessage> deleteJournalBySourceNumber(String sourceNumber);
    SaldoJournal calculateSaldo(SaldoJournalParam param);
}
