public class Note {
    private String name;
    private String noteAddress;     //contains the address of the note file

    //getter 
    public String getName() {
        return name;
    }
    public String getNoteAdress() {
        return noteAddress;
    }
    
    //setter
    public void setName(String name) {
        this.name = name;
    }
    public void setNoteAdress(String noteAdress) {
        this.noteAddress = noteAdress;
    }
}
