-- public.apostilamento definition

-- Drop table

-- DROP TABLE public.apostilamento;

CREATE TABLE public.apostilamento (
	numeroapostilamento varchar NOT NULL,
	numerocontrato int4 NOT NULL,
	databaseproposta date NULL,
	percentualreajuste numeric NULL,
	valorparcelaanterior numeric NULL,
	valorparcelareajustada numeric NULL,
	datasolicitacao date NULL,
	dataaprovacao date NULL,
	pa varchar NULL,
	descricao varchar NULL,
	ano int4 NOT NULL,
	"local" varchar DEFAULT 'TRF5'::character varying NOT NULL,
	login varchar NULL,
	valorreajuste numeric NULL,
	exercicio varchar NULL,
	CONSTRAINT pk_apostilamento PRIMARY KEY (numeroapostilamento, numerocontrato, ano, local)
);

-- Table Triggers

create trigger audit_apostilamento after
insert
    or
delete
    or
update
    on
    public.apostilamento for each row execute function executa_audit_apostilamento();

-- Permissions

ALTER TABLE public.apostilamento OWNER TO apply;
GRANT ALL ON TABLE public.apostilamento TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.apostilamento TO user_apply_aplicacao;


-- public.apostilamento foreign keys

ALTER TABLE public.apostilamento ADD CONSTRAINT fk_contrato FOREIGN KEY (numerocontrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE RESTRICT ON UPDATE RESTRICT;