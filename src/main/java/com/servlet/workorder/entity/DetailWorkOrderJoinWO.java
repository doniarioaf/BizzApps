package com.servlet.workorder.entity;


public class DetailWorkOrderJoinWO {
	private Long idwo;
	private String nocontainer;
	private Long idpartai;
	private String partainame;
	public Long getIdwo() {
		return idwo;
	}
	public void setIdwo(Long idwo) {
		this.idwo = idwo;
	}
	public String getNocontainer() {
		return nocontainer;
	}
	public void setNocontainer(String nocontainer) {
		this.nocontainer = nocontainer;
	}
	public Long getIdpartai() {
		return idpartai;
	}
	public void setIdpartai(Long idpartai) {
		this.idpartai = idpartai;
	}
	public String getPartainame() {
		return partainame;
	}
	public void setPartainame(String partainame) {
		this.partainame = partainame;
	}
	
	
}
