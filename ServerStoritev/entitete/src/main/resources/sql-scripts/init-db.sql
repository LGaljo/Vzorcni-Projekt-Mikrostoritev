INSERT INTO uporabnik (name) VALUES ('Luka');
INSERT INTO uporabnik (name) VALUES ('Vid');
INSERT INTO uporabnik (name) VALUES ('Miha');
INSERT INTO uporabnik (name) VALUES ('Tara');

INSERT INTO tag (name) VALUES ('Prevozna sredstva');
INSERT INTO tag (name) VALUES ('Gostinska ponudba');
INSERT INTO tag (name) VALUES ('Zabava');
INSERT INTO tag (name) VALUES ('Dogodek');

INSERT INTO qna (question) VALUES ('Ali je gneča na postajališču ob živalskem vrtu?');
INSERT INTO qna (answers)
SELECT question FROM qna WHERE question = 'Ali je gneča na postajališču ob živalskem vrtu?'
VALUES ('Prazno');
