create table ACCOUNT (
	A_ID smallint identity,
	HOST varchar(10),
	PW varchar(20) not null,
	PERMISSION TINYINT not null,
	primary key(A_ID),
	)