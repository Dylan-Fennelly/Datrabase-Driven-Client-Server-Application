CREATE TABLE weapon (
    weapon_id INT PRIMARY KEY,
    weapon_name VARCHAR(255),
    weapon_type VARCHAR(255),
    ammo_capacity INT,
    damage DECIMAL(4,2),
    rate_of_fire DECIMAL(4,2),
    weight DECIMAL(4,2),
    level_requirement INT,
    country_of_origin VARCHAR(255),
    attachment_slots INT
);
CREATE TABLE attachments (
    attachment_id INT PRIMARY KEY,
    attachment_name VARCHAR(255),
    attachment_type VARCHAR(255),
    weight DECIMAL(4,2),
    accuracy_bonus DECIMAL(4,2),
    damage_bonus DECIMAL(4,2),
    rate_of_fire_bonus DECIMAL(4,2),
    country_of_origin VARCHAR(255)
);
CREATE TABLE custom_weapons (
    custom_weapon_id INT PRIMARY KEY,
    custom_weapon_name VARCHAR(255)
);
CREATE TABLE custom_weapon_attachments (
    custom_weapon_id INT,
    attachment_id INT,
    FOREIGN KEY (custom_weapon_id) REFERENCES custom_weapons(custom_weapon_id),
    FOREIGN KEY (attachment_id) REFERENCES attachments(attachment_id),
    PRIMARY KEY (custom_weapon_id, attachment_id)
);
CREATE TABLE custom_weapon_weapons (
    custom_weapon_id INT,
    weapon_id INT,
    FOREIGN KEY (custom_weapon_id) REFERENCES custom_weapons(custom_weapon_id),
    FOREIGN KEY (weapon_id) REFERENCES weapon(weapon_id),
    PRIMARY KEY (custom_weapon_id, weapon_id)
);
--CREATE
-- Create a new weapon
INSERT INTO weapons (weapon_name, weapon_type, ammo_capacity, damage, rate_of_fire, weight, level_requirement, country_of_origin, attachment_slots)
VALUES ('M4A1', 'Assault Rifle', 30, 3.5, 8.0, 3.0, 5, 'USA', 3);

-- Create a new attachment
INSERT INTO attachments (attachment_name, attachment_type, weight, accuracy_bonus, damage_bonus, rate_of_fire_bonus, country_of_origin)
VALUES ('Red Dot Sight', 'Sight', 0.5, 2.0, 0.0, 0.0, 'USA');

-- Create a new custom weapon
INSERT INTO custom_weapons (custom_weapon_name, weapon_id, attachment_id)
VALUES ('Custom M4A1', 1, 1);

--READ
-- Retrieve all weapons
SELECT * FROM weapons;

-- Retrieve all attachments
SELECT * FROM attachments;

-- Retrieve all custom weapons with their attached weapon and attachment information
SELECT custom_weapons.custom_weapon_id, custom_weapons.custom_weapon_name, weapons.weapon_name, attachments.attachment_name
FROM custom_weapons
INNER JOIN weapons ON custom_weapons.weapon_id = weapons.weapon_id
INNER JOIN attachments ON custom_weapons.attachment_id = attachments.attachment_id;

--UPDATE
-- Update the weight of the Red Dot Sight attachment to 0.75
UPDATE attachments
SET weight = 0.75
WHERE attachment_name = 'Red Dot Sight';

-- Update the custom name of the Custom M4A1 to "My Custom M4A1"
UPDATE custom_weapons
SET custom_weapon_name = 'My Custom M4A1'
WHERE custom_weapon_id = 1;

--DELETE
-- Delete the custom weapon with id 1
DELETE FROM custom_weapons WHERE custom_weapon_id = 1;

-- Delete all attachments with a weight less than 0.5
DELETE FROM attachments WHERE weight < 0.5;

