package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.PrefixMaster;

public interface PrefixService {
    Boolean addPrefix(PrefixMaster prefixMaster);
    Boolean updatePrefix(PrefixMaster prefixMaster);
    Boolean deletePrefix(PrefixMaster prefixMaster);
    PrefixMaster getPrefix(Integer prefixId);
    List<PrefixMaster> getAllPrefixes();
}
