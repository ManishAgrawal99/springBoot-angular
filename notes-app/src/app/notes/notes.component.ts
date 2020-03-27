import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notebook } from './model/notebook';
import { ApiService } from './../shared/api.service';
import { Note } from './model/note';

@Component({
    selector: 'app-notes',
    templateUrl: './notes.component.html',
    styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

    notebooks: Notebook[] = [];
    notes: Note[] = [];
    selectedNotebook: Notebook;
    selectedNotebookLength: number = 0;
    searchText: string;

    constructor(private apiService: ApiService) { }

    ngOnInit(): void {
        this.getAllNotebooks();
    }

    selectNotebook(notebook: Notebook){
        this.selectedNotebook = notebook;
        this.selectedNotebookLength = notebook.nbOfNotes;
        this.notes = this.selectedNotebook.notes;
        console.log(this.notes);
    }

    selectedAllNotes(){
        this.selectedNotebook = null;
        this.getAllNotes();
        
    }


    public getAllNotebooks() {
        this.apiService.getAllNotebooks().subscribe((res)=>{
            this.notebooks = res;
            this.getAllNotes();
        },
        (err)=>{
            alert("An error occured while fetching data");
        })
    } 



    createNotebook(){
        let newNotebook: Notebook = {
            name: 'New Notebook',
            id: null,
            nbOfNotes: 0,
            notes: []
        }

        this.apiService.postNotebook(newNotebook).subscribe((res)=>{
            newNotebook.id = res.id;
            this.notebooks.push(newNotebook);
        }, err =>{
            alert("Error occured while saving notebook");
        })
    }

    updateNotebook(notebook: Notebook){
        this.apiService.postNotebook(notebook).subscribe((res)=>{
           console.log(this.notes);
        }, err =>{
            alert("Error occured while updating notebook");
        })
    }


    deleteNotebook(notebook: Notebook){
        if(confirm("Are you sure?")){
            this.apiService.deleteNotebook(notebook.id).subscribe((res)=>{
                let index = this.notebooks.indexOf(notebook);
                this.notebooks.splice(index, 1);
            },
            (err)=>{
                alert("Error while deleting");
            })
        }
    }

    getAllNotes(){
        this.notes = [];
        this.notebooks.forEach((notebook)=>{
            notebook.notes.forEach((note)=>{
                this.notes.push(note);
            })
        })
        this.selectedNotebookLength = this.notes.length;
    }

    deleteNote(note: Note){
        if(confirm("Are you sure?")){
            this.apiService.deleteNote(note.id).subscribe((res)=>{
                let index = this.notes.indexOf(note);
                this.notes.splice(index, 1);
                this.selectedNotebookLength = this.notes.length;
            },
            (err)=>{
                alert("Error while deleting");
            })
        }
    }

    createNote(notebookId: string){
        let newNote: Note = {
            id: null,
            lastModifiedOn: new Date().toISOString(),
            notebookId: notebookId,
            text: 'New Note',
            title: 'Enter your title'
        }

        this.apiService.saveNote(newNote).subscribe((res)=>{
            newNote.id = res.id;
            this.notes.push(newNote);
            this.selectedNotebookLength = this.notes.length;
        }, err =>{
            alert("Error occured while saving note");
        })
    }


    updateNote(updatedNote: Note){
        this.apiService.saveNote(updatedNote).subscribe((updatedNote)=>{

        },(err)=>{
            alert("Error occured while updating the Note");
        })
    }
}
