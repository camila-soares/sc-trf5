CREATE SEQUENCE tb_contrato_sgc_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tb_contrato_sgc_seq
  OWNER TO gestor;

CREATE TABLE tb_contrato_sgc
(
  id integer NOT NULL DEFAULT nextval('tb_contrato_sgc_seq'::regclass),
  numero_contrato integer NOT NULL,
  ano_contrato integer NOT NULL,
  valor numeric,
  descricao_objeto text,
  local character varying(4),
  excluido_sgc character(1) DEFAULT 'N',
  cnpjfornecedor character varying(30),
  nomefornecedor character varying,
  iniciovigencia date,
  fimvigencia date,
  processocontratacao character varying(40),
  id_unidadetecnica integer,
  id_centrocusto integer,
  titulo character varying DEFAULT 'Título não informado',
  CONSTRAINT contrato_sgc_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_contrato_sgc
  OWNER TO gestor;
