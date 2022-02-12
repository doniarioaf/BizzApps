package com.servlet.mobile.project.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.customerproject.entity.CustomerProject;
import com.servlet.admin.customerproject.entity.CustomerProjectData;
import com.servlet.admin.customerproject.entity.CustomerProjectPK;
import com.servlet.admin.customerproject.service.CustomerProjectService;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.mobile.project.entity.Project;
import com.servlet.mobile.project.entity.ProjectData;
import com.servlet.mobile.project.entity.ProjectDetailData;
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
	@Autowired
	private CustomerProjectService customerProjectService;
	
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
		List<CustomerProject> listcustproject = new ArrayList<CustomerProject>();
		if(body.getCustomers().length > 0) {
			for (int i = 0; i < body.getCustomers().length; i++) {
				CustomerProjectPK customerProjectPK = new CustomerProjectPK();
				customerProjectPK.setIdcompany(idcompany);
				customerProjectPK.setIdbranch(idbranch);
				customerProjectPK.setIdcustomer(body.getCustomers()[i]);
				customerProjectPK.setIdproject(returndata.getId());
				CustomerProject customerProject = new CustomerProject();
				customerProject.setCustomerProjectPK(customerProjectPK);
				listcustproject.add(customerProject);
			}
			customerProjectService.saveCustomerProjectList(listcustproject);
		}
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
		
		List<CustomerProjectPK> listdelete = new ArrayList<CustomerProjectPK>();
		List<CustomerProjectData> listcust = new ArrayList<CustomerProjectData>(customerProjectService.getListCustomerProject(id));
		if(listcust != null && listcust.size() > 0) {
			for(CustomerProjectData customerProjectData : listcust) {
				CustomerProjectPK customerProjectPK = new CustomerProjectPK();
				customerProjectPK.setIdproject(id);
				customerProjectPK.setIdcompany(idcompany);
				customerProjectPK.setIdbranch(idbranch);
				customerProjectPK.setIdcustomer(customerProjectData.getId());
				listdelete.add(customerProjectPK);
			}
			customerProjectService.deleteAllCustomeProjectByListPK(listdelete);
		}
		
		List<CustomerProject> listcustproject = new ArrayList<CustomerProject>();
		if(body.getCustomers().length > 0) {
			for (int i = 0; i < body.getCustomers().length; i++) {
				CustomerProjectPK customerProjectPK = new CustomerProjectPK();
				customerProjectPK.setIdcompany(idcompany);
				customerProjectPK.setIdbranch(idbranch);
				customerProjectPK.setIdcustomer(body.getCustomers()[i]);
				customerProjectPK.setIdproject(returndata.getId());
				CustomerProject customerProject = new CustomerProject();
				customerProject.setCustomerProjectPK(customerProjectPK);
				listcustproject.add(customerProject);
			}
			customerProjectService.saveCustomerProjectList(listcustproject);
		}
		
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

	@Override
	public ProjectDetailData getProjectByIdDetail(long id, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataProject().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		List<ProjectData> list =  this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataProject(), queryParameters);
		if(list != null && list.size() > 0) {
			List<CustomerProjectData> listcustproject = new ArrayList<CustomerProjectData>(customerProjectService.getListCustomerProject(id));
			ProjectData projectHeader = list.get(0);
			
			ProjectDetailData detail = new ProjectDetailData();
			detail.setId(projectHeader.getId());
			detail.setNama(projectHeader.getNama());
			detail.setDescription(projectHeader.getDescription());
			detail.setProjectnumber(projectHeader.getProjectnumber());
			detail.setCustomers(listcustproject);
			return detail;
		}
		return null;
	}

}
