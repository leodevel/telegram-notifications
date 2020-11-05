package br.com.marino.monitorar.utils;

import br.com.marino.monitorar.models.Alarme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs {

    public static void addLog(Alarme alarme, String msg, LocalDateTime data, Exception ex) {

        File dir = Paths.get("logs").toFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File log = Paths.get("logs/alarme_" + alarme.getId() + ".log").toFile();

        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException ex1) {
            }
        }

        if (ex == null) {

            String text = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " - " + msg;

            try {
                Files.write(log.toPath(), ("\r\n" + text + "\r\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex1) {
            }

        }

        if (ex != null) {
            try {

                try (FileWriter fw = new FileWriter(log, true)) {
                    ex.printStackTrace(new PrintWriter(fw));
                } catch (Exception ex2) {
                }

            } catch (Exception ex2) {
            }

        }
    }

}
