--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8 (Ubuntu 10.8-0ubuntu0.18.10.1)
-- Dumped by pg_dump version 10.8 (Ubuntu 10.8-0ubuntu0.18.10.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.account (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.account OWNER TO mbugua;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_id_seq OWNER TO mbugua;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;


--
-- Name: bill; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.bill (
    id integer NOT NULL,
    date timestamp without time zone,
    description character varying,
    quantity integer,
    price integer,
    paid boolean,
    vendor_id integer,
    purchase_id integer,
    payment_id integer
);


ALTER TABLE public.bill OWNER TO mbugua;

--
-- Name: bill_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.bill_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bill_id_seq OWNER TO mbugua;

--
-- Name: bill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.bill_id_seq OWNED BY public.bill.id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    name character varying,
    number integer,
    email character varying
);


ALTER TABLE public.customer OWNER TO mbugua;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO mbugua;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: expense; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.expense (
    id integer NOT NULL,
    name character varying,
    description character varying,
    amount integer,
    date timestamp without time zone,
    account_id integer
);


ALTER TABLE public.expense OWNER TO mbugua;

--
-- Name: expense_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.expense_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.expense_id_seq OWNER TO mbugua;

--
-- Name: expense_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.expense_id_seq OWNED BY public.expense.id;


--
-- Name: income; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.income (
    id integer NOT NULL,
    name character varying,
    description character varying,
    amount integer,
    date timestamp without time zone,
    account_id integer
);


ALTER TABLE public.income OWNER TO mbugua;

--
-- Name: income_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.income_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.income_id_seq OWNER TO mbugua;

--
-- Name: income_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.income_id_seq OWNED BY public.income.id;


--
-- Name: payment; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.payment (
    id integer NOT NULL,
    name character varying,
    description character varying
);


ALTER TABLE public.payment OWNER TO mbugua;

--
-- Name: payment_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.payment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.payment_id_seq OWNER TO mbugua;

--
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- Name: product_purchase; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.product_purchase (
    id integer NOT NULL,
    date timestamp without time zone,
    name character varying,
    quantity integer,
    price integer,
    vendor_id integer,
    description character varying
);


ALTER TABLE public.product_purchase OWNER TO mbugua;

--
-- Name: product_purchase_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.product_purchase_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_purchase_id_seq OWNER TO mbugua;

--
-- Name: product_purchase_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.product_purchase_id_seq OWNED BY public.product_purchase.id;


--
-- Name: product_sale; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.product_sale (
    id integer NOT NULL,
    date timestamp without time zone,
    name character varying,
    description character varying,
    quantity integer,
    price integer,
    customer_id integer
);


ALTER TABLE public.product_sale OWNER TO mbugua;

--
-- Name: product_sale_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.product_sale_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_sale_id_seq OWNER TO mbugua;

--
-- Name: product_sale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.product_sale_id_seq OWNED BY public.product_sale.id;


--
-- Name: purchases; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.purchases (
    id integer NOT NULL,
    amount integer,
    description character varying,
    vendor_id integer,
    name character varying
);


ALTER TABLE public.purchases OWNER TO mbugua;

--
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.purchases_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO mbugua;

--
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- Name: receipt; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.receipt (
    id integer NOT NULL,
    date timestamp without time zone,
    description character varying,
    paid boolean,
    payment_id integer,
    product_id integer,
    price integer,
    customer_id integer,
    quantity integer
);


ALTER TABLE public.receipt OWNER TO mbugua;

--
-- Name: receipt_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.receipt_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.receipt_id_seq OWNER TO mbugua;

--
-- Name: receipt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.receipt_id_seq OWNED BY public.receipt.id;


--
-- Name: sales; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.sales (
    id integer NOT NULL,
    name character varying,
    amount integer,
    description character varying
);


ALTER TABLE public.sales OWNER TO mbugua;

--
-- Name: sales_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.sales_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sales_id_seq OWNER TO mbugua;

--
-- Name: sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.sales_id_seq OWNED BY public.sales.id;


--
-- Name: vendor; Type: TABLE; Schema: public; Owner: mbugua
--

CREATE TABLE public.vendor (
    id integer NOT NULL,
    name character varying,
    number integer,
    email character varying
);


ALTER TABLE public.vendor OWNER TO mbugua;

--
-- Name: vendor_id_seq; Type: SEQUENCE; Schema: public; Owner: mbugua
--

CREATE SEQUENCE public.vendor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vendor_id_seq OWNER TO mbugua;

--
-- Name: vendor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mbugua
--

ALTER SEQUENCE public.vendor_id_seq OWNED BY public.vendor.id;


--
-- Name: account id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);


--
-- Name: bill id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.bill ALTER COLUMN id SET DEFAULT nextval('public.bill_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: expense id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.expense ALTER COLUMN id SET DEFAULT nextval('public.expense_id_seq'::regclass);


--
-- Name: income id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.income ALTER COLUMN id SET DEFAULT nextval('public.income_id_seq'::regclass);


--
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- Name: product_purchase id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.product_purchase ALTER COLUMN id SET DEFAULT nextval('public.product_purchase_id_seq'::regclass);


--
-- Name: product_sale id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.product_sale ALTER COLUMN id SET DEFAULT nextval('public.product_sale_id_seq'::regclass);


--
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- Name: receipt id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.receipt ALTER COLUMN id SET DEFAULT nextval('public.receipt_id_seq'::regclass);


--
-- Name: sales id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.sales ALTER COLUMN id SET DEFAULT nextval('public.sales_id_seq'::regclass);


--
-- Name: vendor id; Type: DEFAULT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.vendor ALTER COLUMN id SET DEFAULT nextval('public.vendor_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.account (id, name) FROM stdin;
1	Mpesa
2	Bank
3	Cash in Hand
\.


--
-- Data for Name: bill; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.bill (id, date, description, quantity, price, paid, vendor_id, purchase_id, payment_id) FROM stdin;
1	2019-06-06 16:38:22.246107	Bought a goat	10	4500	t	1	1	3
2	2019-06-06 16:54:19.067368	Bought a goat	3	4500	f	1	1	1
4	2019-06-06 18:25:00.738923	Mgongo	1	3711	f	1	1	1
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.customer (id, name, number, email) FROM stdin;
5	Harley Quinn	893452	john.doe@anonymous
6	Tile Company	342841924	john.doe@anonymous
7	Tile Company	3242525	john.doe@anonymous
\.


--
-- Data for Name: expense; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.expense (id, name, description, amount, date, account_id) FROM stdin;
1	Tiles	Bought tiles for roof	3242	2019-06-06 13:36:54.811391	2
\.


--
-- Data for Name: income; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.income (id, name, description, amount, date, account_id) FROM stdin;
1	Nyama Choma	Sale of a back	3521	2019-06-06 13:05:28.494828	1
2	Tiles	Bought tiles for roof	3122	2019-06-06 13:35:02.47802	2
\.


--
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.payment (id, name, description) FROM stdin;
\.


--
-- Data for Name: product_purchase; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.product_purchase (id, date, name, quantity, price, vendor_id, description) FROM stdin;
\.


--
-- Data for Name: product_sale; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.product_sale (id, date, name, description, quantity, price, customer_id) FROM stdin;
\.


--
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.purchases (id, amount, description, vendor_id, name) FROM stdin;
1	31	rabbit	1	Meat
\.


--
-- Data for Name: receipt; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.receipt (id, date, description, paid, payment_id, product_id, price, customer_id, quantity) FROM stdin;
1	2019-06-06 18:18:20.953641	Sale of beans	t	1	2	18	5	2
\.


--
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.sales (id, name, amount, description) FROM stdin;
1	Meat	3921	Mgongo
2	Maharagwe	21	black beans
\.


--
-- Data for Name: vendor; Type: TABLE DATA; Schema: public; Owner: mbugua
--

COPY public.vendor (id, name, number, email) FROM stdin;
1	Meat Company	35829052	john.doe@anonymous
\.


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.account_id_seq', 3, true);


--
-- Name: bill_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.bill_id_seq', 4, true);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.customer_id_seq', 7, true);


--
-- Name: expense_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.expense_id_seq', 1, true);


--
-- Name: income_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.income_id_seq', 2, true);


--
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.payment_id_seq', 1, false);


--
-- Name: product_purchase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.product_purchase_id_seq', 1, false);


--
-- Name: product_sale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.product_sale_id_seq', 1, false);


--
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.purchases_id_seq', 1, true);


--
-- Name: receipt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.receipt_id_seq', 1, true);


--
-- Name: sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.sales_id_seq', 2, true);


--
-- Name: vendor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mbugua
--

SELECT pg_catalog.setval('public.vendor_id_seq', 1, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: bill bill_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: expense expense_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.expense
    ADD CONSTRAINT expense_pkey PRIMARY KEY (id);


--
-- Name: income income_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- Name: product_purchase product_purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.product_purchase
    ADD CONSTRAINT product_purchase_pkey PRIMARY KEY (id);


--
-- Name: product_sale product_sale_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.product_sale
    ADD CONSTRAINT product_sale_pkey PRIMARY KEY (id);


--
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


--
-- Name: receipt receipt_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT receipt_pkey PRIMARY KEY (id);


--
-- Name: sales sales_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);


--
-- Name: vendor vendor_pkey; Type: CONSTRAINT; Schema: public; Owner: mbugua
--

ALTER TABLE ONLY public.vendor
    ADD CONSTRAINT vendor_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

