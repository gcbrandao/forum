INSERT INTO usuario(id, nome, email, password) VALUES ( 2, 'admin', 'admin@gmail.com', '$2a$12$7pGCC6eexM6pjcQSu8Jtm.SFc0NKPNEZBDTBcyddd4ktGdSs9zanK' );
INSERT INTO role(id, nome) VALUES (2, 'ADMIN');
INSERT INTO usuario_role(usuario_id, role_id) VALUES (2, 2);