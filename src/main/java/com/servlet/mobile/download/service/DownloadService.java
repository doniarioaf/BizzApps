package com.servlet.mobile.download.service;

import com.servlet.mobile.download.entity.DownloadData;

public interface DownloadService {
	DownloadData donwload(long iduser, long idcompany, long idbranch);
}
