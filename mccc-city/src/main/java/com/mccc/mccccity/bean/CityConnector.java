package com.mccc.mccccity.bean;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CityConnector {

	private static final Log LOG = LogFactory.getLog(CityConnector.class);

	private CityConnector() {
	}

	public static boolean connect(City origin, City destination) {

		LOG.info("Origin: " + origin.getName() + ", destination: " + destination.getName());

		if (origin.equals(destination))
			return true;

		if (origin.getSameRoute().contains(destination))
			return true;

		Set<City> connected = new HashSet<>(Collections.singleton(origin));

		Deque<City> bucketlist = new ArrayDeque<>(origin.getSameRoute());

		while (!bucketlist.isEmpty()) {

			City city = bucketlist.getLast();

			if (city.equals(destination))
				return true;

			if (!connected.contains(city)) {

				connected.add(city);

				bucketlist.addAll(city.getSameRoute());
				bucketlist.removeAll(connected);

			} else {
				bucketlist.removeAll(Collections.singleton(city));
			}
		}

		return false;
	}
}
