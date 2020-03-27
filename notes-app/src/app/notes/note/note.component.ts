import { Component, OnInit, Input, Output } from '@angular/core';
import { Note } from './../model/note';
import { EventEmitter } from '@angular/core';

@Component({
    selector: 'app-note',
    templateUrl: './note.component.html',
    styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

    @Input() note: Note;
    @Output() noteUpdated: EventEmitter<Note> = new EventEmitter<Note>();
    @Output() noteDeleted: EventEmitter<Note> = new EventEmitter<Note>();

    constructor() { }

    ngOnInit(): void {
    }

    updateNote(note: Note) {
        note.lastModifiedOn = new Date().toISOString();
        console.log(note.lastModifiedOn);
        this.noteUpdated.emit(note);
    }

    deleteNote(note: Note) {
        this.noteDeleted.emit(note);
    }

}
