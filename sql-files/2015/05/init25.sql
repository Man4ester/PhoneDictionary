ALTER TABLE client DROP COLUMN addressId;
ALTER TABLE address ADD COLUMN addressClientId int(11) DEFAULT NULL;
ALTER TABLE phone CHANGE clientId phoneClientId int(11) DEFAULT NULL;