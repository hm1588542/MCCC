package com.mccc.mccccity.bean;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class City {

	private String name;

	private Set<City> sameRoute = new HashSet<>();

	private City(String name) {
		Objects.requireNonNull(name);
		this.name = name.trim().toUpperCase();
	}

	private City() {
	}

	public static City getCity(String name) {
		return new City(name);
	}


	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City addSameRoute(City city) {
		sameRoute.add(city);
		return this;
	}

	public Set<City> getSameRoute() {
		return sameRoute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	

}
