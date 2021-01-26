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