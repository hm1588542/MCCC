package com.mccc.mccccity.repo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mccc.mccccity.bean.City;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class CityDataLoad {

	private final Log LOG = LogFactory.getLog(getClass());

	private Map<String, City> cityMap = new HashMap<>();

	@Value("${data.file:classpath:cities.txt}")
	private String cityFile;

	@Autowired
	private ResourceLoader resourceLoader;


	@PostConstruct
	private void loadFile() throws IOException {

		LOG.info("File reading starting ");

		Resource resource = resourceLoader.getResource(cityFile);

		InputStream is;

		if (!resource.exists()) {
			is = new FileInputStream(new File(cityFile));
		} else {
			is = resource.getInputStream();
		}
		Scanner scanner = new Scanner(is);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (StringUtils.isEmpty(line))
				continue;
			LOG.info(line);
			String[] split = line.split(",");
			String o = split[0].trim().toUpperCase();
			String d = split[1].trim().toUpperCase();
			if (!o.equals(d)) {
				City origin = cityMap.getOrDefault(o, City.getCity(o));
				City destination = cityMap.getOrDefault(d, City.getCity(d));

				origin.addSameRoute(destination);
				destination.addSameRoute(origin);

				cityMap.put(origin.getName(), origin);
				cityMap.put(destination.getName(), destination);
			}
		}
		scanner.close();
	}

	public City getCity(String name) {
		return cityMap.get(name);
	}

}
