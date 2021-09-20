package com.pomoguy.MonArch.model.committee;

import javax.persistence.CollectionTable;
import javax.persistence.Table;

public enum CommitteeStatusStale {
    NEW,
    PENDING_COMMITTEE,
    PENDING_CORRECTION,
    RESOLVED_REJECTED,
    RESOLVED_DONE


}
