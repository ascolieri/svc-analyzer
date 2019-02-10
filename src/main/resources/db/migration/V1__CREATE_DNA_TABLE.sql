--changeset ascolieri:1
CREATE TABLE dna
(
    id BIGSERIAL PRIMARY KEY,
    sequence VARCHAR(512),
    is_mutant BOOLEAN,
    cdt TIMESTAMP,
    mdt TIMESTAMP
);

CREATE UNIQUE INDEX dna_sequence_uindex
  ON dna (sequence);

