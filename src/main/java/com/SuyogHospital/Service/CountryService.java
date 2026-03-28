package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.CountryMaster;

public interface CountryService {
	
		public Boolean addCountryService(CountryMaster countryMaster);
	    public Boolean updateCountryService(CountryMaster countryMaster);
	    public Boolean deleteCountryService(CountryMaster countryMaster);
	    public CountryMaster getCountryService(Integer countryId);
	    public List<CountryMaster> getAllCountryService();
}
