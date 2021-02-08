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


--Patient whos account is not confirmed by email.
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Aleksa', 'Tomić', 'MALE', 'Miseluk', 1, 45.265666787857164, 19.81474531978888, '55553333', 'hesoyampharmacy+leksa@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), false, 'PATIENT', false);
INSERT INTO patient (id, penalty_points) VALUES (3, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);

------------------------------------------------------------------------------------
-- SALES (DATA IS NOT CONSISTENT WITH PREVIOUS DATA eg Appointments and Medicines)
------------------------------------------------------------------------------------

INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-01', 540, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-10', 620, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-11', 1240, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-15', 371, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-20', 520, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-25', 366, 'COUNSELING', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2020-12-28', 888, 'COUNSELING', 1);

INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-01-05', 463, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-01-12', 787, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-01-23', 890, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-02-02', 678, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-02-05', 400, 'CHECK_UP', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-02-07', 250, 'CHECK_UP', 1);

INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-01-09', 250, 'COUNSELING', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-01-20', 125, 'COUNSELING', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-01-27', 300, 'COUNSELING', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-02-04', 180, 'COUNSELING', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-02-06', 197, 'COUNSELING', 1);
INSERT INTO sale (dtype, date_of_sale, price, service_type, pharmacy_id)
VALUES ('ServiceSale', '2021-02-07', 240, 'COUNSELING', 1);

INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2020-12-02', 425, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2020-12-13', 600, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2020-12-21', 600, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2020-12-29', 600, 1);

INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-01-02', 425, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-01-14', 425, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-01-19', 425, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-01-29', 580, 1);

INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-02-01', 425, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-02-02', 360, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-02-05', 750, 1);
INSERT INTO sale (dtype, date_of_sale, price, pharmacy_id)
VALUES('MedicineSale', '2021-02-06', 665, 1);