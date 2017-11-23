package inview.translationcard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipIt {

    public void zip(final Path sourceDirectory, Path zipFilePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath.toFile());
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)
        ) {
            Files.walkFileTree(sourceDirectory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    zipOutputStream.putNextEntry(new ZipEntry(sourceDirectory.relativize(file).toString()));
                    Files.copy(file, zipOutputStream);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    if (dir.equals(sourceDirectory)) return FileVisitResult.CONTINUE; //skip root
                    zipOutputStream.putNextEntry(new ZipEntry(sourceDirectory.relativize(dir).toString() + "/"));
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
    /*
    public static void main(String[] args) {
        ZipIt zipIt = new ZipIt();
        Path p = Paths.get(System.getProperty("user.dir"),"CardArchive");
        try {
            zipIt.zip(p, Paths.get("CardArchive.zip"));
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    } */

}
