INSERT INTO roles (role_name) VALUES ('ROLE_NONE');
INSERT INTO roles (role_name) VALUES ('ROLE_PATIENT');
INSERT INTO roles (role_name) VALUES ('ROLE_PHARMACIST');
INSERT INTO roles (role_name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO roles (role_name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO roles (role_name) VALUES ('ROLE_SYS_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_SUPPLIER');

INSERT INTO country (country_code, country_name) VALUES ('RS', 'Serbia');
INSERT INTO country (country_code, country_name) VALUES ('ALB', 'Albania');
INSERT INTO country (country_code, country_name) VALUES ('ARM', 'Armenia');
INSERT INTO country (country_code, country_name) VALUES ('AUS', 'Australia');
INSERT INTO country (country_code, country_name) VALUES ('AUT', 'Russia');
INSERT INTO country (country_code, country_name) VALUES ('MKD', 'Republic of North Macedonia');
INSERT INTO city (city_name, country_id) VALUES ('Novi Sad', 1);
INSERT INTO city (city_name, country_id) VALUES ('Gornji Milanovac', 1);
INSERT INTO city (city_name, country_id) VALUES ('Beograd', 1);
INSERT INTO city (city_name, country_id) VALUES ('Leskovac', 1);
INSERT INTO city (city_name, country_id) VALUES ('Vranje', 1);

INSERT INTO city (city_name, country_id) VALUES ('Delvinë', 2);
INSERT INTO city (city_name, country_id) VALUES ('Finiq', 2);
INSERT INTO city (city_name, country_id) VALUES ('Vorë', 2);
INSERT INTO city (city_name, country_id) VALUES ('Pukë', 2);
INSERT INTO city (city_name, country_id) VALUES ('Bajram Curri', 2);

INSERT INTO city (city_name, country_id) VALUES ('Tsaghkadzor', 3);
INSERT INTO city (city_name, country_id) VALUES ('Vedi', 3);
INSERT INTO city (city_name, country_id) VALUES ('Talin', 3);
INSERT INTO city (city_name, country_id) VALUES ('Sevan', 3);
INSERT INTO city (city_name, country_id) VALUES ('Maralik', 3);

INSERT INTO city (city_name, country_id) VALUES ('Darwin', 4);
INSERT INTO city (city_name, country_id) VALUES ('Newcastle–Maitland', 4);
INSERT INTO city (city_name, country_id) VALUES ('Gladstone–Tannum Sands', 4);
INSERT INTO city (city_name, country_id) VALUES ('Yanchep', 4);
INSERT INTO city (city_name, country_id) VALUES ('Geraldton', 4);

INSERT INTO city (city_name, country_id) VALUES ('Voronezh', 5);
INSERT INTO city (city_name, country_id) VALUES ('Irkutsk', 5);
INSERT INTO city (city_name, country_id) VALUES ('Moscow', 5);

INSERT INTO city (city_name, country_id) VALUES ('Kruševo', 6);
INSERT INTO city (city_name, country_id) VALUES ('Gostivar', 6);
INSERT INTO city (city_name, country_id) VALUES ('Prilep', 6);
INSERT INTO city (city_name, country_id) VALUES ('Skopje', 6);
INSERT INTO city (city_name, country_id) VALUES ('Berovo', 6);


-------------
-- PHARMACIES
-------------

-- 1 Jankovic
INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('Apoteka Janković', 'Poverenje, sigurnost i dostupnost su, već skoro 30 godina, glavna obeležja Apotekarske ustanove Janković', 'Bulevar oslobođenja 135', 45.24147051535973, 19.84340829263456, 1, 0);
--INSERT INTO

-- 2 BENU
INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('BENU Pharmacy', 'BENU apoteke odlikuje nov i moderan pristup u sprovođenju farmaceutske usluge u oblasti medicinske zaštite i prevencije.', 'Bulevar cara Lazara 57', 45.242099257464965, 19.834720141296856, 1, 0);


-- 3 Tilia
INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('Apoteka Tilia', '', 'Bulevar oslobođenja 3A', 45.26416960023307, 19.830897136780703, 1, 0);



























INSERT INTO manufacturer (name, address_line, latitude, longitude, city_id) VALUES ('GALENIKA AD BEOGRAD', 'Ulica bb', 0, 0, 3);
INSERT INTO manufacturer (name, address_line, latitude, longitude, city_id) VALUES ('PHARMASWISS D.O.O., BEOGRAD', 'Ulica bb', 0, 0, 3);

INSERT INTO contraindication (name) VALUES ('Alergija na cefaleksin');
INSERT INTO contraindication (name) VALUES ('Teška insuficijencija jetre');
INSERT INTO contraindication (name) VALUES ('Teška srčana insuficijencija');
INSERT INTO contraindication (name) VALUES ('Teška insuficijencija bubrega');
INSERT INTO contraindication (name) VALUES ('Treći trimestar trudnoće');

INSERT INTO composition_item (name, quantity, unit) VALUES ('Cefaleksin', 250, 'MG');
INSERT INTO composition_item (name, quantity, unit) VALUES ('Ibuprofen', 200, 'MG');
INSERT INTO composition_item (name, quantity, unit) VALUES ('Diclofenak-natrijum', 75, 'MG');

-- 1 Palitrex
INSERT INTO medicine_specification (quantity, unit) VALUES (250, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (1, 1);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (1, 1);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id) VALUES
('Palitrex', 'CAPSULE', 4, '', 'WITHOUT_PRESCRIPTION', 1, 1);

-- 2 Rapidol
INSERT INTO medicine_specification (quantity, unit) VALUES (200, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 2);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 3);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 4);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 5);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (2, 2);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id) VALUES
('Rapidol', 'PILL', 5, '', 'WITHOUT_PRESCRIPTION', 2, 2);

-- 3 Diclofenac Duo
INSERT INTO medicine_specification (quantity, unit) VALUES (75, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 2);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 3);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 4);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 5);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (3, 3);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id) VALUES
('Diclofenac Duo', 'CAPSULE', 7, '', 'WITHOUT_PRESCRIPTION', 2, 3);


--------------
-- SYS ADMIN
--------------

-- 1
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Pera', 'Perić', 'MALE', 'Bulevar cara Lazara 88', 1, 45.24136867255004, 19.829921403835918, '4444444444', 'hesoyampharmacy+admin@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('14-01-2021', 'DD-MM-YYYY'), true, 'SYS_ADMIN');
INSERT INTO sys_admin (id) VALUES (1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 6);

-------------------
-- ADMINISTRATORS
-------------------

-- 2
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Nikola', 'Dragić', 'MALE', 'Bulevar oslobođenja 147', 1, 45.24046220156171, 19.843969833190673, '4444444444', 'hesoyampharmacy+dragic@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('15-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR');
INSERT INTO administrator (id, pharmacy_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 5);

-- 3
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Luka', 'Kričković', 'MALE', 'Narodnog fronta 187', 1, 45.2373330656835, 19.82722036933235, '4444444444', 'hesoyampharmacy+kricko@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR');
INSERT INTO administrator (id, pharmacy_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 5);

-- 4
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Aleksa', 'Vučaj', 'MALE', 'Fruškogorska 24', 1, 45.24092378110751, 19.84746195352302, '4444444444', 'hesoyampharmacy+vucaj@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR');
INSERT INTO administrator (id, pharmacy_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 5);

-- 5
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Gergelj', 'Kiš', 'MALE', 'Bulevar Evrope 42', 1, 45.24366408340239, 19.82013338399792, '4444444444', 'hesoyampharmacy+kis@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR');
INSERT INTO administrator (id, pharmacy_id) VALUES (5, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 5);

----------------
-- PHARMACISTS
----------------

-- 6
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Mila', 'Milanović', 'FEMALE', 'Šabačka 41', 3, 44.80115575659176, 20.49294129684957, '4444444444', 'hesoyampharmacy+mila@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST');
INSERT INTO employee
(id, rating) VALUES (6, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (6, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 3);

-- 7
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Jovan', 'Jovanović', 'MALE', 'Karađorđeva 32', 2, 44.02636626175418, 20.45674605711011, '4444444444', 'hesoyampharmacy+jovan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST');
INSERT INTO employee
(id, rating) VALUES (7, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (7, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 3);

-- 8
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Milan', 'Milanović', 'MALE', 'Savski trg 11', 3, 44.80878328562551, 20.45740555451793, '4444444444', 'hesoyampharmacy+milan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST');
INSERT INTO employee
(id, rating) VALUES (8, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (8, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (8, 3);

-- 9
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Jadranka', 'Jadranovič', 'FEMALE', 'Subotička 21', 1, 45.24542077279943, 19.813604101767236, '4444444444', 'hesoyampharmacy+jadranka@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST');
INSERT INTO employee
(id, rating) VALUES (9, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (9, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (9, 3);

-------------------
-- DERMATOLOGISTS
-------------------

-- 10
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Ivana', 'Ivanović', 'FEMALE', 'Ćirpanova 4', 1, 45.25299175875651, 19.835303400426874, '4444444444', 'hesoyampharmacy+ivana@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST');
INSERT INTO employee
(id, rating) VALUES (10, 0);
INSERT INTO dermatologist (id) VALUES (10);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (1, 10);
INSERT INTO user_roles (user_id, role_id) VALUES (10, 4);

-- 11
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Mika', 'Mikić', 'DONT_SAY', 'Tolstojeva 12', 1, 45.24535673373681, 19.833914938362007, '4444444444', 'hesoyampharmacy+mika@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST');
INSERT INTO employee
(id, rating) VALUES (11, 0);
INSERT INTO dermatologist (id) VALUES (11);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (2, 11);
INSERT INTO user_roles (user_id, role_id) VALUES (11, 4);

-- 12
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Dragan', 'Dragić', 'MALE', 'Dositejeva 17', 1, 45.26315474237813, 19.84141604011822, '4444444444', 'hesoyampharmacy+dragan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST');
INSERT INTO employee
(id, rating) VALUES (12, 0);
INSERT INTO dermatologist (id) VALUES (12);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (1, 12);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (3, 12);
INSERT INTO user_roles (user_id, role_id) VALUES (12, 4);

-- 13
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Marko', 'Marković', 'MALE', 'Masarikova 18', 1, 45.25780011333192, 19.83853464148449, '4444444444', 'hesoyampharmacy+marko@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST');
INSERT INTO employee
(id, rating) VALUES (13, 0);
INSERT INTO dermatologist (id) VALUES (13);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (2, 13);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (1, 13);
INSERT INTO user_roles (user_id, role_id) VALUES (13, 4);

-------------
-- PATIENTS
-------------

-- 14
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Veselin', 'Tomić', 'MALE', 'Rumenačka 147', 1, 45.265666787857164, 19.81474531978888, '4444444444', 'hesoyampharmacy+veselin@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT');
INSERT INTO patient (id, penalty_points) VALUES (14, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (14, 2);

-- 15
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Svetlana', 'Branković', 'FEMALE', 'Futoški put 76', 1, 45.247088659951785, 19.803792899301826, '4444444444', 'hesoyampharmacy+ceca@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT');
INSERT INTO patient (id, penalty_points) VALUES (15, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (15, 2);

-- 16
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Lidija', 'Kovač', 'FEMALE', 'Futoška 46', 1, 45.24900654614174, 19.826952620050363, '4444444444', 'hesoyampharmacy+lidija@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT');
INSERT INTO patient (id, penalty_points) VALUES (16, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (16, 2);

-- 17
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Radovan', 'Petrović', 'MALE', 'Novosadskog sajma 41', 1, 45.253902952298034, 19.825503048784817, '4444444444', 'hesoyampharmacy+radovan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT');
INSERT INTO patient (id, penalty_points) VALUES (17, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (17, 2);

--------------
-- SUPPLIERS
--------------

-- 18
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Sandra', 'Filipović', 'FEMALE', 'Sarajevska 83', 3, 44.800638193104234, 20.451638118625414, '4444444444', 'hesoyampharmacy+sandra@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'SUPPLIER');
INSERT INTO supplier (id) VALUES (18);
INSERT INTO user_roles (user_id, role_id) VALUES (18, 7);

-- 19
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum)
VALUES
('Marina', 'Marić', 'FEMALE', 'Novosadska 37', 3, 44.914103309371924, 20.267538944371907, '4444444444', 'hesoyampharmacy+marina@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'SUPPLIER');
INSERT INTO supplier (id) VALUES (19);
INSERT INTO user_roles (user_id, role_id) VALUES (19, 7);
