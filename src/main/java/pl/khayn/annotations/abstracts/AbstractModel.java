package pl.khayn.annotations.abstracts;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import pl.khayn.annotations.annotation.InputParam;
import pl.khayn.annotations.annotation.OutputParam;

public abstract class AbstractModel {

	private Map<String, List<String>> output = new HashMap<>();

	public Map<String, List<String>> getOutput() {

		output.clear();

		for (Field field : this.getClass().getDeclaredFields()) {
			OutputParam annotation = field.getAnnotation(OutputParam.class);

			if (annotation != null) {
				try {
					field.setAccessible(true);
					output.put(StringUtils.isNotBlank(annotation.name()) ? annotation.name() : field.getName(),
							Arrays.asList((String) field.get(this)));
					field.setAccessible(false);

				} catch (IllegalArgumentException | IllegalAccessException e) {

				}
			}
		}

		return output;
	}

	public void setInput(List<Map<String, String>> input) {

		for (Map<String, String> row : input) {
			for (Entry<String, String> entry : row.entrySet()) {
				for (Field field : this.getClass().getDeclaredFields()) {

					InputParam annotation = field.getAnnotation(InputParam.class);

					if (annotation != null && field.getName().equals(entry.getKey())) {
						try {
							field.setAccessible(true);
							field.set(this, entry.getValue());
							field.setAccessible(false);

						} catch (IllegalArgumentException | IllegalAccessException e) {

						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractModel [output=");
		builder.append(output);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((output == null) ? 0 : output.hashCode());
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
		AbstractModel other = (AbstractModel) obj;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		return true;
	}

}
