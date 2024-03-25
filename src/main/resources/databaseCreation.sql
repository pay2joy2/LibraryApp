
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

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.authors (
    authorid bigint NOT NULL,
    authorname character varying(255) NOT NULL
);

CREATE TABLE public.books (
    bookid bigint NOT NULL,
    booktitle character varying(255) NOT NULL,
    publisherid bigint
);


CREATE TABLE public.booksauthors (
    bookid bigint NOT NULL,
    authorid bigint NOT NULL
);

CREATE TABLE public.publishers (
    publisherid bigint NOT NULL,
    publishername character varying(255)
);


ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (authorid);


ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (bookid);


ALTER TABLE ONLY public.booksauthors
    ADD CONSTRAINT booksauthors_pkey PRIMARY KEY (bookid, authorid);


ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (publisherid);


ALTER TABLE ONLY public.booksauthors
    ADD CONSTRAINT booksauthors_authorid_fkey FOREIGN KEY (authorid) REFERENCES public.authors(authorid) ON DELETE CASCADE;


ALTER TABLE ONLY public.booksauthors
    ADD CONSTRAINT booksauthors_bookid_fkey FOREIGN KEY (bookid) REFERENCES public.books(bookid) ON DELETE CASCADE;


ALTER TABLE ONLY public.books
    ADD CONSTRAINT fk_publishersid FOREIGN KEY (publisherid) REFERENCES public.publishers(publisherid) ON DELETE CASCADE;

