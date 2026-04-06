-- remover constraints antigas
ALTER TABLE tb_movie_category DROP CONSTRAINT fk_movie_category_movie;
ALTER TABLE tb_movie_category DROP CONSTRAINT fk_movie_category_category;

-- recriar com cascade
ALTER TABLE tb_movie_category
ADD CONSTRAINT fk_movie_category_movie
FOREIGN KEY (movie_id)
REFERENCES tb_movie(id)
ON DELETE CASCADE;

ALTER TABLE tb_movie_category
ADD CONSTRAINT fk_movie_category_category
FOREIGN KEY (category_id)
REFERENCES tb_category(id)
ON DELETE CASCADE;