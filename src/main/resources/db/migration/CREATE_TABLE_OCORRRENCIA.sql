-- public.ocorrencia definition

-- Drop table

-- DROP TABLE public.ocorrencia;

CREATE TABLE public.ocorrencia (
	numero serial4 NOT NULL,
	"data" timestamptz NULL,
	descricao text NULL,
	usuario varchar NULL,
	contrato int4 NOT NULL,
	ano int4 NOT NULL,
	arquivo int4 NULL,
	"local" varchar DEFAULT 'TRF5'::character varying NOT NULL,
	providencia varchar NULL,
	login varchar NULL,
	CONSTRAINT pk_ocorrencia PRIMARY KEY (numero, contrato, ano, local)
);

-- Table Triggers

create trigger audit_ocorrencia after
insert
    or
delete
    or
update
    on
    public.ocorrencia for each row execute function executa_audit_ocorrencia();

-- Permissions

ALTER TABLE public.ocorrencia OWNER TO apply;
GRANT ALL ON TABLE public.ocorrencia TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.ocorrencia TO user_apply_aplicacao;


-- public.ocorrencia foreign keys

ALTER TABLE public.ocorrencia ADD CONSTRAINT fk_contrato FOREIGN KEY (contrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;