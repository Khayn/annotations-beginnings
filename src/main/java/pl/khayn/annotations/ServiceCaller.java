package pl.khayn.annotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.khayn.annotations.abstracts.AbstractModel;
import pl.khayn.annotations.abstracts.AbstractTypedService;

public class ServiceCaller {

	public static void main(String[] args) {

		List<Map<String, String>> data = new ArrayList<>();
		Map<String, String> row = new HashMap<>();
		row.put("name", "exampleName");
		row.put("surename", "exampleSurename");
		data.add(row);

		AbstractModel input = new ExamplePojo();
		input.setInput(data);

		AbstractTypedService<ExamplePojo> service = new ExampleService();
		service.callService((ExamplePojo) input);

		System.out.println("Service output: " + input.getOutput());

	}
}
