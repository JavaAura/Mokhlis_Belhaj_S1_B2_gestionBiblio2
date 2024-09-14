-- Création de la base de données
CREATE DATABASE gestionBiblio;

-- Connexion à la base de données
\c gestionBiblio;

-- Création des tables

CREATE TABLE public.utilisateur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE public.document (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255),
    date_publication DATE,
    nombre_de_pages INTEGER
);

CREATE TABLE public.livre (
    id SERIAL PRIMARY KEY,
    document_id INTEGER REFERENCES public.document(id) ON DELETE CASCADE,
    isbn VARCHAR(20)
);

CREATE TABLE public.magazine (
    id SERIAL PRIMARY KEY,
    document_id INTEGER REFERENCES public.document(id) ON DELETE CASCADE,
    numero INTEGER
);

CREATE TABLE public.journal_scientifique (
    id SERIAL PRIMARY KEY,
    document_id INTEGER REFERENCES public.document(id) ON DELETE CASCADE,
    domaine_recherche VARCHAR(255)
);

CREATE TABLE public.these_universitaire (
    id SERIAL PRIMARY KEY,
    document_id INTEGER REFERENCES public.document(id) ON DELETE CASCADE,
    universite VARCHAR(255),
    domaine VARCHAR(255)
);

CREATE TABLE public.etudiant (
    id SERIAL PRIMARY KEY,
    matricule VARCHAR(20) NOT NULL UNIQUE,
    utilisateur_id INTEGER REFERENCES public.utilisateur(id) ON DELETE CASCADE
);

CREATE TABLE public.professeur (
    id SERIAL PRIMARY KEY,
    specialite VARCHAR(100) NOT NULL,
    utilisateur_id INTEGER REFERENCES public.utilisateur(id) ON DELETE CASCADE
);

CREATE TABLE public.emprunt (
    id SERIAL PRIMARY KEY,
    utilisateur_id INTEGER NOT NULL REFERENCES public.utilisateur(id),
    document_id INTEGER NOT NULL REFERENCES public.document(id),
    date_emprunt DATE NOT NULL,
    date_retour DATE
);

CREATE TABLE public.reservation (
    id SERIAL PRIMARY KEY,
    utilisateur_id INTEGER NOT NULL REFERENCES public.utilisateur(id),
    document_id INTEGER NOT NULL REFERENCES public.document(id),
    date_reservation DATE NOT NULL
);

-- Création de la vue pour visualiser tous les documents

CREATE VIEW public.all_documents AS
    SELECT d.id, d.titre AS title, d.auteur AS author, d.date_publication AS publication_date, 'document' AS doc_type
    FROM public.document d
    WHERE NOT EXISTS (
        SELECT 1 FROM public.livre l WHERE l.document_id = d.id
        UNION
        SELECT 1 FROM public.magazine m WHERE m.document_id = d.id
        UNION
        SELECT 1 FROM public.journal_scientifique js WHERE js.document_id = d.id
        UNION
        SELECT 1 FROM public.these_universitaire tu WHERE tu.document_id = d.id
    )
    UNION
    SELECT d.id, d.titre AS title, d.auteur AS author, d.date_publication AS publication_date, 'livre' AS doc_type
    FROM public.document d
    JOIN public.livre l ON d.id = l.document_id
    UNION
    SELECT d.id, d.titre AS title, d.auteur AS author, d.date_publication AS publication_date, 'magazine' AS doc_type
    FROM public.document d
    JOIN public.magazine m ON d.id = m.document_id
    UNION
    SELECT d.id, d.titre AS title, d.auteur AS author, d.date_publication AS publication_date, 'journal_scientifique' AS doc_type
    FROM public.document d
    JOIN public.journal_scientifique js ON d.id = js.document_id
    UNION
    SELECT d.id, d.titre AS title, d.auteur AS author, d.date_publication AS publication_date, 'these_universitaire' AS doc_type
    FROM public.document d
    JOIN public.these_universitaire tu ON d.id = tu.document_id;
