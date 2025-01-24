package com.servlet.filedocument.repo;

import com.servlet.filedocument.entity.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FileDocumentRepo")
public interface FileDocumentRepo extends JpaRepository<FileDocument, Long> {
}
