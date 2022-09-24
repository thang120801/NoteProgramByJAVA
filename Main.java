import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        NoteManage newNoteManage = new NoteManage();
        ArrayList<Note> noteList = new ArrayList<Note>();
        int yourChoice;
        while (true) {
            newNoteManage.showMenu();
            System.out.println("Enter here your selection: ");
            yourChoice = input.nextInt();
            switch (yourChoice) {
                case 1: 
                newNoteManage.addNote(noteList);
                noteList = newNoteManage.writeFile(noteList);   //after each time add a new note will put this note in noteList and write noteList in noteList.txt
                break;
                case 2:
                newNoteManage.removeNote(noteList);
                noteList = newNoteManage.writeFile(noteList);   //after each delete a note will delete this note from noteList and update again noteList.txt
                break;
                case 3: 
                newNoteManage.openNote(noteList);
                break;
                case 4:
                newNoteManage.showList(noteList);
                break;
                case 5:
                System.exit(0);
                case 6: 
                noteList = newNoteManage.loadFile(noteList);    //load data from file noteList.txt
                System.out.println("Downloaded data successfully \n");
            }
        }
    }
}