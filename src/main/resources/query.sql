ALTER TABLE public.m_vendor ADD idarea int8 NULL;
ALTER TABLE public.purchasereceive ADD setor_pinjaman float8 NOT NULL DEFAULT 0;

CREATE TABLE public.pinjaman (
	id bigserial NOT NULL,
	idcompany int8 NOT NULL,
	idbranch int8 NOT NULL,
	idvendor int8 NOT NULL,
	amount float8 NOT NULL DEFAULT 0,
	"date" date NOT NULL,
	nodocument varchar NOT NULL,
	isdelete bool NOT NULL DEFAULT false,
	isactive bool NOT NULL DEFAULT true,
	createdby int8 NULL,
	createddate timestamp NULL,
	modifiedby int8 NULL,
	modifieddate timestamp NULL,
	deleteby int8 NULL,
	deletedate timestamp NULL,
	CONSTRAINT pinjaman_pk PRIMARY KEY (id)
);

INSERT INTO m_running_number VALUES ('P', 1, 6,1);
INSERT INTO m_running_number VALUES ('P', 1, 2,1);

INSERT INTO m_permissions VALUES (nextval('permissions_id_seq'::regclass), 'READ_PINJAMAN', 'Read Pinjaman');
INSERT INTO m_permissions VALUES (nextval('permissions_id_seq'::regclass), 'CREATE_PINJAMAN', 'Create Pinjaman');
INSERT INTO m_permissions VALUES (nextval('permissions_id_seq'::regclass), 'EDIT_PINJAMAN', 'Edit Pinjaman');
INSERT INTO m_permissions VALUES (nextval('permissions_id_seq'::regclass), 'DELETE_PINJAMAN', 'Delete Pinjaman');

ALTER TABLE public.purchasereceive_charge ADD chargenamecustom varchar NULL;
INSERT INTO m_charge VALUES (nextval('m_charge_id_seq'::regclass),1,1,'SETORPINJAMAN',false);

ALTER TABLE public.m_customer ADD city varchar NULL;

ALTER TABLE public.m_category_product ADD forcategory varchar NULL;
update m_category_product set forcategory='VENDOR';
INSERT INTO m_parameter_client VALUES (nextval('m_parameter_client_id_seq'::regclass),1, 'COUNTRYOFORIGIN', 'CENGKARENG',true,false,1,now(),null,null,null,null,'TEXT',null);

INSERT INTO m_permissions VALUES (nextval('permissions_id_seq'::regclass), 'READ_REPORT_KARTUPINJAMAN', 'Read Report Pinjaman');

ALTER TABLE public.m_running_number ADD "year" int4 NOT NULL DEFAULT 0;
ALTER TABLE public.m_running_number ADD "month" int NOT NULL DEFAULT 0;
ALTER TABLE public.m_running_number DROP CONSTRAINT m_running_number_pk;
ALTER TABLE public.m_running_number ADD CONSTRAINT m_running_number_pk PRIMARY KEY (code,idcompany,idbranch,"year","month");
ALTER TABLE public.invoice ADD ispackinglistupdate bool NOT NULL DEFAULT false;

ALTER TABLE public.m_vendor ADD address1 varchar NULL;
ALTER TABLE public.m_vendor ADD address2 varchar NULL;
ALTER TABLE public.m_vendor ADD npwp varchar NULL;
ALTER TABLE public.m_vendor ADD phone varchar NULL;
ALTER TABLE public.packinglist ADD idvendor int8 NULL;