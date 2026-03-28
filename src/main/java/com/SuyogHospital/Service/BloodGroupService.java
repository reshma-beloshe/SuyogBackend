package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.BloodGroupMaster;

public interface BloodGroupService {
    Boolean addBloodGroup(BloodGroupMaster bloodGroupMaster);
    Boolean updateBloodGroup(BloodGroupMaster bloodGroupMaster);
    Boolean deleteBloodGroup(BloodGroupMaster bloodGroupMaster);
    BloodGroupMaster getBloodGroup(Integer bloodGroupId);
    List<BloodGroupMaster> getAllBloodGroups();
}
