--changeset ascolieri:2
CREATE TABLE stats
(
    id BIGSERIAL PRIMARY KEY,
    count_mutant_dna BIGINT,
    count_human_dna BIGINT,
    mdt TIMESTAMP
);

INSERT INTO stats(count_mutant_dna, count_human_dna, mdt) VALUES (0,0,now());