create view STOCKCHECK as
 select I.ES_ID, I.I_NO, (I.QUANTITY - isnull(TD.QUANTITY,0)) as STOCK
   from ENTRYSHEET E
		inner join SELLER S  on E.S_ID  = S.S_ID
		inner join ITEM   I  on E.ES_ID = I.ES_ID
		left outer join (select ES_ID,I_NO,sum(QUANTITY)as QUANTITY
							from TRADEDETAIL T
							GROUP BY ES_ID,I_NO
						) TD on I.ES_ID = TD.ES_ID