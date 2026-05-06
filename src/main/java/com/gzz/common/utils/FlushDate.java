package com.gzz.common.utils;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;

import com.gzz.common.config.Const;

/**
 * @author 高振中
 * @summary 刷新代码注释中的日期
 * @date 2026-05-05 11:25:52
 **/
public class FlushDate {
    private static final String ROOT = "user.dir";
    private static final String PATH = "src/main/java";
    private static final String DATE = " * @date ";
    private static final Path INDEX = Paths.get("src/main/resources/static/index.html");
    private static final String REGEX = "(☂)(.*?)(☂)";

    public static void main(String[] args) {
        flushDate();
        updateVersion();
    }

    @SneakyThrows
    private static void updateVersion() {
		String date = "Version:" + LocalDateTime.now().format(Const.FORMAT_ALL);
        String content = "$1" + date + "$3";
        StringBuilder file = new StringBuilder();
        Files.lines(INDEX).forEach(line -> {
            line = line.replaceAll(REGEX, content);
            file.append(line).append("\n");
        });
        INDEX.toFile().delete();
        Files.writeString(INDEX, file.toString(), StandardOpenOption.CREATE);
    }

    @SneakyThrows
    private static void flushDate() {
        Path path = Paths.get(System.getProperty(ROOT), PATH);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                StringBuilder text = new StringBuilder();
                Files.lines(path).forEach(line -> {
                    if (line.equals(" */"))
                        line = " **/";
                    if (line.startsWith(DATE))
                        line = DATE + LocalDateTime.now().format(Const.FORMAT_ALL);
                    text.append(line).append("\n");
                });
                if (path.toFile().delete()) {
                    Files.writeString(path, text.toString(), StandardOpenOption.CREATE);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
