import static java.lang.System.out;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(final String[] args) {        
        if (args.length < 1 || args.length > 1) {
            out.print("param missing\n");
        } else {
            /*
            File[] files = new File(args[0]).listFiles();

            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            Calendar c = Calendar.getInstance();
            c.set(2010, Calendar.MARCH, 20);
            for (File file : files) {
                c.add(Calendar.DATE, 1); 
                out.println("directory: " + file.getName());                
                Path p = Paths.get(file.getPath());
                try {
                    Files.setAttribute(p, "lastModifiedTime", FileTime.fromMillis(c.getTimeInMillis()));
                    Files.setAttribute(p, "creationTime", FileTime.fromMillis(c.getTimeInMillis()));
                    Files.setAttribute(p, "lastAccessTime", FileTime.fromMillis(c.getTimeInMillis()));
                } catch (IOException e) {
                    out.print("cannot write attribute\n");
                }
            }
            */
            sortInsideDirectory(args[0]);

            //out.println("found: " + files.length);
        }
    }

    public static void sortInsideDirectory(String directory) {
        out.println("Sorting path: " + directory);

        File[] files = new File(directory).listFiles();

        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        Calendar c = Calendar.getInstance();
        c.set(2010, Calendar.MARCH, 20);

        for (File file : files) {
            c.add(Calendar.DATE, 1); 
            
            
            if (file.isDirectory()) {
                sortInsideDirectory(file.getPath());
            }
                        
            Path p = Paths.get(file.getPath());
            try {
                Files.setAttribute(p, "lastModifiedTime", FileTime.fromMillis(c.getTimeInMillis()));
                Files.setAttribute(p, "creationTime", FileTime.fromMillis(c.getTimeInMillis()));
                Files.setAttribute(p, "lastAccessTime", FileTime.fromMillis(c.getTimeInMillis()));
                out.println("written attributes for file: " + p.getFileName());                
            } catch (IOException e) {
                out.print("cannot write attribute\n");
            }


        }
    }
}

//javac Main.java
//java Main /media/diego/STORE\ N\ GO/