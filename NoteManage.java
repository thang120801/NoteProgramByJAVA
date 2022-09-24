import java.io.*;
import java.util.*;

public class NoteManage {
    Scanner input = new Scanner(System.in);

    //function used to display the selection menu
    public void showMenu() {
        System.out.println("1. Add note \n");
        System.out.println("2. Delete note \n");
        System.out.println("3. See notes \n");
        System.out.println("4. See list note \n");
        System.out.println("5. Exit program \n");
        System.out.println("6. Download existing note data \n");
    }

    //function used to add new note to noteList
    public void addNote (ArrayList<Note> noteList) {
        System.out.println("Enter the title of the note");
        String noteName = input.nextLine();
        try {
            File file = new File(noteName + ".txt");
            String noteAddress = noteName + ".txt";
            System.out.println(noteAddress);
            if(file.exists()) {
                System.out.println("The name of the note file already exists, please give it another name! \n");
            }
            else {
                file.createNewFile();
                System.out.println("Note created successfully! now note what you want ! \n");
                Note newNode = new Note();
                newNode.setName(noteName);
                newNode.setNoteAdress(noteAddress);
                Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + noteAddress);
                //pro.waitFor();
                //System.out.println("Đã đóng file!");   
                noteList.add(newNode);
            }
            showMenu();
        }catch (Exception e) {
            System.out.println("An error occurred while creating a new note !!!");
        }
    }

    //function used to open a file note
    public void openNote (ArrayList<Note> noteList) throws IOException {
        System.out.println("This is a list of all available notes, which one do you want to open? \n");
        showList(noteList);
        System.out.println("***********************************************************************************\n");
        System.out.println("Enter here the name of the note you want to open: ");
        String name = input.nextLine();
        try {
            for (Note i : noteList) {
                if(name.equals(i.getName())) {
                    Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + i.getNoteAdress());
                    break;
                }
                System.out.println("There are no notes with the same name as the one you entered, please double check the name! \n");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while opening the file ! \n");
        }
    }

    //function used to display all saved notes
    public void showList (ArrayList<Note> noteList) {
        for (Note i : noteList) {
            System.out.println("The title of the note: " + i.getName() + "\n");
        }
    }
    public void removeNote (ArrayList<Note> noteList) {
        System.out.println("Here is the list of notes, which note do you want to delete from this list? \n");
        showList(noteList);
        System.out.println("***********************************************************************************\n");
        System.out.println("Enter here the title of the note you want to delete: ");
        String i = input.nextLine();
        i = i + ".txt";
        for (Note k : noteList) {
            if (i == k.getName()) {
                noteList.remove(k);
                System.out.println("Deleted successfully! \n");
                break;
            }
            System.out.println("The note you want to delete does not exist, please check the note name again ! \n");
        }
    }

    //function used to write data of noteList to a file .txt
    public ArrayList<Note> writeFile(ArrayList<Note> noteList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File("NoteList.txt"));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(noteList);
            if(fos != null) {
                fos.close();
            }
            if(oos != null) {
                oos.close();
            }
        } catch (Exception e) { System.out.println("An error occurred while saving the list of notes !!! \n");
        }
        return noteList;
    }

    //function used to load data from file noteList.txt and write to noteList
    public ArrayList<Note> loadFile(ArrayList<Note> noteList) throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File("NoteList.txt"));
            ois = new ObjectInputStream(fis);

            noteList = (ArrayList<Note>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            fis.close();
            ois.close();
        }
        return noteList;
    }
}