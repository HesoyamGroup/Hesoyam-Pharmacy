INSERT INTO roles (role_name) VALUES ('ROLE_NONE');
INSERT INTO roles (role_name) VALUES ('ROLE_PATIENT');
INSERT INTO roles (role_name) VALUES ('ROLE_PHARMACIST');
INSERT INTO roles (role_name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO roles (role_name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO roles (role_name) VALUES ('ROLE_SYS_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_SUPPLIER');

INSERT INTO country (country_code, country_name) VALUES ('RS', 'Serbia');
INSERT INTO city (city_name, country_id) VALUES ('Novi Sad', 1);


INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('Apoteka Janković', 'Poverenje, sigurnost i dostupnost su, već skoro 30 godina, glavna obeležja Apotekarske ustanove Janković', 'Bulevar oslobođenja 135', 45.24147051535973, 19.84340829263456, 1, 0);

INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Mila', 'Milanović', 'FEMALE', 'Šabačka 41', 1, 44.80115575659176, 20.49294129684957, '4444444444', 'hesoyampharmacy+mila@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST', false);
INSERT INTO employee
(id, rating) VALUES (1, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 3);


INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Nikola', 'Dragić', 'MALE', 'Bulevar oslobođenja 147', 1, 45.24046220156171, 19.843969833190673, '4444444444', 'hesoyampharmacy+dragic@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('15-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR', false);
INSERT INTO administrator (id, pharmacy_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 5);