import java.io.File;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class AddNote {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String noteName = null;
        System.out.println("Nhập tên file note: ");
        noteName = input.nextLine();
        String noteNameTXT = noteName.replaceAll(" ", "");
        System.out.println(noteNameTXT);
        try {
            File file = new File(noteName + ".txt");
            if(file.exists()) {
                System.out.println("File đã tồn tại, vui lòng đặt tên khác ! \n");
            }
            else {
                file.createNewFile();
                System.out.println("Đã tạo thành công note mới, bây giờ hãy note những gì bạn muốn ! \n");
                TimeUnit.SECONDS.sleep(1);  //pause 1s before opening file
                Process pro = Runtime.getRuntime().exec("Rundll32 url.dll, FileProtocolHandler" + file);
                pro.waitFor();
            }
        }catch (Exception e) {
            System.out.println("Đã có lỗi xảy ra !!!");
        }
    }
}
