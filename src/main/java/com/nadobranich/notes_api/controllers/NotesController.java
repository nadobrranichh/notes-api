package com.nadobranich.notes_api.controllers;

import com.nadobranich.notes_api.domain.NoteDto;
import com.nadobranich.notes_api.domain.NoteEntity;
import com.nadobranich.notes_api.mapping.NoteMapper;
import com.nadobranich.notes_api.services.NotesService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/notes")
public class NotesController {

    private NotesService notesService;
    private NoteMapper noteMapper;

    public NotesController(NotesService notesService, NoteMapper noteMapper) {
        this.notesService = notesService;
        this.noteMapper = noteMapper;
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable int id){
        Optional<NoteEntity> foundNote = notesService.getNoteById(id);
        if(foundNote.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(noteMapper.mapTo(foundNote.get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto){
        NoteEntity createdNote = notesService.createNote(noteDto.getContent());
        return new ResponseEntity<>(noteMapper.mapTo(createdNote), HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable int id, @RequestBody String newContent){
        // if the note was found: updates, returns the updated note, status 200
        // if the note wasn't found: returns status 404

        try {
            NoteEntity updatedNote = notesService.updateNote(id, newContent);
            return new ResponseEntity<>(noteMapper.mapTo(updatedNote), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable int id){
        notesService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
