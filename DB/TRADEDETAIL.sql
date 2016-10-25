create table TRADEDETAIL (
	T_ID smallint,
	T_NO smallint,
	ES_ID smallint not null,
	I_NO smallint not null,
	PRICE smallint not null,
	QUANTITY smallint not null
	primary key(T_ID,T_NO),
	foreign key(T_ID)
		references SALESLOG(T_ID)
	)