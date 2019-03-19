INSERT INTO uporabnik (name) VALUES ('Luka');
INSERT INTO uporabnik (name) VALUES ('Vid');
INSERT INTO uporabnik (name) VALUES ('Miha');
INSERT INTO uporabnik (name) VALUES ('Tara');

INSERT INTO tag (name) VALUES ('Prevozna sredstva');
INSERT INTO tag (name) VALUES ('Gostinska ponudba');
INSERT INTO tag (name) VALUES ('Zabava');
INSERT INTO tag (name) VALUES ('Dogodek');

INSERT INTO question (question, tag_id) VALUES ('Ali je gneča na postajališču ob živalskem vrtu?', 1);
INSERT INTO question (question, tag_id) VALUES ('Ali je gneča na južni ljubljanski obvoznici?', 1);
INSERT INTO question (question, tag_id) VALUES ('Kako se vam je zdela zabava?', 2);
INSERT INTO question (question, tag_id) VALUES ('Bi prijateljem priporočili ta lokal?', 2);
INSERT INTO question (question, tag_id) VALUES ('Bi se naslednje leto ponovno udeležili dogodka?', 3);
