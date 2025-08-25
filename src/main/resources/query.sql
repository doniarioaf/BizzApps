CREATE TABLE public.cancel_packinglist (
	id bigserial NOT NULL,
	idcompany int4 NOT NULL,
	idbranch int4 NOT NULL,
	idpackinglist int8 NOT NULL,
	nodocument varchar NOT NULL,
	datecancel date NOT NULL,
	isdelete bool NOT NULL DEFAULT false,
	createdby int8 NULL,
	createddate timestamp NULL,
	modifiedby int8 NULL,
	modifieddate timestamp NULL,
	deleteby int8 NULL,
	deletedate timestamp NULL,
	CONSTRAINT cancel_packinglist_pk PRIMARY KEY (id)
);

CREATE TABLE public.cancel_packinglistitems (
	idcancelpackinglist int8 NOT NULL,
	idproduct int8 NOT NULL,
	idcategoryproduct int8 NOT NULL,
	qty int8 NOT NULL DEFAULT 0,
	brutoweight float8 NOT NULL DEFAULT 0,
	allowance float8 NOT NULL DEFAULT 0,
	nettoweight float8 NOT NULL DEFAULT 0,
	price float8 NOT NULL DEFAULT 0,
	totalprice float8 NOT NULL DEFAULT 0,
	box varchar NOT NULL,
	noseq int8 NOT NULL DEFAULT 0,
	CONSTRAINT cancel_packinglistitems_pk PRIMARY KEY (idcancelpackinglist, idproduct, idcategoryproduct, box, noseq)
);

ALTER TABLE public.cancel_packinglist ADD keterangan varchar NOT NULL;