package com.pomoguy.MonArch.model.committee;

import javax.persistence.CollectionTable;
import javax.persistence.Table;

public enum Status {
    NEW,
    PENDING_COMMITTE,
    PENDING_REVISION,
    RESOLVED_DONE,
    RESOLVED_REJECTED

}
