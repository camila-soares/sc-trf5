-- public.contratogarantia definition

-- Drop table

-- DROP TABLE public.contratogarantia;

CREATE TABLE public.contratogarantia (
	numcontrato int4 NOT NULL,
	numgarantia int4 NOT NULL,
	ano int4 NOT NULL,
	"local" varchar DEFAULT 'TRF5'::character varying NOT NULL,
	login varchar NULL,
	CONSTRAINT pk_contratogarantia PRIMARY KEY (numgarantia, numcontrato, ano, local)
);

-- Table Triggers

create trigger audit_contratogarantia after
insert
    or
delete
    or
update
    on
    public.contratogarantia for each row execute function executa_audit_contratogarantia();

-- Permissions

ALTER TABLE public.contratogarantia OWNER TO apply;
GRANT ALL ON TABLE public.contratogarantia TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.contratogarantia TO user_apply_aplicacao;


-- public.contratogarantia foreign keys

ALTER TABLE public.contratogarantia ADD CONSTRAINT fk_contrato FOREIGN KEY (numcontrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;