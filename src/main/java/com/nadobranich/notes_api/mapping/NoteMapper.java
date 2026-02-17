package com.nadobranich.notes_api.mapping;

import com.nadobranich.notes_api.domain.NoteDto;
import com.nadobranich.notes_api.domain.NoteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper implements Mapper<NoteEntity, NoteDto> {

    private ModelMapper modelMapper;

    public NoteMapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public NoteDto mapTo(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDto.class);
    }

    @Override
    public NoteEntity mapFrom(NoteDto noteDto) {
        return modelMapper.map(noteDto, NoteEntity.class);
    }
}
