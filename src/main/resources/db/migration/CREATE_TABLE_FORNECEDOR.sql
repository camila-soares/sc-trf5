-- public.fornecedor definition

-- Drop table

-- DROP TABLE public.fornecedor;

CREATE TABLE public.fornecedor (
	cnpj varchar NOT NULL,
	nomerazaosocial varchar NOT NULL,
	codramoatividade int4 NOT NULL,
	excluido int4 NOT NULL,
	email varchar NULL,
	telefone varchar NULL,
	fax varchar NULL,
	tipo bpchar(2) NULL,
	telefone2 varchar NULL,
	codsubramo int4 NULL,
	login varchar NULL,
	unidadegestora varchar(6) NULL,
	gestao varchar(5) NULL,
	localfornecedor varchar(8) NULL,
	justnomerazaosocial text NULL,
	CONSTRAINT pk_fornecedor PRIMARY KEY (cnpj)
);

-- Table Triggers

create trigger audit_fornecedor after
insert
    or
delete
    or
update
    on
    public.fornecedor for each row execute function executa_audit_fornecedor();

-- Permissions

ALTER TABLE public.fornecedor OWNER TO apply;
GRANT ALL ON TABLE public.fornecedor TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.fornecedor TO user_apply_aplicacao;


-- public.fornecedor foreign keys

ALTER TABLE public.fornecedor ADD CONSTRAINT fk_subramoatividade FOREIGN KEY (codramoatividade,codsubramo) REFERENCES public.subramoatividade(ramo,codigo) ON DELETE RESTRICT ON UPDATE RESTRICT;