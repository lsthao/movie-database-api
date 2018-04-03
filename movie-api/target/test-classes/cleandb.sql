DELETE FROM Movies WHERE id > 0;
DELETE FROM Genre WHERE id  > 0;

INSERT INTO Movies (id, title, description, releaseYear) VALUES (1,  "test movie title", "test movie description", 2018);
INSERT INTO Movies (id, title, description, releaseYear) VALUES (2,  "test movie title 2", "test movie description 2", 2017);
INSERT INTO Movies (id, title, description, releaseYear) VALUES (3,  "test movie title 3", "test movie description 3", 2016);

INSERT INTO Genre (id, genreName) VALUES (1, "comedy");
INSERT INTO Genre (id, genreName) VALUES (2, "horror");
INSERT INTO Genre (id, genreName) VALUES (3, "action");


