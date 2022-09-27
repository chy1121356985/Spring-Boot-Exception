package auto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMessage {
    private String fileName;
    private Long fileSize;

    public FileMessage(String fileName, Long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public static void main(String[] args) {
        String str = "0";
        String pattern = "0 == Unknown 1 == Rendering  2 == Idle  3 == Offline  4 == Stalled  8 == StartingJob";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}