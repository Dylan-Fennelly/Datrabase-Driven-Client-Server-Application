CREATE TABLE weapon (
    weapon_id INT AUTO_INCREMENT PRIMARY KEY,
    weapon_name VARCHAR(255),
    weapon_type VARCHAR(255),
    ammo_capacity INT,
    damage INT,
    rate_of_fire INT,
    weight DECIMAL(4,2),
    reload_time DECIMAL(4,2),
	accuracy DECIMAL(4,2),
	range_of_effectiveness INT,
	recoil DECIMAL(4,2),
    attachment_slots INT,
	price INT
);
CREATE TABLE attachment (
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    attachment_name VARCHAR(255),
    attachment_type VARCHAR(255),
    weight DECIMAL(4,2),
    accuracy_bonus DECIMAL(4,2),
	recoilBonus DECIMAL(4,2),
    damage_bonus DECIMAL(4,2),
    rate_of_fire_bonus DECIMAL(4,2),
	reload_time_bonus DECIMAL(4,2),
	price INT
);
CREATE TABLE custom_weapon (
    custom_weapon_id INT AUTO_INCREMENT PRIMARY KEY,
    custom_weapon_name VARCHAR(255),
	weapon_id INT,
	FOREIGN KEY (weapon_id) REFERENCES weapon(weapon_id)
);
CREATE TABLE custom_weapon_attachments (
    custom_weapon_id INT,
    attachment_id INT,
    FOREIGN KEY (custom_weapon_id) REFERENCES custom_weapon(custom_weapon_id),
    FOREIGN KEY (attachment_id) REFERENCES attachment(attachment_id),
    PRIMARY KEY (custom_weapon_id, attachment_id)
);
--CREATE
--To add a new weapon:
INSERT INTO weapon (weapon_name, weapon_type, ammo_capacity, damage, rate_of_fire, weight, reload_time, accuracy, recoil, attachment_slots, price)
VALUES ('AK-47', 'Assault Rifle', 30, 45.00, 600, 3.50, 2.50, 70.00, 10.00, 4, 1500);

--To add a new attachment:
INSERT INTO attachment (attachment_name, attachment_type, weight, accuracy_bonus, recoilBonus, damage_bonus, rate_of_fire_bonus, reload_time_bonus, price)
VALUES ('Red Dot Sight', 'Sight', 0.50, 10.00, 5.00, 0.00, 0.00, 0.00, 100);

--To create a custom weapon with attachments:
--First, insert a new custom weapon into the "custom_weapon" table
INSERT INTO custom_weapon (weapon_id, custom_weapon_name)
VALUES (1, 'Custom AK-47');

--Next, insert the attachments for the custom weapon into the "custom_weapon_attachments" table
INSERT INTO custom_weapon_attachments (custom_weapon_id, attachment_id)
VALUES (1, 1); --Attach the Red Dot Sight to the custom AK-47

--****

--READ
--To retrieve all weapons:
SELECT * FROM weapon;

--To retrieve all attachments:
SELECT * FROM attachment;

--To retrieve all attachments for a specific custom weapon:
SELECT attachment.attachment_id, attachment.attachment_name, attachment.attachment_type, attachment.weight, attachment.accuracy_bonus, attachment.recoilBonus, attachment.damage_bonus, attachment.rate_of_fire_bonus, attachment.reload_time_bonus, attachment.price
FROM custom_weapon_attachments
JOIN attachment ON custom_weapon_attachments.attachment_id = attachment.attachment_id
WHERE custom_weapon_attachments.custom_weapon_id = 1;


--UPDATE
--To update the price of an attachment:
UPDATE attachment
SET price = 200
WHERE attachment_id = 1;

--To update the name of a custom weapon:
UPDATE custom_weapon
SET custom_weapon_name = 'Modified AK-47'
WHERE custom_weapon_id = 1;

--DELETE
--To delete a weapon:
DELETE FROM weapon
WHERE weapon_id = 1;

--To delete an attachment:
DELETE FROM attachment
WHERE attachment_id = 1;

--To delete all attachments for a specific custom weapon:
DELETE FROM custom_weapon_attachments
WHERE custom_weapon_id = 1;


INSERT INTO attachment (attachment_name, attachment_type, weight, accuracy_bonus, recoilBonus, damage_bonus, rate_of_fire_bonus, reload_time_bonus, price)
VALUES
('Red Dot Sight', 'Sight', 0.5, 10.0, 5.0, 0.0, 0.0, 0.0, 100),
('Holographic Sight', 'Sight', 0.6, 15.0, 6.0, 0.0, 0.0, 0.0, 150),
('4x Scope', 'Sight', 1.2, 25.0, 10.0, 0.0, 0.0, 0.0, 200),
('Suppressor', 'Muzzle', 0.8, 0.0, 15.0, 0.0, 0.0, -1.0, 250),
('Flash Hider', 'Muzzle', 0.7, 5.0, 10.0, 0.0, 0.0, -0.5, 150),
('Compensator', 'Muzzle', 0.6, 0.0, 20.0, 0.0, 5.0, 0.5, 300),
('Extended Magazine', 'Magazine', 0.5, 0.0, 0.0, 0.0, 10.0, 1.0, 200),
('Quickdraw Magazine', 'Magazine', 0.4, 0.0, 0.0, 0.0, 15.0, 0.5, 250),
('Grip', 'Underbarrel', 0.6, 10.0, 5.0, 0.0, 0.0, 0.0, 150),
('Bipod', 'Underbarrel', 1.0, 5.0, 10.0, 0.0, 0.0, 1.0, 300);

INSERT INTO weapon (weapon_name, weapon_type, ammo_capacity, damage, rate_of_fire, weight, reload_time, accuracy, range_of_effectiveness, recoil, attachment_slots, price)
VALUES
('AK-47', 'Assault Rifle', 30, 47, 600, 3.5, 2.5, 0.70, 400, 20, 3, 600),
('M4A1', 'Assault Rifle', 30, 43, 700, 3.0, 2.3, 0.80, 500, 15, 4, 800),
('Desert Eagle', 'Pistol', 7, 60, 150, 1.8, 1.5, 0.90, 100, 50, 1, 1200),
('Glock 17', 'Pistol', 17, 34, 500, 0.65, 1.8, 0.85, 200, 30, 2, 500),
('Remington 870', 'Shotgun', 8, 80, 60, 4.5, 4.0, 0.40, 50, 80, 2, 1000),
('M249', 'Machine Gun', 100, 35, 1000, 10.0, 6.0, 0.60, 800, 30, 3, 2500),
('RPG-7', 'Rocket Launcher', 1, 1000, 1, 7.0, 7.0, 0.30, 1000, 90, 0, 5000),
('M67 Grenade', 'Grenade', 0, 300, 0, 0.5, 0, 0.60, 50, 50, 0, 150),
('Claymore Mine', 'Explosive', 0, 1000, 0, 1.5, 0, 0.40, 20, 90, 0, 750),
('Bowie Knife', 'Melee', 0, 150, 0, 0.3, 0, 0.95, 0, 10, 0, 250);

