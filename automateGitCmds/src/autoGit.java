import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/* A script to automate git commands for CS61B git commits.
 * The specific sequence consists of:
 * 1) git add <filename>
 * 2) git commit -m <message>
 * 3) git tag <filename>-#
 * 4) git push (easy peasy)
 * 5) git push --tags (standard stuff)
 * @author Meng Lu
 * */
public class autoGit {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please just enter a file name");
            System.exit(1);
        }
        String[] cmds = {"git add ", "git commit -m ", "git tag ", "git " +
                "push", "git push --tags"};
        String filename = args[0]; // takes only one file at a time
        String CWD = System.getProperty("user.dir");
        String persistDir = CWD + "/.persistForAutoGit";
        String persistFile = persistDir + "/" + filename + ".txt";

        if (!new File(persistDir).exists()) {
            new File(persistDir).mkdir();
        }
        // The way I kept track of the same commits is by a simple number
        if (!new File(persistFile).exists()) {
            writeContents(new File(persistFile), "0");
        } else {
            int num =
                    Integer.parseInt(readContentsAsString(new File(persistFile))) + 1;
            writeContents(new File(persistFile), Integer.toString(num));
        }
        if (new File(CWD + "/" + filename).exists()) {
            try {
                // This is running "git add filename"
                Runtime.getRuntime().exec(cmds[0] + filename);
                // This is running "git commit -m testing#" a default message
                Runtime.getRuntime().exec(cmds[1] + "testing" + readContentsAsString(new File(persistFile)));
                // This is running "git tag filename-#" the format of the class's git control style
                Runtime.getRuntime().exec(cmds[2] + filename + "-" + readContentsAsString(new File(persistFile)));
                // git push
                Runtime.getRuntime().exec(cmds[3]);
                // git push --tags
                Runtime.getRuntime().exec(cmds[4]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
        }
    }

    // Borrowed code from cs61b gitlet Utils.java
    /** Return the entire contents of FILE as a byte array.  FILE must
     *  be a normal file.  Throws IllegalArgumentException
     *  in case of problems. */
    static byte[] readContents(File file) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("must be a normal file");
        }
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /** Return the entire contents of FILE as a String.  FILE must
     *  be a normal file.  Throws IllegalArgumentException
     *  in case of problems. */
    static String readContentsAsString(File file) {
        return new String(readContents(file), StandardCharsets.UTF_8);
    }

    /** Write the result of concatenating the bytes in CONTENTS to FILE,
     *  creating or overwriting it as needed.  Each object in CONTENTS may be
     *  either a String or a byte array.  Throws IllegalArgumentException
     *  in case of problems. */
    static void writeContents(File file, Object... contents) {
        try {
            if (file.isDirectory()) {
                throw new IllegalArgumentException("cannot overwrite directory");
            }
            BufferedOutputStream str =
                    new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            for (Object obj : contents) {
                if (obj instanceof byte[]) {
                    str.write((byte[]) obj);
                } else {
                    str.write(((String) obj).getBytes(StandardCharsets.UTF_8));
                }
            }
            str.close();
        } catch (IOException | ClassCastException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }
}
