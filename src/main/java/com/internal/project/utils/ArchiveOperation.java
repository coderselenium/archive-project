package com.internal.project.utils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArchiveOperation {
    private static final String filePath = "C:\\Users\\Shrinidhi\\Desktop\\books";
    public static void main(String[] args) {
        String targetPath = "C:\\Users\\Shrinidhi\\Desktop";
        File zipFile = new File(targetPath + "\\books.zip");
        File zipInput = new File(filePath);
        File[] files = zipInput.listFiles();
        try(ArchiveOutputStream aos = new ZipArchiveOutputStream(zipFile)) {
            addFileToArchive(files, aos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addFileToArchive(File[] files, ArchiveOutputStream aos) throws IOException {
        for (File file :
                files) {
            if(file.isDirectory()){
                addFileToArchive(file.listFiles(), aos);
            }
            Path path = Paths.get(filePath).relativize(file.toPath());
            ArchiveEntry entry = new ZipArchiveEntry(file, path.toString());
            aos.putArchiveEntry(entry);
            aos.closeArchiveEntry();
        }
    }
}
