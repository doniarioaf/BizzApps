package com.servlet.coa.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.coa.entity.BodyCoa;
import com.servlet.coa.entity.Coa;
import com.servlet.coa.entity.CoaData;
import com.servlet.coa.mapper.GetCoaData;
import com.servlet.coa.repo.CoaRepo;
import com.servlet.coa.service.CoaService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class CoaHandler implements CoaService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CoaRepo repository;
	
	@Override
	public List<CoaData> getListAll(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCoaData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.isdelete = false and data.isshowlist = true ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCoaData(), queryParameters);
	}

	@Override
	public CoaData getById(Long idcompany, Long idbranch, Long id) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCoaData().schema());
		sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false and data.isshowlist = true ");
		final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
		List<CoaData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetCoaData(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ReturnData saveCoa(Long idcompany, Long idbranch,Long iduser, BodyCoa body) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		long idsave = 0;
		if(validations.size() == 0) {
			try {
				Timestamp ts = new Timestamp(new Date().getTime());
				Coa table = new Coa();
				table.setIdcompany(idcompany);
				table.setIdbranch(idbranch);
				table.setCode(body.getCode());
				table.setNama(body.getNama());
				table.setRefid(body.getRefid());
				table.setDescription(body.getDescription());
				table.setIsactive(body.isIsactive());
				table.setIsdelete(false);
				table.setCreatedby(iduser.toString());
				table.setIsshowlist(false);
				table.setCreateddate(ts);
				
				idsave = repository.saveAndFlush(table).getId();
			}catch (Exception e) {
				// TODO: handle exception
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
				validations.add(msg);
			}
		}
		ReturnData data = new ReturnData();
		data.setId(idsave);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}

	@Override
	public List<CoaData> getListActiveCOA(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetCoaData().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isactive = true  and data.isdelete = false and data.isshowlist = true ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetCoaData(), queryParameters);
	}

	@Override
	public ReturnData uploadCSV(Long idcompany, Long idbranch, Long iduser) {
		// TODO Auto-generated method stub
		HashMap<String, Object> mapp = readCSV();
		List<String> header = (List<String>) mapp.get("header");
		List<List<String>> rows = (List<List<String>>) mapp.get("rows");
		
		int idxCOA_ID = 0;//header.indexOf("COA_ID");
		System.out.println("COA_ID "+idxCOA_ID);
		int idxCOA_Name = header.indexOf("COA_Name");
		System.out.println("idxCOA_Name "+idxCOA_Name);
		int idxCOA_RefID = header.indexOf("COA_RefID");
		System.out.println("idxCOA_RefID "+idxCOA_RefID);
		int idxCOA_Active = header.indexOf("COA_Active");
		System.out.println("COA_Active "+idxCOA_Active);
		int idxCOA_Description = header.indexOf("COA_Description");
		System.out.println("COA_Description "+idxCOA_Description);
		System.out.println("header "+header);
		System.out.println("rows "+rows);
		for(List<String> kolom : rows) {
			System.out.println("kolom "+kolom);
			
			String coaCode = kolom.get(idxCOA_ID);
			String coaName = kolom.get(idxCOA_Name);
			String refId = kolom.get(idxCOA_RefID);
			boolean isActive = kolom.get(idxCOA_Active).equals("Y")?true:false;
			String desc = kolom.get(idxCOA_Description);
			
			BodyCoa body = new BodyCoa();
			body.setCode(coaCode);
			body.setNama(coaName);
			body.setRefid(refId);
			body.setDescription(desc);
			body.setIsactive(isActive);
			
			saveCoa(idcompany, idbranch,iduser, body);
			
		}
		return null;
	}
	
	private HashMap<String, Object> readCSV() {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		String csvFile = "C:\\Users\\User\\Desktop\\Bizz\\Ali Data\\MstCOA.csv";
		 String delimiter = ",";
		 List<String> header = new ArrayList<>();
         List<String> column = new ArrayList<>();
         List<List<String>> rows = new ArrayList<>();
	        try {
	          File file = new File(csvFile);
	            FileReader fr = new FileReader(file);
	            BufferedReader br = new BufferedReader(fr);
	          String line = " ";
	          String[] tempArr;
	            int no = 0;
	          while ((line = br.readLine()) != null) {
	            tempArr = line.split(delimiter);
	            column = new ArrayList<>();
	            for (String tempStr: tempArr) {
	                if(no == 0){
	                    header.add(tempStr);
	                }else{
	                    column.add(tempStr);
	                }
	              System.out.print(tempStr + " ");
	            }
	            if(column.size() > 0) {
	            	rows.add(column);
	            }
	            
	            no++;
	            System.out.println();
	          }
	          int index = header.indexOf("COA_Name");
	          System.out.print("COA_Name = "+rows.get(2).get(index));
	          br.close();
	        }
	        catch(IOException ioe) {
	          ioe.printStackTrace();
	        }
	        hash.put("header", header);
	        hash.put("rows", rows);
		return hash;
		
	}

}
