package com.nadobranich.notes_api.services;

import com.nadobranich.notes_api.domain.NoteEntity;
import com.nadobranich.notes_api.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesService {

    private NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public NoteEntity createNote(String content){
        NoteEntity newNote = new NoteEntity(content);
        return notesRepository.save(newNote);
    }

    public Optional<NoteEntity> getNoteById(int id){
        return notesRepository.findById(id);
    }

    public void deleteNoteById(int id){
        notesRepository.deleteById(id);
    }

    public NoteEntity updateNote(int id, String newContent) {
        NoteEntity existingNote = notesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found!"));
        existingNote.setContent(newContent);
        return notesRepository.save(existingNote);
    }
}
