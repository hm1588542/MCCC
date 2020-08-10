package com.mccc.mccccity.service;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mccc.mccccity.bean.City;
import com.mccc.mccccity.bean.CityConnector;
import com.mccc.mccccity.repo.CityDataLoad;

@Service
public class CityService {

	private final Log LOG = LogFactory.getLog(getClass());
	@Autowired
	CityDataLoad cityDataLoad;

	public boolean isConnected(String origin, String destination) {
		City originCity = cityDataLoad.getCity(origin.toUpperCase());
		City destCity = cityDataLoad.getCity(destination.toUpperCase());

		Objects.requireNonNull(originCity);
		Objects.requireNonNull(destCity);

		LOG.info("Origin: " + originCity.getName() + ", destination: " + destCity.getName());

		return CityConnector.connect(originCity, destCity);
	}
}
