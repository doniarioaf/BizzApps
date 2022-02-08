package com.servlet.mobile.project.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.mobile.project.entity.Project;
import com.servlet.mobile.project.entity.ProjectData;
import com.servlet.mobile.project.mapper.GetDataProject;
import com.servlet.mobile.project.repo.ProjectRepo;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.shared.ReturnData;

@Service
public class ProjectHandler implements ProjectService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ProjectRepo repository;
	
	@Override
	public ReturnData saveProject(BodyProject body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Project project = new Project();
		project.setIdcompany(idcompany);
		project.setIdbranch(idbranch);
		project.setNama(body.getNama());
		project.setDescription(body.getDescription());
		project.setProjectnumber(body.getProjectnumber());
		project.setIsdelete(false);
		
		Project returndata = repository.saveAndFlush(project);
		ReturnData data = new ReturnData();
		data.setId(returndata.getId());
		return data;
	}

	@Override
	public ReturnData updateProject(long id, BodyProject body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		Project project = repository.getById(id);
		project.setNama(body.getNama());
		project.setDescription(body.getDescription());
		
		Project returndata = repository.saveAndFlush(project);
		ReturnData data = new ReturnData();
		data.setId(returndata.getId());
		return data;
	}

	@Override
	public List<ProjectData> getAllListProject(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataProject().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataProject(), queryParameters);
	}

	@Override
	public ProjectData getProjectById(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataProject().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {id, idcompany,idbranch};
		List<ProjectData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataProject(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ProjectData getProjectByProjectNumber(String projectnumber, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataProject().schema());
		sqlBuilder.append(" where data.projectnumber = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {projectnumber, idcompany,idbranch};
		List<ProjectData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataProject(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
