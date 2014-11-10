
-- Add the KRMS Type for our TermResolverTypeService
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM)
  values ('KRATRN-KRMS-NCAA-RSLVR-TYP', 'ncaaParticipationTermResolver', 'KRA-TRNG', 'ncaaParticipationTermResolver')
;

-- Add the TermResolver for the NCAA Participation term
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID)
  values ('KRATRN-KRMS-NCAA-RSLVR', 'KRA-TRNG', 'ncaaParticipationTermResolver', 'KRATRN-KRMS-NCAA-RSLVR-TYP', 'KRATRN-KRMS-NCAA-TERM-SPEC');
;
