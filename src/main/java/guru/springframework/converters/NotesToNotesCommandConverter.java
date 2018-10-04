package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommandConverter  implements Converter<Notes, NotesCommand> {
	
	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes source) {
		if (source == null) return null;
		
		final NotesCommand target = new NotesCommand();
		target.setId(source.getId());
		return target;
	}
}
