package pl.khayn.annotations;

import pl.khayn.annotations.abstracts.AbstractTypedService;

public class ExampleService extends AbstractTypedService<ExamplePojo> {

	@Override
	public void callService(ExamplePojo data) {
		System.out.println(">> Service called with data: " + data);

		data.setData(formatCustomerData(data));
		data.setLength(String.valueOf(data.getData().length()));

		System.out.println(">> Service finished with data: " + data);
	}

	private String formatCustomerData(ExamplePojo data) {

		return String.format("%s %s", data.getName(), data.getSurename());
	}

}
