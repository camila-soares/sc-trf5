CREATE SEQUENCE tb_objeto_aditivo_sgc_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tb_objeto_aditivo_sgc_seq
  OWNER TO gestor;

CREATE TABLE public.tb_objeto_aditivo_sgc (
id_aditivo integer NOT NULL DEFAULT nextval('tb_objeto_aditivo_sgc_seq'::regclass),
id_objeto_contrato integer,
numero_aditivo character varying,
pa_aditivo character varying,
tipo_aditivo character varying,
valor character varying,
data_assinatura date,
inicio_vigencia date,
fim_vigencia date,
exercicio character varying NOT NULL,
id_aditivo_contrato_sgc integer,
excluido_sgc character(1) DEFAULT 'N',
CONSTRAINT tb_objeto_aditivo_sgc_pkey PRIMARY KEY (id_aditivo),
CONSTRAINT fk_objeto_aditivo_objeto_contrato FOREIGN KEY (id_objeto_contrato)
	REFERENCES public.tb_objeto_contrato_sgc (id)	MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);
ALTER TABLE tb_objeto_aditivo_sgc
  OWNER TO gestor;