-- public.objetocontratoexercicio definition

-- Drop table

-- DROP TABLE public.objetocontratoexercicio;

CREATE TABLE public.objetocontratoexercicio (
	cod_obj_contrato_ex int4 NOT NULL,
	ano int4 NOT NULL,
	"local" varchar NOT NULL,
	codobjeto int4 NOT NULL,
	numcontrato int4 NOT NULL,
	exercicio varchar NOT NULL,
	valor varchar(15) NULL,
	CONSTRAINT pk_cod_obj_contrato_ex PRIMARY KEY (cod_obj_contrato_ex)
);

-- Table Triggers

create trigger sicronizar_objeto_ex_sgo after
insert
    or
delete
    or
update
    on
    public.objetocontratoexercicio for each row execute function sincronizacao_objeto_exer_sgo_func();

COMMENT ON TRIGGER sicronizar_objeto_ex_sgo ON public.objetocontratoexercicio IS 'Trigger criada para chamar a funcao que realizara insert na tabela no sgo atravez de db link';

-- Permissions

ALTER TABLE public.objetocontratoexercicio OWNER TO apply;
GRANT ALL ON TABLE public.objetocontratoexercicio TO apply;
GRANT TRUNCATE, REFERENCES, UPDATE, SELECT, DELETE, INSERT, TRIGGER ON TABLE public.objetocontratoexercicio TO user_apply_aplicacao;


-- public.objetocontratoexercicio foreign keys

ALTER TABLE public.objetocontratoexercicio ADD CONSTRAINT objetocontratoexercicio_codobjeto_fkey FOREIGN KEY (codobjeto) REFERENCES public.objeto(codobjeto) ON DELETE CASCADE;
ALTER TABLE public.objetocontratoexercicio ADD CONSTRAINT objetocontratoexercicio_numcontrato_fkey FOREIGN KEY (numcontrato,ano,"local") REFERENCES public.contrato(numero,ano,"local") ON DELETE CASCADE;