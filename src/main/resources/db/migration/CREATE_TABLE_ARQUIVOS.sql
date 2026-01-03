-- public.arquivos definition

-- Drop table

-- DROP TABLE public.arquivos;

CREATE TABLE public.arquivos (
	numerocontrato int4 NOT NULL,
	ano int2 NOT NULL,
	descricao varchar NULL,
	tipo varchar NULL,
	urlarquivo varchar NULL,
	codarquivo serial4 NOT NULL,
	"local" varchar DEFAULT 'TRF5'::character varying NOT NULL,
	login varchar NULL,
	CONSTRAINT pk_arquivo PRIMARY KEY (codarquivo, numerocontrato, ano, local)
);

-- Table Triggers

create trigger audit_arquivos after
insert
    or
delete
    or
update
    on
    public.arquivos for each row execute function executa_audit_arquivos();

-- Permissions

ALTER TABLE public.arquivos OWNER TO apply;
GRANT ALL ON TABLE public.arquivos TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.arquivos TO user_apply_aplicacao;


-- public.arquivos foreign keys

ALTER TABLE public.arquivos ADD CONSTRAINT fk_contrato FOREIGN KEY (numerocontrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;