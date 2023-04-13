CREATE TABLE weapon (
    weapon_id INT PRIMARY KEY,
    weapon_name VARCHAR(255),
    weapon_type VARCHAR(255),
    ammo_capacity INT,
    damage DECIMAL(4,2),
    rate_of_fire DECIMAL(4,2),
    weight DECIMAL(4,2),
    reload_time DECIMAL(4,2),
	accuracy DECIMAL(4,2),
	recoil DECIMAL(4,2),
    attachment_slots INT,
	price INT
);
CREATE TABLE attachment (
    attachment_id INT PRIMARY KEY,
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
    custom_weapon_id INT PRIMARY KEY,
    custom_weapon_name VARCHAR(255)
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
INSERT INTO weapon (weapon_id, weapon_name, weapon_type, ammo_capacity, damage, rate_of_fire, weight, reload_time, accuracy, recoil, attachment_slots, price) 
VALUES (1, 'AK-47', 'Assault Rifle', 30, 45.00, 600, 3.50, 2.50, 70.00, 10.00, 4, 1500);

--To add a new attachment:
INSERT INTO attachment (attachment_id, attachment_name, attachment_type, weight, accuracy_bonus, recoilBonus, damage_bonus, rate_of_fire_bonus, reload_time_bonus, price) 
VALUES (1, 'Red Dot Sight', 'Sight', 0.50, 10.00, 5.00, 0.00, 0.00, 0.00, 100);

--To create a custom weapon with attachments:
--First, insert a new custom weapon into the "custom_weapon" table
INSERT INTO custom_weapon (custom_weapon_id, custom_weapon_name)
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




