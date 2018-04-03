DELETE FROM Movies WHERE id > 0;
DELETE FROM Genre WHERE id  > 0;
INSERT INTO Movies (id, title, description, releaseYear) VALUES (1,  "test movie title", "test movie description", 2018);
INSERT INTO Movies (id, title, description, releaseYear) VALUES (2,  "test movie title 2", "test movie description 2", 2017);
INSERT INTO Movies (id, title, description, releaseYear) VALUES (3,  "test movie title 3", "test movie description 3", 2016);
INSERT INTO Genre (id, genreName) VALUES (1, "comedy");
INSERT INTO Genre (id, genreName) VALUES (2, "horror");
INSERT INTO Genre (id, genreName) VALUES (3, "action");
INSERT INTO Director (id, directorName) VALUES (1, "director test1");
INSERT INTO Director (id, directorName) VALUES (2, "director test2");
INSERT INTO Director (id, directorName) VALUES (3, "director test3");
INSERT INTO Rating (id, ratingName) VALUES (1, "G");
INSERT INTO Rating (id, ratingName) VALUES (2, "PG");
INSERT INTO Rating (id, ratingName) VALUES (3, "R");

