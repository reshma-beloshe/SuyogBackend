package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.WardMaster;

public interface WardService {

	public Boolean addWardService(WardMaster wardMaster);
	public Boolean updateWardService(WardMaster wardMaster);
	public Boolean deleteWardService(WardMaster wardMaster);
	public WardMaster getWardService(Integer WardId);
	public List<WardMaster> getAllWardService();
	
}
