package com.servlet.journal.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JournalDetailPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long journalid;
    private String accountcode;

    public Long getJournalid() {
        return journalid;
    }

    public void setJournalid(Long journalid) {
        this.journalid = journalid;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }
}
