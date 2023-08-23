
import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class FileManagement {

    private static void listDirContents(File dir) throws IOException {
        File[] ls = dir.listFiles();
        for (File f : ls) {
            if (f.isFile()) {
                System.out.println(f.getAbsoluteFile());
            } else {
                System.out.println(f.getCanonicalPath());
                listDirContents(f);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        
        // jdk7Minus(); // How files were read and written to before JDK1.7

        jdk7Plus(); // How files were read and written to JDK1.7+
    }
    
    private static void jdk7Minus() throws IOException {
        /*
        File currentPath = new File("/usr/tmp");
        System.out.println(currentPath.getCanonicalPath());
        System.out.println(currentPath.getAbsolutePath());
        System.out.println(currentPath.getPath());
        System.out.println(currentPath.isDirectory());
         */

        String homeFolder = System.getProperty("user.home");
        String slash = File.separator;
        String newLine = System.getProperty("line.separator");
        File currentFolder = new File(homeFolder);
        File newFile = new File(currentFolder, slash + "example.txt");
        System.out.println(currentFolder.getCanonicalPath());
        System.out.println(currentFolder.isDirectory());
        System.out.println(newFile.getCanonicalPath());
        System.out.println(newFile.isFile()); // will return false until it exists
        System.out.println(newFile.exists());

        // listDirContents(currentFolder);
        System.out.println(newFile.delete());

        try (FileWriter fw = new FileWriter(newFile, true); BufferedWriter bw = new BufferedWriter(fw); FileReader fr = new FileReader(newFile); BufferedReader br = new BufferedReader(fr)) {
            bw.write("Hello from the JVM" + newLine);
            bw.write("Using the old ways!" + newLine + newLine);

            for (int i = 0; i < 10_000_000; i++) {
                bw.write(i + newLine);
            }

            /*
            for (int i = 0; i < 100; i++) {
                if (fr.read() == -1) { break; }
                System.out.println((char)fr.read());
            } */
 /*
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
             */
            List<String> myLines = br.lines()
                            .filter(line -> line.startsWith("Using"))
                            .toList();
            for (String myLine : myLines) {
                System.out.println(myLine);
            }
            
        }
    }
    
    private static void jdk7Plus() throws IOException {
        
        String homeDir = System.getProperty("user.home");
        String slash = File.separator;
        String newLine = System.getProperty("line.separator");
        
        Path currDir = Paths.get(homeDir);
        System.out.println( Files.isDirectory(currDir) );
        
        // Creating and writing to a file
        Path report = Paths.get(homeDir + slash + "report.txt");
        Files.writeString(report, "This is report 1" + newLine);
        Files.writeString(report, "Everything is ok." + newLine, StandardOpenOption.APPEND);
        
        List<String> reportContents = List.of("Matt", "Susan", "Dave", "Jim", "Diane");
        Files.write(report, reportContents, StandardOpenOption.APPEND);
        
        List<String> fileContents = Files.readAllLines(report);
        System.out.println(fileContents);
        
        Scanner fileScanner = new Scanner(Paths.get(homeDir + slash + "example.txt"));
        while(fileScanner.hasNextLine()) {
            System.out.println(fileScanner.nextLine());
        }
    }
}
