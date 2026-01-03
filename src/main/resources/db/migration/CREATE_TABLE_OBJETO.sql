CREATE SEQUENCE tb_objeto_contrato_sgc_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tb_objeto_contrato_sgc_seq
  OWNER TO gestor;

CREATE TABLE public.tb_objeto_contrato_sgc (
id integer NOT NULL DEFAULT nextval('tb_objeto_contrato_sgc_seq'::regclass),
id_contrato integer,
numero_contrato integer,
ano_contrato integer,
valor double precision,
valor_atualizado double precision,
descricao_objeto character varying,
local character varying(10),
titulo character varying DEFAULT 'Título não informado',
inicio_vigencia date,
fim_vigencia date,
id_objeto_contrato_sgc integer NOT NULL,
excluido_sgc character(1) DEFAULT 'N',
tipo_objeto character varying,
tipo_valor character varying,
tipo_pagamento character varying,
tipo_grupo_sgc character varying,
CONSTRAINT tb_objeto_contrato_sgc_pkey PRIMARY KEY (id),
CONSTRAINT fk_objeto_contrato_contrato FOREIGN KEY (id_contrato)
	REFERENCES public.tb_contrato_sgc (id)	MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);
ALTER TABLE tb_objeto_contrato_sgc
  OWNER TO gestor;