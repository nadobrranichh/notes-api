package com.nadobranich.notes_api.repositories;

import com.nadobranich.notes_api.domain.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends CrudRepository<NoteEntity, Integer> {

}
