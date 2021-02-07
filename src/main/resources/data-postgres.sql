-- Šifra svakog korisnika je 00000000

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
-- MEDICINE
-------------

INSERT INTO manufacturer (name, address_line, latitude, longitude, city_id) VALUES ('GALENIKA AD BEOGRAD', 'Ulica bb', 44.79237538733406, 20.45986955932357, 3);
INSERT INTO manufacturer (name, address_line, latitude, longitude, city_id) VALUES ('PHARMASWISS D.O.O., BEOGRAD', 'Ulica bb', 44.79585941445145, 20.444138466824253, 3);

INSERT INTO contraindication (name) VALUES ('Alergija na cefaleksin');
INSERT INTO contraindication (name) VALUES ('Teška insuficijencija jetre');
INSERT INTO contraindication (name) VALUES ('Teška srčana insuficijencija');
INSERT INTO contraindication (name) VALUES ('Teška insuficijencija bubrega');
INSERT INTO contraindication (name) VALUES ('Treći trimestar trudnoće');
INSERT INTO contraindication (name) VALUES ('Preosetljivost na penicilin');
INSERT INTO contraindication (name) VALUES ('Alergija na ibuprofen');
INSERT INTO contraindication (name) VALUES ('Alergija na alprazolam');
INSERT INTO contraindication (name) VALUES ('Preosetljivost na vitamin C');
INSERT INTO contraindication (name) VALUES ('Srednje-teska insuficijencija jetre.');


INSERT INTO composition_item (name, quantity, unit) VALUES ('Cefaleksin', 250, 'MG');
INSERT INTO composition_item (name, quantity, unit) VALUES ('Ibuprofen', 200, 'MG');
INSERT INTO composition_item (name, quantity, unit) VALUES ('Diclofenak-natrijum', 75, 'MG');
INSERT INTO composition_item (name, quantity, unit) VALUES ('Amoksicilin', 250, 'MG');
INSERT INTO composition_item (name, quantity, unit) VALUES ('Klavulanska kiselina', 125, 'MG');


-- 1 Palitrex
INSERT INTO medicine_specification (quantity, unit) VALUES (250, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (1, 1);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (1, 1);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id, rating) VALUES
('Palitrex', 'CAPSULE', 4, '', 'WITHOUT_PRESCRIPTION', 1, 1, 5);

-- 2 Rapidol
INSERT INTO medicine_specification (quantity, unit) VALUES (200, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 2);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 3);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 4);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (2, 5);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (2, 2);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id, rating) VALUES
('Rapidol', 'PILL', 5, '', 'WITHOUT_PRESCRIPTION', 2, 2, 4);

-- 3 Diclofenac Duo
INSERT INTO medicine_specification (quantity, unit) VALUES (75, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 2);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 3);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 4);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (3, 5);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (3, 3);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id, rating) VALUES
('Diclofenac Duo', 'CAPSULE', 7, '', 'WITHOUT_PRESCRIPTION', 2, 3, 4);

-- 4 Panklav
INSERT INTO medicine_specification (quantity, unit) VALUES (375, 'MG');
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (4, 2);
INSERT INTO medicine_contraindication (medicine_specification_id, contraindication_id) VALUES (4, 6);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (4, 4);
INSERT INTO medicine_composition (medicine_specification_id, composition_id) VALUES (4, 5);
--INSERT INTO replacement_medicine
INSERT INTO medicine (name, medicine_type, loyalty_points, notes, prescription_mode, manufacturer_id, medicine_specification_id, rating) VALUES
('Panklav', 'PILL', 6, '', 'WITH_PRESCRIPTION', 2, 4, 0);

-------------
-- PHARMACIES
-------------

-- 1 Jankovic
INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('Apoteka Janković', 'Poverenje, sigurnost i dostupnost su, već skoro 30 godina, glavna obeležja Apotekarske ustanove Janković', 'Bulevar oslobođenja 135', 45.24147051535973, 19.84340829263456, 1, 2.35);
--INSERT INTO

-- 2 BENU
INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('BENU Pharmacy', 'BENU apoteke odlikuje nov i moderan pristup u sprovođenju farmaceutske usluge u oblasti medicinske zaštite i prevencije.', 'Bulevar cara Lazara 57', 45.242099257464965, 19.834720141296856, 1, 5);


-- 3 Tilia
INSERT INTO pharmacy (name, description, address_line, latitude, longitude, city_id, rating) VALUES
('Apoteka Tilia', '', 'Bulevar oslobođenja 3A', 45.26416960023307, 19.830897136780703, 1, 3);

-----------------------
-- PHARMACY INVENTORIES
-----------------------

-- 1 Jankovic's Inventory
INSERT INTO inventory(pharmacy_id)
VALUES (1);

-- 2 BENU's Inventory
INSERT INTO inventory(pharmacy_id)
VALUES (2);

-- 3 Tilia's Inventory
INSERT INTO inventory(pharmacy_id)
VALUES (3);

----------------------------------------
-- INVENTORY ITEMS
---------------------------------------
-----------------------
-- Jankovic Inventory Items
-----------------------

-- Palitrex
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (5, 0, 1, 1);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (555, '2021-01-29', '2021-02-28', 1);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (500, '2021-03-01', '2021-03-15', 1);
-- Rapidol
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (4, 0, 2, 1);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (888, '2021-01-25', '2021-02-25', 2);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (666, '2021-02-26', '2021-03-10', 2);
-- Diclofenac Duo
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (7, 0, 3, 1);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (999, '2021-01-29', '2021-03-28', 3);
-- Panklav
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (0, 0, 4, 1);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (444, '2021-01-29', '2021-03-26', 4);


-----------------------
-- BENU Inventory Items
-----------------------

-- Palitrex
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (7, 0, 1, 2);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (500, '2021-01-29', '2021-02-28', 5);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (555, '2021-03-01', '2021-03-15', 5);
-- Rapidol
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (5, 0, 2, 2);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (999, '2021-01-29', '2021-03-28', 6);
-- Diclofenac Duo
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (2, 0, 3, 2);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (888, '2021-01-25', '2021-02-25', 7);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (666, '2021-02-26', '2021-03-10', 7);
-- Panklav
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (3, 0, 4, 2);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (333, '2021-01-29', '2021-02-28', 8);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (222, '2021-03-01', '2021-03-15', 8);

-----------------------
-- Tilia Inventory Items
-----------------------

-- Palitrex
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (2, 0, 1, 3);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (222, '2021-01-29', '2021-02-28', 9);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (333, '2021-03-01', '2021-03-15', 9);
-- Rapidol
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (1, 0, 2, 3);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (777, '2021-01-25', '2021-02-25', 10);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (700, '2021-02-26', '2021-03-10', 10);
-- Diclofenac Duo
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (8, 0, 3, 3);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (567, '2021-01-29', '2021-03-28', 11);
-- Panklav
INSERT INTO inventory_item(available, reserved, medicine_id, inventory_id)
VALUES (5, 0, 4, 3);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (333, '2021-01-29', '2021-02-28', 12);
INSERT INTO inventory_item_price(price, from_date, to_date, inventory_item_id)
VALUES (222, '2021-03-01', '2021-03-15', 12);

--------------
-- SYS ADMIN
--------------

-- 1
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Pera', 'Perić', 'MALE', 'Bulevar cara Lazara 88', 1, 45.24136867255004, 19.829921403835918, '4444444444', 'hesoyampharmacy+admin@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('14-01-2021', 'DD-MM-YYYY'), true, 'SYS_ADMIN', false);
INSERT INTO sys_admin (id) VALUES (1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 6);

-------------------
-- ADMINISTRATORS
-------------------

-- 2
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Nikola', 'Dragić', 'MALE', 'Bulevar oslobođenja 147', 1, 45.24046220156171, 19.843969833190673, '4444444444', 'hesoyampharmacy+dragic@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('15-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR', false);
INSERT INTO administrator (id, pharmacy_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 5);

-- 3
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Luka', 'Kričković', 'MALE', 'Narodnog fronta 187', 1, 45.2373330656835, 19.82722036933235, '4444444444', 'hesoyampharmacy+kricko@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR', false);
INSERT INTO administrator (id, pharmacy_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 5);

-- 4
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Aleksa', 'Vučaj', 'MALE', 'Fruškogorska 24', 1, 45.24092378110751, 19.84746195352302, '4444444444', 'hesoyampharmacy+vucaj@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR', false);
INSERT INTO administrator (id, pharmacy_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 5);

-- 5
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Gergelj', 'Kiš', 'MALE', 'Bulevar Evrope 42', 1, 45.24366408340239, 19.82013338399792, '4444444444', 'hesoyampharmacy+kis@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'ADMINISTRATOR', false);
INSERT INTO administrator (id, pharmacy_id) VALUES (5, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 5);

----------------
-- PHARMACISTS
----------------

-- 6
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Mila', 'Milanović', 'FEMALE', 'Šabačka 41', 3, 44.80115575659176, 20.49294129684957, '4444444444', 'hesoyampharmacy+mila@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST', false);
INSERT INTO employee
(id, rating) VALUES (6, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (6, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 3);

-- 7
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Jovan', 'Jovanović', 'MALE', 'Karađorđeva 32', 2, 44.02636626175418, 20.45674605711011, '4444444444', 'hesoyampharmacy+jovan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST', false);
INSERT INTO employee
(id, rating) VALUES (7, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (7, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 3);

-- 8
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Milan', 'Milanović', 'MALE', 'Savski trg 11', 3, 44.80878328562551, 20.45740555451793, '4444444444', 'hesoyampharmacy+milan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST', false);
INSERT INTO employee
(id, rating) VALUES (8, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (8, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (8, 3);

-- 9
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Jadranka', 'Jadranovič', 'FEMALE', 'Subotička 21', 1, 45.24542077279943, 19.813604101767236, '4444444444', 'hesoyampharmacy+jadranka@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'PHARMACIST', false);
INSERT INTO employee
(id, rating) VALUES (9, 0);
INSERT INTO pharmacist (id, pharmacy_id) VALUES (9, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (9, 3);

-------------------
-- DERMATOLOGISTS
-------------------

-- 10
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Ivana', 'Ivanović', 'FEMALE', 'Ćirpanova 4', 1, 45.25299175875651, 19.835303400426874, '4444444444', 'hesoyampharmacy+ivana@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST', false);
INSERT INTO employee
(id, rating) VALUES (10, 0);
INSERT INTO dermatologist (id) VALUES (10);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (1, 10);
INSERT INTO user_roles (user_id, role_id) VALUES (10, 4);

-- 11
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Mika', 'Mikić', 'DONT_SAY', 'Tolstojeva 12', 1, 45.24535673373681, 19.833914938362007, '4444444444', 'hesoyampharmacy+mika@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST', false);
INSERT INTO employee
(id, rating) VALUES (11, 0);
INSERT INTO dermatologist (id) VALUES (11);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (2, 11);
INSERT INTO user_roles (user_id, role_id) VALUES (11, 4);

-- 12
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Dragan', 'Dragić', 'MALE', 'Dositejeva 17', 1, 45.26315474237813, 19.84141604011822, '4444444444', 'hesoyampharmacy+dragan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST', false);
INSERT INTO employee
(id, rating) VALUES (12, 0);
INSERT INTO dermatologist (id) VALUES (12);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (1, 12);
INSERT INTO pharmacies_dermatologists (pharmacy_id, dermatologist_id) VALUES (3, 12);
INSERT INTO user_roles (user_id, role_id) VALUES (12, 4);

-- 13
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Marko', 'Marković', 'MALE', 'Masarikova 18', 1, 45.25780011333192, 19.83853464148449, '4444444444', 'hesoyampharmacy+marko@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('24-01-2021', 'DD-MM-YYYY'), true, 'DERMATOLOGIST', false);
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
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Veselin', 'Tomić', 'MALE', 'Rumenačka 147', 1, 45.265666787857164, 19.81474531978888, '4444444444', 'hesoyampharmacy+veselin@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT', false);
INSERT INTO patient (id, penalty_points) VALUES (14, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (14, 2);

-- 15
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Svetlana', 'Branković', 'FEMALE', 'Futoški put 76', 1, 45.247088659951785, 19.803792899301826, '4444444444', 'hesoyampharmacy+ceca@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT', false);
INSERT INTO patient (id, penalty_points) VALUES (15, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (15, 2);

-- 16
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Lidija', 'Kovač', 'FEMALE', 'Futoška 46', 1, 45.24900654614174, 19.826952620050363, '4444444444', 'hesoyampharmacy+lidija@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT', false);
INSERT INTO patient (id, penalty_points) VALUES (16, 0);
INSERT INTO user_roles (user_id, role_id) VALUES (16, 2);

-- 17
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Radovan', 'Petrović', 'MALE', 'Novosadskog sajma 41', 1, 45.253902952298034, 19.825503048784817, '4444444444', 'hesoyampharmacy+radovan@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'PATIENT', false);
INSERT INTO patient (id, penalty_points) VALUES (17, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (17, 2);

--------------
-- SUPPLIERS
--------------


-- 18
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Sandra', 'Filipović', 'FEMALE', 'Sarajevska 83', 3, 44.800638193104234, 20.451638118625414, '4444444444', 'hesoyampharmacy+sandra@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'SUPPLIER', false);
INSERT INTO supplier (id) VALUES (18);
INSERT INTO user_roles (user_id, role_id) VALUES (18, 7);

-- 19
INSERT INTO users
(first_name, last_name, gender, address_line, city_id, latitude, longitude, telephone, email, password, last_password_reset_date, enabled, role_enum, password_reset)
VALUES
('Marina', 'Marić', 'FEMALE', 'Novosadska 37', 3, 44.914103309371924, 20.267538944371907, '4444444444', 'hesoyampharmacy+marina@gmail.com', '$2a$10$Cz0brjWGJa525Fd/ub3nW.U1aLsYRYp7mslmoh2B7Gcm/VQb4CTOS', to_timestamp('04-01-2021', 'DD-MM-YYYY'), true, 'SUPPLIER', false);
INSERT INTO supplier (id) VALUES (19);
INSERT INTO user_roles (user_id, role_id) VALUES (19, 7);


-----------------------
-- Medicine Reservation
-----------------------
INSERT INTO medicine_reservation(id, code, medicine_reservation_status, pick_up_date, patient_id, pharmacy_id)
VALUES (1, 'sdadasdadadasasdasasdda', 'CREATED', '2021-02-01 23:59:59', 14, 1);
INSERT INTO medicine_reservation(id, code, medicine_reservation_status, pick_up_date, patient_id, pharmacy_id)
VALUES (2, 'wqksdhajdhaushydhwqkjmsdkami', 'COMPLETED', '2021-02-19 23:59:59', 14, 1);
INSERT INTO medicine_reservation(id, code, medicine_reservation_status, pick_up_date, patient_id, pharmacy_id)
VALUES (3, 'bcvobocjiwque878dusijksdfj', 'CREATED', '2021-03-01 23:59:59', 14, 1);
INSERT INTO medicine_reservation(id, code, medicine_reservation_status, pick_up_date, patient_id, pharmacy_id)
VALUES (4, 'dfjiwdjas8idlak0ti9kdsmklc', 'CANCELLED', '2021-02-16 23:59:59', 14, 2);
INSERT INTO medicine_reservation(id, code, medicine_reservation_status, pick_up_date, patient_id, pharmacy_id)
VALUES (5, 'sdfjiosdofsdipkdogjuhuas', 'COMPLETED', '2021-01-31 23:59:59', 14, 2);
INSERT INTO medicine_reservation(id, code, medicine_reservation_status, pick_up_date, patient_id, pharmacy_id)
VALUES (6, 'jcjsiajdojiosd0weoklssmad', 'CREATED', '2021-05-12 23:59:59', 14, 2);

---------------------------
--Medicine Reservation Item
---------------------------
INSERT INTO medicine_reservation_item(id, quantity, medicine_id, medicine_reservation_id)
VALUES (1, 1, 2, 1);
INSERT INTO medicine_reservation_item(id, quantity, medicine_id, medicine_reservation_id)
VALUES (2, 1, 1, 2);
INSERT INTO medicine_reservation_item(id, quantity, medicine_id, medicine_reservation_id)
VALUES (3, 1, 3, 3);
INSERT INTO medicine_reservation_item(id, quantity, medicine_id, medicine_reservation_id)
VALUES (4, 1, 1, 4);
INSERT INTO medicine_reservation_item(id, quantity, medicine_id, medicine_reservation_id)
VALUES (5, 1, 2, 5);
INSERT INTO medicine_reservation_item(id, quantity, medicine_id, medicine_reservation_id)
VALUES (6, 1, 4, 6);

-------------------
-- APPOINTMENTS (needs to be redone with better data)
-------------------

INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 14, 1, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 15, 3, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 16, 1, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 14, 2, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 17, 1, 11, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 17, 2, null, 6);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gospodine...', 17, 1, null, 6);

INSERT INTO appointment(dtype, appointment_status, from_date, to_date, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'TAKEN', '2021-02-06 23:05:00', '2021-02-06 23:25:00', 'Gospodine...', 17, 1, null, 6);


--Checkup

INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'FREE', '2021-02-08 21:00:00.189796', '2021-02-08 21:15:00.189796', 2500.0 , null , null, 1, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'FREE', '2021-02-08 21:30:00.189796', '2021-02-08 21:45:00.189796', 1500.0 , null , null, 1, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'FREE', '2021-02-08 21:45:00.189796', '2021-02-08 22:00:00.189796', 1750.0 , null , null, 1, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'FREE', '2021-02-08 22:00:00.189796', '2021-02-08 22:15:00.189796', 1250.0 , null , null, 1, 10, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'TAKEN', '2021-02-06 12:00:00.189796', '2021-02-06 12:15:00.189796', 1250.0 , null , 14, 2, 11, null);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('CHECKUP', 'TAKEN', '2021-02-08 23:15:00.189796', '2021-02-08 23:30:00.189796', 1250.0 , null , 14, 1, 10, null);

-- Counseling
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'FREE', '2021-02-08 21:00:00.189796', '2021-02-08 21:25:00.189796', 2500.0 , null , null, 1, null, 6);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'FREE', '2021-02-08 21:00:00.189796', '2021-02-08 21:30:00.189796', 1500.0 , null , null, 2, null, 7);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'FREE', '2021-02-08 21:10:00.189796', '2021-02-08 21:15:00.189796', 1750.0 , null , null, 3, null, 8);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'FREE', '2021-02-08 21:00:00.189796', '2021-02-08 21:15:00.189796', 1250.0 , null , null, 1, null, 6);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'TAKEN', '2021-02-06 21:00:00.189796', '2021-02-06 21:15:00.189796', 1250.0 , null , 14, 2, null, 6);
INSERT INTO appointment(dtype, appointment_status, from_date, to_date, price, report, patient_id, pharmacy_id, dermatologist_id, pharmacist_id)
VALUES ('COUNSELING', 'TAKEN', '2021-02-08 21:15:00.189796', '2021-02-08 21:30:00.189796', 1250.0 , null , 14, 1, null, 8);

--------------------
-- Patient allergies
--------------------

INSERT INTO patient_allergies(patient_id, medicine_id)
VALUES (14, 2);
INSERT INTO patient_allergies(patient_id, medicine_id)
VALUES (14, 4);


-----------------------
-- Complaints
-----------------------

--1
INSERT INTO complaint (dtype, body, complaint_status, patient_id, reply_id, employee_id, pharmacy_id)
VALUES ('EmployeeComplaint', 'Ovo je prvi complaint', 'OPENED', 17, null, 6, null);
--2
INSERT INTO reply (text, sys_admin_id) VALUES ('Ovo je odgovor na drugi complaint od strane sys admin 1', 1);
INSERT INTO complaint (dtype, body, complaint_status, patient_id, reply_id, employee_id, pharmacy_id)
VALUES ('EmployeeComplaint', 'Ovo je drugi complaint', 'CLOSED', 16, 1, 6, null);
--3
INSERT INTO complaint (dtype, body, complaint_status, patient_id, reply_id, employee_id, pharmacy_id)
VALUES ('EmployeeComplaint', 'Ovo je treci complaint', 'OPENED', 17, null, 11, null);

--4
INSERT INTO complaint (dtype, body, complaint_status, patient_id, reply_id, employee_id, pharmacy_id)
VALUES ('PharmacyComplaint', 'Ovo je prvi PHARMACY complaint, neodgovoren complaint', 'OPENED', 16, null, null, 1);

--5
INSERT INTO complaint (dtype, body, complaint_status, patient_id, reply_id, employee_id, pharmacy_id)
VALUES ('PharmacyComplaint', 'Ovo je drugi PHARMACY complaint, ODGOVOREN complaint', 'CLOSED', 16, null, null, 1);


-----------------------
-- Loyalty system
-----------------------

INSERT INTO loyalty_program_config (check_up_points, counseling_points, last_updated) VALUES (1, 2, CURRENT_TIMESTAMP);
INSERT INTO loyalty_account_membership (discount, min_points, name, config_id) VALUES (0, 0, 'BASIC', 1);
INSERT INTO loyalty_account_membership (discount, min_points, name, config_id) VALUES (12.5, 15, 'BRONZE', 1);
INSERT INTO loyalty_account_membership (discount, min_points, name, config_id) VALUES (25.0, 50, 'SILVER', 1);
INSERT INTO loyalty_account_membership (discount, min_points, name, config_id) VALUES (35.0, 150, 'GOLD', 1);

--14
INSERT INTO loyalty_account (points, membership_id, patient_id) VALUES (0, 1, 14);
--15
INSERT INTO loyalty_account (points, membership_id, patient_id) VALUES (0, 1, 15);
--16 (Lidija)
INSERT INTO loyalty_account (points, membership_id, patient_id) VALUES (30, 3, 16);
--17 (Radovan)
INSERT INTO loyalty_account (points, membership_id, patient_id) VALUES (36, 4, 17);

-----------------------
-- Orders
-----------------------

--1 Deadline : 3rd March 2020, CREATED by Administrator 2 in Pharmacy 1
INSERT INTO orders (dead_line, order_status, administrator_id, pharmacy_id) VALUES ('2021-3-03 00:00:00-07', 'CREATED', 2, 1);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (15, 1, 1);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (30, 2, 1);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (45, 3, 1);

--2 Deadline : 18th April 2020, CREATED by Administrator 2 in Pharmacy 1
INSERT INTO orders (dead_line, order_status, administrator_id, pharmacy_id) VALUES ('2021-2-18 00:00:00-07' , 'CREATED', 2, 1);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (950, 1, 2);
--3 Accepted : Administrator 2 Pharmacy 1
INSERT INTO orders (dead_line, order_status, administrator_id, pharmacy_id) VALUES ('2021-1-6 00:00:00-07' , 'ACCEPTED', 3, 2);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (55, 1, 3);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (150, 4, 3);

--4
INSERT INTO orders (dead_line, order_status, administrator_id, pharmacy_id) VALUES ('2021-12-10 00:00:00-07' , 'CREATED', 3, 2);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (55, 1, 4);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (150, 4, 4);


--5
INSERT INTO orders (dead_line, order_status, administrator_id, pharmacy_id) VALUES ('2021-12-10 00:00:00-07' , 'CREATED', 3, 2);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (5, 1, 5);
INSERT INTO order_item (quantity, medicine_id, order_id) VALUES (3, 2, 5);

--1 Offer by Sandra(18) for order 3 which is accepted
INSERT INTO offer (delivery_date, offer_status, total_price, supplier_id, order_id) VALUES ('2021-1-2 00:00:00-07', 'ACCEPTED', '666', 18, 3);
--2 Offer by Marina(19) for order 3 which is rejected
INSERT INTO offer (delivery_date, offer_status, total_price, supplier_id, order_id) VALUES ('2021-1-3 00:00:00-07', 'REJECTED', '755', 19, 3);
--3 Offer by Sandra(18) for order 1 which is created
INSERT INTO offer (delivery_date, offer_status, total_price, supplier_id, order_id) VALUES ('2021-2-18 00:00:00-07', 'CREATED', '2500', 18, 1);
--4 Offer by Marina(19) for order 1 which is CREATED
-- INSERT INTO offer (delivery_date, offer_status, total_price, supplier_id, order_id) VALUES ('2021-2-1 00:00:00-07', 'CREATED', '755', 19, 2);



-------------------
-- SHIFTS
-------------------

-- dermatologist 12, pharmacy 1
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-02 08:00', '2021-02-02 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-03 08:00', '2021-02-03 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-04 08:00', '2021-02-04 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-05 08:00', '2021-02-05 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-06 08:00', '2021-02-06 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-07 08:00', '2021-02-07 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-08 08:00', '2021-02-08 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-09 08:00', '2021-02-09 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-10 08:00', '2021-02-10 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-11 08:00', '2021-02-11 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-12 08:00', '2021-02-12 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-13 08:00', '2021-02-13 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-14 08:00', '2021-02-14 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-15 08:00', '2021-02-15 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-16 08:00', '2021-02-16 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-17 08:00', '2021-02-17 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-18 08:00', '2021-02-18 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-19 08:00', '2021-02-19 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-20 08:00', '2021-02-20 12:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 1, '2021-02-21 08:00', '2021-02-21 12:00', 'WORK');

-- dermatologist 12, pharmacy 3
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-04 12:30', '2021-02-04 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-02 12:30', '2021-02-02 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-03 12:30', '2021-02-03 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-05 12:30', '2021-02-05 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-06 12:30', '2021-02-06 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-07 12:30', '2021-02-07 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-08 12:30', '2021-02-08 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-09 12:30', '2021-02-09 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-10 12:30', '2021-02-10 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-11 12:30', '2021-02-11 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-12 12:30', '2021-02-12 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-13 12:30', '2021-02-13 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-14 12:30', '2021-02-14 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-15 12:30', '2021-02-15 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-16 12:30', '2021-02-16 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-17 12:30', '2021-02-17 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-18 12:30', '2021-02-18 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-19 12:30', '2021-02-19 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-20 12:30', '2021-02-20 14:00', 'WORK');
INSERT INTO shift (employee_id, pharmacy_id, from_date, to_date, type) VALUES (12, 3, '2021-02-21 12:30', '2021-02-21 14:00', 'WORK');



-------------------
-- E-Prescriptions
-------------------
--Veselin(id=14)
INSERT INTO eprescription (issuing_date, patient_id, prescription_status) VALUES (CURRENT_TIMESTAMP, 14, 'ACTIVE'); -- (id=1)
INSERT INTO eprescription (issuing_date, patient_id, prescription_status) VALUES (CURRENT_TIMESTAMP, 14, 'ACTIVE'); -- (id=2)

-- (eprescription 1)
INSERT INTO prescription_item (quantity, medicine_id, eprescription_id) VALUES (3, 1 ,1);
INSERT INTO prescription_item (quantity, medicine_id, eprescription_id) VALUES (1, 2, 1);

-- (eprescription 2) (only pharmacy 3 can fulfill)
INSERT INTO prescription_item (quantity, medicine_id, eprescription_id) VALUES (8,3,2);


--Radovan(id=17)
INSERT INTO eprescription (issuing_date, patient_id, prescription_status) VALUES (CURRENT_TIMESTAMP, 17, 'ACTIVE'); -- (id=3);
-- (eprescription 3)
INSERT INTO prescription_item (quantity, medicine_id, eprescription_id) VALUES (3, 1,3);
INSERT INTO prescription_item (quantity, medicine_id, eprescription_id) VALUES (2,4,3);
------------------------
-- SUBSCRIBED PATIENTS
------------------------

INSERT INTO subscription (pharmacy_id, patient_id) VALUES (3, 15);
INSERT INTO subscription (pharmacy_id, patient_id) VALUES (3, 16);
INSERT INTO subscription (pharmacy_id, patient_id) VALUES (3, 17);

INSERT INTO subscription (pharmacy_id, patient_id) VALUES (2, 14);
INSERT INTO subscription (pharmacy_id, patient_id) VALUES (2, 15);

----------------
-- PROMOTIONS
----------------

INSERT INTO promotion (from_date, to_date, description, title, administrator_id, pharmacy_id)
VALUES('2021-02-08', '2021-02-15', 'Opis prve promocije', 'Prva promocija', 3, 2);
INSERT INTO promotion (from_date, to_date, description, title, administrator_id, pharmacy_id)
VALUES('2021-02-10', '2021-02-25', 'Opis druge promocije', 'Druga promocija', 4, 2);
INSERT INTO promotion (from_date, to_date, description, title, administrator_id, pharmacy_id)
VALUES('2021-02-13', '2021-03-01', 'Opis treće promocije', 'Treća promocija', 5, 3);

----------------------
-- VACATION REQUESTS
----------------------

INSERT INTO vacation_request (employee_id, from_date, to_date, status) VALUES (12, '2021-02-13', '2021-02-17', 'CREATED');
INSERT INTO vacation_request (employee_id, from_date, to_date, status) VALUES (7, '2021-02-13', '2021-02-17', 'CREATED');
