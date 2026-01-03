-- public.gestor definition

-- Drop table

-- DROP TABLE public.gestor;

CREATE TABLE public.gestor (
	matricula varchar NOT NULL,
	numcontrato int4 NOT NULL,
	docdes varchar NULL,
	tipo varchar NULL,
	numdocumento varchar NULL,
	ano int4 NOT NULL,
	"local" varchar DEFAULT 'TRF5'::character varying NOT NULL,
	inicio timestamp NOT NULL,
	fim date NULL,
	excluido int2 DEFAULT 0 NULL,
	login varchar NULL
);

-- Table Triggers

create trigger audit_gestor after
insert
    or
delete
    or
update
    on
    public.gestor for each row execute function executa_audit_gestor();

-- Permissions

ALTER TABLE public.gestor OWNER TO apply;
GRANT ALL ON TABLE public.gestor TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.gestor TO user_apply_aplicacao;


-- public.gestor foreign keys

ALTER TABLE public.gestor ADD CONSTRAINT fk_contrato FOREIGN KEY (numcontrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE public.gestor ADD CONSTRAINT fk_servidor FOREIGN KEY (matricula,"local") REFERENCES public.servidor(matricula,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;