package com.servlet.admin.db.handler;

import com.servlet.admin.db.DbData;
import com.servlet.admin.db.service.DbService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;

@Service
public class DbHandler implements DbService {
    @Override
    public DbData backUpDb() throws IOException, InterruptedException {
        String dbName = "sumberberliansamudra";
        String userDb = "postgres";
        String passDb = "postgres";
//        String fileName = "backup_" + dbName + "_" + System.currentTimeMillis() + ".backup";


        String outputPath = backupDatabase(dbName, userDb,passDb, "localhost");

        Path path = Paths.get(outputPath);
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        String fileName = path.getFileName().toString();

        DbData data = new DbData();
        data.setResource(resource);
        data.setFileName(fileName);
        data.setPath(path);
        return data;
    }

    private String backupDatabase(String dbName, String user,String password, String host) throws InterruptedException, IOException {
        // 1. Ambil temp directory sesuai OS, lalu buat sub-folder khusus untuk backup
        String baseTempDir = System.getProperty("java.io.tmpdir"); // otomatis beda tiap OS/komputer
        Path backupDir = Paths.get(baseTempDir, "db-backups");     // contoh: C:\Users\xxx\AppData\Local\Temp\db-backups

        // 2. Pastikan direktori ada, kalau belum -> buat otomatis (termasuk parent folder jika perlu)
        if (!Files.exists(backupDir)) {
            Files.createDirectories(backupDir);
        }

        // 3. Susun nama file & path lengkap
        String fileName = "backup_" + dbName + "_" + System.currentTimeMillis() + ".backup";
        Path outputPath = backupDir.resolve(fileName);

        String pgDumpPath = "C:\\Program Files\\PostgreSQL\\14\\bin\\pg_dump.exe";
        ProcessBuilder pb = new ProcessBuilder(
                pgDumpPath,
                "-h", host,
                "-U", user,
                "-F", "c",              // custom format -> hasil .backup / .dump (binary, bisa di-restore selektif)
                "-f", outputPath.toString(),       // path output file
                dbName
        );
        pb.environment().put("PGPASSWORD", password); // hindari prompt password
        pb.redirectErrorStream(true);

        Process process = pb.start();

        // baca output/log proses (opsional tapi disarankan buat debug)
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Backup gagal, exit code: " + exitCode);
        }
        return outputPath.toString();
    }
}
