package com.servlet.admin.db.service;

import com.servlet.admin.db.DbData;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface DbService {
    DbData backUpDb() throws IOException, InterruptedException;
}
