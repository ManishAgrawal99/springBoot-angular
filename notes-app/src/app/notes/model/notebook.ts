import { Note } from './note';

export interface Notebook{

    id: string;
    name: string;
    nbOfNotes: number;
    notes: Note[];
}