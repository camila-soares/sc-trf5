CREATE SEQUENCE public.tb_processopagto_contrato_id_processopagto_contrato_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
     ALTER TABLE  public.tb_processopagto_contrato_id_processopagto_contrato_seq
 OWNER TO gestor;

CREATE TABLE public.tb_processopagto_contrato
(
    id_processopagto_contrato integer NOT NULL DEFAULT nextval('tb_processopagto_contrato_id_processopagto_contrato_seq'::regclass),
    numero_processo character varying(40),
    id_contrato_sgc integer,
    exercicio integer NOT NULL,
    CONSTRAINT tb_processopagto_contrato_pkey PRIMARY KEY (id_processopagto_contrato),
    CONSTRAINT fk_processopagto_contrato_contrato_sgc FOREIGN KEY (id_contrato_sgc)
        REFERENCES public.tb_contrato_sgc (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
 ALTER TABLE  public.tb_processopagto_contrato
 OWNER TO gestor;