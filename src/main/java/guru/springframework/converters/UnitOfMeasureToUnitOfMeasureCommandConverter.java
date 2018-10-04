package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommandConverter  implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
	
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if (source == null) return null;
		
		final UnitOfMeasureCommand target = new UnitOfMeasureCommand();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}
}
