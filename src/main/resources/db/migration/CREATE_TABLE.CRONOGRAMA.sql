-- public.cronograma definition

-- Drop table

-- DROP TABLE public.cronograma;

CREATE TABLE public.cronograma (
	numerocontrato int4 NOT NULL,
	codcronograma int4 NOT NULL,
	ano int4 NOT NULL,
	"local" varchar DEFAULT 'TRF5'::character varying NOT NULL,
	login varchar NULL,
	CONSTRAINT pk_cronograma PRIMARY KEY (codcronograma)
);

-- Table Triggers

create trigger audit_cronogramafinanceiro after
insert
    or
delete
    or
update
    on
    public.cronograma for each row execute function executa_audit_cronograma();

-- Permissions

ALTER TABLE public.cronograma OWNER TO apply;
GRANT ALL ON TABLE public.cronograma TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.cronograma TO user_apply_aplicacao;


-- public.cronograma foreign keys

ALTER TABLE public.cronograma ADD CONSTRAINT fk_contrato FOREIGN KEY (numerocontrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;