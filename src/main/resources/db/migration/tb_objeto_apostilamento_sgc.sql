CREATE SEQUENCE tb_objeto_apostilamento_sgc_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tb_objeto_apostilamento_sgc_seq
  OWNER TO gestor;

CREATE TABLE public.tb_objeto_apostilamento_sgc (
id_apostilamento integer NOT NULL DEFAULT nextval('tb_objeto_apostilamento_sgc_seq'::regclass),
id_objeto_contrato integer,
numero_apostilamento character varying,
pa_apostilamento character varying,
valor double precision,
data_solicitacao date,
data_aprovacao date,
exercicio integer NOT NULL,
descricao character varying,
id_apostilamento_contrato_sgc integer,
excluido_sgc character(1) DEFAULT 'N',
CONSTRAINT tb_objeto_apostilamento_sgc_pkey PRIMARY KEY (id_apostilamento),
CONSTRAINT fk_objeto_apostilamento_objeto_contrato FOREIGN KEY (id_objeto_contrato)
	REFERENCES public.tb_objeto_contrato_sgc (id)	MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);
ALTER TABLE tb_objeto_apostilamento_sgc
  OWNER TO gestor;